package digital.components.parts;

import java.awt.Graphics;

/**
 *
 * @author AMAUROTE
 */
public class IOport {

    // port id
    private final int id;

    // connection point
    private int conX, conY;

    // selection pivot
    private int selectPivotX, selectPivotY;

    // port type (true = I / false = O)
    private final boolean portType;

    // port state (true = H / false = L)
    private boolean portState;

    public IOport(int id, int conX, int conY, boolean portType) {
        this.id = id;
        this.conX = conX;
        this.conY = conY;
        this.selectPivotX = this.conX;
        this.selectPivotY = this.conY;
        this.portType = portType;
        this.portState = false;
    }

    public void render(Graphics g) {           
        //TODO render if selected... maybe...
    }

    public void setConPosition(int x, int y) {
        conX = x;
        conY = y;
        setSelectPivot(x, y);
    }

    private void setSelectPivot(int x, int y) {
        selectPivotX = x;
        selectPivotY = y;
    }

    public void setState(boolean state) {
        portState = state;
    }
}
