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

/**
 *
 * @author AMAUROTE
 */
public class Handler {

    ////////////////////////////////////////////////////////////////////////////
    // VARIABLES
    // in case of selected port
    private static IOport selectedPort;
    private static Wire wire;

    // selected component type
    public static enum SELECTED {
        NOTHING, DEVICE, PORT, WIRE;
    }

    public static SELECTED selected;

    // mouse select area
    private static Point selectAreaPointA;
    private static Point selectAreaPointB;

    // mouse last position
    private static int mouseLastX;
    private static int mouseLastY;

    ////////////////////////////////////////////////////////////////////////////
    // INITIALIZATION
    public static void init() {
        selectAreaPointA = new Point(0, 0);
        selectAreaPointB = new Point(0, 0);
        mouseLastX = -1;
        mouseLastY = -1;
        deselect();

        // test
        for (Device device : ComponentManager.getDeviceList()) {
            System.out.println(device.getLastX() + " " + device.getLastY());
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    // TIMING    
    public static void update() {

    }

    public static void render(Graphics g) {
        // render select rectangle
        if (selectAreaPointA.getX() != selectAreaPointB.getX()
                && selectAreaPointA.getY() != selectAreaPointB.getY()) {

            int x, y, width, height;

            width = (int) Math.abs(selectAreaPointA.getX() - selectAreaPointB.getX());
            height = (int) Math.abs(selectAreaPointA.getY() - selectAreaPointB.getY());
            x = (int) (selectAreaPointA.getX() < selectAreaPointB.getX()
                    ? selectAreaPointA.getX() : selectAreaPointB.getX());
            y = (int) (selectAreaPointA.getY() < selectAreaPointB.getY()
                    ? selectAreaPointA.getY() : selectAreaPointB.getY());

            g.setColor(Color.blue);
            g.drawRect(x, y, width, height);
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    // SELECT
    private static void selectDevices(Point a, Point b) {
        int xLeft = (int) ((a.getX() < b.getX()) ? a.getX() : b.getX());
        int yUp = (int) ((a.getY() < b.getY()) ? a.getY() : b.getY());
        int xRight = (int) ((a.getX() > b.getX()) ? a.getX() : b.getX());
        int yDown = (int) ((a.getY() > b.getY()) ? a.getY() : b.getY());

        for (Device device : ComponentManager.getDeviceList()) {
            int x = device.getPivotX() * Config.GRID_SIZE;
            int y = device.getPivotY() * Config.GRID_SIZE;

            if ((xLeft < x && yUp < y && xRight > x && yDown > y)) {
                device.setSelect(true);
            }
        }
    }

    private static int getSelectedDevicesCount() {
        int count = 0;

        for (Device device : ComponentManager.getDeviceList()) {
            if (device.isSelected()) {
                count++;
            }
        }

        return count;
    }

    public static void deselect() {
        // deselect devices
        for (Device device : ComponentManager.getDeviceList()) {
            if (!Config.HOLD_CTRL) {
                device.setSelect(false);
            }
        }

        // in case of selected port
        selectedPort = null;
        wire = null;

        selected = SELECTED.NOTHING;
    }

    ////////////////////////////////////////////////////////////////////////////
    // FIND SOMETHING AT X Y
    private static Device findDevice(int x, int y) {
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

    private static IOport findPort(int x, int y) {
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

    ////////////////////////////////////////////////////////////////////////////
    // MOUSE EVENTS
    public static void mouseDown(int x, int y) {
        // mouse coordinates translation
        x /= Config.GRID_SIZE;
        y /= Config.GRID_SIZE;

        Device selectedDevice = findDevice(x, y);
        selectedPort = findPort(x, y);

        if (selected == SELECTED.NOTHING) {
            if (selectedDevice != null) {
                selected = SELECTED.DEVICE;
                selectedDevice.setSelect(true);
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

        //TODO click select
        deselect();

        Device device = findDevice(x, y);
        if (device != null) {
            device.setSelect(true);
        }
    }

    public static void mouseReleased(int x, int y) {
        // mouse coordinates translation
        x /= Config.GRID_SIZE;
        y /= Config.GRID_SIZE;

        // reset last mouse and device position
        updatePositions();

        // if there is only one selected device, then deselect
        if (getSelectedDevicesCount() == 1) {
            deselect();
        }

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

        if (selected != SELECTED.DEVICE) {
            deselect();
        }

        if (selected == SELECTED.NOTHING) {
            selectDevices(selectAreaPointA, selectAreaPointB);
            selectAreaPointA.setLocation(0, 0);
            selectAreaPointB.setLocation(0, 0);
        }
    }

    public static void mouseMove(int x, int y) {
        // SELECTED DEVICE/S
        if (selected == SELECTED.DEVICE) {

            // mouse coordinates translation
            x /= Config.GRID_SIZE;
            y /= Config.GRID_SIZE;

            if (mouseLastX == -1 || mouseLastY == -1) {
                mouseLastX = x;
                mouseLastY = y;
            }

            for (Device device : ComponentManager.getDeviceList()) {
                if (device.isSelected()) {
                    device.move(x - mouseLastX + device.getLastX(),
                            y - mouseLastY + device.getLastY());
                }
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
                    if (selectAreaPointA.getX() == 0 && selectAreaPointA.getY() == 0) {
                        selectAreaPointA.setLocation(x, y);
                        selectAreaPointB.setLocation(x, y);
                    } else {
                        selectAreaPointB.setLocation(x, y);
                    }
                }
            }

            // SELECTED WIRE
            if (selected == SELECTED.WIRE && wire != null) {
                wire.setRelPos(x, y);
            }
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    // MOVE AND POSITIONS
    public static void revertMove() {
        for (Device device : ComponentManager.getDeviceList()) {
            device.revertPosition();
        }

        if (wire != null) {
            wire.revert();
            wire = null;
        }
    }

    private static void updatePositions() {
        for (Device device : ComponentManager.getDeviceList()) {
            device.updateLastPosition();
        }

        mouseLastX = -1;
        mouseLastY = -1;
    }
}
