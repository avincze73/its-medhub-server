/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * TDSGlassPane.java
 *
 * Created on 2011.03.15., 14:47:01
 */
package commonmodule.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author vincze.attila
 */
public class ITSGlassPane extends javax.swing.JPanel implements MouseListener {

    /** Creates new form TDSGlassPane */
    public ITSGlassPane() {
        initComponents();
        setCursor(new Cursor(Cursor.WAIT_CURSOR));
        addMouseListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //g.setColor(new Color(32/255,74/255,135/255, 0.7f));
        g.setColor(new Color(32, 74, 135, 100));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
    }

    @Override
    public void setVisible(boolean aFlag) {
        super.setVisible(aFlag);
        lblBusy.setBusy(aFlag);
    }


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblBusy = new org.jdesktop.swingx.JXBusyLabel();

        setFocusable(false);
        setOpaque(false);
        setRequestFocusEnabled(false);
        setLayout(new java.awt.GridBagLayout());

        lblBusy.setText("Please wait...");
        add(lblBusy, new java.awt.GridBagConstraints());
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.jdesktop.swingx.JXBusyLabel lblBusy;
    // End of variables declaration//GEN-END:variables

    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        Toolkit.getDefaultToolkit().beep();
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }
}
