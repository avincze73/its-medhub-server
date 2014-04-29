package tdsclients.client_general;

import javax.swing.*;

import tdsclients.radClient.CaseViewer;
import tdsclients.radClient.GUIUtility;


import java.awt.*;
import java.io.File;

//base object to build the main application window

public class FrameWithMenu extends JFrame{
	
	
	
	public class MenuWithBottomBorder extends JMenuBar{
		
		public void paintComponent(Graphics g)
		{		
			super.paintComponent(g);
				
			Graphics2D gc= (Graphics2D)g;
			
			int h = this.getSize().height; 
			int w = this.getSize().width;
			gc.setColor(new Color(175,175,175));
			gc.drawLine(0,h-2,w ,h-2 );
			gc.setColor(new Color(255,255,255));
			gc.drawLine(0,h-1,w ,h-1 );
		}		
		
	}
	
	
	
	
	protected String[][]menuNames;
	
//	protected MenuWithBottomBorder menuBar;
	protected JMenuBar menuBar;
	protected JMenu[] menus;
	protected JMenuItem[][] menuPoints;
	//protected JPanel glassPane;
	protected JPanel backPane;
	
	

//	public FrameWithMenu()	{ super();	}

	public FrameWithMenu()
		{	super();	}	
	
	
	protected void setupFrameWithMenu(String title, int sizeX, int sizeY, String[] menuNames, Action[][] menuActions, int[][] separators, boolean menuBottomBorder){
		
		setTitle(title);	
		setSize(sizeX,sizeY);
		
		if (menuBottomBorder)
			menuBar=new MenuWithBottomBorder();
		else
			menuBar=new JMenuBar();
		menuBar.setBorderPainted(true);
		menuBar.setFocusable(true);
		menuBar.add(new JLabel(GUIUtility.loadIcon("Icons"+File.separator+"separatorSmallStickIcon.png", new Color(0,255,0))));
		menuBar.setBorder(BorderFactory.createEmptyBorder(4,0,6,3));
		setJMenuBar(menuBar);

		menus=new JMenu[menuNames.length];
		menuPoints=new JMenuItem[menuNames.length][];
		
		
		for(int i=0;i<menuNames.length;i++)
			{
			menus[i]=new JMenu(menuNames[i]);
//			Menus[i].setFont(new Font("Times New Roman",Font.PLAIN,14));
			menus[i].setFont(new Font("Arial",Font.PLAIN,12));
			menus[i].setRolloverEnabled(true);
			menus[i].setOpaque(false);
			menus[i].setContentAreaFilled(false);	
			menus[i].setBackground(CaseViewer.GUIBACKGROUNDCOLOR);
			
			menuBar.add(menus[i]);
			menuPoints[i]=new JMenuItem[menuActions[i].length];	
			
			
/*			Menus[i].setContentAreaFilled(true);
			Menus[i].setFocusPainted(true);
			Menus[i].setRolloverEnabled(true);*/

			menus[i].setContentAreaFilled(false);
			menus[i].setFocusPainted(false);
			menus[i].setRolloverEnabled(false);

			
			for(int j=0;j<menuActions[i].length;j++)
				{
				menuPoints[i][j]=menus[i].add(menuActions[i][j]);
//				MenuPoints[i][j].setFont(new Font("Times New Roman",Font.PLAIN,14));
				menuPoints[i][j].setFont(new Font("Arial",Font.PLAIN,12));
				for(int k=0;k<separators.length;k++){ if(separators[k][0]==i+1  && separators[k][1]==j+1){menus[i].addSeparator();}}											
				}
			}		
		
		//getGlassPane().setVisible(true);
		//glassPane =(JPanel)getGlassPane();
//		glassPane.setLayout(new FlowLayout());

		backPane  =(JPanel)getContentPane();
	}
	
	
	protected JPanel getBackPane(){return backPane; }
	
	
}
