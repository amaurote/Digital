package digital.components.devices;

import digital.Config;
import digital.components.parts.IOport;
import digital.components.parts.Input;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 *
 * @author AMAUROTE
 */
public class Monitor extends Device {

    // only one input port
    private final Input input;

    ////////////////////////////////////////////////////////////////////////////
    // CONSTRUCTOR
    public Monitor(int id, int x, int y) {
        // set parameters
        this.id = id;
        this.x = x;
        this.y = y;
        
        this.name = "Monitor";
        this.width = 6;
        this.height = 6;

        // set input
        input = new Input(this, 0, x - 1, y + height / 2);
        devicePorts.add(input);
    }

    @Override
    public void update() {
        super.update();

        // reset
        input.setState(false);
    }

    @Override
    public void render(Graphics g) {
        super.render(g);

        // coordinates translation
        g.translate(x * Config.GRID_SIZE, y * Config.GRID_SIZE);

        // fill
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width * Config.GRID_SIZE, height * Config.GRID_SIZE);
        g.fillRect(-1 * Config.GRID_SIZE, 2 * Config.GRID_SIZE, 1 * Config.GRID_SIZE, 2 * Config.GRID_SIZE);

        // outlines
        g.setColor((selected) ? Color.BLUE :Color.BLACK);
        g.drawRect(0, 0, width * Config.GRID_SIZE, height * Config.GRID_SIZE);
        g.drawRect(-1 * Config.GRID_SIZE, 2 * Config.GRID_SIZE, 1 * Config.GRID_SIZE, 2 * Config.GRID_SIZE);

        // reset coordinates translation
        g.translate(-x * Config.GRID_SIZE, -y * Config.GRID_SIZE);

        // label
        g.setColor((input.getState() ? Color.red : Color.blue));
        g.setFont(new Font("Arial", 1, 28));
        g.drawString((input.getState()) ? "H" : "L", (x + 1) * Config.GRID_SIZE, (y + 5) * Config.GRID_SIZE);
    }

    @Override
    public void timer_1ms() {

    }

    @Override
    public IOport getPort(int id) {
        // there is only one port, id doesnt matter
        return input;
    }
}
