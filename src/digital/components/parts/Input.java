package digital.components.parts;

import digital.Config;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author AMAUROTE
 */
public class Input extends IOport {

    private boolean occupied;

    public Input(int id, int conX, int conY) {
        super(id, conX, conY);

        occupied = false;
    }

    @Override
    public void update() {
        if (!occupied) {
            portState = Config.HI_IMPEDANCE_INTERPRETATION;
        }

        // reset
        occupied = false;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.green);
        super.render(g);
    }

    @Override
    public void setOccupied() {
        occupied = true;
    }

}
