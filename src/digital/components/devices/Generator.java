package digital.components.devices;

import digital.Config;
import digital.components.ComponentSpecialParameter;
import digital.components.DeviceInterface;
import digital.components.parts.IOport;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author AMAUROTE
 */
public class Generator implements DeviceInterface {

    //id, position, size, name
    private final int id;
    private int x, y;
    private final int width, height;
    private final String name = "Generator";

    // generator type (L / H)
    private boolean generatorType;

    // only one output port
    private final IOport output;

    // Special Parameters List
    private final List<ComponentSpecialParameter> specParametersList;

    ////////////////////////////////////////////////////////////////////////////
    // CONSTRUCTOR
    public Generator(int id, int x, int y) {
        // set parameters
        this.id = id;
        this.x = x;
        this.y = y;
        this.width = 6;
        this.height = 6;

        // set output
        output = new IOport(0, x + width + 1, y + height / 2, false);

        // set specParametersList and add some
        specParametersList = new ArrayList<>();
        specParametersList.add(new ComponentSpecialParameter("Type L/H", 0, 0));
    }

    @Override
    public void update() {
        output.update();
        generatorType = (specParametersList.get(0).getValue() == 1);
        output.setState(generatorType);     
    }

    @Override
    public void render(Graphics g) {
        // coordinates translation
        g.translate(x * Config.GRID_SIZE, y * Config.GRID_SIZE);

        // fill
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width * Config.GRID_SIZE, height * Config.GRID_SIZE);
        g.fillRect(6 * Config.GRID_SIZE, 2 * Config.GRID_SIZE, 1 * Config.GRID_SIZE, 2 * Config.GRID_SIZE);

        // outlines
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, width * Config.GRID_SIZE, height * Config.GRID_SIZE);
        g.drawRect(6 * Config.GRID_SIZE, 2 * Config.GRID_SIZE, 1 * Config.GRID_SIZE, 2 * Config.GRID_SIZE);

        // reset coordinates translation
        g.translate(-x * Config.GRID_SIZE, -y * Config.GRID_SIZE);

        // label
        g.setFont(new Font("Arial", 1, 28));
        g.drawString((generatorType) ? "H" : "L", (x + 1) * Config.GRID_SIZE, (y + 5) * Config.GRID_SIZE);

        // port
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
        // there is only one port, id doesnt matter
        return output;
    }

    @Override
    public List<ComponentSpecialParameter> getSpecParametersList() {
        return specParametersList;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void displayPorts(boolean allPortsVisible) {
        output.setVisible(allPortsVisible);
    }

    @Override
    public void displayPorts(boolean type, boolean visible) {
        if (!type) {
            output.setVisible(visible);
        }
    }
}
