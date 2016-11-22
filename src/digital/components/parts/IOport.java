package digital.components.parts;

import digital.Config;
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
    protected int selectPivotX, selectPivotY;

    // port state (true = H / false = L)
    protected boolean portState;

    // visibility
    protected boolean visible;

    public IOport(int id, int conX, int conY) {
        this.id = id;

        this.conX = conX;
        this.conY = conY;

        this.selectPivotX = this.conX;
        this.selectPivotY = this.conY;

        this.portState = false;

        this.visible = false;
    }

    public void update() {

    }

    public void render(Graphics g) {
        int gs = Config.GRID_SIZE;

        if (visible || Config.SHOW_ALL_PORTS) {
            g.drawRect((selectPivotX - 1) * gs, (selectPivotY - 1) * gs, 2 * gs, 2 * gs);
        }
    }
    
    public void move(int x, int y) {
        conX = x;
        conY = y;
        selectPivotX = x;
        selectPivotY = y;
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

    public void setOccupied() {
        // used only by input
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
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
