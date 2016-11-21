package digital.components.parts;

import digital.Config;
import java.awt.Color;
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

    // visibility
    private boolean visible;
    
    // occupied
    private boolean occupied;

    public IOport(int id, int conX, int conY, boolean portType) {
        this.id = id;

        this.conX = conX;
        this.conY = conY;

        this.selectPivotX = this.conX;
        this.selectPivotY = this.conY;

        this.portType = portType;

        this.portState = false;

        this.visible = false;
        
        this.occupied = false;
    }
    
    public void update() {
        // reset
        occupied = false;
    }
    
    public void render(Graphics g) {
        int gs = Config.GRID_SIZE;

        if (visible) {
            g.setColor((portType) ? Color.BLUE : Color.RED);
            g.drawRect((selectPivotX - 1) * gs, (selectPivotY - 1) * gs,
                    2 * gs, 2 * gs);
        }
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
    
    public void switchState() {
        portState = !portState;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void setOccupied() {
        occupied = true;
    }
    
    public boolean getState() {
        return portState;
    }
    
    public int getConX() {
        return conX;
    }
    
    public int getConY() {
        return conY;
    }
}
