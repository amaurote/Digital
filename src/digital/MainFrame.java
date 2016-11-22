package digital;

import digital.userinterface.Handler;
import java.awt.Canvas;
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
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainCanvas = new java.awt.Canvas();
        jButtonShowIO = new javax.swing.JButton();
        jToggleWrapWires = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(0, 0));
        setName("MainFrame"); // NOI18N
        setResizable(false);

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
        jButtonShowIO.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButtonShowIOMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jButtonShowIOMouseReleased(evt);
            }
        });

        jToggleWrapWires.setText("Wrap Wires");
        jToggleWrapWires.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jToggleWrapWiresMouseClicked(evt);
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
                        .addComponent(jButtonShowIO)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jToggleWrapWires, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(mainCanvas, javax.swing.GroupLayout.PREFERRED_SIZE, 601, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(153, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonShowIO)
                    .addComponent(jToggleWrapWires))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainCanvas, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mainCanvasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mainCanvasMouseClicked
        
    }//GEN-LAST:event_mainCanvasMouseClicked

    private void mainCanvasMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mainCanvasMouseDragged
        Handler.move(evt.getX(), evt.getY());
    }//GEN-LAST:event_mainCanvasMouseDragged

    private void mainCanvasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mainCanvasMouseExited
        Handler.revertMove();
        Handler.deselect();
    }//GEN-LAST:event_mainCanvasMouseExited

    private void mainCanvasMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mainCanvasMouseReleased
        Handler.deselect();
    }//GEN-LAST:event_mainCanvasMouseReleased

    private void mainCanvasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mainCanvasMousePressed
        if (evt.getButton() == MouseEvent.BUTTON1) {
            Handler.selectComponent(evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_mainCanvasMousePressed

    private void jToggleWrapWiresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jToggleWrapWiresMouseClicked
        Config.WIRE_APPERANCE_WRAPPED = jToggleWrapWires.isSelected();
    }//GEN-LAST:event_jToggleWrapWiresMouseClicked

    private void jButtonShowIOMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonShowIOMousePressed
        Config.SHOW_ALL_PORTS = true;
    }//GEN-LAST:event_jButtonShowIOMousePressed

    private void jButtonShowIOMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonShowIOMouseReleased
        Config.SHOW_ALL_PORTS = false;
    }//GEN-LAST:event_jButtonShowIOMouseReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonShowIO;
    private javax.swing.JToggleButton jToggleWrapWires;
    private java.awt.Canvas mainCanvas;
    // End of variables declaration//GEN-END:variables

    public Canvas getCanvas() {
        return mainCanvas;
    }
}
