/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Window;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;

/**
 *
 * @author vincze.attila
 */
public class GuiUtils {

    private static JFrame _mainFrame;

    /**
     * Centers <CODE>wind</CODE> within its parent. If it has no parent then
     * center within the screen. If centering would cause the title bar to go
     * above the parent (I.E. cannot see the titlebar and so cannot move the
     * window) then move the window down.
     *
     * @param wind
     *          The Window to be centered.
     *
     * @throws IllegalArgumentException
     *           If <TT>wind</TT> is <TT>null</TT>.
     */
    public static void centerWithinParent(Window wind) {
        if (wind == null) {
            throw new IllegalArgumentException("null Window passed");
        }
        final Container parent = wind.getParent();
        if (parent != null && parent.isVisible()) {
            center(wind, new Rectangle(parent.getLocationOnScreen(), parent.getSize()));
        } else {
            centerWithinScreen(wind);
        }
    }

    /**
     * Centers passed internal frame within its desktop area. If centering would
     * cause the title bar to go off the top of the screen then move the window
     * down.
     *
     * @param frame
     *          The internal frame to be centered.
     *
     * @throws IllegalArgumentException
     *           If <TT>frame</TT> is <TT>null</TT>.
     */
    public static void centerWithinDesktop(JInternalFrame frame) {
        if (frame == null) {
            throw new IllegalArgumentException("null JInternalFrame passed");
        }
        final Container parent = frame.getDesktopPane();
        if (parent != null && parent.isVisible()) {
            center(frame, new Rectangle(new Point(0, 0), parent.getSize()));
        }
    }

    /**
     * Centers <CODE>wind</CODE> within the screen. If centering would cause the
     * title bar to go off the top of the screen then move the window down.
     *
     * @param wind
     *          The Window to be centered.
     *
     * @throws IllegalArgumentException
     *           If <TT>wind</TT> is <TT>null</TT>.
     */
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

    /**
     * Centers <CODE>wind</CODE> within the passed rectangle.
     *
     * @param wind
     *          The Window to be centered.
     * @param rect
     *          The rectangle (in screen coords) to center <CODE>wind</CODE>
     *          within.
     *
     * @throws IllegalArgumentException
     *           If <TT>Window</TT> or <TT>Rectangle</TT> is <TT>null</TT>.
     */
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
}
