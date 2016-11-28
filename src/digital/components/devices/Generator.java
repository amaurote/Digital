package digital.components.devices;

import digital.Config;
import digital.components.ComponentSpecialParameter;
import digital.components.parts.IOport;
import digital.components.parts.Output;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 *
 * @author AMAUROTE
 */
public class Generator extends Device {

    // generator type (L / H)
    private boolean generatorType;

    // only one output port
    private final Output output;

    ////////////////////////////////////////////////////////////////////////////
    // CONSTRUCTOR
    public Generator(int id, int x, int y) {
        // set parameters
        this.id = id;
        this.x = x;
        this.y = y;
        
        this.name = "Generator";
        this.width = 6;
        this.height = 6;

        // set output
        output = new Output(this, 0, x + width + 1, y + height / 2);
        devicePorts.add(output);
        
        // set specParameterList and add some
        specParameterList.add(new ComponentSpecialParameter("Type L/H", 0, 0));
        
    }

    @Override
    public void update() {
        super.update();
        
        // output state update
        generatorType = (specParameterList.get(0).getValue() == 1);
        output.setState(generatorType);
    }

    @Override
    public void render(Graphics g) {
        super.render(g);

        // coordinates translation
        g.translate(x * Config.GRID_SIZE, y * Config.GRID_SIZE);

        // fill
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width * Config.GRID_SIZE, height * Config.GRID_SIZE);
        g.fillRect(6 * Config.GRID_SIZE, 2 * Config.GRID_SIZE, 1 * Config.GRID_SIZE, 2 * Config.GRID_SIZE);

        // outlines
        g.setColor((selected) ? Color.BLUE :Color.BLACK);
        g.drawRect(0, 0, width * Config.GRID_SIZE, height * Config.GRID_SIZE);
        g.drawRect(6 * Config.GRID_SIZE, 2 * Config.GRID_SIZE, 1 * Config.GRID_SIZE, 2 * Config.GRID_SIZE);

        // reset coordinates translation
        g.translate(-x * Config.GRID_SIZE, -y * Config.GRID_SIZE);

        // label
        g.setFont(new Font("Arial", 1, 28));
        g.drawString((generatorType) ? "H" : "L", (x + 1) * Config.GRID_SIZE, (y + 5) * Config.GRID_SIZE);
    }

    @Override
    public void timer_1ms() {

    }

    @Override
    public IOport getPort(int id) {
        // there is only one port, id doesnt matter
        return output;
    }
}
