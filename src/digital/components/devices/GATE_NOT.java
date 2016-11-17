package digital.components.devices;

import digital.Config;
import digital.components.ComponentSpecialParameter;
import digital.components.DeviceInterface;
import digital.components.parts.IOport;
import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

/**
 *
 * @author AMAUROTE
 */
public class GATE_NOT implements DeviceInterface {

    //id, position, size, name
    private final int id;
    private int x, y;
    private final int width, height;
    private final String name = "Generator";

    // ports  
    private final IOport input;
    private final IOport output;

    public GATE_NOT(int id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.width = 6;
        this.height = 6;

        // ports
        input = new IOport(1, x - 1, y + 3, true);
        output = new IOport(0, x + width + 2, y + 3, false);
    }

    @Override
    public void update() {
        output.setState(!input.getState());
    }

    @Override
    public void render(Graphics g) {
        // coordinates translation
        g.translate(x * Config.GRID_SIZE, y * Config.GRID_SIZE);

        int[] xpoints = {0, 6 * Config.GRID_SIZE, 0};
        int[] ypoints = {0, 3 * Config.GRID_SIZE, 6 * Config.GRID_SIZE};
        int gs = Config.GRID_SIZE;
        g.setColor(Color.WHITE);
        g.fillPolygon(xpoints, ypoints, 3);
        g.fillRect(-1 * gs, 2 * gs, 1 * gs, 2 * gs);
        g.fillOval(6 * gs, 2 * gs + 1, 2 * gs - 2, 2 * gs - 2);
        g.setColor(Color.BLACK);
        g.drawPolygon(xpoints, ypoints, 3);
        g.drawRect(-1 * gs, 2 * gs, 1 * gs, 2 * gs);
        g.drawOval(6 * gs, 2 * gs + 1, 2 * gs - 2, 2 * gs - 2);

        // reset coordinates translation
        g.translate(-x * Config.GRID_SIZE, -y * Config.GRID_SIZE);
        
        // ports
        input.render(g);
        output.render(g);       
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
        // there are only two ports, id doesnt matter
        return (id == 1) ? input : output;
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
        output.setVisible(allPortsVisible);
    }

    @Override
    public void displayPorts(boolean type, boolean visible) {
        if(type) {
            input.setVisible(visible);
        } else {
            output.setVisible(visible);
        }
    }
}
