package digital.userinterface;

import digital.Config;
import digital.components.ComponentManager;
import digital.components.devices.Device;
import digital.components.parts.IOport;
import digital.components.parts.Input;
import digital.components.parts.Output;
import digital.components.parts.Wire;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author AMAUROTE
 */
public class Handler {

    // in case of selected device/s
    private static List<Device> selectedDevices;

    // in case of selected port
    private static IOport selectedPort;
    private static Wire wire;

    // selected component type
    public static enum SELECTED {
        NOTHING, DEVICE, PORT, WIRE;
    }

    public static SELECTED selected;

    // mouse select area
    private static Point selectAreaA;
    private static Point selectAreaB;

    // mouse offset (for Device only)
    private static int mouseOffsetX;
    private static int mouseOffsetY;

    ////////////////////////////////////////////////////////////////////////////
    // INITIALIZATION
    public static void init() {
        selectedDevices = new ArrayList<>();
        selectAreaA = new Point(0, 0);
        selectAreaB = new Point(0, 0);
        deselect();
    }

    public static void update() {
        for (Device device : selectedDevices) {
            device.setSelect(true);
        }
    }

    public static void render(Graphics g) {
        if (selectAreaA.getX() != selectAreaB.getX()
                && selectAreaA.getY() != selectAreaB.getY()) {

            int x, y, width, height;

            width = (int) Math.abs(selectAreaA.getX() - selectAreaB.getX());
            height = (int) Math.abs(selectAreaA.getY() - selectAreaB.getY());
            x = (int) (selectAreaA.getX() < selectAreaB.getX()
                    ? selectAreaA.getX() : selectAreaB.getX());
            y = (int) (selectAreaA.getY() < selectAreaB.getY()
                    ? selectAreaA.getY() : selectAreaB.getY());

            g.setColor(Color.blue);
            g.drawRect(x, y, width, height);
        }

    }

    public static void selectDevices(Point a, Point b) {
        int xLeft = (int) ((a.getX() < b.getX()) ? a.getX() : b.getX());
        int xRight = (int) ((a.getX() > b.getX()) ? a.getX() : b.getX());
        int yUp = (int) ((a.getY() < b.getY()) ? a.getY() : b.getY());
        int yDown = (int) ((a.getY() > b.getY()) ? a.getY() : b.getY());

        for (Device device : ComponentManager.getDeviceList()) {
            int x = device.getX() * Config.GRID_SIZE;
            int y = device.getY() * Config.GRID_SIZE;
            if (x > xLeft && x < xRight && y > yUp && y < yDown) {
                selectedDevices.add(device);
                System.out.println("GOTCHA!");
            }
        }

    }

    public static Device findDevice(int x, int y) {
        for (int i = ComponentManager.getDeviceList().size() - 1; i >= 0; i--) {
            Device d = ComponentManager.getDeviceList().get(i);

            if (d.getX() <= x && d.getY() <= y
                    && d.getX() + d.getWidth() > x && d.getY() + d.getHeight() > y) {
                System.out.printf("%d.%d | %s %d \n", x, y, d.getName(), d.getID());
                return d;
            }
        }

        return null;
    }

    public static IOport findPort(int x, int y) {
        for (int i = ComponentManager.getDeviceList().size() - 1; i >= 0; i--) {
            Device d = ComponentManager.getDeviceList().get(i);

            for (IOport port : d.getPortList()) {
                if (x >= port.getConX() - 1 && x <= port.getConX()
                        && y >= port.getConY() - 1 && y <= port.getConY()) {
                    System.out.println(d.getID() + " " + d.getName());
                    selectedPort = port;
                    return port;
                }
            }
        }

        return null;
    }

    public static void findSomethingToSelect(int x, int y) {
        // mouse coordinates translation
        x /= Config.GRID_SIZE;
        y /= Config.GRID_SIZE;

        Device selectedDevice = findDevice(x, y);
        selectedPort = findPort(x, y);

        if (selected == SELECTED.NOTHING) {
            if (selectedDevice != null) {
                selected = SELECTED.DEVICE;
                selectedDevices.add(selectedDevice);
                selectedDevice.updateLastPosition();

                selectedPort = null;
            } else {
                if (selectedPort != null) {
                    selected = SELECTED.PORT;
                    wire = selectedPort.getConnectedWire();
                }
            }
        }
    }

    public static void mouseClick(int x, int y) {
        // mouse coordinates translation
        x /= Config.GRID_SIZE;
        y /= Config.GRID_SIZE;

        // TODO deselect, unless CTRL is not pushed
        deselect();
    }

    public static void mouseReleased(int x, int y) {
        // mouse coordinates translation
        x /= Config.GRID_SIZE;
        y /= Config.GRID_SIZE;

        if (selected == SELECTED.WIRE && wire != null) {
            IOport port = findPort(x, y);
            if (port != null) {
                if (port instanceof Input && port.getConnectedWire() == null) {
                    wire.connect((Input) port);
                } else {
                    wire.revert();
                }
            } else {
                wire.setRelativeState(false);
            }
        }

        if (selected == SELECTED.NOTHING) {
            selectDevices(selectAreaA, selectAreaB);
            selectAreaA.setLocation(0, 0);
            selectAreaB.setLocation(0, 0);
        } else {
            deselect();
        }
    }

    public static void deselect() {
        // in case of selected device
        for (Device device : selectedDevices) {
            device.setSelect(false);
            device.updateLastPosition();
        }
        selectedDevices.clear();

        // in case of selected port
        selectedPort = null;
        wire = null;

        selected = SELECTED.NOTHING;

        // mouse offset (Device move only)
        mouseOffsetX = 0;
        mouseOffsetY = 0;
    }

    public static void move(int x, int y) {
        // SELECTED DEVICE/S
        if (selected == SELECTED.DEVICE) {

            // mouse coordinates translation
            x /= Config.GRID_SIZE;
            y /= Config.GRID_SIZE;

            for (Device device : selectedDevices) {
                if (mouseOffsetX == 0 || mouseOffsetY == 0) {
                    mouseOffsetX = x - device.getX();
                    mouseOffsetY = y - device.getY();
                }
                device.move(x - mouseOffsetX, y - mouseOffsetY);
            }

        } else {

            // SELECTED PORT
            if (selected == SELECTED.PORT && selectedPort != null) {
                // move wire if there is some
                if (selectedPort instanceof Input) {
                    if (selectedPort.getConnectedWire() != null) {
                        wire = selectedPort.getConnectedWire();

                        selectedPort.disconnect();
                        selectedPort = null;

                        selected = SELECTED.WIRE;
                    }
                } else {
                    // create new wire
                    if (selectedPort instanceof Output) {
                        wire = new Wire(selectedPort, null);
                        ComponentManager.addWire(wire);
                        wire.setRelPos(x, y);
                        selectedPort = null;
                        selected = SELECTED.WIRE;
                    }
                }
            } else {

                // SELECTED NOTHING
                if (selected == SELECTED.NOTHING) {
                    deselect();
                    if (selectAreaA.getX() == 0 && selectAreaA.getY() == 0) {
                        selectAreaA.setLocation(x, y);
                    } else {
                        selectAreaB.setLocation(x, y);
                    }
                }
            }

            // SELECTED WIRE
            if (selected == SELECTED.WIRE && wire != null) {
                wire.setRelPos(x, y);
            }

        }
    }

    public static void revertMove() {
        for (Device device : selectedDevices) {
            device.revertPosition();
        }

        if (wire != null) {
            wire.revert();
            wire = null;
        }
    }
}
