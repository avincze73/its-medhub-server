/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ReportView.java
 *
 * Created on 2010.12.13., 10:07:13
 */
package view;

import iview.IReportEditorView;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Window;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import viewmodel.ReportEditorViewModel;

/**
 *
 * @author vincze.attila
 */
public class ReportView extends javax.swing.JFrame implements IReportEditorView {

    private ReportEditorViewModel viewModel;

    /** Creates new form ReportView */
    public ReportView(javax.swing.JFrame parent) {
        viewModel = new ReportEditorViewModel(this);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReportView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ReportView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ReportView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(ReportView.class.getName()).log(Level.SEVERE, null, ex);
        }
        initComponents();
        setSize(600, 800);

        addWindowListener(new java.awt.event.WindowAdapter() {

            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                //System.exit(0);
            }
        });

        if (parent != null) {
            Rectangle rect = new Rectangle(parent.getLocationOnScreen(), parent.getSize());
            Dimension windSize = this.getSize();
            int x = ((rect.width - windSize.width) / 2) + rect.x;
            int y = ((rect.height - windSize.height) / 2) + rect.y;
            if (y < rect.y) {
                y = rect.y;
            }
            this.setLocation(x, y);
        }
        setVisible(true);
    }

    public void positionToParent() {
        GuiUtils.centerWithinParent(this);
    }

    public static void centerWithinScreen(Window wind) {
        if (wind == null) {
            throw new IllegalArgumentException("null Window passed");
        }
        final Toolkit toolKit = Toolkit.getDefaultToolkit();
        final Rectangle rcScreen = new Rectangle(toolKit.getScreenSize());
        final Dimension windSize = wind.getSize();
        final Dimension parentSize = new Dimension(rcScreen.width, rcScreen.height);
        if (windSize.height > parentSize.height) {
            windSize.height = parentSize.height;
        }
        if (windSize.width > parentSize.width) {
            windSize.width = parentSize.width;
        }
        center(wind, rcScreen);
    }

    private static void center(Component wind, Rectangle rect) {
        if (wind == null || rect == null) {
            throw new IllegalArgumentException("null Window or Rectangle passed");
        }
        Dimension windSize = wind.getSize();
        int x = ((rect.width - windSize.width) / 2) + rect.x;
        int y = ((rect.height - windSize.height) / 2) + rect.y;
        if (y < rect.y) {
            y = rect.y;
        }
        wind.setLocation(x, y);
    }

    public ReportEditorViewModel getViewModel() {
        return viewModel;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        toolBar = new javax.swing.JToolBar();
        jLabel1 = new javax.swing.JLabel();
        btnSave = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        btnDone = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        btnConfirm = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        epReport = new javax.swing.JEditorPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Report editor");

        toolBar.setFloatable(false);
        toolBar.setPreferredSize(new java.awt.Dimension(100, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/separator.png"))); // NOI18N
        toolBar.add(jLabel1);

        btnSave.setAction(viewModel.getSaveAction());
        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/savereport.png"))); // NOI18N
        btnSave.setToolTipText("Lelet mentése");
        btnSave.setFocusable(false);
        btnSave.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSave.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(btnSave);
        toolBar.add(jSeparator2);

        btnDone.setAction(viewModel.getDoneAction());
        btnDone.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/donereport.png"))); // NOI18N
        btnDone.setToolTipText("Lelet befejezése");
        btnDone.setFocusable(false);
        btnDone.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDone.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(btnDone);
        toolBar.add(jSeparator3);

        btnConfirm.setAction(viewModel.getConfirmAction());
        btnConfirm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/confirmreport.png"))); // NOI18N
        btnConfirm.setToolTipText("Lelet lezárása");
        btnConfirm.setFocusable(false);
        btnConfirm.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnConfirm.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${viewModel.confirmButtonEnabled}"), btnConfirm, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        toolBar.add(btnConfirm);

        getContentPane().add(toolBar, java.awt.BorderLayout.PAGE_START);

        epReport.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(30, 30, 30, 30, java.awt.Color.gray), javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.darkGray), javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0))));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${viewModel.reportText}"), epReport, org.jdesktop.beansbinding.BeanProperty.create("text"));
        bindingGroup.addBinding(binding);

        jScrollPane1.setViewportView(epReport);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConfirm;
    private javax.swing.JButton btnDone;
    private javax.swing.JButton btnSave;
    private javax.swing.JEditorPane epReport;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar toolBar;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables


    public void showWaitingPane() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void hideWaitingPane() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}