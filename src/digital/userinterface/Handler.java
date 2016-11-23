package digital.userinterface;

import digital.Config;
import digital.components.ComponentManager;
import digital.components.devices.Device;
import digital.components.parts.IOport;

/**
 *
 * @author AMAUROTE
 */
public class Handler {

    private static Device selectedDevice;
    private static IOport selectedPort;

    //docasne asi neviem
    private static int selectedDeviceId;
    private static int selectedPortId;

    private static int lastX;
    private static int lastY;
    private static int mouseOffsetX;
    private static int mouseOffsetY;

    public static void init() {
        selectedDevice = null;
        selectedPort = null;

        //docasne
        selectedDeviceId = -1;
        selectedPortId = -1;

        lastX = -1;
        lastY = -1;
    }

    public static void findSomethingToSelect(int x, int y) {
        x /= Config.GRID_SIZE;
        y /= Config.GRID_SIZE;

        for (int i = ComponentManager.getDeviceList().size() - 1; i >= 0; i--) {
            Device d = ComponentManager.getDeviceList().get(i);

            // first check, if click is above port
            for (IOport port : d.getPortList()) {
                if (x >= port.getConX() - 1 && x <= port.getConX()
                        && y >= port.getConY() - 1 && y <= port.getConY()) {
                    System.out.println(d.getID() + " " + d.getName() + " " + port.getId());
                    selectedDeviceId = d.getID();
                    selectedPortId = port.getId();
                    return;
                }
            }

            // second check, if click is above component
            if (d.getX() <= x && d.getY() <= y
                    && d.getX() + d.getWidth() > x && d.getY() + d.getHeight() > y) {
                System.out.printf("%d.%d | %s %d \n", x, y, d.getName(), d.getID());
                selectedDevice = d;
                selectedDeviceId = d.getID();
                selectedPortId = -1;
                return;
            }
        }
    }

    public static void deselect() {
        selectedDevice = null;
        selectedDeviceId = -1;
        selectedPortId = -1;

        lastX = -1;
        lastY = -1;

        mouseOffsetX = 0;
        mouseOffsetY = 0;
    }

    public static void move(int x, int y) {
        if (selectedDevice != null) {
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
        }
    }

    public static void revertMove() {
        if (selectedDevice != null) {
            selectedDevice.move(lastX, lastY);
        }
    }
}
