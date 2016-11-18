package digital.components.devices;

import digital.Config;
import digital.components.ComponentSpecialParameter;
import digital.components.DeviceInterface;
import digital.components.parts.IOport;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.List;

/**
 *
 * @author AMAUROTE
 */
public class Monitor implements DeviceInterface {

    //id, position, size, name
    private final int id;
    private int x, y;
    private final int width, height;
    private final String name = "Monitor";

    // only one input port
    private final IOport input;

    public Monitor(int id, int x, int y) {
        // set parameters
        this.id = id;
        this.x = x;
        this.y = y;
        this.width = 6;
        this.height = 6;

        // set input
        input = new IOport(0, x - 1, y + height / 2, true);
    }

    @Override
    public void update() {
        // reset
        input.setState(false);
    }

    @Override
    public void render(Graphics g) {
        // coordinates translation
        g.translate(x * Config.GRID_SIZE, y * Config.GRID_SIZE);

        // fill
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width * Config.GRID_SIZE, height * Config.GRID_SIZE);
        g.fillRect(-1 * Config.GRID_SIZE, 2 * Config.GRID_SIZE, 1 * Config.GRID_SIZE, 2 * Config.GRID_SIZE);

        // outlines
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, width * Config.GRID_SIZE, height * Config.GRID_SIZE);
        g.drawRect(-1 * Config.GRID_SIZE, 2 * Config.GRID_SIZE, 1 * Config.GRID_SIZE, 2 * Config.GRID_SIZE);

        // reset coordinates translation
        g.translate(-x * Config.GRID_SIZE, -y * Config.GRID_SIZE);

        // label
        g.setColor(Color.red);
        g.setFont(new Font("Arial", 1, 28));
        g.drawString((input.getState()) ? "H" : "L", (x + 1) * Config.GRID_SIZE, (y + 5) * Config.GRID_SIZE);

        // port
        input.render(g);
    }

    @Override
    public void timer_1ms() {

    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public IOport getPort(int id) {
        // there is only one port, id doesnt matter
        return input;
    }

    @Override
    public List<ComponentSpecialParameter> getSpecParametersList() {
        return null;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void displayPorts(boolean allPortsVisible) {
        input.setVisible(allPortsVisible);
    }

    @Override
    public void displayPorts(boolean type, boolean visible) {
        if (type) {
            input.setVisible(visible);
        }
    }

}