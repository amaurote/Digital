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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainCanvas, javax.swing.GroupLayout.PREFERRED_SIZE, 601, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(153, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainCanvas, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Canvas mainCanvas;
    // End of variables declaration//GEN-END:variables

    public Canvas getCanvas() {
        return mainCanvas;
    }
}
