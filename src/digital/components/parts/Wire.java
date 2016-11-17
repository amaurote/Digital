package digital.components.parts;

import digital.components.ComponentManager;
import digital.components.DeviceInterface;
import java.awt.Graphics;

/**
 *
 * @author AMAUROTE
 */
public class Wire {

    // from
    private int outComponentId;
    private int outPortId;

    // to
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

        this.wrapped = false; //TODO
    }

    public void update() {
        DeviceInterface output = ComponentManager.getComponent(outComponentId);
        DeviceInterface input = ComponentManager.getComponent(inComponentId);

        if (output == null || input == null) {
            removeWire();
        } else if (output.getPort(outPortId) == null || input.getPort(inPortId) == null) {
            removeWire();
        } else {
            input.getPort(inPortId).setState(output.getPort(outPortId).getState());
        }
    }

    public void render(Graphics g) {
        //TODO
    }

    public void removeWire() {
        ComponentManager.getWireList().remove(this);
    }
}
