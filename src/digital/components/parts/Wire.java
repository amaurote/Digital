package digital.components.parts;

import digital.Config;
import digital.components.ComponentManager;
import digital.components.DeviceInterface;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author AMAUROTE
 */
public class Wire {

    // from (left)
    private int outComponentId;
    private int outPortId;

    // to (right)
    private int inComponentId;
    private int inPortId;

    // apperance
    private boolean wrapped;

    // TODO xy position while moving... and which end
    public Wire(int outComponentId, int outPortId, int inComponentId, int inPortId) {
        this.outComponentId = outComponentId;
        this.outPortId = outPortId;
        this.inComponentId = inComponentId;
        this.inPortId = inPortId;
    }

    public void update() {
        DeviceInterface leftEnd = ComponentManager.getComponent(outComponentId);
        DeviceInterface rightEnd = ComponentManager.getComponent(inComponentId);

        if (leftEnd == null || rightEnd == null) {
            removeWire();
        } else if (leftEnd.getPort(outPortId) == null || rightEnd.getPort(inPortId) == null) {
            removeWire();
        } else {
            rightEnd.getPort(inPortId).setState(leftEnd.getPort(outPortId).getState());
        }
    }

    public void render(Graphics g) {
        DeviceInterface leftEnd = ComponentManager.getComponent(outComponentId);
        DeviceInterface rightEnd = ComponentManager.getComponent(inComponentId);
        int lX = leftEnd.getPort(outPortId).getConX();
        int lY = leftEnd.getPort(outPortId).getConY();
        int rX = rightEnd.getPort(inPortId).getConX();
        int rY = rightEnd.getPort(inPortId).getConY();
        int gs = Config.GRID_SIZE;

        if (Config.WIRE_APPERANCE_WRAPPED) {
            //TODO
        } else {
            g.setColor(Color.red);
            g.drawLine(lX * gs, lY * gs, rX * gs, rY * gs);
        }
    }

    public void removeWire() {
        ComponentManager.getWireList().remove(this);
    }
}
