package digital.components;

import digital.components.parts.IOport;
import java.awt.Graphics;
import java.util.List;

/**
 *
 * @author AMAUROTE
 */
public interface DeviceInterface {

    public void update();

    public void render(Graphics g);

    public void timer_1ms();

    public void move(int x, int y);

    public void displayPorts(boolean allPortsVisible);

    public void displayPorts(boolean type, boolean visible);
    
    public int getID();

    public int getX();

    public int getY();

    public int getWidth();

    public int getHeight(); 

    public IOport getPort(int id);

    public List<ComponentSpecialParameter> getSpecParametersList();

    public String getName();   
}
