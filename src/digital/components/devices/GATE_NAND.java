package digital.components.devices;

import digital.Config;
import digital.components.parts.IOport;
import digital.components.parts.Input;
import digital.components.parts.Output;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 *
 * @author AMAUROTE
 */
public class GATE_NAND extends Device {

    // ports  
    private final Input inputA;
    private final Input inputB;
    private final Output output;

    ////////////////////////////////////////////////////////////////////////////
    // CONSTRUCTOR
    public GATE_NAND(int id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
        
        this.name = "Gate NAND";
        this.width = 6;
        this.height = 6;

        // ports
        output = new Output(this, 0, x + width + 2, y + 3);
        inputA = new Input(this, 1, x - 1, y + 1);
        inputB = new Input(this, 1, x - 1, y + 5);
        devicePorts.add(output);
        devicePorts.add(inputA);
        devicePorts.add(inputB);     
    }

    @Override
    public void update() {
        super.update();
        
        // output state update
        output.setState((!inputA.getState() || !inputB.getState()));
    }

    @Override
    public void render(Graphics g) {
        super.render(g);

        int gs = Config.GRID_SIZE;

        // coordinates translation
        g.translate(x * gs, y * gs);

        // fill
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width * gs, height * gs);
        g.fillRect(-1 * gs, 0 * gs, 1 * gs, 2 * gs);
        g.fillRect(-1 * gs, 4 * gs, 1 * gs, 2 * gs);
        g.fillOval(width * gs, 2 * gs + 1, 2 * gs - 2, 2 * gs - 2);

        // outlines
        g.setColor((selected) ? Color.BLUE :Color.BLACK);
        g.drawRect(0, 0, width * gs, height * gs);
        g.drawRect(-1 * gs, 0 * gs, 1 * gs, 2 * gs);
        g.drawRect(-1 * gs, 4 * gs, 1 * gs, 2 * gs);
        g.drawOval(width * gs, 2 * gs + 1, 2 * gs - 2, 2 * gs - 2);

        // reset coordinates translation
        g.translate(-x * gs, -y * gs);

        // label
        g.setFont(new Font("Arial", 0, 24));
        g.drawString("&", (x + 1) * gs, (y + 5) * gs);
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
        switch (id) {
            case 0:
                return output;
            case 1:
                return inputA;
            case 2:
                return inputB;
            default:
                return null;
        }
    }
}
