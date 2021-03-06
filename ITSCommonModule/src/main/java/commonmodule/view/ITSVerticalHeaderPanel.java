/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * TDSVerticalHeaderPanel.java
 *
 * Created on 2011.04.06., 16:24:46
 */
package commonmodule.view;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.Icon;
import javax.swing.JButton;

/**
 *
 * @author vincze.attila
 */
public class ITSVerticalHeaderPanel extends javax.swing.JPanel {

    private Icon upIcon;
    private Icon downIcon;
    private boolean up;

    /** Creates new form TDSVerticalHeaderPanel */
    public ITSVerticalHeaderPanel() {
        initComponents();
        upIcon = new javax.swing.ImageIcon(getClass().getResource("/commonmodule/image/arrow_left.png"));
        downIcon = new javax.swing.ImageIcon(getClass().getResource("/commonmodule/image/arrow_right.png"));
        up = true;
    }

    public JButton getBtnHide() {
        return btnHide;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
        btnHide.setIcon(up ? upIcon : downIcon);
    }

//    @Override
//    protected void paintComponent(Graphics g) {
//        if (!isOpaque()) {
//            super.paintComponent(g);
//            return;
//        }
//
//        Graphics2D g2d = (Graphics2D) g;
//        int w = getWidth();
//        int h = getHeight();
//
//        Color color1 = new Color(199,223,249);
//        Color color2 = new Color(227,239,251);// color1.brighter();
//        GradientPaint gp = new GradientPaint(
//                w, 0, color2,
//                0, 0, color1);
//
//        g2d.setPaint(gp);
//        g2d.fillRect(0, 0, w, h);
//
//        setOpaque(false);
//        super.paintComponent(g);
//        setOpaque(true);
//
//    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        btnHide = new javax.swing.JButton();

        setBackground(new java.awt.Color(245, 249, 254));
        setLayout(new java.awt.BorderLayout());

        jToolBar1.setFloatable(false);
        jToolBar1.setOpaque(false);
        jToolBar1.setRollover(true);

        btnHide.setIcon(new javax.swing.ImageIcon(getClass().getResource("/commonmodule/image/arrow_left.png"))); // NOI18N
        btnHide.setFocusable(false);
        btnHide.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnHide.setOpaque(false);
        btnHide.setPreferredSize(new java.awt.Dimension(20, 20));
        btnHide.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnHide.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHideActionPerformed(evt);
            }
        });
        jToolBar1.add(btnHide);

        add(jToolBar1, java.awt.BorderLayout.PAGE_START);
    }// </editor-fold>//GEN-END:initComponents

    private void btnHideActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHideActionPerformed
        // TODO add your handling code here:
        btnHide.setIcon(btnHide.getIcon().equals(downIcon) ? upIcon : downIcon);
        setUp(!up);
}//GEN-LAST:event_btnHideActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHide;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables
}
