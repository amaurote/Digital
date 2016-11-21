package digital.components.devices;

import digital.components.ComponentSpecialParameter;
import digital.components.DeviceInterface;
import digital.components.parts.IOport;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author AMAUROTE
 */
public class Oscilator implements DeviceInterface {

    //id, position, size, name
    private final int id;
    private int x, y;
    private final int width, height;
    private final String name = "Oscilator";

    // oscilator frequency
    private int freq;
    private int counter = 0;

    // only one output port
    private final IOport output;

    // Special Parameters List
    private final List<ComponentSpecialParameter> specParameterList;

    ////////////////////////////////////////////////////////////////////////////
    // CONSTRUCTOR
    public Oscilator(int id, int x, int y) {
        // set parameters
        this.id = id;
        this.x = x;
        this.y = y;
        this.width = 6;
        this.height = 6;

        // set output
        output = new IOport(0, x + width + 1, y + height / 2, false);

        // set specParameterList and add some
        specParameterList = new ArrayList<>();
        specParameterList.add(new ComponentSpecialParameter("Frequency", 1, 2));
    }

    @Override
    public void update() {
        output.update();
        freq = specParameterList.get(0).getValue();
    }

    @Override
    public void render(Graphics g) {
        
    }

    @Override
    public void timer_1ms() {
        // output switch state
        if(++counter >= 1000 / freq) {            
            output.switchState();
            counter = 0;
        }
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public IOport getPort(int id) {
        // there is only one port, id doesnt matter
        return output;
    }

    @Override
    public List<ComponentSpecialParameter> getSpecParametersList() {
        return specParameterList;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void displayPorts(boolean allPortsVisible) {
        output.setVisible(allPortsVisible);
    }

    @Override
    public void displayPorts(boolean type, boolean visible) {
        if (!type) {
            output.setVisible(visible);
        }
    }

}
