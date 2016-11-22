package digital.components.devices;

import digital.Config;
import digital.components.parts.IOport;
import digital.components.parts.Input;
import digital.components.parts.Output;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author AMAUROTE
 */
public class GATE_NOT extends Device {

    // ports  
    private final Input input;
    private final Output output;

    ////////////////////////////////////////////////////////////////////////////
    // CONSTRUCTOR
    public GATE_NOT(int id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
        
        this.name = "Gate NOT";
        this.width = 6;
        this.height = 6;

        // ports
        input = new Input(1, x - 1, y + 3);
        output = new Output(0, x + width + 2, y + 3);
    }

    @Override
    public void update() {
        input.update();
        output.update();

        output.setState(!input.getState());
    }

    @Override
    public void render(Graphics g) {
        input.render(g);
        output.render(g);

        int gs = Config.GRID_SIZE;
        int[] xpoints = {0, width * gs, 0};
        int[] ypoints = {0, 3 * gs, height * gs};

        // coordinates translation
        g.translate(x * gs, y * gs);

        // fill
        g.setColor(Color.WHITE);
        g.fillPolygon(xpoints, ypoints, 3);
        g.fillRect(-1 * gs, 2 * gs, 1 * gs, 2 * gs);
        g.fillOval(width * gs, 2 * gs + 1, 2 * gs - 2, 2 * gs - 2);

        // outlines
        g.setColor(Color.BLACK);
        g.drawPolygon(xpoints, ypoints, 3);
        g.drawRect(-1 * gs, 2 * gs, 1 * gs, 2 * gs);
        g.drawOval(width * gs, 2 * gs + 1, 2 * gs - 2, 2 * gs - 2);

        // reset coordinates translation
        g.translate(-x * gs, -y * gs);
    }

    @Override
    public void timer_1ms() {

    }

    @Override
    public void move(int x, int y) {
        input.move(x - 1, y + 3);
        output.move(x + width + 2, y + 3);
        this.x = x;
        this.y = y;
    }

    @Override
    public void displayPorts(boolean allPortsVisible) {
        input.setVisible(allPortsVisible);
        output.setVisible(allPortsVisible);
    }

    @Override
    public void displayPorts(boolean type, boolean visible) {
        if (type) {
            input.setVisible(visible);
        } else {
            output.setVisible(visible);
        }
    }

    @Override
    public IOport getPort(int id) {
        // there are only two ports, id doesnt matter
        return (id == 1) ? input : output;
    }
}
