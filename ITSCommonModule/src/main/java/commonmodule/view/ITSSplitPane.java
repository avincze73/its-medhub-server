/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commonmodule.view;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JSplitPane;
import javax.swing.plaf.basic.BasicSplitPaneDivider;
import javax.swing.plaf.basic.BasicSplitPaneUI;

/**
 *
 * @author vincze.attila
 */
public class ITSSplitPane extends JSplitPane {

    public ITSSplitPane() {
        setUI(new BasicSplitPaneUI() {

            @Override
            public BasicSplitPaneDivider createDefaultDivider() {
                return new BasicSplitPaneDivider(this) {
                    @Override
                    protected void dragDividerTo(int location) {
                        super.dragDividerTo(location);
                    }

                    @Override
                    public void paint(Graphics g) {
                        try {
                            Graphics2D g2d = (Graphics2D) g;
                            int w = getWidth();
                            int h = getHeight();
                            //System.out.println(w + " " + h);
                            Color color1 = new Color(32, 74, 135);
                            Color color2 = new Color(238, 243, 250); // color1.brighter();
                            GradientPaint gp = new GradientPaint(0, 0, Color.GRAY, 0, h, Color.LIGHT_GRAY);
                            BufferedImage bi = ImageIO.read(getClass().getResourceAsStream("/commonmodule/image/splittericon.png"));
                            g2d.drawImage(bi, w / 2 - 15, 0, 30, 6, null);
                            g2d.setColor(Color.LIGHT_GRAY);
                            g2d.drawLine(0, 2, w / 2 - 21, 2);
                            g2d.drawLine(w / 2 + 20, 2, w, 2);
                        } catch (IOException ex) {
                            Logger.getLogger(ITSSplitPane.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                };
            }
            
        });
        setOrientation(VERTICAL_SPLIT);
        setDividerSize(6);
        setDividerLocation(100);
        setResizeWeight(1.0);
        setBorder(null);
        setContinuousLayout(true);
        setBackground(ITSColor.color2);
        setOpaque(false);
        
    };


    
//    @Override
//    public SplitPaneUI getUI() {
//        return new BasicSplitPaneUI() {
//
//            @Override
//            public BasicSplitPaneDivider createDefaultDivider() {
//                return new BasicSplitPaneDivider(this) {
//
//                    @Override
//                    protected void dragDividerTo(int location) {
//                        //super.dragDividerTo(location);
//                    }
//
//                    @Override
//                    public void paint(Graphics g) {
//                        try {
//                            Graphics2D g2d = (Graphics2D) g;
//                            int w = getWidth();
//                            int h = getHeight();
//                            System.out.println(w + " " + h);
//                            Color color1 = new Color(32, 74, 135);
//                            Color color2 = new Color(238, 243, 250); // color1.brighter();
//                            GradientPaint gp = new GradientPaint(0, 0, Color.GRAY, 0, h, Color.LIGHT_GRAY);
//                            BufferedImage bi = ImageIO.read(getClass().getResourceAsStream("/commonmodule/image/divider.png"));
//                            g2d.drawImage(bi, w / 2 - 15, 0, 30, 6, null);
//                            g2d.setColor(Color.LIGHT_GRAY);
//                            g2d.drawLine(0, 3, w / 2 - 20, 3);
//                            g2d.drawLine(w / 2 + 20, 3, w, 3);
//                        } catch (IOException ex) {
//                            Logger.getLogger(TDSSplitPane.class.getName()).log(Level.SEVERE, null, ex);
//                        }
//                    }
//                };
//            }
//        };
    //}
}
