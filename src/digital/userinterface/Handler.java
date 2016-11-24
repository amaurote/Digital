package digital.userinterface;

import digital.Config;
import digital.components.ComponentManager;
import digital.components.devices.Device;
import digital.components.parts.IOport;
import digital.components.parts.Input;
import digital.components.parts.Output;
import digital.components.parts.Wire;

/**
 *
 * @author AMAUROTE
 */
public class Handler {

    // in case of selected device
    private static Device selectedDevice;

    // in case of selected port
    private static IOport selectedPort;
    private static Wire wire = null;

    // last position of moved device
    private static int lastX;
    private static int lastY;

    // mouse offset (Device move only)
    private static int mouseOffsetX;
    private static int mouseOffsetY;

    public static void init() {
        deselect();
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
                    System.out.println(d.getID() + " " + d.getName() + " " + port.getId());
                    selectedPort = port;
                    selectedDevice = null;
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

        selectedDevice = findDevice(x, y);
        selectedPort = findPort(x, y);

        if (selectedDevice != null) {
            selectedPort = null;
        } else {
            if (selectedPort != null) {
                selectedDevice = null;
            }
        }
    }

    public static void mouseReleased(int x, int y) {
        // mouse coordinates translation
        x /= Config.GRID_SIZE;
        y /= Config.GRID_SIZE;

        if (wire != null) {
            IOport port = findPort(x, y);
            if (port != null) {
                if (port instanceof Input) {
                    if (port.getConnectedWire() == null && wire != null) {
                        wire.connect((Input) port);
                    } else {
                        wire.revert();
                    }
                } else {
                    wire.revert();
                }
            } else {
                wire.setRelativeState(false);
            }
        }

        deselect();
    }

    public static void deselect() {
        // in case of selected device
        selectedDevice = null;

        // in case of selected port
        selectedPort = null;
        wire = null;

        // last position of moved device
        lastX = -1;
        lastY = -1;

        // mouse offset (Device move only)
        mouseOffsetX = 0;
        mouseOffsetY = 0;
    }

    public static void move(int x, int y) {
        if (selectedDevice != null) {

            // device is selected
            if (lastX == -1 || lastY == -1) {
                lastX = selectedDevice.getX();
                lastY = selectedDevice.getY();
            }

            x /= Config.GRID_SIZE;
            y /= Config.GRID_SIZE;

            if (mouseOffsetX == 0 || mouseOffsetY == 0) {
                mouseOffsetX = x - selectedDevice.getX();
                mouseOffsetY = y - selectedDevice.getY();
            }

            selectedDevice.move(x - mouseOffsetX, y - mouseOffsetY);

        } else {

            // port is selected
            if (selectedPort != null) {
                if (selectedPort instanceof Input) {
                    if (selectedPort.getConnectedWire() != null) {
                        wire = selectedPort.getConnectedWire();
                        selectedPort.disconnect();
                    } else {
                        if (wire != null) {
                            wire.setRelPos(x, y);
                        }
                    }
                } else {
                    if (selectedPort instanceof Output) {
                        wire = new Wire(selectedPort, null);
                        ComponentManager.addWire(wire);
                        wire.setRelPos(x, y);
                        selectedPort = null;
                    }
                }
            } else {
                if (wire != null) {
                    wire.setRelPos(x, y);
                }
            }
        }
    }

    public static void revertMove() {
        if (selectedDevice != null) {
            selectedDevice.move(lastX, lastY);
            selectedDevice = null;
        }
        
        if (wire != null) {
            wire.revert();
            wire = null;
        }
    }
}
