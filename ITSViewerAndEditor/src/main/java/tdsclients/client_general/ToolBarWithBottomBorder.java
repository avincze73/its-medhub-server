package tdsclients.client_general;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.BorderFactory;
import javax.swing.JToolBar;

public class ToolBarWithBottomBorder extends JToolBar {

	public ToolBarWithBottomBorder(){
		super();
		
		this.setOpaque(true);
		this.setVisible(true);
		setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
	}
	
	public void paintComponent(Graphics g)
	{		
		super.paintComponent(g);
			
		Graphics2D gc= (Graphics2D)g;
		
/*		int h = this.getSize().height; 
		int w = this.getSize().width;

		gc.setColor(new Color(175,175,175));
		gc.drawLine(0,h-1,w ,h-1 );

*/		
		
		int h = this.getSize().height; 
		int w = this.getSize().width;
		gc.setColor(new Color(175,175,175));
		gc.drawLine(0,h-2,w ,h-2 );
		gc.setColor(new Color(255,255,255));
		gc.drawLine(0,h-1,w ,h-1 );

	}		
	
	
}
