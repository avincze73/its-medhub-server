/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * TDSListPanel.java
 *
 * Created on 2011.02.10., 17:29:50
 */

package commonmodule.view;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author vincze.attila
 */
public class ITSListPanel extends javax.swing.JPanel {

    private Action newAction;

    /** Creates new form TDSListPanel */
    public ITSListPanel() {
        initComponents();
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (!isOpaque()) {
            super.paintComponent(g);
            return;
        }

        Graphics2D g2d = (Graphics2D) g;
        int w = getWidth();
        int h = getHeight();

        //Color color1 = getBackground();
        //Color color2 = color1.darker();
        Color color1 = new Color(199,223,249);
        Color color2 = new Color(227,239,251);// color1.brighter();
        GradientPaint gp = new GradientPaint(
                w, 0, color2,
                0, 0, color1);

        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);

        setOpaque(false);
        super.paintComponent(g);
        setOpaque(true);

    }

    public JLabel getLblInfo() {
        return lblInfo;
    }

    public void setLblInfo(JLabel lblInfo) {
        this.lblInfo = lblInfo;
    }

    public Action getNewAction() {
        return newAction;
    }

    public void setNewAction(Action newAction) {
        this.newAction = newAction;
    }

    public JButton getBtnNew() {
        return btnNew;
    }

    

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        lblInfo = new javax.swing.JLabel();
        jToolBar1 = new javax.swing.JToolBar();
        btnNew = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(479, 25));
        setLayout(new java.awt.BorderLayout());

        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(34, 22));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        lblInfo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblInfo.setForeground(new java.awt.Color(0, 0, 128));
        lblInfo.setText("Utolsó keresés");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanel1.add(lblInfo, gridBagConstraints);

        add(jPanel1, java.awt.BorderLayout.CENTER);

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);
        jToolBar1.setOpaque(false);

        btnNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/commonmodule/image/new.png"))); // NOI18N
        btnNew.setBorder(null);
        btnNew.setBorderPainted(false);
        btnNew.setFocusable(false);
        btnNew.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnNew.setMaximumSize(new java.awt.Dimension(23, 23));
        btnNew.setMinimumSize(new java.awt.Dimension(23, 23));
        btnNew.setOpaque(false);
        btnNew.setPreferredSize(new java.awt.Dimension(20, 20));
        btnNew.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });
        jToolBar1.add(btnNew);

        add(jToolBar1, java.awt.BorderLayout.LINE_END);
    }// </editor-fold>//GEN-END:initComponents

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        // TODO add your handling code here:
        newAction.actionPerformed(evt);
    }//GEN-LAST:event_btnNewActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNew;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lblInfo;
    // End of variables declaration//GEN-END:variables

}
