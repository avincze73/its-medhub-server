package tdsclients.radClient;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Rectangle;

public class ScreenAssignment {

	private static boolean multiscreen;
	private int startScreen, viewerScreen, reportScreen ;
	private Rectangle startScreenBounds, viewerScreenBounds, reportScreenBounds; 
	
	public ScreenAssignment(int startScreenIn, int viewerScreenIn, int reportScreenIn){
		startScreen =startScreenIn;
		viewerScreen =viewerScreenIn;
		reportScreen =reportScreenIn;
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice defaultScreen = ge.getDefaultScreenDevice(); 
		GraphicsDevice[] gs = ge.getScreenDevices();
		int numScreens = gs.length;
		GraphicsConfiguration[] gc = new GraphicsConfiguration[numScreens];
		for (int i=0;i<numScreens;i++)
					gc[i] = gs[i].getDefaultConfiguration();
		
		multiscreen = (numScreens>1) && (startScreen !=0 || viewerScreen!= 0 || reportScreen!=0); 
	
		if(startScreen<numScreens)
			startScreenBounds = gc[startScreen].getBounds();
		else
			startScreenBounds = defaultScreen.getDefaultConfiguration().getBounds();
		
		if(viewerScreen<numScreens)
			viewerScreenBounds = gc[viewerScreen].getBounds();
		else
			viewerScreenBounds = defaultScreen.getDefaultConfiguration().getBounds();

		if(reportScreen<numScreens)
			reportScreenBounds = gc[reportScreen].getBounds();
		else
			reportScreenBounds = defaultScreen.getDefaultConfiguration().getBounds();
	}
	
	
	public boolean ismultiScreen(){ return multiscreen;}

	public int getStartScreenNum(){ return startScreen;}
	public int getViewerScreenNum(){ return viewerScreen;}
	public int getReportScreenNum(){ return reportScreen;}
	
	public Rectangle getStartScreenBounds(){ return startScreenBounds;}
	public Rectangle getViewScreenBounds(){ return viewerScreenBounds;}
	public Rectangle getReportScreenBounds(){ return reportScreenBounds;}
	
	public Point getStartScreenCenter(){ return new Point(startScreenBounds.x+startScreenBounds.width/2,startScreenBounds.y+startScreenBounds.height/2);}
	public Point getViewScreenCenter(){ return new Point(viewerScreenBounds.x+viewerScreenBounds.width/2,viewerScreenBounds.y+viewerScreenBounds.height/2);}
	public Point getReportScreenCenter(){ return new Point(reportScreenBounds.x+reportScreenBounds.width/2,reportScreenBounds.y+reportScreenBounds.height/2);}

}
