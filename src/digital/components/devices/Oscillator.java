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
public class Oscillator extends Device {

    // oscilator frequency
    private int freq;
    private int counter = 0;

    // only one output port
    private final Output output;

    ////////////////////////////////////////////////////////////////////////////
    // CONSTRUCTOR
    public Oscillator(int id, int x, int y) {
        // set parameters    
        this.id = id;
        this.x = x;
        this.y = y;

        this.name = "Oscilator";
        this.width = 6;
        this.height = 6;

        this.freq = 1;

        // set output
        output = new Output(this, 0, x + width + 1, y + height / 2);
        devicePorts.add(output);

        // add some specParameter
        specParameterList.add(new ComponentSpecialParameter("Frequency", 1, freq));
    }

    @Override
    public void update() {
        super.update();

        // frequency update
        freq = specParameterList.get(0).getValue();
    }

    @Override
    public void render(Graphics g) {
        super.render(g);

        // coordinates translation
        g.translate(x * Config.GRID_SIZE, y * Config.GRID_SIZE);

        // fill
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 6 * Config.GRID_SIZE, 6 * Config.GRID_SIZE);
        g.fillRect(6 * Config.GRID_SIZE, 2 * Config.GRID_SIZE, 1 * Config.GRID_SIZE, 2 * Config.GRID_SIZE);

        // outlines
        g.setColor((selected) ? Color.BLUE :Color.BLACK);
        g.drawRect(0, 0, 6 * Config.GRID_SIZE, 6 * Config.GRID_SIZE);
        g.drawRect(6 * Config.GRID_SIZE, 2 * Config.GRID_SIZE, 1 * Config.GRID_SIZE, 2 * Config.GRID_SIZE);

        // reset coordinates translation
        g.translate(-x * Config.GRID_SIZE, -y * Config.GRID_SIZE);

        // label
        g.setFont(new Font("Arial", 0, 10));
        g.drawString("OSC", (x + 1) * Config.GRID_SIZE, (y + 3) * Config.GRID_SIZE);
        g.drawString(freq + "Hz", (x + 1) * Config.GRID_SIZE, (y + 5) * Config.GRID_SIZE);
    }

    @Override
    public void timer_1ms() {
        // output switch state
        if (++counter >= 1000 / (freq * 2)) {
            output.switchState();
            counter = 0;
        }
    }

    @Override
    public IOport getPort(int id) {
        // there is only one port, id doesnt matter
        return output;
    }
}
