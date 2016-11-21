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
        // reset
        occupied = false;
    }

    @Override
    public void render(Graphics g) {
        int gs = Config.GRID_SIZE;

        if (visible) {
            g.setColor(Color.green);
            g.drawRect((selectPivotX - 1) * gs, (selectPivotY - 1) * gs,
                    2 * gs, 2 * gs);
        }
    }

    public void setOccupied() {
        occupied = true;
    }

}
