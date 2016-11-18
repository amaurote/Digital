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
    
    DeviceInterface leftEnd;

    // to (right)
    private int inComponentId;
    private int inPortId;
    
    DeviceInterface rightEnd;

    // apperance
    private boolean wrapped;

    // TODO xy position while moving... and which end
    public Wire(int outComponentId, int outPortId, int inComponentId, int inPortId) {
        this.outComponentId = outComponentId;
        this.outPortId = outPortId;
        this.inComponentId = inComponentId;
        this.inPortId = inPortId;
        
        leftEnd = ComponentManager.getComponent(outComponentId);
        rightEnd = ComponentManager.getComponent(inComponentId);
    }

    public void update() {
    if (leftEnd == null || rightEnd == null) {
            removeWire();
        } else if (leftEnd.getPort(outPortId) == null || rightEnd.getPort(inPortId) == null) {
            removeWire();
        } else {
            rightEnd.getPort(inPortId).setState(leftEnd.getPort(outPortId).getState());
            rightEnd.getPort(inPortId).setOccupied();
        }
    }

    public void render(Graphics g) {
        int lX = leftEnd.getPort(outPortId).getConX();
        int lY = leftEnd.getPort(outPortId).getConY();
        int rX = rightEnd.getPort(inPortId).getConX();
        int rY = rightEnd.getPort(inPortId).getConY();
        int gs = Config.GRID_SIZE;

        if (Config.WIRE_APPERANCE_WRAPPED) {
            //TODO
        } else {
            g.setColor((leftEnd.getPort(outPortId).getState()) ? Color.red : Color.blue);
            g.drawLine(lX * gs, lY * gs, rX * gs, rY * gs);
        }
    }

    public void removeWire() {
        ComponentManager.getWireList().remove(this);
    }
}
