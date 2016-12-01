package digital;

import digital.components.ComponentManager;
import digital.userinterface.Handler;
import java.awt.Canvas;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 *
 * @author AMAUROTE
 */
public class MainFrame extends javax.swing.JFrame {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        //</editor-fold>
        Core core = new Core();
        core.start();
    }

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();
        mainCanvas.setSize(Config.CANVAS_HORIZONTAL, Config.CANVAS_VERTICAL);
        //this.setSize(Config.FRAME_HORIZONTAL, Config.FRAME_VERTICAL);
    }

    public void update() {
        this.requestFocus();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainCanvas = new java.awt.Canvas();
        jButtonShowIO = new javax.swing.JButton();
        jToggleWrapWires = new javax.swing.JToggleButton();
        jScrollPaneDevices = new javax.swing.JScrollPane();
        jListDevices = new javax.swing.JList<>();
        jButtonAdd = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(0, 0));
        setName("MainFrame"); // NOI18N
        setResizable(false);
        setSize(new java.awt.Dimension(764, 459));
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                formKeyReleased(evt);
            }
        });

        mainCanvas.setName("mainCanvas"); // NOI18N
        mainCanvas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mainCanvasMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                mainCanvasMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                mainCanvasMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                mainCanvasMouseReleased(evt);
            }
        });
        mainCanvas.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                mainCanvasMouseDragged(evt);
            }
        });

        jButtonShowIO.setText("IO");
        jButtonShowIO.setFocusable(false);
        jButtonShowIO.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButtonShowIOMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jButtonShowIOMouseReleased(evt);
            }
        });

        jToggleWrapWires.setText("Wrap Wires");
        jToggleWrapWires.setFocusable(false);
        jToggleWrapWires.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jToggleWrapWiresStateChanged(evt);
            }
        });

        jScrollPaneDevices.setFocusable(false);

        jListDevices.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Generator", "Oscilator", "Monitor", " ", "Gate NOT", "Gate NAND" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jListDevices.setSelectedIndex(0);
        jScrollPaneDevices.setViewportView(jListDevices);

        jButtonAdd.setText("Add");
        jButtonAdd.setFocusable(false);
        jButtonAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonAddMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(mainCanvas, javax.swing.GroupLayout.PREFERRED_SIZE, 601, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPaneDevices, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButtonAdd)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonShowIO)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jToggleWrapWires, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonShowIO)
                    .addComponent(jToggleWrapWires))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPaneDevices, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonAdd))
                    .addComponent(mainCanvas, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mainCanvasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mainCanvasMouseClicked
        Handler.mouseClick(evt.getX(), evt.getY());
    }//GEN-LAST:event_mainCanvasMouseClicked

    private void mainCanvasMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mainCanvasMouseDragged
        Handler.mouseMove(evt.getX(), evt.getY());
    }//GEN-LAST:event_mainCanvasMouseDragged

    private void mainCanvasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mainCanvasMouseExited
        // revert and deselect
        /*
        if (Handler.selected != Handler.SELECTED.NOTHING) {
            Handler.revertMove();
            Handler.deselect();
        }
         */
    }//GEN-LAST:event_mainCanvasMouseExited

    private void mainCanvasMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mainCanvasMouseReleased
        // right mouse button
        if (evt.getButton() == MouseEvent.BUTTON3) {
            // revert and deselect
            Handler.revertMove();
            Handler.deselect();
        } else {
            // just let it know
            Handler.mouseReleased(evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_mainCanvasMouseReleased

    private void mainCanvasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mainCanvasMousePressed
        if (evt.getButton() == MouseEvent.BUTTON1) {
            Handler.mouseDown(evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_mainCanvasMousePressed

    private void jButtonShowIOMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonShowIOMousePressed
        Config.SHOW_ALL_PORTS = true;
    }//GEN-LAST:event_jButtonShowIOMousePressed

    private void jButtonShowIOMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonShowIOMouseReleased
        Config.SHOW_ALL_PORTS = false;
    }//GEN-LAST:event_jButtonShowIOMouseReleased

    private void jToggleWrapWiresStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jToggleWrapWiresStateChanged
        Config.WIRE_APPERANCE_WRAPPED = jToggleWrapWires.isSelected();
    }//GEN-LAST:event_jToggleWrapWiresStateChanged

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_CONTROL) {
            Config.HOLD_CTRL = true;
        }
    }//GEN-LAST:event_formKeyPressed

    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
        Config.HOLD_CTRL = false;
    }//GEN-LAST:event_formKeyReleased

    private void jButtonAddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonAddMouseClicked

        int x = Config.CANVAS_HORIZONTAL / 2 / Config.GRID_SIZE;
        int y = Config.CANVAS_VERTICAL / 2 / Config.GRID_SIZE;
        switch (jListDevices.getSelectedIndex()) {

            case 0:
                ComponentManager.addDevice(1, x, y);
                break;
            case 1:
                ComponentManager.addDevice(2, x, y);
                break;
            case 2:
                ComponentManager.addDevice(3, x, y);
                break;
            case 4:
                ComponentManager.addDevice(4, x, y);
                break;
            case 5:
                ComponentManager.addDevice(5, x, y);
                break;
        }
    }//GEN-LAST:event_jButtonAddMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonShowIO;
    private javax.swing.JList<String> jListDevices;
    private javax.swing.JScrollPane jScrollPaneDevices;
    private javax.swing.JToggleButton jToggleWrapWires;
    private java.awt.Canvas mainCanvas;
    // End of variables declaration//GEN-END:variables

    public Canvas getCanvas() {
        return mainCanvas;
    }
}
