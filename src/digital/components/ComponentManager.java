package digital.components;

import digital.components.devices.Device;
import digital.components.devices.GATE_NAND;
import digital.components.devices.GATE_NOT;
import digital.components.devices.Generator;
import digital.components.devices.Monitor;
import digital.components.devices.Oscillator;
import digital.components.parts.IOport;
import digital.components.parts.Input;
import digital.components.parts.Output;
import digital.components.parts.Wire;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author AMAUROTE
 */
public class ComponentManager {

    private static List<Device> devices;
    private static List<Wire> wires;

    private static int idCount = 0;

    public static void init() {
        devices = new ArrayList<>();
        wires = new ArrayList<>();
    }

    public static void update() {
        for (Device device : devices) {
            device.update();
        }
        for (Wire wire : wires) {
            wire.update();
        }

    }

    public static void render(Graphics g) {
        for (Device device : devices) {
            device.render(g);
        }
        for (Wire wire : wires) {
            wire.render(g);
        }
    }

    public static void timer_1ms() {
        for (Device device : devices) {
            device.timer_1ms();
        }
    }

    public static void addDevice(int type, int x, int y) {
        switch (type) {
            case 1:
                devices.add(new Generator(idCount++, x, y));
                break;
            case 2:
                devices.add(new GATE_NOT(idCount++, x, y));
                break;
            case 3:
                devices.add(new Monitor(idCount++, x, y));
                break;
            case 4:
                devices.add(new Oscillator(idCount++, x, y));
                break;
            case 5:
                devices.add(new GATE_NAND(idCount++, x, y));
                break;
        }
    }

    public static void addWire(IOport output, IOport input) {
        wires.add(new Wire(output, input));
    }

    public static Device getDevice(int id) {
        for (Device device : devices) {
            if (device.getID() == id) {
                return device;
            }
        }
        return null;
    }
    
    public static List<Device> getDeviceList() {
        return devices;
    }

    public static List<Wire> getWireList() {
        return wires;
    }
}
