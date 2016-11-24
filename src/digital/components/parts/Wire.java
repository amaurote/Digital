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
    private Output output;
    // to (right)
    private Input input;

    // last input connected
    private Input lastInput = null;

    // while moving
    private boolean relativeState;
    private int relPosX;
    private int relPosY;

    public Wire(IOport output, IOport input) {
        this.output = (Output) output;

        if (input != null) {
            this.input = (Input) input;
            relativeState = false;
        } else {
            relativeState = true;
        }
    }

    public void update() {
        if (!relativeState) {
            if (output.getParent() == null || input.getParent() == null
                    || output == null || input == null) {
                removeWire();
            } else {
                input.setState(output.getState());
                input.connect(this);
            }

            lastInput = (Input) input;
        }
    }

    public void render(Graphics g) {
        // define variables
        int gs = Config.GRID_SIZE;
        int lx, ly, rx, ry;

        lx = output.getConX() * gs;
        ly = output.getConY() * gs;

        if (relativeState) {
            rx = relPosX;
            ry = relPosY;
        } else {
            rx = input.getConX() * gs;
            ry = input.getConY() * gs;
            
        }

        // choose color
        g.setColor((output.getState()) ? Color.red : Color.blue);

        // draw
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

    public void setRelPos(int relPosX, int relPosY) {
        relativeState = true;

        this.relPosX = relPosX;
        this.relPosY = relPosY;

        if (input != null) {
            input.disconnect();
        }

        input = null;
    }

    public void setRelativeState(boolean state) {
        relativeState = state;
        
        if(!relativeState) {
            if(input == null) {
                removeWire();
            }
        }
    }

    public void connect(Input input) {
        this.input = input;
        relativeState = false;
    }

    public void revert() {
        if (lastInput != null) {
            connect(lastInput);
        } else {
            removeWire();
        }
    }
}
