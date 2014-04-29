package tdsclients.client_general;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Icon;
import javax.swing.JToggleButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class ToolBarToggleButton extends JToggleButton{
	
	public static Dimension BUTTONSIZE = new Dimension(32,32);
	private ToolBarToggleButton thisButton;
	protected boolean smooth;
	
	public ToolBarToggleButton(Icon icon, String command, String tooltip, ActionListener actionListener, boolean smoothIn){
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
        
		ChangeListener  changeListener =  new ChangeListener() 
		{        
			public void stateChanged(ChangeEvent e)
	        {				

				if (thisButton.isSelected())
					{
						thisButton.setBorderPainted(true);
						thisButton.setOpaque(true);
						thisButton.repaint();

					}
					else
						if(smooth)
						{
							thisButton.setBorderPainted(false);
							thisButton.setOpaque(false);
							thisButton.repaint();
						}	
			}	        
	        
		};
		super.addChangeListener(changeListener);
        	
		
		MouseListener  mouseListener =  new MouseListener() 
		{        
			public void mouseClicked(MouseEvent e)
	        {
			}	        
			public void mouseEntered(MouseEvent e)
	        {
			
			}
			public void mouseExited(MouseEvent e)
	        {
				if(thisButton.isSelected())
					{
/*						thisButton.setBorderPainted(true);
					thisButton.setOpaque(true);
					thisButton.repaint();	*/
					}
				else
					{
					thisButton.setBorderPainted(false);						
					thisButton.setOpaque(false);
					thisButton.repaint();
					}
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
			}
		};
		super.addMouseListener(mouseListener);
		
	}	

	
	
	
	public ToolBarToggleButton(String label, String command, String tooltip, ActionListener actionListener, boolean smoothIn){
		super(label);
        setMargin(new Insets(1,1,1,1));
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
        
		ChangeListener  changeListener =  new ChangeListener() 
		{        
			public void stateChanged(ChangeEvent e)
	        {				

				if (thisButton.isSelected())
					{
						thisButton.setBorderPainted(true);
						thisButton.setOpaque(true);
						thisButton.repaint();

					}
					else
						if(smooth)
						{
							thisButton.setBorderPainted(false);
							thisButton.setOpaque(false);
							thisButton.repaint();
						}	
			}	        
	        
		};
		super.addChangeListener(changeListener);
        	
		
		MouseListener  mouseListener =  new MouseListener() 
		{        
			public void mouseClicked(MouseEvent e)
	        {
			}	        
			public void mouseEntered(MouseEvent e)
	        {
			
			}
			public void mouseExited(MouseEvent e)
	        {
				if(thisButton.isSelected())
					{
/*						thisButton.setBorderPainted(true);
					thisButton.setOpaque(true);
					thisButton.repaint();	*/
					}
				else
					{
					thisButton.setBorderPainted(false);						
					thisButton.setOpaque(false);
					thisButton.repaint();
					}
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
			}
		};
		super.addMouseListener(mouseListener);
		
	}	
	
	
}

