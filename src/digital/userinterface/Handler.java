package digital.userinterface;

import digital.Config;
import digital.components.ComponentManager;
import digital.components.DeviceInterface;

/**
 *
 * @author AMAUROTE
 */
public class Handler {

    public static void init() {

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
                return;  
            }

            index++;
        } while (ComponentManager.getComponentByIndex(index) != null);
    }
}
