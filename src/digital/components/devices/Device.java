package digital.components.devices;

import digital.components.ComponentSpecialParameter;
import digital.components.parts.IOport;
import digital.components.parts.Input;
import digital.components.parts.Output;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author AMAUROTE
 */
public abstract class Device {

    //id, position, size, name
    protected int id;
    protected int x, y;
    protected int width, height; // width and height are useful to determine selectable area
    protected String name;

    // Special Parameters List
    protected final List<ComponentSpecialParameter> specParameterList = new ArrayList<>();

    // List of IOports
    protected final List<IOport> devicePorts = new ArrayList<>();

    public void update() {
        for (IOport devicePort : devicePorts) {
            devicePort.update();
        }
    }

    public void render(Graphics g) {
        for (IOport devicePort : devicePorts) {
            devicePort.render(g);
        }
    }

    public void timer_1ms() {
    }

    public void move(int x, int y) {
        for (IOport devicePort : devicePorts) {
            devicePort.move(devicePort.getConX() - (this.x - x),
                    devicePort.getConY() - (this.y - y));
        }
        this.x = x;
        this.y = y;
    }

    public void displayPorts(boolean allPortsVisible) {
        for (IOport devicePort : devicePorts) {
            devicePort.setVisible(allPortsVisible);
        }
    }

    public void displayPorts(boolean type, boolean visible) {
        for (IOport devicePort : devicePorts) {
            if (type) {
                if (devicePort instanceof Input) {
                    devicePort.setVisible(visible);
                }
            } else if (devicePort instanceof Output) {
                devicePort.setVisible(visible);
            }
        }
    }

    public int getID() {
        return id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public IOport getPort(int id) {
        return null;
    }
    
    public List<IOport> getPortList() {
        return devicePorts;
    }

    public List<ComponentSpecialParameter> getSpecParametersList() {
        return specParameterList;
    }

    public String getName() {
        return name;
    }

}
