package digital.components.devices;

import digital.Config;
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

    ////////////////////////////////////////////////////////////////////////////
    // VARIABLES
    // id, position, size, name
    protected int id;
    protected int x, y;
    private int lastX;
    private int lastY;
    protected int width, height;  
    protected String name;

    // selected
    protected boolean selected = false;

    // Special Parameters List
    protected final List<ComponentSpecialParameter> specParameterList = new ArrayList<>();

    // List of IOports
    protected final List<IOport> devicePorts = new ArrayList<>();

    ////////////////////////////////////////////////////////////////////////////
    // TIMING
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

    ////////////////////////////////////////////////////////////////////////////
    // MOVEMENT METHODS
    public void move(int x, int y) {
        for (IOport devicePort : devicePorts) {
            devicePort.move(devicePort.getConX() - (this.x - x),
                    devicePort.getConY() - (this.y - y));
        }

        this.x = x;
        this.y = y;

        if (x < 0) {
            move(0, y);
        }
        if (y < 0) {
            move(x, 0);
        }
        if (x + width > Config.CANVAS_HORIZONTAL / Config.GRID_SIZE) {
            move(Config.CANVAS_HORIZONTAL / Config.GRID_SIZE - width, y);
        }
        if (y + height > Config.CANVAS_VERTICAL / Config.GRID_SIZE) {
            move(x, Config.CANVAS_VERTICAL / Config.GRID_SIZE - height);
        }
    }

    public void updateLastPosition() {
        lastX = x;
        lastY = y;
    }

    public void revertPosition() {
        move(lastX, lastY);
    }

    ////////////////////////////////////////////////////////////////////////////
    // DISPLAY PORTS
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
            } else {
                if (devicePort instanceof Output) {
                    devicePort.setVisible(visible);
                }
            }
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    // GETTERS / SETTERS
    public int getID() {
        return id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getLastX() {
        return lastX;
    }

    public int getLastY() {
        return lastY;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getPivotX() {
        return x + width / 2;
    }

    public int getPivotY() {
        return y + height / 2;
    }

    public String getName() {
        return name;
    }

    public boolean isSelected() {
        return selected;
    }

    public List<ComponentSpecialParameter> getSpecParametersList() {
        return specParameterList;
    }

    public List<IOport> getPortList() {
        return devicePorts;
    }

    public IOport getPort(int id) {
        return null;
    }

    public void setSelect(boolean selected) {
        this.selected = selected;
    }
}
