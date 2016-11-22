package digital.userinterface;

import digital.Config;
import digital.components.ComponentManager;
import digital.components.DeviceInterface;

/**
 *
 * @author AMAUROTE
 */
public class Handler {

    private static DeviceInterface selected;
    private static int lastX;
    private static int lastY;

    public static void init() {
        selected = null;
        lastX = -1;
        lastY = -1;
    }

    public static void selectComponent(int x, int y) {
        int index = 0;
        x = x / Config.GRID_SIZE;
        y = y / Config.GRID_SIZE;

        do {
            DeviceInterface d = ComponentManager.getComponentByIndex(index);

            if (d.getX() <= x && d.getY() <= y
                    && d.getX() + d.getWidth() > x && d.getY() + d.getHeight() > y) {

                System.out.printf("%d.%d | %s %d \n", x, y, d.getName(), d.getID());
                selected = d;
                return;
            }

            index++;
        } while (ComponentManager.getComponentByIndex(index) != null);
    }

    public static void deselect() {
        selected = null;
        lastX = -1;
        lastY = -1;
    }

    public static void move(int x, int y) {
        if (selected != null) {
            if (lastX == -1 || lastY == -1) {
                lastX = selected.getX();
                lastY = selected.getY();
            }

            selected.move(x / Config.GRID_SIZE, y / Config.GRID_SIZE);
        }
    }

    public static void revertMove() {
        if (selected != null) {
            selected.move(lastX, lastY);
        }
    }
}
