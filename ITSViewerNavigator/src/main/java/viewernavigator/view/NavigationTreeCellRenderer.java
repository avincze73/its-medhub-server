/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viewernavigator.view;

import casemodule.downloading.CaseDownloader;
import casemodule.dto.DicomImageDTO;
import casemodule.dto.SeriesDTO;
import dicommodule.tdsdicomadapter.DicomAdapter;
import dicommodule.tdsdicomadapter.DicomInterfaceDBSide;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;
import tdsdicomimage.DicomImage;
import tdsdicomimage.exception.DImgException;
import tdsdicominterface.DicomDataSet;
import tdsdicominterface.exception.DIException;

/**
 *
 * @author vincze.attila
 */
public class NavigationTreeCellRenderer implements TreeCellRenderer {

    JLabel firstNameLabel = new JLabel(" ");
    JLabel lastNameLabel = new JLabel(" ");
    JLabel salaryLabel = new JLabel(" ");
    JPanel renderer = new JPanel();
    Color backgroundSelectionColor;
    Color backgroundNonSelectionColor;
    DefaultTreeCellRenderer defaultRenderer = new DefaultTreeCellRenderer();
    Color infoColor;
    Color iconBorderColor;
    Color viewerBackgroundColor;
    private Color studyNodeColor;
    private Font studyNodeFont;
    private Border studyNodeBorder;
    private Color rootNodeColor;
    private Font rootNodeFont;
    private Border rootNodeBorder;

    public NavigationTreeCellRenderer(Color infoColor, Color iconBorderColor, Color viewerBackgroundColor) {
        this.viewerBackgroundColor = viewerBackgroundColor;
        this.infoColor = infoColor;
        this.iconBorderColor = iconBorderColor;
        renderer.setSize(130, 130);
        firstNameLabel.setForeground(Color.BLUE);
        renderer.add(firstNameLabel);

        lastNameLabel.setForeground(Color.BLUE);
        renderer.add(lastNameLabel);

        salaryLabel.setHorizontalAlignment(JLabel.RIGHT);
        salaryLabel.setForeground(Color.RED);
        renderer.add(salaryLabel);
        renderer.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        backgroundSelectionColor = defaultRenderer.getBackgroundSelectionColor();
        backgroundNonSelectionColor = defaultRenderer.getBackgroundNonSelectionColor();
        defaultRenderer.setClosedIcon(null);
        defaultRenderer.setOpenIcon(null);
    }

    public NavigationTreeCellRenderer(Color iconBorderColor, Color viewerBackgroundColor, Color infoColor,
            Color studyNodeColor, Font studyNodeFont, Border studyNodeBorder,
            Color rootNodeColor, Font rootNodeFont, Border rootNodeBorder) {
        this.viewerBackgroundColor = viewerBackgroundColor;
        this.iconBorderColor = iconBorderColor;
        this.studyNodeColor = studyNodeColor;
        this.studyNodeFont = studyNodeFont;
        this.studyNodeBorder = studyNodeBorder;
        this.rootNodeColor = rootNodeColor;
        this.rootNodeFont = rootNodeFont;
        this.rootNodeBorder = rootNodeBorder;
        this.infoColor = infoColor;

        renderer.setSize(130, 130);
        firstNameLabel.setForeground(Color.BLUE);
        renderer.add(firstNameLabel);

        lastNameLabel.setForeground(Color.BLUE);
        renderer.add(lastNameLabel);

        salaryLabel.setHorizontalAlignment(JLabel.RIGHT);
        salaryLabel.setForeground(Color.RED);
        renderer.add(salaryLabel);
        renderer.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        backgroundSelectionColor = defaultRenderer.getBackgroundSelectionColor();
        backgroundNonSelectionColor = defaultRenderer.getBackgroundNonSelectionColor();
        defaultRenderer.setClosedIcon(null);
        defaultRenderer.setOpenIcon(null);
    }

    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        Component returnValue = null;
        if (leaf) {
            try {
                System.out.println(value);
                SeriesDTO seriesDTO = CaseDownloader.getInstance().getSeriesDTO(value.toString());
                DicomImage dicomImage = new DicomImage();
                int i = seriesDTO.getDicomImageList().size();
                //DicomDataSet dataSet = seriesDTO.getDicomImageList().get(0).getDicomDataSet();
                DicomDataSet dataSet = seriesDTO.getDicomImageList().get(0).getDicomDataSetIcon();
                if (dataSet != null) {
                    DicomInterfaceDBSide dicomIntf = new DicomAdapter(dataSet);
                    Integer w = dicomIntf.getColumns().value;
                    Integer h = dicomIntf.getRows().value;
                    int ww = w >= h ? 130 : (int) (((double) w / h) * 130.0);
                    int hh = h >= w ? 130 : (int) (((double) h / w) * 130.0);
                    dicomImage.loadImage(dataSet);
                    BufferedImage bufferedImage = dicomImage.getBufferedImage(new Dimension(ww, hh));
                    //returnValue = new TDSTreeImage(ImageIO.read(getClass().getResourceAsStream("orvosi_kepek.png")));
                    returnValue = new TDSTreeImage(bufferedImage,
                            seriesDTO.getModality().getName(), seriesDTO.getHospitalBodyRegion().getEnglishName(),
                            seriesDTO.getDicomImageList().size(), infoColor, iconBorderColor);
                    //returnValue = new TDSTreeImage(bufferedImage);
                }

            } catch (DIException ex) {
                Logger.getLogger(NavigationTreeCellRenderer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (DImgException ex) {
                Logger.getLogger(NavigationTreeCellRenderer.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JLabel label = new JLabel(value.toString());
            //tree.setRowHeight(50);
            //label.setPreferredSize(new Dimension(150, 16));
            if (((DefaultMutableTreeNode) value).getUserObject() instanceof TDSRootNode) {
                if (rootNodeFont == null) {
                    label.setFont(new Font("Serif", Font.PLAIN, 12));
                } else {
                    label.setFont(rootNodeFont);
                }
                if (rootNodeColor == null) {
                    label.setForeground(Color.BLACK);
                } else {
                    label.setForeground(rootNodeColor);
                }
                if (rootNodeBorder == null) {
                    label.setBorder(new EmptyBorder(5, 0, 5, 0));
                } else {
                    label.setBorder(rootNodeBorder);
                }
            } else {
                if (studyNodeFont == null) {
                    label.setFont(new Font("Serif", Font.PLAIN, 12));
                } else {
                    label.setFont(studyNodeFont);
                }
                if (studyNodeColor == null) {
                    label.setForeground(Color.BLACK);
                } else {
                    label.setForeground(studyNodeColor);
                }
                if (studyNodeBorder == null) {
                    label.setBorder(new EmptyBorder(5, 0, 5, 0));
                } else {
                    label.setBorder(studyNodeBorder);
                }
            }
            
            label.setBackground(viewerBackgroundColor);
            //returnValue = defaultRenderer.getTreeCellRendererComponent(tree, value, leaf, expanded, leaf, row, hasFocus);
            returnValue = label;
        }
        return returnValue;
    }
}
