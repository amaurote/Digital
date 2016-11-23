package digital.components.parts;

import digital.components.devices.Device;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author AMAUROTE
 */
public class Output extends IOport {

    public Output(Device parent, int id, int conX, int conY) {
        super(parent, id, conX, conY);
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
