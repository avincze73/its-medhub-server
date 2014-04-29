package tdsclients.client_general;

import java.awt.Dimension;
import java.awt.image.BufferedImage;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class ToolBarSeparator extends JLabel {
	
	public static Dimension SEPARATORSIZE = new Dimension(14,40);
	
	public ToolBarSeparator(Icon icon){
		super(icon);
		setSize(SEPARATORSIZE);
		setPreferredSize(SEPARATORSIZE);
		setMinimumSize(SEPARATORSIZE);
		setMaximumSize(SEPARATORSIZE);
	}
	
	
	
}
