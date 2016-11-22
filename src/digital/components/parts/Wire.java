package digital.components.parts;

import digital.Config;
import digital.components.ComponentManager;
import digital.components.devices.Device;
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

    Device leftEnd;

    // to (right)
    private int inComponentId;
    private int inPortId;

    Device rightEnd;

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
        int gs = Config.GRID_SIZE;
        int lx = leftEnd.getPort(outPortId).getConX() * gs;
        int ly = leftEnd.getPort(outPortId).getConY() * gs;
        int rx = rightEnd.getPort(inPortId).getConX() * gs;
        int ry = rightEnd.getPort(inPortId).getConY() * gs;

        g.setColor((leftEnd.getPort(outPortId).getState()) ? Color.red : Color.blue);

        if (Config.WIRE_APPERANCE_WRAPPED) {
            int distance = rx - lx;
            g.drawLine(lx, ly, lx + distance / 2, ly);
            g.drawLine(lx + distance / 2, ly, lx + distance / 2, ry);
            g.drawLine(lx + distance / 2, ry, rx, ry);
        } else {
            g.drawLine(lx, ly, rx, ry);
        }
    }

    public void removeWire() {
        ComponentManager.getWireList().remove(this);
    }
}
