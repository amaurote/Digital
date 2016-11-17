package digital.components;

import digital.components.devices.GATE_NOT;
import digital.components.devices.Generator;
import digital.components.parts.Wire;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author AMAUROTE
 */
public class ComponentManager {

    private static List<DeviceInterface> devices;
    private static List<Wire> wires;

    private static int idCount = 0;

    public static void init() {
        devices = new ArrayList<>();
        wires = new ArrayList<>();
    }

    public static void update() {
        for (DeviceInterface device : devices) {
            device.update();
        }
        for (Wire wire : wires) {
            wire.update();
        }
        
    }

    public static void render(Graphics g) {
        for (DeviceInterface device : devices) {
            device.render(g);
        }
        for (Wire wire : wires) {
            wire.render(g);
        }
    }

    public static void timer_1ms() {
        for (DeviceInterface device : devices) {
            device.timer_1ms();
        }
    }

    public static void addComponent(int type, int x, int y) {
        switch (type) {
            case 1:
                devices.add(new Generator(idCount++, x, y));
                break;
            case 2:
                devices.add(new GATE_NOT(idCount++, x, y));
                break;
        }
    }

    public static DeviceInterface getComponent(int id) {
        for (DeviceInterface device : devices) {
            if (device.getID() == id) {
                return device;
            }
        }
        return null;
    }
    
    public static List<Wire> getWireList() {
        return wires;
    }
}
