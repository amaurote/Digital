package digital;

import digital.components.ComponentManager;
import digital.userinterface.Handler;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

/**
 *
 * @author AMAUROTE
 */
public class Core implements Runnable {

    private static BufferStrategy bs;
    private static Graphics g;

    private static Thread thread;
    private static boolean running;

    private static MainFrame mainFrame;

    public Core() {
        // Create and set MainFrame and Canvas
        mainFrame = new MainFrame();

        mainFrame.setVisible(true);
        mainFrame.setTitle("Digital");
        mainFrame.setLocationRelativeTo(null);

        // Other
        running = false;

        init();
    }

    private void init() {
        ComponentManager.init();
        Handler.init();

        //test
        ComponentManager.addDevice(1, 3, 3);
        ComponentManager.addDevice(2, 30, 9);
        ComponentManager.addDevice(3, 100, 20);
        ComponentManager.addDevice(4, 3, 40);
        ComponentManager.addDevice(5, 65, 27);
        ComponentManager.addDevice(5, 27, 65);
        
        ComponentManager.addWire(ComponentManager.getDevice(0).getPort(0), ComponentManager.getDevice(1).getPort(1));
        ComponentManager.addWire(ComponentManager.getDevice(1).getPort(0), ComponentManager.getDevice(4).getPort(1));
        ComponentManager.addWire(ComponentManager.getDevice(3).getPort(0), ComponentManager.getDevice(4).getPort(2));
        ComponentManager.addWire(ComponentManager.getDevice(4).getPort(0), ComponentManager.getDevice(2).getPort(1));
    }

    public synchronized void start() {
        if (!running) {
            thread = new Thread(this);
            thread.start();
            running = true;
        }
    }

    public synchronized void stop() {
        if (running) {
            try {
                thread.join();
                running = false;
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        long now;
        long timer = System.currentTimeMillis();
        final double ns = 1000000000.0 / Config.FPS;
        double delta = 0;
        int frames = 0;
        int updates = 0;
        int time1msBuffer = 0;

        while (running) {
            now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1.0) {
                update();
                updates++;
                delta--;
            }
            render();
            frames++;

            // timer 1ms
            if (System.currentTimeMillis() - timer >= 1) {
                timer = System.currentTimeMillis();

                // 1 millisecond
                timer_1ms();

                // 1 second
                if (++time1msBuffer == 1000) {
                    mainFrame.setTitle(Config.FRAME_TITLE + "  |  " + updates + " ups, " + frames + " fps");
                    updates = 0;
                    frames = 0;
                    time1msBuffer = 0;
                }
            }

            // sleep
            try {
                Thread.sleep(0, 999);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        stop();
    }

    private void update() {
        ComponentManager.update();
        Handler.update();
        mainFrame.update();
    }

    private void render() {
        bs = mainFrame.getCanvas().getBufferStrategy();
        if (bs == null) {
            mainFrame.getCanvas().createBufferStrategy(3);
            return;
        }

        g = bs.getDrawGraphics();

        // draw here
        g.clearRect(0, 0, mainFrame.getCanvas().getWidth(), mainFrame.getCanvas().getHeight());
        drawGrid(g);
        ComponentManager.render(g);
        Handler.render(g);

        // end drawing
        bs.show();
        g.dispose();
    }

    private void drawGrid(Graphics g) {
        g.setColor(Color.LIGHT_GRAY);
        for (int i = 0; i < mainFrame.getCanvas().getWidth(); i = i + Config.GRID_SIZE) {
            g.drawLine(i, 0, i, mainFrame.getCanvas().getHeight());
        }
        for (int i = 0; i < mainFrame.getCanvas().getHeight(); i = i + Config.GRID_SIZE) {
            g.drawLine(0, i, mainFrame.getCanvas().getWidth(), i);
        }
    }

    private void timer_1ms() {
        ComponentManager.timer_1ms();
    }

}
