package digital.components.parts;

import digital.Config;
import digital.components.devices.Device;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author AMAUROTE
 */
public class Input extends IOport {

    private Wire connectedWire;

    public Input(Device parent, int id, int conX, int conY) {
        super(parent, id, conX, conY);

        connectedWire = null;
    }

    @Override
    public void update() {
        if (connectedWire == null) {
            portState = Config.HI_IMPEDANCE_INTERPRETATION;
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.green);
        super.render(g);
    }

    @Override
    public void connect(Wire wire) {
        connectedWire = wire;
    }
    
    public void disconnect() {
        connectedWire = null;
    }
    
    public Wire getConnectedWire() {
        return connectedWire;
    }

}
