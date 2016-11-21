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
public class GATE_NAND implements DeviceInterface {

//id, position, size, name
    private final int id;
    private int x, y;
    private final int width, height;
    private final String name = "Gate NOT";

    // ports  
    private final IOport inputA;
    private final IOport inputB;
    private final IOport output;

    public GATE_NAND(int id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.width = 5;
        this.height = 6;

        // ports
        inputA = new IOport(1, x - 1, y + 1, true);
        inputB = new IOport(1, x - 1, y + 5, true);
        output = new IOport(0, x + width + 2, y + 3, false);
    }

    @Override
    public void update() {
        inputA.update();
        inputB.update();
        output.update();

        output.setState((inputA.getState() != true || inputB.getState() != true));
    }

    @Override
    public void render(Graphics g) {
        inputA.render(g);
        inputB.render(g);
        output.render(g);

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
        g.setColor(Color.BLACK);
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
        inputA.setVisible(allPortsVisible);
        inputB.setVisible(allPortsVisible);
        output.setVisible(allPortsVisible);
    }

    @Override
    public void displayPorts(boolean type, boolean visible) {
        if (type) {
            inputA.setVisible(visible);
            inputB.setVisible(visible);
        } else {
            output.setVisible(visible);
        }
    }

}