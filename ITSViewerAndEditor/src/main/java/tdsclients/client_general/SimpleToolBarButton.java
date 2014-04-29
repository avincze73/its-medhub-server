package tdsclients.client_general;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Icon;
import javax.swing.JButton;


public class SimpleToolBarButton extends JButton{
	
	public static Dimension BUTTONSIZE = new Dimension(32,32);
	private SimpleToolBarButton thisButton;
	protected boolean smooth;
	
	public SimpleToolBarButton(Icon icon, String command, String tooltip, ActionListener actionListener, boolean smoothIn){
		super(icon);
        setMargin(new Insets(1,1,1,1));
		
		setSize(BUTTONSIZE);
		setPreferredSize(BUTTONSIZE);
		setMinimumSize(BUTTONSIZE);
		setMaximumSize(BUTTONSIZE);
        setFocusable(false);
		smooth = smoothIn;	
        if(smooth)
    	{
    		setBorderPainted(false);
    		setOpaque(false);
    	}
        setActionCommand(command);
        setToolTipText(tooltip);
        addActionListener(actionListener);
        
        thisButton = this;	 
		MouseListener  mouseListener =  new MouseListener() 
		{        
			public void mouseClicked(MouseEvent e)
	        {
			}	        
			public void mouseEntered(MouseEvent e)
	        {
//				thisButton.setBorderPainted(true);
//				thisButton.setOpaque(true);
//				thisButton.repaint();
			}
			public void mouseExited(MouseEvent e)
	        {
				thisButton.setBorderPainted(false);
				thisButton.setOpaque(false);
				thisButton.repaint();
			}
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				//thisButton.setSelected(true);
				thisButton.setBorderPainted(true);
				thisButton.setOpaque(true);
				thisButton.repaint();
			}
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub		
				thisButton.setBorderPainted(false);
				thisButton.setOpaque(false);
				thisButton.repaint();
				
			}
		};
		super.addMouseListener(mouseListener);
	}	
	
}
