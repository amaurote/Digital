package digital.components.devices;

import digital.components.ComponentSpecialParameter;
import digital.components.parts.IOport;
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
    protected final List<IOport> devicePorts = new ArrayList<>();
    
    public void update() {
    }

    public void render(Graphics g) {
    }

    public void timer_1ms() {
    }

    public void move(int x, int y) {
    }

    public void displayPorts(boolean allPortsVisible) {
    }

    public void displayPorts(boolean type, boolean visible) {
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

    public List<ComponentSpecialParameter> getSpecParametersList() {
        return specParameterList;
    }

    public String getName() {
        return name;
    }

}
