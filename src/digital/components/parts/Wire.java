package digital.components.parts;

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
        
    }
    
    public void render(Graphics g) {
        
    }
}
