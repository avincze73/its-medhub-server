/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * TDSTitlePanel.java
 *
 * Created on 2011.02.16., 16:19:13
 */
package commonmodule.view;

import event.CaseStatusModifiedEvent;
import event.NavigateBackEvent;
import event.RefreshFrameContentEvent;
import event.ITSEventManager;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 *
 * @author vincze.attila
 */
public class ITSTitlePanel extends javax.swing.JPanel {

    private Font mainTitleFont;
    private Font detailTitleFont;
    private String mainTitle;
    private String detailTitle;

    /** Creates new form TDSTitlePanel */
    public ITSTitlePanel() {
        initComponents();
        mainTitleFont = lblMainTitle.getFont();
        detailTitleFont = lblDetailTitle.getFont();
        setPreferredSize(new Dimension(600, 35));
    }

    public boolean isBackVisible() {
        return btnBack.isVisible();
    }

    public void setBackVisible(boolean backVisible) {
        btnBack.setVisible(backVisible);
    }

    public Font getDetailTitleFont() {
        return detailTitleFont;
    }

    public void setDetailTitleFont(Font detailTitleFont) {
        this.detailTitleFont = detailTitleFont;
        lblDetailTitle.setFont(detailTitleFont);
    }

    public Font getMainTitleFont() {
        return mainTitleFont;
    }

    public void setMainTitleFont(Font mainTitleFont) {
        this.mainTitleFont = mainTitleFont;
        lblMainTitle.setFont(mainTitleFont);
    }

    public boolean isRefreshVisible() {
        return btnRefresh.isVisible();
    }

    public void setRefreshVisible(boolean refreshVisible) {
        btnRefresh.setVisible(refreshVisible);
    }

    public String getDetailTitle() {
        return lblDetailTitle.getText();
    }

    public void setDetailTitle(String detailTitle) {
        lblDetailTitle.setText(detailTitle);
    }

    public String getMainTitle() {
        return lblMainTitle.getText();
    }

    public void setMainTitle(String mainTitle) {
        lblMainTitle.setText(mainTitle);
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
        //Color color1 = new Color(170, 202, 237);
        Color color1 = ITSColor.color4;
        Color color2 = ITSColor.color1;// color1.brighter();
        GradientPaint gp = new GradientPaint(
                500, 0, color2,
                0, 0, color1);

        GradientPaint gp2 = new GradientPaint(
                w, 0, color2,
                0, 0, color2);

        g2d.setPaint(gp);
        g2d.fillRect(0, 0, 500, h);
        g2d.setPaint(gp2);
        g2d.fillRect(500, 0, w, h);

        setOpaque(false);
        super.paintComponent(g);
        setOpaque(true);

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

        jToolBar1 = new javax.swing.JToolBar();
        btnBack = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        lblMainTitle = new javax.swing.JLabel();
        lblDetailTitle = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(206, 35));
        setLayout(new java.awt.BorderLayout());

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);
        jToolBar1.setBorderPainted(false);
        jToolBar1.setOpaque(false);
        jToolBar1.setPreferredSize(new java.awt.Dimension(80, 35));

        btnBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/commonmodule/image/BalraKicsi.png"))); // NOI18N
        btnBack.setAlignmentX(0.5F);
        btnBack.setBorderPainted(false);
        btnBack.setFocusable(false);
        btnBack.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBack.setMaximumSize(new java.awt.Dimension(29, 29));
        btnBack.setMinimumSize(new java.awt.Dimension(29, 29));
        btnBack.setPreferredSize(new java.awt.Dimension(29, 29));
        btnBack.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/commonmodule/image/BenyomvaKicsi.png"))); // NOI18N
        btnBack.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btnBack.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
        jToolBar1.add(btnBack);

        btnRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/commonmodule/image/JobbraKicsi.png"))); // NOI18N
        btnRefresh.setBorderPainted(false);
        btnRefresh.setFocusable(false);
        btnRefresh.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnRefresh.setMaximumSize(new java.awt.Dimension(29, 29));
        btnRefresh.setMinimumSize(new java.awt.Dimension(29, 29));
        btnRefresh.setPreferredSize(new java.awt.Dimension(29, 29));
        btnRefresh.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/commonmodule/image/BenyomvaKicsi.png"))); // NOI18N
        btnRefresh.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btnRefresh.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });
        jToolBar1.add(btnRefresh);

        add(jToolBar1, java.awt.BorderLayout.LINE_END);

        jPanel1.setOpaque(false);
        jPanel1.setLayout(new java.awt.GridBagLayout());

        lblMainTitle.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        lblMainTitle.setText("TDS MANAGER -");
        lblMainTitle.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        lblMainTitle.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(8, 20, 6, 0);
        jPanel1.add(lblMainTitle, gridBagConstraints);

        lblDetailTitle.setFont(new java.awt.Font("Calibri", 2, 14)); // NOI18N
        lblDetailTitle.setText("Dr. X Y");
        lblDetailTitle.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        lblDetailTitle.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(8, 10, 6, 0);
        jPanel1.add(lblDetailTitle, gridBagConstraints);

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        ITSEventManager.fireEvent(new NavigateBackEvent(""));
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        ITSEventManager.fireEvent(new RefreshFrameContentEvent(""));
    }//GEN-LAST:event_btnRefreshActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lblDetailTitle;
    private javax.swing.JLabel lblMainTitle;
    // End of variables declaration//GEN-END:variables
}
