package digital.components.parts.ports;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author AMAUROTE
 */
public class Output extends IOport {

    public Output(int id, int conX, int conY) {
        super(id, conX, conY);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.yellow);
        super.render(g);
    }
    
    public void switchState() {
        portState = !portState;
    }
}
