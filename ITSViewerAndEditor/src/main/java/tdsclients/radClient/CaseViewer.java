package tdsclients.radClient;


import casemodule.downloading.CaseDownloader;
import java.awt.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.TextField;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;


import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.*;

import commonmodule.view.ITSLookAndFeel;


import casemodule.dto.StudyDTO;

//import com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel;


//import datamodel.CaseData;
//import datamodel.ActSessionCaseData;
//import datamodel.imagedatamodel.Series;
import event.CloseFrameEvent;
import event.CloseFrameEventListener;
import event.ITSEventManager;

import tdsclients.client_general.FrameWithMenu;
import tdsclients.client_general.SimpleToolBarButton;
import tdsclients.client_general.ToolBarSeparator;
import tdsclients.client_general.ToolBarToggleButton;
import tdsclients.client_general.ToolBarWithBottomBorder;
import tdsclients.radClient.imaging.DisplayImageFrame;
import tdsclients.radClient.imaging.DisplayModel;
import tdsclients.radClient.imaging.ThumbnailImageFrame;
import tdsclients.radClient.imaging.tools.MDistance;
import tdsclients.radClient.imaging.tools.MPointDens;
import view.CommentView;
import view.ReportEditorView;

import view.TroubleshootView;
import viewernavigator.view.TDSNavigationTree;


//import com.sun.java.swing.plaf.motif.MotifLookAndFeel;
//MotifLookAndFeel;


//import externalinterface.dicom.DICOM_DataDictionary;
//import externalinterface.dicom.DICOM_File;
//import externalinterface.dicom.DICOM_FileContent;

//public class CaseViewer extends ClientFrame {


public class CaseViewer extends FrameWithMenu implements CloseFrameEventListener {

	public class TestLayer extends JLayeredPane{
		private String seriesTest;
		
		public void setSeriesTest(String seriesStr){
			System.out.println("\n\n***\n drop argumentum: "+seriesStr+"\n***\n\n");
			seriesTest = seriesStr;
		}

		public String getSeriesTest(String seriesStr){return seriesTest;}
		
		
	}
	
	
	
	
	
        public void eventOccured(CloseFrameEvent event) {
            WindowEvent wev = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
            Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);
        }

	
	public class UndoButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
            System.out.println("\n\n\nYou clicked Button UNDO");
//            performOperationOnAll(CaseViewer.UNSELECTALLTOOLSOP, 0, 0);
//	            String dispStateToShow = undoRedoModel.undoBrowsingStep();
//	            System.out.println("New shown display state = "+dispStateToShow);
            
            boolean [] displayChangeIndicators = undoRedoModel.getFirstUndoChangeIndicators();		// an indicator array, defining which displays are going to be changed loading the first undo state
            DisplayModel[] statesToShow = undoRedoModel.undoBrowsingStep(getCopyOfCurrentViewerStates(displayChangeIndicators));
//            System.gc();
//            String stateToShow = testUndoRedoModel.undoBrowsingStep(actTestState);
            if (statesToShow != null) //if undo done
            {
//            	actTestState = stateToShow;
//            	System.out.println("*New state after undo: " + actTestState+" *");
//            	System.out.println("\tStarting image pos (of frame 0)                = ("+mainDisplayFrames[0].getImagePosX()+","+mainDisplayFrames[0].getImagePosY()+")");
 //           	System.out.println("\tLoading previous (undo) pos state (of frame 0) = ("+statesToShow[0].getActPosX()+","+statesToShow[0].getActPosY()+")");
//            	System.out.println("*!!!Loading previous (undo) state:");
//            		System.out.println("\tOld frame pointer: "+mainDisplayFrames+"\n\t\t disp[0] posy = "+mainDisplayFrames[0].getImagePosY()+", disp[1] posy = "+mainDisplayFrames[1].getImagePosY()+", disp[2] posy = "+mainDisplayFrames[2].getImagePosY());
//            		System.out.println("\tNew state pointer: "+statesToShow+"\n\t\t disp[0] posy = "+statesToShow[0].getActPosY()+", disp[1] posy = "+statesToShow[1].getActPosY()+", disp[2] posy = "+statesToShow[2].getActPosY());
//            		System.out.println("\tnew state pointer: "+statesToShow+", disp[0] = "+statesToShow[0]+", disp[1] = "+statesToShow[1]+", disp[2] = "+statesToShow[2]);

            	loadExDisplayStates(statesToShow);
            	
            	printToolLists();  //TODO delete this
            	
//            	System.out.println("\tSo the new pos state (of frame 0) is           = ("+mainDisplayFrames[0].getImagePosX()+","+mainDisplayFrames[0].getImagePosY()+")");
//        		System.out.println("\tNew frame pointer: "+mainDisplayFrames+"\n\t\t disp[0] posy = "+mainDisplayFrames[0].getImagePosY()+", disp[1] posy = "+mainDisplayFrames[1].getImagePosY()+", disp[2] posy = "+mainDisplayFrames[2].getImagePosY());

//                System.gc();

//				double actHeapSize = ((double)Runtime.getRuntime().totalMemory())/1000/1000; 
//				double maxHeapSize = ((double)Runtime.getRuntime().maxMemory())/1000/1000; 
//				double freeHeapSize = ((double)Runtime.getRuntime().freeMemory())/1000/1000; 
//				double usedHeapSize = actHeapSize - freeHeapSize;
//				System.out.println(" *** MAX TOTAL MEMORY = "+maxHeapSize+" MB *** ");
//				System.out.println(" *** ACT TOTAL MEMORY = "+actHeapSize+" MB *** ");
//				System.out.println(" *** USED MEMORY = "+usedHeapSize+" MB *** ");
//				System.out.println(" *** FREE MEMORY = "+freeHeapSize+" MB *** ");
				
/*            	for(int i=1; i<mainDisplayFrames.length; i++)
            		mainDisplayFrames[i].loadDisplayModel(statesToShow[i]);*/
//            	setupDisplayField();
//       		displayField.revalidate();
//        		displayField.repaint();
//        		for(int i=0; i < mainDisplayFrames.length; i++)
//       			mainDisplayFrames[i].repaint();
            }
            lastOperation = UNDOOP;
    		lastOpTime = System.currentTimeMillis();
    		clearIndicator();
		}
	}
	
	
	
	public class RedoButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
            System.out.println("\n\n\nYou clicked Button REDO");
//            String dispStateToShow = undoRedoModel.redoBrowsingStep();
//            System.out.println("New shown display state = "+dispStateToShow);
            boolean [] displayChangeIndicators = undoRedoModel.getFirstRedoChangeIndicators();		// an indicator array, defining which displays are going to be changed loading the first undo state
            DisplayModel[] statesToShow = undoRedoModel.redoBrowsingStep(getCopyOfCurrentViewerStates(displayChangeIndicators));

//            DisplayModel[] statesToShow = undoRedoModel.redoBrowsingStep(getCopyOfCurrentViewerStates());
//            System.gc();

//            String stateToShow = testUndoRedoModel.redoBrowsingStep(actTestState);
            if (statesToShow != null) //if redo done
            {
//            	actTestState = stateToShow;
//            	System.out.println("*New state after redo: " + actTestState+" *");
//            	System.out.println("*!!!Loading later(redo) state:");
//        		System.out.println("\tOld state pointer: "+mainDisplayFrames+", disp[0] posy = "+mainDisplayFrames[0].getImagePosY()+", disp[1] posy = "+mainDisplayFrames[1].getImagePosY()+", disp[2] posy = "+mainDisplayFrames[2].getImagePosY());
//        		System.out.println("\tnew state pointer: "+statesToShow+", disp[0] posy = "+statesToShow[0].getImagePosY()+", disp[1] posy = "+statesToShow[1].getImagePosY()+", disp[2] posy = "+statesToShow[2].getImagePosY());
//        		System.out.println("\tnew state pointer: "+statesToShow+", disp[0] = "+statesToShow[0]+", disp[1] = "+statesToShow[1]+", disp[2] = "+statesToShow[2]);
            	
        		loadExDisplayStates(statesToShow);
        		
            	printToolLists();  //TODO delete this

//                System.gc();

//				double actHeapSize = ((double)Runtime.getRuntime().totalMemory())/1000/1000; 
//				double maxHeapSize = ((double)Runtime.getRuntime().maxMemory())/1000/1000; 
//				double freeHeapSize = ((double)Runtime.getRuntime().freeMemory())/1000/1000; 
//				double usedHeapSize = actHeapSize - freeHeapSize;
//				System.out.println(" *** MAX TOTAL MEMORY = "+maxHeapSize+" MB *** ");
//				System.out.println(" *** ACT TOTAL MEMORY = "+actHeapSize+" MB *** ");
//				System.out.println(" *** USED MEMORY = "+usedHeapSize+" MB *** ");
//				System.out.println(" *** FREE MEMORY = "+freeHeapSize+" MB *** ");
				
/*            	mainDisplayFrames = stateToShow;
            	setupDisplayField();
        		displayField.revalidate();
        		displayField.repaint();
        		for(int i=0; i < mainDisplayFrames.length; i++)
        			mainDisplayFrames[i].repaint();*/
            }
            lastOperation = REDOOP;
    		lastOpTime = System.currentTimeMillis();
    		clearIndicator();
			
		}
	}
	
	
	
	public class ResetButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
            System.out.println("You clicked Button RESET");
            
            
            if( noDisplaySelected() )
            	performOperationOnAll(RESETOP,null);            	
            else
            	performOperationOnSelected(RESETOP,null);
            

		}
	}
	
	
	
	public class CenterButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
            System.out.println("You clicked Button CENTER");
			performOperationOnSelected(CENTEROP,null);
		
			
            lastOperation = CENTEROP;
    		lastOpTime = System.currentTimeMillis();
    		clearIndicator();
			
		}
	}
	
	
	
	public class FitDisplayButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
            System.out.println("You clicked Button FIT TO DISPLAY");
			performOperationOnSelected(FITDISPOP,null);

            lastOperation = FITDISPOP;
    		lastOpTime = System.currentTimeMillis();
    		clearIndicator();
            
		}
	}
	
	
	
	public class ClearButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
            System.out.println("You clicked Button CLEAR");
//            String newState = Integer.toString((int) Math.round(Math.random()*9-0.5));
//            System.out.println("Functionality now is to produce a new state. The state now is: "+newState);
//            undoRedoModel.pushNewState(newState);
//            System.out.println("New shown display state = "+newState); 
            
			performOperationOnSelected(CLEAROP,null);
            
            lastOperation = CLEAROP;
    		lastOpTime = System.currentTimeMillis();
    		clearIndicator();
            
		}
	}
	
	
	
	public class Zoom100ButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
            System.out.println("You clicked Button ZOOM 100%");
			performOperationOnSelected(ZOOM100OP,null);
			
            lastOperation = ZOOM100OP;
    		lastOpTime = System.currentTimeMillis();
    		clearIndicator();
		}
	}
	
	
	
	public class ResetWindowButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
            System.out.println("You clicked Button RESET WINDOW");
			performOperationOnSelected(RESETWINDOWOP,null);
			
            lastOperation = RESETWINDOWOP;
    		lastOpTime = System.currentTimeMillis();
    		clearIndicator();
		}
	}
	
	
	
	public class AutoWindowButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
            System.out.println("You clicked Button AUTO WINDOW");
			performOperationOnSelected(AUTOWINDOWOP,null);
			
            lastOperation = AUTOWINDOWOP;
    		lastOpTime = System.currentTimeMillis();
    		clearIndicator();
		}
	}
	
	
	public class PresetWindowButtonListener implements ActionListener{

/*		Component invoker;
		
		public PresetWindowButtonListener(Component invokerIn){
			 invoker = invokerIn;
		}
	*/	
		@Override
		public void actionPerformed(ActionEvent arg0) {
			System.out.println("INVOKING POPUP WINDOW");
			
			int x = 0;//windowChooserButton.getLocationOnScreen().x;
			int y = ctWindowChooserButton.getHeight()+5;//windowChooserButton.getLocationOnScreen().y;
			presetWindowMenu.show(ctWindowChooserButton, x, y);
			
			
			
/*			// TODO Auto-generated method stub
            System.out.println("You clicked Button AUTO WINDOW");
			performOperationOnSelected(AUTOWINDOWOP,0,0);
			
            lastOperation = AUTOWINDOWOP;
    		lastOpTime = System.currentTimeMillis();
    		clearIndicator();*/
		}
	}
	
	

	public abstract class WinChooserAction extends AbstractAction{

		protected int menuNum;
		
		public WinChooserAction(String name, int menuNumIn){
			super(name);
			menuNum = menuNumIn;
		}
		
	}
	
	class ToolChangeListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			performOperationOnAll(CaseViewer.UNSELECTALLTOOLSOP, null);
		}
		
	}
	
/*	class SeriesModeChangeListener implements ChangeListener
	{
		@Override
		public void stateChanged(ChangeEvent arg0) {			//TODO make it nicer - don't remove displays!!! only add/remove second series bar and revalidate!!!
			if (twoSeriesButton.isSelected())			resetDisplayPanel(2);
			else				resetDisplayPanel(1);
		}		
	}*/
	
	
/*	class OpenEditorListener implements ChangeListener
	{

		public void stateChanged(ChangeEvent arg0) {
			if (openEditorButton.isSelected() && !ReportEditor.isEditorUp())			
				openEditor();				
		}		
	}*/
	
	
	

	class TroubleShootActionListener implements ActionListener
	{
		private CaseViewer thisViewer;
	
		
		public TroubleShootActionListener(CaseViewer thisViewer){
			this.thisViewer = thisViewer;
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			TroubleshootView dialog = new TroubleshootView(thisViewer, true);
		}

	
	}

    class ReportActionListener implements ActionListener
	{
		private CaseViewer thisViewer;


		public ReportActionListener(CaseViewer thisViewer){
			this.thisViewer = thisViewer;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
                    new ReportEditorView(thisViewer);
		}


	}

    class CommentActionListener implements ActionListener
	{
		private CaseViewer thisViewer;


		public CommentActionListener(CaseViewer thisViewer){
			this.thisViewer = thisViewer;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
                    new CommentView(thisViewer);
		}
	}

    
    class ConsultActionListener implements ActionListener
	{
		private CaseViewer thisViewer;


		public ConsultActionListener(CaseViewer thisViewer){
			this.thisViewer = thisViewer;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
		}


	}

	
	
	private static BufferedImage iconTDS;
	private static final String FRAMETITLEPART1 = "[Viewer ";
	private static final String FRAMETITLEPART2 = "] ITS Radiologist - Dr. David Smith";
	private static final int SIZEX = 1250;
	private static final int SIZEY = 752;
//	private static final String TAB1NAME = "      Case Data      ";
//	private static final String TAB2NAME = "      Study Browser      ";
	//public static final String ICONDIR = /*File.separator+*/"Icons"+File.separator;
        //public static final String ICONDIR = /*File.separator+*/"f:\\DiagDance\\ITS\\sources\\ITSViewerAndEditor\\src\\main\\resources\\Icons"+File.separator;
        public static final String ICONDIR = /*File.separator+*/"./src/main/resources/Icons"+File.separator;
        //public static final String ICONDIR = /*File.separator+*/"..\\resources\\Icons"+File.separator;
	public static final String TBIsepFN = ICONDIR+"separatorStickIcon4.png";
	public static final String TBIfillerFN = ICONDIR+"filler2.png";
	public static final String TBI0FN = ICONDIR+"toolbarIcon0t.png";
	public static final String TBI1FN = ICONDIR+"toolbarIcon1t.png";
	public static final String TBI2FN = ICONDIR+"toolbarIcon2t.png";
	public static final String TBI3FN = ICONDIR+"toolbarIcon3t.png";
	public static final String TBI4FN = ICONDIR+"toolbarIcon4t.png";
	public static final String TBI5FN = ICONDIR+"toolbarIcon5t.png";
	public static final String TBI6FN = ICONDIR+"toolbarIcon6t.png";
	public static final String TBI7FN = ICONDIR+"toolbarIcon7t.png";
	public static final String TBI8FN = ICONDIR+"toolbarIcon8t.png";
	public static final String TBI9FN = ICONDIR+"toolbarIcon9t.png";
	public static final String TBI10FN = ICONDIR+"toolbarIcon10t.png";
	public static final String TBIundoFN = ICONDIR+"undoIcon8.png";
	public static final String TBIredoFN = ICONDIR+"redoIcon8.png";
	public static final String TBIresetFN = ICONDIR+"resetIcon6.png";
	public static final String TBIclearFN = ICONDIR+"clearIcon6.png";
	public static final String TBIhandFN = ICONDIR+"handIcon12.png";
	//private static final String TBIhandFN = ICONDIR+"hand7.png";
	public static final String TBIdistMeaFN = ICONDIR+"distMeasIcon14.png";
	public static final String TBIpointMeaFN = ICONDIR+"pointDensMeasIcon5.png";
	public static final String TBIareaMeaFN = ICONDIR+"areaDensMeasIcon3.png";
	public static final String TBIangleMeaFN = ICONDIR+"angleMeasIcon3.png";
	public static final String TBIcobbAngleMeaFN = ICONDIR+"cobbAngleMeasIcon4.png";
	//public static final String TBImagnGlassFN = ICONDIR+"magnGlassIcon3.png";
	public static final String TBIzoomSelectedFN = ICONDIR+"zoomSelectedIcon3.png";
	public static final String TBIfitDispFN = ICONDIR+"fitScreenIcon12.png";
	public static final String TBIcenterImageFN = ICONDIR+"centerImageIcon2.png";
	public static final String TBIrotateFN = ICONDIR+"rotateIcon4.png";
	public static final String TBIflipHorFN = ICONDIR+"flipHorIcon2.png";
	public static final String TBIflipVerFN = ICONDIR+"flipVertIcon2.png";
	public static final String TBIresetWindowFN = ICONDIR+"resetWindowIcon5.png";
	public static final String TBIautoWindowFN = ICONDIR+"autoWindowIcon5.png";
	public static final String TBIctWindowFN = ICONDIR+"ctWindowIcon5.png";	
	public static final String TBIselectAreaWindowFN = ICONDIR+"selectAreaWindowIcon2.png";
	public static final String TBIzoom100FN = ICONDIR+"zoom100percentIcon.png";
	public static final String TBIshowRefLineFN = ICONDIR+"showRefLineIcon3.png";
	public static final String TBIselectDisplayModeFN = ICONDIR+"selectDisplayMode.png";
	public static final String TBIshowStudyParamsFN = ICONDIR+"showStudyParamIcon2.png";
	public static final String TBIscaleRulerFN = ICONDIR+"scaleRulerIcon.png";
	public static final String TBI2seriesFN = ICONDIR+"2seriesIcon2.png";
	public static final String TBI1seriesFN = ICONDIR+"1seriesIcon2.png";
	public static final String TBIopenCaseFN = ICONDIR+"openCaseIcon3.png";
	public static final String TBIopenEditorFN = ICONDIR+"openEditorIcon.png";
	public static final String TBIcommentFN = ICONDIR+"commentIcon.png";
	public static final String TBItroubleshootFN = ICONDIR+"troubleshootIcon.png";
	public static final String TBIconsultFN = ICONDIR+"ConsultIcon.png";
	public static final String TBIflagFN = ICONDIR+"flagIcon.png";
	public static final String TBIscreenAssFN = ICONDIR+"screenAssignIcon.png";
	public static final String TBIhelpFN = ICONDIR+"helpIcon3.png";
	public static final String TBIguideFN = ICONDIR+"guideIcon.png";
	
	private static int TOOLBARBUTTONSIZE = 20;

	private static final Dimension SCROLLDISPLAYTHUMBNAILSIZE = new Dimension(80,85);
//	private static final Dimension SCROLLDISPLAYTHUMBNAILSIZE = new Dimension(70,70);
	private static final Dimension SCROLLDISPLAYSIZE = new Dimension(80,18);
//	private static final Dimension WINDOWCHOOSERSIZE = new Dimension(70,18);
//	private static final Dimension WINDOWCHOOSERSIZE = new Dimension(120,18);
//	private static final Dimension WINDOWCHOOSERSIZE = new Dimension(120,18);
	public static final Color DISPLAYBACKGROUNDCOLOR = Color.BLACK;
	public static final Color GUIBACKGROUNDCOLOR = new Color(220,220,220);

	public static final int NUMMAINFRAMES = 6;
	
	public static final int MINDISPLAYFRAMETOP = 10;
	public static final int MINDISPLAYFRAMELEFT = 10;
	public static final int MINDISPLAYFRAMEBOTTOM = 10;
	public static final int MINDISPLAYFRAMERIGHT = 10;
//	public static final Color DISPLAYBACKGROUNDCOLOR = new Color(90,108,122);
//	public static final Color DISPLAYBACKGROUNDCOLOR = new Color(80,118,192);
//	public static final Color DISPLAYBACKGROUNDCOLOR = new Color(200,221,242);	//Java LIGHT BLUE
	private static final int CASEDATATABNUM = 0;
	private static final int VIEWERTABNUM = 1;
//	private static final int SERIESCHOOSERWIDTH = 100;
	
	private static final int[][] DEFAULTWINDOWDEFINITIONS = new int[][]{	{250,    45},		//width, center
																			{4000,  500},
																			{100,    30},
																			{2000, -500},
																			{440,    45},
																			{300,    45},
																			{1200, -600}};  
	private static final String[] DEFAULTWINDOWNAMES = new String[] {"Base", "Bone", "Crane", "Lung", "Mediastinum", "Pelvis", "Initial"};	
	
	
	public static final int NA_OP = -1;
	
	public static final int RESETOP = 0;
	public static final int UNDOOP = 1;
	public static final int REDOOP = 2;
	
	public static final int SELECTOP = 6;
	public static final int UNSELECTOP = 7;
	
	public static final int MOVEOP = 14;
	public static final int WINDOWOP = 15;
	public static final int ZOOMOP = 16;
	public static final int CHANGEIMAGEOP = 17;
	
	public static final int DELETEOP =21;
	public static final int CLEAROP = 22;
	public static final int CENTEROP = 23;
	public static final int FITDISPOP = 24;
	public static final int ZOOM100OP =25;
	public static final int RESETWINDOWOP =26;
	public static final int AUTOWINDOWOP =27;
	public static final int SETWINDOWOP =28;
	
	
	public static final int UNSELECTALLTOOLSOP =40;
	public static final int SELECTTOOLOP =41;
	public static final int DELETETOOLOP =42;
	public static final int MOVETOOLOP =43;
	

		
	public static final int ADD_M_POINTDENS =51;
	public static final int ADD_M_DIST =52;
//	public static final int SETWINDOWOP =28;
//	public static final int SETWINDOWOP =28;
	
	public static final int OPERATIONRELAXTIME = 3000;//1000; //milliseconds
	private DisplayModel[] startingState;
	private int lastOperation;
	private long lastOpTime;
	private boolean[] lastOpIndicator;
//	private int toolbarEdgewidth = 15;
	
	
//model
	private static Container parentContainer;
	private static CaseViewer viewer1, viewer2;		//viewer references
	private static int numViewersUp;				//number of viewers
	private static CaseDownloader caseDataDownloader;
	private int viewerNum;							//number of this viewer	
	private static ReportEditor reportEditor;
	private int displayFieldLayout;
	
	private UndoRedoModel undoRedoModel;
/*	private ImageDisplay[] displays;
	private ImageDisplay[] series1Displays;
	private ImageDisplay[] series2Displays;*/
	private DisplayImageFrame[] mainDisplayFrames;
//	private ImageDisplay[] series1Displays;
//	private ImageDisplay[] series2Displays;
	private ThumbnailImageFrame[] series1Displays;
	private ThumbnailImageFrame[] series2Displays;
	
/*	private CaseData[] loadedCases;
	private CaseData case1;
	private int studyNumCase1;
	private CaseData case2;
	private int studyNumCase2;
	private CaseData case3;
	private int studyNumCase3;
	private CaseData case4;
	private int studyNumCase4;*/
	private int toolMode;
	private int seriesMode;

	
	
//components
	
	//general
//	private JPanel[] panels; 
	
	//viewer components
//	private ToolBarWithBottomBorder toolbar;
	private JToolBar toolbar;
	private ButtonGroup displayViewButtons,actionToolButtons, selectionModeButtons;
	private ToolBarToggleButton[] viewButtons, actionButtons;
	private ToolBarToggleButton handButton, distMButton, pointDensMButton, areaDensMButton, angleMButton, cobbAngleMButton, magnGlassButton, zoomSelAreaButton,
								twoSeriesButton, selectRefLineModeButton, 	selectDisplayModeButton, selectAreaWinButton,
								showStudyParamsButton, scaleRulerButton, openEditorButton, ctWindowChooserButton;
	private SimpleToolBarButton resetButton, undoButton, redoButton, clearButton, winResetButton, fitDispButton,centerImageButton,						
						rotateButton, flipHButton, flipVButton, autoWinButton,zoom100Button, openCaseButton, addCommentButton, troubleshootButton, consultButton;
	//private JComboBox windowChooser;	
    private JPopupMenu presetWindowMenu = new JPopupMenu();
	
//	private JButton 
//	private JToolBar series1Chooser;
//	private JToolBar series2Chooser;
	private JPanel seriesChooserPane1;
	private JPanel seriesChooserPane2;
//	private JLabel series1CaseLabel;
//	private JLabel series2CaseLabel;
	private JComboBox series1Studychooser;
	private JComboBox series2Studychooser;
	
	private JScrollPane series1Panel;
	private JScrollPane series2Panel;
	private JPanel infopanel;
	private JPanel displayPanel;
	private JPanel displayField;

	private JPanel messagePanel;
	private JLabel permanentMessagesLabel,temporaryMessagesLabel;
	private TDSNavigationTree caseTree;
	
	
	
	private CaseViewer(int viewerNumIn, CaseDownloader caseDataDownloaderIn)//, String[] menuNames,Action[][] menuActions,int[][] separators,ChangeListener tabChangeListener, BufferedImage icon, Series caseToLoad)
	{
		super();
                
		
		Action reloadResetAct, undoAct, redoAct, flagAct,troubleshootAct, openEditorAct, openCaseAct, consultAct, dropAct, closeAct,screenAssignmentAct,helpAct,guideAct;
		Action[][] menuActs;
		String[] menuNames;
		int[][] separators;	
		menuNames=new String[]{"Case","Displays","Options","Help"/*, "Worklist","Admin","Connection","Options"*/};
//		loadCaseAct = new AbstractAction("Load case for reference") {public void actionPerformed(ActionEvent ae){/*newGame();*/}}; 
		reloadResetAct = new AbstractAction(" Reload/Reset All") {public void actionPerformed(ActionEvent ae){/*exit();*/}}; 
		undoAct = new AbstractAction(" Undo",GUIUtility.loadIcon(CaseViewer.TBIundoFN, new Color(0,255,0))) {public void actionPerformed(ActionEvent ae){/*exit();*/}}; 
		redoAct = new AbstractAction(" Redo",GUIUtility.loadIcon(CaseViewer.TBIredoFN, new Color(0,255,0))) {public void actionPerformed(ActionEvent ae){/*exit();*/}}; 
		flagAct = new AbstractAction(" Flag Case",GUIUtility.loadIcon(CaseViewer.TBIflagFN , new Color(0,255,0))) {public void actionPerformed(ActionEvent ae){/*exit();*/}}; 
		troubleshootAct = new AbstractAction(" Troubleshoot",GUIUtility.loadIcon(CaseViewer.TBItroubleshootFN, new Color(0,255,0))) {public void actionPerformed(ActionEvent ae){/*exit();*/}}; 
		openEditorAct = new AbstractAction(" Open Report Editor  ",GUIUtility.loadIcon(CaseViewer.TBIopenEditorFN, new Color(0,255,0))) {public void actionPerformed(ActionEvent ae){/*exit();*/}}; 
		openCaseAct  = new AbstractAction(" Open Another Case for Reference  ",GUIUtility.loadIcon(CaseViewer.TBIopenCaseFN, new Color(0,255,0))) {public void actionPerformed(ActionEvent ae){/*exit();*/}}; 
		consultAct = new AbstractAction(" Request Consultation  ",GUIUtility.loadIcon(CaseViewer.TBIconsultFN, new Color(0,255,0))) {public void actionPerformed(ActionEvent ae){/*exit();*/}}; 
//		dropAct = new AbstractAction("Drop Case") {public void actionPerformed(ActionEvent ae){/*exit();*/}}; 
		closeAct = new AbstractAction(" Close") {public void actionPerformed(ActionEvent ae){/*exit();*/}};

		screenAssignmentAct = new AbstractAction(" Screen Assignment",GUIUtility.loadIcon(CaseViewer.TBIscreenAssFN, new Color(0,255,0))) {public void actionPerformed(ActionEvent ae){/*exit();*/}}; 
		
		helpAct = new AbstractAction(" User Aid",GUIUtility.loadIcon(CaseViewer.TBIhelpFN, new Color(0,255,0))) {public void actionPerformed(ActionEvent ae){/*exit();*/}}; 
		guideAct = new AbstractAction(" Guide and Tips  ",GUIUtility.loadIcon(CaseViewer.TBIguideFN, new Color(0,255,0))) {public void actionPerformed(ActionEvent ae){/*exit();*/}}; 

		
		menuActs=new Action[][]{
						new Action[]{openEditorAct, openCaseAct, consultAct, flagAct,troubleshootAct,closeAct},
						new Action[]{undoAct,redoAct,reloadResetAct},
						new Action[]{screenAssignmentAct},
						new Action[]{helpAct,guideAct}/*,
						new Action[]{},
						new Action[]{}*/
					   };
		separators=new int[][]{ new int[]{1,3},
								new int[]{1,5} };
		
		super.setupFrameWithMenu(FRAMETITLEPART1+viewerNumIn+FRAMETITLEPART2+"", SIZEX, SIZEY,
				menuNames, menuActs, separators, true);
		
		
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent arg0) 
				{	if(CaseViewer.getViewer(1).closingViewer())	closeViewer(1);		}
		} );
		
		super.menuBar.setBackground(GUIBACKGROUNDCOLOR);
		

		String mainCaseName = findMainCaseName();		
		this.setName(mainCaseName);
                ITSEventManager.getEventListenerList().add(CloseFrameEventListener.class, this);


		
	//	BufferedImage icon = ; //TODO
	//	setIconImage(null);
		
        try {
    	    // Set System L&F
            UIManager.setLookAndFeel(new ITSLookAndFeel());
                //UIManager.getSystemLookAndFeelClassName());
        } 
        catch (UnsupportedLookAndFeelException e) {
           // handle exception
        }
/*        catch (ClassNotFoundException e) {
           // handle exception
        }
        catch (InstantiationException e) {
           // handle exception
        }
        catch (IllegalAccessException e) {
           // handle exception
        }*/

		
/*		try {UIManager.setLookAndFeel(new NimbusLookAndFeel());}
		catch(Exception e1){ 
			try {UIManager.setLookAndFeel(new MetalLookAndFeel());}
				catch(Exception e2){ }					
		};	*/	

		//try {UIManager.setLookAndFeel(new MetalLookAndFeel());}
		//catch(Exception e){		};
		
		
		if (viewerNumIn==1)viewer1=this;
		else if (viewerNumIn==2) viewer2=this;		
		
		
		numViewersUp++;
		
		caseDataDownloader = caseDataDownloaderIn;
		viewerNum = viewerNumIn;


	//	iconTDS = icon;
		toolMode=0;
		seriesMode=1;
		displayFieldLayout = 6;
//		displayFieldMode = 2;
		
		mainDisplayFrames = new DisplayImageFrame[NUMMAINFRAMES];
		for (int i=0;i<mainDisplayFrames.length;i++)
		{
//			mainDisplayFrames[i] = new ImageFrame(MINDISPLAYFRAMETOP,MINDISPLAYFRAMELEFT,MINDISPLAYFRAMEBOTTOM,MINDISPLAYFRAMERIGHT, DISPLAYBACKGROUNDCOLOR);	
			mainDisplayFrames[i] = new DisplayImageFrame(this,i,0,0,0,0, DISPLAYBACKGROUNDCOLOR);
			mainDisplayFrames[i].setTransferHandler(new TransferHandler("seriesInstanceUid"));
		}
/*		displays[0].select();
		displays[1].select();
		displays[5].select();
		displays[0].refSelect();
		displays[4].refSelect();*/
		
	
		//TODO load series displays and thumbnails
		
//		series1Displays = new ImageDisplay[15];
		series1Displays = new ThumbnailImageFrame[15];
/*		for (int i=0;i<series1Displays.length;i++)
//			series1Displays[i] = new ImageDisplay(null,SCROLLDISPLAYTHUMBNAILSIZE, true, DISPLAYBACKGROUNDCOLOR);	
			series1Displays[i] = new ThumbnailImageFrame(this,0,0,0,0, SCROLLDISPLAYTHUMBNAILSIZE, DISPLAYBACKGROUNDCOLOR);	*/
		
/*		series1Displays[0].select();
		series1Displays[1].select();
		series1Displays[2].select();
		series1Displays[7].select();
		series1Displays[1].refSelect();
		series1Displays[5].refSelect();*/
		

		//	controllerLink = controllerDef;
		
		
		SwingUtilities.updateComponentTreeUI(getRootPane());
	
//		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
//		int mx = ge.getCenterPoint().x, my = ge.getCenterPoint().y;		
//		this.setLocation(mx-SIZEX/2, my-SIZEY/2);

		this.setLocation(getScreenInfo().getViewScreenCenter().x*2 - SIZEX, getScreenInfo().getViewScreenCenter().y*0 /*+ SIZEY*/);
		this.setIgnoreRepaint(false);
		
/*		this.addWindowFocusListener(new WindowFocusListener(){	
				public void windowGainedFocus(WindowEvent arg0) {
					validate();
					repaint();
				}
		
				public void windowLostFocus(WindowEvent arg0) {					
				}	
		});	*/
		
		
		
		this.addWindowStateListener(new WindowStateListener(){
			@Override
			public void windowStateChanged(WindowEvent arg0) {
				validate();
				repaint();			
				createBufferStrategy(4);
			}
			});

		/*
//		if (fullScreenMode)
		{
			this.setLocation(RCController.getScreenInfo().getViewScreenBounds().x,RCController.getScreenInfo().getViewScreenBounds().y);
			this.setSize(RCController.getScreenInfo().getViewScreenBounds().width,RCController.getScreenInfo().getViewScreenBounds().height);
		}
	*/	
		
		buildViewerPanel();
		
		this.setFocusable(true);
		
		this.addKeyListener(new KeyListener(){
			@Override
			public void keyPressed(KeyEvent arg0) {}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				if(arg0.getKeyCode() == KeyEvent.VK_DELETE)
				{
					//clear all selected displays
					//imageDisplay.getModel().detachSeriesFromdisplay();
					if(arg0.isShiftDown())
					{
						System.out.println("\n\n*** SHIFT+DEL PRESSED!!!");
//						performOperationOnSelected(DELETEOP,0,0);
						performOperationOnSelected(DELETEOP,null);
						
					}
					else
					{
						System.out.println("\n\n*** DEL PRESSED!!!");
						int[] selectedTool = getSelectedToolIndices(); //{displayNum,toolType,toolNum}
						if(selectedTool[0]!=-1)  //del selected tool only
							performOperationOnOne(DELETETOOLOP, new int[]{selectedTool[1], selectedTool[2]}, mainDisplayFrames[selectedTool[0]] ); 
//							mainDisplayFrames[selectedTool[0]].deleteSelectedToolOfActImage(selectedTool[1],selectedTool[2]);
						else	//clear act display
							performOperationOnSelected(CLEAROP,null);						
					}
				}
			}

			@Override
			public void keyTyped(KeyEvent arg0) {}
		});
		
				
		
		loadMainCase();
		//TODO load Case
		
		
		undoRedoModel = new UndoRedoModel(mainDisplayFrames.length);

		lastOperation = NA_OP;
		lastOpTime = System.currentTimeMillis();
		lastOpIndicator = new boolean[NUMMAINFRAMES];
		clearIndicator();
				
	//	testUndoRedoModel = new UndoRedoModel2();
		
		
		
		
	}
	
	
	int[] getSelectedToolIndices(){		//int[]{displayNum,toolType,toolNum}
		int displayNum,toolType,toolNum;
		
		int[] displayAnswer;
		    
		for(int i=0;i<mainDisplayFrames.length ; i++)
		{
			displayAnswer = mainDisplayFrames[i].checkToolSelection();
			if(displayAnswer[0]!=-1)
			{
				displayNum = i;
				toolType = displayAnswer[0];
				toolNum = displayAnswer[1];
				return new int[] {displayNum,toolType,toolNum};
			}
		}		
		return new int[] {-1 , -1, -1};
	}
	
	
	
/*	private void loadCase(Series testSeries){
		
		mainDisplayFrames[0].assignSeriesToDisplay(testSeries,true);
		mainDisplayFrames[1].assignSeriesToDisplay(testSeries,true);
		mainDisplayFrames[2].assignSeriesToDisplay(testSeries,true);
				
	}*/
	
	
	
	
	private void buildViewerPanel(){
//		JPanel viewerPanel = getBackPane();	
//		JPanel viewerPanel = (JPanel)getContentPane();
//		viewerPanel.setFocusable(true);
//		viewerPanel.setBackground(GUIBACKGROUNDCOLOR);
		
		
/*		getContentPane().setLayout(new BorderLayout());

		caseTree = new TDSNavigationTree();
		//caseTree.setBackground(DISPLAYBACKGROUNDCOLOR);
		caseTree.setBackground(Color.RED);
		//caseTree.setAlignmentX(LEFT_ALIGNMENT);
		//caseTree.setMinimumSize(new Dimension(SERIESTREEWIDTH,30));		
		getContentPane().add(caseTree,BorderLayout.WEST);
		
//		getContentPane().add(seriesChooserPane1,BorderLayout.WEST);
		TestLayer testLayer = new TestLayer();
		testLayer.setTransferHandler(new TransferHandler("seriesTest"));
		testLayer.setBackground(Color.BLUE);
		testLayer.setOpaque(true);
		testLayer.setSize(150,150);
		testLayer.setPreferredSize(new Dimension(150,150));
		
		
		TextField testTextField = new TextField();
		testTextField.setBounds(0, 0, 150, 70);
		
//		testLayer.add(testTextField,BorderLayout.EAST);
		testLayer.add(testTextField,JLayeredPane.DEFAULT_LAYER);
		
		
		
		getContentPane().add(testLayer,BorderLayout.CENTER);*/
		

		
		
		
		
		
		
		
		
		
		//Setting up viewer Panel components	
		
		//bottom message panel
		messagePanel = new JPanel();
		messagePanel.setBackground(GUIBACKGROUNDCOLOR);
//		messagePanel.setBackground(Color.GREEN);
		
		permanentMessagesLabel = new JLabel("Test text - permanent");
		permanentMessagesLabel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(new Color(175,175,175), 1),
				BorderFactory.createEmptyBorder(3,10,3,10)));
		permanentMessagesLabel.setBackground(GUIBACKGROUNDCOLOR);
		permanentMessagesLabel.setOpaque(true);
//		permanentMessagesLabel.setHorizontalTextPosition(JLabel.LEFT);
		
		temporaryMessagesLabel = new JLabel("Test text - temporary",SwingConstants.RIGHT);
		temporaryMessagesLabel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(new Color(175,175,175), 1),
				BorderFactory.createEmptyBorder(3,10,3,10)));
//		temporaryMessagesLabel.setBorder(BorderFactory.createLineBorder(new Color(175,175,175), 1));
		temporaryMessagesLabel.setBackground(GUIBACKGROUNDCOLOR);
		temporaryMessagesLabel.setOpaque(true);
//		temporaryMessagesLabel.setHorizontalTextPosition(JLabel.RIGHT);

		messagePanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc=new GridBagConstraints();		
		gbc.fill=GridBagConstraints.BOTH;
		gbc.gridx=0;
		gbc.weightx=1;
		gbc.insets = new Insets(0,0,0,2);
		messagePanel.add(permanentMessagesLabel,gbc);
		gbc.gridx=1;
		gbc.insets = new Insets(0,2,0,0);
		messagePanel.add(temporaryMessagesLabel,gbc);	
		
		
		//toolbar
		toolbar = new ToolBarWithBottomBorder(); 
//		toolbar = new JToolBar(); 
		setupToolbar(); 
		
		//populating displayPanel	
		populateDisplayPanel();		
		
		//populating SeriesScrollPanes
		populateSeriesScrollPanes();		
		
		//viewer panel assembly
//		viewerPanel.setLayout(new GridBagLayout());
		getContentPane().setLayout(new GridBagLayout());
		
		
		gbc.insets = new Insets(0,0,0,0);
		gbc.fill=GridBagConstraints.BOTH;
		gbc.gridx=0;
		gbc.gridy=0;
		gbc.weighty=0;
		gbc.weightx=1;
		gbc.gridwidth = 2;
//		viewerPanel.add(toolbar,gbc);
		getContentPane().add(toolbar,gbc);   //TODO RE
		
		gbc.gridx=0;
		gbc.gridy=2;
		gbc.weighty=1;
		gbc.weightx=1;
		gbc.gridwidth = 2;
		gbc.insets = new Insets(5,0,0,5);
//		viewerPanel.add(displayPanel,gbc);		
		getContentPane().add(displayPanel,gbc); //TODO RE
		
		gbc.gridx=0;
		gbc.gridy=3;
		gbc.weighty=0;
		gbc.weightx=1;
		gbc.gridwidth = 2;
		gbc.insets = new Insets(5,7,7,8);
//		viewerPanel.add(messagePanel,gbc);
		getContentPane().add(messagePanel,gbc); //TODO RE
		
//		viewerPanel.setOpaque(true);		
//		getContentPane().setOpaque(true);
		
/*		System.out.println("viewerPanelHeight0: "+viewerPanel.getBounds().height);
		System.out.println("viewerPanelHeight00: "+viewerPanel.getHeight());
		System.out.println("viewerPanelHeight1: "+viewerPanel.getSize().height);
		System.out.println("tabPanelHeight: "+getBackPane().getSize().height);
		System.out.println("displayPanelHeight: "+displayPanel.getSize().height);*/
		//pack();
	}
	
	
		
	
	private void populateDisplayPanel(){
				

		//setting up the displayPanel
		displayPanel = new JPanel();
		displayPanel.setBackground(GUIBACKGROUNDCOLOR);
		
		displayField = new JPanel();
		displayField.setOpaque(true);
		displayField.setBackground(DISPLAYBACKGROUNDCOLOR);
		displayField.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createEmptyBorder(0,0,0,0),
				BorderFactory.createCompoundBorder(
						new BevelBorder(BevelBorder.LOWERED),
						BorderFactory.createEmptyBorder(3,3,3,3))));

		//populating displayField
		setupDisplayField(); 
		
		
		//setting up series panel
		seriesChooserPane1 = new JPanel();
		seriesChooserPane1.setBackground(GUIBACKGROUNDCOLOR);
		series1Panel = new JScrollPane();
		series1Panel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		//if()
			series1Panel.getVerticalScrollBar().setBackground(new Color(140,140,140));

		GridBagConstraints gbc=new GridBagConstraints();

		//populating series panel
		seriesChooserPane1.setLayout(new GridBagLayout());		
		gbc.fill=GridBagConstraints.BOTH;
//		gbc.gridy=2;
		gbc.gridx=0;
		gbc.gridy=0;
		gbc.weightx=1;
		gbc.weighty=1;
		gbc.insets = new Insets(0,0,0,0);
		seriesChooserPane1.add(series1Panel,gbc);		

		
		//populate display panel
		displayPanel.setLayout(new GridBagLayout());
		gbc.fill=GridBagConstraints.BOTH;
		gbc.gridx=0;
		gbc.gridy=0;
		gbc.gridheight=2;
		gbc.weighty=1;
		gbc.weightx=0;		
		if(seriesMode == 1)
			gbc.insets = new Insets(0,7,2,2);
		else
			gbc.insets = new Insets(5,4,2,0);		
		displayPanel.add(seriesChooserPane1,gbc);
		
		gbc.gridx=2;
		gbc.weightx=1;
		gbc.gridheight=1;
		gbc.insets = new Insets(0,2,3,3);
		displayPanel.add(displayField,gbc);
/*		TestLayer testLayer = new TestLayer();
		testLayer.setTransferHandler(new TransferHandler("seriesTest"));
		testLayer.setBackground(Color.BLUE);
		testLayer.setOpaque(true);
		displayPanel.add(testLayer,gbc);*/		
		
		
/*		displayPanel.setLayout(new BorderLayout());
		displayPanel.add(seriesChooserPane1,BorderLayout.WEST);
		TestLayer testLayer = new TestLayer();
		testLayer.setTransferHandler(new TransferHandler("seriesTest"));
		testLayer.setBackground(Color.BLUE);
		testLayer.setOpaque(true);
		displayPanel.add(testLayer,BorderLayout.CENTER);	*/	
	}
	
	
	
	
	private void setupToolbar( ){
	    toolbar.setFloatable(false);
	    toolbar.setBackground(GUIBACKGROUNDCOLOR);    
		boolean smoothButtons = true;
			 
		GridBagConstraints gbc=new GridBagConstraints();
		toolbar.setLayout(new GridBagLayout());
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridy=0;
		gbc.weighty=0;
		gbc.weightx=0;
		

		
		
		Icon sepIcon = GUIUtility.loadIcon(TBIsepFN, new Color(0,255,0));
		Icon fillerIcon = GUIUtility.loadIcon(TBIfillerFN, new Color(0,255,0));


		//for sizing testing
//		JLabel heightener = new JLabel(sepIcon);
//		Dimension size = new Dimension(20,60);
//		heightener.setSize(size);
//		heightener.setPreferredSize(size);
//		heightener.setMinimumSize(size);
//		heightener.setMaximumSize(size);
//		toolbar.add(heightener);
//	    toolbar.addSeparator();	

		
		
		//View toolbar
		Icon[] icons = new Icon[]{
		GUIUtility.loadIcon(TBI0FN, new Color(0,255,0)),
		GUIUtility.loadIcon(TBI1FN, new Color(0,255,0)),
		GUIUtility.loadIcon(TBI2FN, new Color(0,255,0)),
		GUIUtility.loadIcon(TBI3FN, new Color(0,255,0)),
		GUIUtility.loadIcon(TBI4FN, new Color(0,255,0)),
		GUIUtility.loadIcon(TBI5FN, new Color(0,255,0)),
		GUIUtility.loadIcon(TBI6FN, new Color(0,255,0)),
		GUIUtility.loadIcon(TBI7FN, new Color(0,255,0))/*,
		GUIUtility.loadIcon(TBI8FN, new Color(0,255,0))/*, 
		GUIUtility.loadIcon(TBI9FN, new Color(0,255,0)), 
		GUIUtility.loadIcon(TBI10FN, new Color(0,255,0)) */
		};
		
		viewButtons = new ToolBarToggleButton[11];
		
		String[] commands = new String[]{"Empty Display", "Single Display", "2x1 Display", "1x2 Display", "1+2 Display (horizontal split)", "1+2 Display (vertical split)", "2x2 Display", "2x3 Display"/*, "3x2 Display"/*, "3x3 Display", "4x4 Display"*/};
		String[] tooltips = new String[]{"Empty Display", "Single Display", "2x1 Display", "1x2 Display", "1+2 Display (horizontal split)", "1+2 Display (vertical split)", "2x2 Display", "2x3 Display"/*, "3x2 Display"/*, "3x3 Display", "4x4 Display"*/};
		
		displayViewButtons = new ButtonGroup(); 
		ActionListener  viewButtonsActionListener =  new ActionListener() 
												{        
													public void actionPerformed(ActionEvent e)
											        {
														for( int i=0; i<displayViewButtons.getButtonCount(); i++ )
															if (displayViewButtons.getSelection() == viewButtons[i+1].getModel())
															{
																setDisplayFieldLayout(i+1);
																System.out.println("You clicked Button VIEW "+(i+1));//+"(command: "+commands[i+1]+")");
															}	
											            System.out.println("You clicked a button");
											        }
											    };

	    toolbar.add(new ToolBarSeparator(sepIcon));
											    

	    int ct = 1;
		for (int i = 0, n = icons.length; i < n; i++, ct++) 
	    {
			viewButtons[i] = new ToolBarToggleButton(icons[i], commands[i], tooltips[i], viewButtonsActionListener, smoothButtons);
			System.out.println("i = "+i);
			if(i>0)
			{
				displayViewButtons.add(viewButtons[i]);	        
				toolbar.add(viewButtons[i]);
			}
	    }



		
		Icon  resetIcon = GUIUtility.loadIcon(TBIresetFN, new Color(0,255,0));
		Icon  undoIcon = GUIUtility.loadIcon(TBIundoFN, new Color(0,255,0));
		Icon  redoIcon = GUIUtility.loadIcon(TBIredoFN, new Color(0,255,0));
		Icon  clearIcon = GUIUtility.loadIcon(TBIclearFN, new Color(0,255,0));
		Icon  fitDispIcon = GUIUtility.loadIcon(TBIfitDispFN, new Color(0,255,0));
		Icon  centerImageIcon = GUIUtility.loadIcon(TBIcenterImageFN, new Color(0,255,0));
		Icon  resetWinIcon = GUIUtility.loadIcon(TBIresetWindowFN, new Color(0,255,0));
//		Icon  rotateIcon = GUIUtility.loadIcon(TBIrotateFN, new Color(0,255,0));
//		Icon  flipHIcon = GUIUtility.loadIcon(TBIflipHorFN, new Color(0,255,0));
//		Icon  flipVIcon = GUIUtility.loadIcon(TBIflipVerFN, new Color(0,255,0));
		Icon  autoWinIcon = GUIUtility.loadIcon(TBIautoWindowFN, new Color(0,255,0));
		Icon  ctWinIcon = GUIUtility.loadIcon(TBIctWindowFN, new Color(0,255,0));
		Icon  selectAreaWinIcon = GUIUtility.loadIcon(TBIselectAreaWindowFN, new Color(0,255,0));
		Icon  zoom100Icon = GUIUtility.loadIcon(TBIzoom100FN, new Color(0,255,0));
		Icon  handIcon = GUIUtility.loadIcon(TBIhandFN, new Color(0,255,0));
//		ImageIcon  handIcon = new ImageIcon(TBIhandFN);
//		handIcon.		
		Icon  distIcon = GUIUtility.loadIcon(TBIdistMeaFN, new Color(0,255,0));
		Icon  pointDensIcon = GUIUtility.loadIcon(TBIpointMeaFN, new Color(0,255,0));
		Icon  areaDensIcon = GUIUtility.loadIcon(TBIareaMeaFN, new Color(0,255,0));
		Icon  angleIcon = GUIUtility.loadIcon(TBIangleMeaFN, new Color(0,255,0));
		Icon  cobbAngleIcon = GUIUtility.loadIcon(TBIcobbAngleMeaFN, new Color(0,255,0));		
		//Icon  magnGlassIcon = GUIUtility.loadIcon(TBImagnGlassFN, new Color(0,255,0));
		Icon  zoomSelIcon = GUIUtility.loadIcon(TBIzoomSelectedFN, new Color(0,255,0));		
		Icon  showRefLineIcon = GUIUtility.loadIcon(TBIshowRefLineFN, new Color(0,255,0));		
//		Icon  selectDisplayModeIcon = GUIUtility.loadIcon(TBIselectDisplayModeFN, new Color(0,255,0));		
		Icon  showStudyParamsIcon = GUIUtility.loadIcon(TBIshowStudyParamsFN, new Color(0,255,0));		
		Icon  scaleRulerIcon = GUIUtility.loadIcon(TBIscaleRulerFN, new Color(0,255,0));		
		Icon  twoSeriesIcon = GUIUtility.loadIcon(TBI2seriesFN, new Color(0,255,0));
		Icon  oneSeriesIcon = GUIUtility.loadIcon(TBI1seriesFN, new Color(0,255,0));
		Icon  openCaseIcon = GUIUtility.loadIcon(TBIopenCaseFN, new Color(0,255,0));
		Icon  openEditorIcon = GUIUtility.loadIcon(TBIopenEditorFN, new Color(0,255,0));
		Icon  commentIcon = GUIUtility.loadIcon(TBIcommentFN, new Color(0,255,0));
		Icon  troubleshootIcon = GUIUtility.loadIcon(TBItroubleshootFN, new Color(0,255,0));
		Icon  consultIcon = GUIUtility.loadIcon(TBIconsultFN, new Color(0,255,0));
		
				
		undoButton = new SimpleToolBarButton(undoIcon, "Undo", "Undo", new UndoButtonListener(), smoothButtons);  
		redoButton = new SimpleToolBarButton(redoIcon, "Redo", "Redo", new RedoButtonListener(), smoothButtons);        
		resetButton = new SimpleToolBarButton(resetIcon, "Reset", "Reset: Reset selected displays to starting default",  new ResetButtonListener(), smoothButtons); 
		centerImageButton = new SimpleToolBarButton(centerImageIcon, "Center Image", "Center Image: align the center of the image with the center of the display" , new CenterButtonListener(), smoothButtons);  
		clearButton = new SimpleToolBarButton(clearIcon, "Clear", "Clear", new ClearButtonListener(), smoothButtons);   
		fitDispButton = new SimpleToolBarButton(fitDispIcon, "Fit display", "Fit Display: Fit image to display" , new FitDisplayButtonListener(), smoothButtons);  
	    zoom100Button = new SimpleToolBarButton(zoom100Icon, "Zoom to 100%", "Zoom 100%: Zoom to 100% image size", new Zoom100ButtonListener(), smoothButtons);
	    
		winResetButton = new SimpleToolBarButton(resetWinIcon, "Window Reset", "Reset Window: Reset window of selected displays to default",  new ResetWindowButtonListener(), smoothButtons);
			//TODO popup menu for given preset windows
	    autoWinButton = new SimpleToolBarButton(autoWinIcon, "Set Window Automatically", "Auto Window: Set window of selected displays automatically based on the whole of the assigned series", new AutoWindowButtonListener(), smoothButtons);
	    	//TODO popup menu for auto windows
	    ctWindowChooserButton = new ToolBarToggleButton(ctWinIcon, "Preset CT Window", "Choose from a set of preset CT windows to be applied (will only effect displays with CT series)", new PresetWindowButtonListener(), smoothButtons);
	    
	    
		handButton = new ToolBarToggleButton(handIcon, DisplayImageFrame.HANDCOMMANDSTRING, "Hand tool: move images", null, smoothButtons);
		zoomSelAreaButton = new ToolBarToggleButton(zoomSelIcon, "Zoom Selected Area", "Magnifying glass tool: set a part of the display for zoom",  null, smoothButtons);
	    selectAreaWinButton = new ToolBarToggleButton(selectAreaWinIcon, "Set Auto Window For Selected Area", "Auto Selection Window: set auto window for selected area)", null, smoothButtons);
		pointDensMButton = new ToolBarToggleButton(pointDensIcon, MPointDens.COMMANDSTRING, "Point density measurement tool: measure point density",  null, smoothButtons);
		areaDensMButton = new ToolBarToggleButton(areaDensIcon, "Area Density Measurement", "Area density measurement tool: measure the mean and std of density in the selected area", null, smoothButtons);
		distMButton = new ToolBarToggleButton(distIcon, MDistance.COMMANDSTRING, "Distance measurement tool: measure distance", null, smoothButtons);
		angleMButton = new ToolBarToggleButton(angleIcon, "Angle Measurement", "Angle measurement tool: measure angle in degrees", null, smoothButtons);
		cobbAngleMButton = new ToolBarToggleButton(cobbAngleIcon, "Cobb Angle Measurement", "Cobb-angle measurement tool: measure angle between two arbitrary lines",  null, smoothButtons);

		selectRefLineModeButton = new ToolBarToggleButton(showRefLineIcon, "Select displays for reference line", "Reference line selection mode: select displays for reference",   null, smoothButtons);
//	    selectDisplayModeButton = new ToolBarToggleButton(selectDisplayModeIcon, "Select displays", "Normal selection mode: select displays",  null, smoothButtons);	    
		showStudyParamsButton = new ToolBarToggleButton(showStudyParamsIcon, "Show Study Parameters", "Details: show study parameters and data",  null, smoothButtons);
		scaleRulerButton = new ToolBarToggleButton(scaleRulerIcon, "Show Scale ruler", "Ruler",  null, smoothButtons);
//		JToggleButton handButton;
//		handButton = new JToggleButton(handIcon);

/*		twoSeriesButton = new ToolBarToggleButton(oneSeriesIcon, "Switch between single and double series modes", "Switch between single or double study modes",  null, smoothButtons);		
		twoSeriesButton.setSelectedIcon(twoSeriesIcon);
		twoSeriesButton.addChangeListener(new SeriesModeChangeListener());*/
		openCaseButton = new SimpleToolBarButton(openCaseIcon, "Import Reference case", "Import Another Case for Comparison",  null, smoothButtons);		
		openEditorButton = new ToolBarToggleButton(openEditorIcon, "Open Report Editor", "Open Report Editor",  null, smoothButtons);		
		//openEditorButton.addChangeListener(new OpenEditorListener());
                openEditorButton.addActionListener(new ReportActionListener(this));
   		addCommentButton = new SimpleToolBarButton(commentIcon, "Add Comment", "Add Comment: add a comment to the current/finalized version of the report",  null, smoothButtons);
   				addCommentButton.addActionListener(new CommentActionListener(this));
		troubleshootButton = new SimpleToolBarButton(troubleshootIcon, "Troubleshoot", "Troubleshoot: press for any professional or management problem",  null, smoothButtons);
		consultButton = new SimpleToolBarButton(consultIcon, "Consult", "Request Consultation: send a system request for consultation",  null, smoothButtons);
                consultButton.addActionListener(new ConsultActionListener(this));
                

		actionButtons = new ToolBarToggleButton[]{handButton,zoomSelAreaButton,pointDensMButton,areaDensMButton,distMButton,angleMButton,cobbAngleMButton};
		actionToolButtons = new ButtonGroup(); 
		
		actionToolButtons.add(handButton);	        
		actionToolButtons.add(selectAreaWinButton);
		actionToolButtons.add(zoomSelAreaButton);	
		actionToolButtons.add(selectAreaWinButton);	
		actionToolButtons.add(pointDensMButton);	        
		actionToolButtons.add(areaDensMButton);	        
		actionToolButtons.add(distMButton);	        
		actionToolButtons.add(angleMButton);	        
		actionToolButtons.add(cobbAngleMButton);
		
		handButton.addActionListener(new ToolChangeListener());	        
		selectAreaWinButton.addActionListener(new ToolChangeListener());	   
		zoomSelAreaButton.addActionListener(new ToolChangeListener());	   
		selectAreaWinButton.addActionListener(new ToolChangeListener());	   
		pointDensMButton.addActionListener(new ToolChangeListener());	   
		areaDensMButton.addActionListener(new ToolChangeListener());	   	        
		distMButton.addActionListener(new ToolChangeListener());	   	        
		angleMButton.addActionListener(new ToolChangeListener());	   	        
		cobbAngleMButton.addActionListener(new ToolChangeListener());	   
		
		consultButton.addActionListener(new ActionListener(){	//TODO delete this!!!
			@Override
			public void actionPerformed(ActionEvent arg0) {
				printToolLists();
			}});
		
		troubleshootButton.addActionListener(new TroubleShootActionListener(this));

		
//		actionToolButtons.add(selectRefLineModeButton);
//		selectionModeButtons = new ButtonGroup(); 		
//		selectionModeButtons.add(selectRefLineModeButton);
//	    selectDisplayModeButton.setSelected(true);
//		selectionModeButtons.add(selectDisplayModeButton);
		
		presetWindowMenu.setFont(new Font("Arial",Font.PLAIN,12));

		for(int i=0; i<DEFAULTWINDOWNAMES.length; i++)
		{
			
			Action presetWindowAction = new WinChooserAction(DEFAULTWINDOWNAMES[i],i) 
				{
					public void actionPerformed(ActionEvent ae)
						{
							//	untoggleButton
							ctWindowChooserButton.setSelected(false);
							//	setWindow
				            System.out.println("You selected to apply PRESET WINDOW '"+DEFAULTWINDOWNAMES[menuNum]+"'");
							performOperationOnSelected(SETWINDOWOP, new int[]{ DEFAULTWINDOWDEFINITIONS[menuNum][0], DEFAULTWINDOWDEFINITIONS[menuNum][1]} );
							
				            lastOperation = SETWINDOWOP;
				    		lastOpTime = System.currentTimeMillis();
				    		clearIndicator();
							//	hide menu						
						}
				};
			presetWindowMenu.add(presetWindowAction);
			presetWindowMenu.getComponents()[i].setFont(new Font("Arial",Font.PLAIN,12));
			//getSubElements()[i].setFont(new Font("Arial",Font.PLAIN,12));
		}
		


		presetWindowMenu.addPopupMenuListener(new PopupMenuListener(){

			@Override
			public void popupMenuCanceled(PopupMenuEvent arg0) {
//				System.out.println("\n\n\n\n\n\n***\nMENU CANCELLED\n\n***\n\n\n\n\n\n");
				ctWindowChooserButton.setSelected(false);
			}

			@Override
			public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) {	}

			@Override
			public void popupMenuWillBecomeVisible(PopupMenuEvent arg0) {	}});
		
		
//		gbc.gridx=ct+1;
	    toolbar.addSeparator();	
//	    gbc.gridx=ct+2;
		toolbar.add(new ToolBarSeparator(sepIcon));

		//toolbar.add(new JLabel("Reset"));	
//	    toolbar.addSeparator();	
		
//		gbc.gridx=ct+3;

		toolbar.add(resetButton); 
	    toolbar.add(clearButton);    		
	    toolbar.add(undoButton);
	    toolbar.add(redoButton);
	    
	    toolbar.addSeparator();		
		toolbar.add(new ToolBarSeparator(sepIcon));
		
	    toolbar.add(centerImageButton);	
	    toolbar.add(fitDispButton);	
	    toolbar.add(zoom100Button);
	    toolbar.add(winResetButton);
	    toolbar.add(autoWinButton);
			    
//	    toolbar.addSeparator();	 

//		toolbar.add(new JLabel("Display"));
//	    toolbar.addSeparator();	
		
/*		rotateButton = new ToolBarButton(rotateIcon, "Rotate -90 degrees",  null, smoothButtons);
	    toolbar.add(rotateButton);		
		flipHButton = new ToolBarButton(flipHIcon, "Flip Horizontally",  null, smoothButtons);
	    toolbar.add(flipHButton);
		flipVButton = new ToolBarButton(flipVIcon, "Flip Vertically",  null, smoothButtons);
	    toolbar.add(flipVButton);		*/
//	    toolbar.addSeparator();	
	    
	    
/*	    windowChooser = new JComboBox();
	    windowChooser.setMaximumSize(WINDOWCHOOSERSIZE);
	    windowChooser.setMinimumSize(WINDOWCHOOSERSIZE);
	    windowChooser.setSize(WINDOWCHOOSERSIZE);
	    windowChooser.setPreferredSize(WINDOWCHOOSERSIZE);
	    windowChooser.setToolTipText("Preset Windoews: choose a preset window for the selected displays");
	    windowChooser.setFont(new Font("Arial",Font.PLAIN,12));
	    windowChooser.addItem("(choose window)");
	    for(int i=0;i<DEFAULTWINDOWNAMES.length;i++)
	    {
//	    	String listText = DEFAULTWINDOWNAMES[i]+" - (c:"+DEFAULTWINDOWDEFINITIONS[i][1]+",w:"+DEFAULTWINDOWDEFINITIONS[i][0]+")";
	    	String listText = DEFAULTWINDOWNAMES[i];
	    	windowChooser.addItem(listText);
	    }toolbar.add(windowChooser); //TODO   */


	    
	    
//	    windowChooserButton.setFont(new Font("Arial",Font.PLAIN,12));
	    
//	    toolbar.addSeparator();	 
	    toolbar.add(ctWindowChooserButton); 
//	    toolbar.addSeparator();	
	    
//		toolbar.addSeparator();	         
	    toolbar.addSeparator();	 
		toolbar.add(new ToolBarSeparator(sepIcon));
		
		
//		toolbar.add(new JLabel("Tools"));	
//	    toolbar.addSeparator();	
	    
	    
	    toolbar.add(handButton);
//	    toolbar.add(zoomSelAreaButton);
//	    toolbar.add(selectAreaWinButton);
	    toolbar.add(pointDensMButton);	    
//	    toolbar.add(areaDensMButton);	    
	    toolbar.add(distMButton);	    
	    toolbar.add(angleMButton);	    
//	    toolbar.add(cobbAngleMButton);	

	    
//		toolbar.addSeparator();	  
//	    toolbar.addSeparator();	 
//	    
//	    toolbar.add(selectDisplayModeButton);	
//		magnGlassButton = new ToolBarToggleButton(magnGlassIcon, "Magnifying Glass",  null, smoothButtons);
//		actionToolButtons.add(magnGlassButton);	        
//	    toolbar.add(magnGlassButton);		
		
//		toolbar.addSeparator();	  
//	    toolbar.addSeparator();		
//		toolbar.add(new ToolBarSeparator(sepIcon));
		
		
//		toolbar.add(new JLabel("Settings"));	
//	    toolbar.addSeparator();	
	    
//	    toolbar.add(selectRefLineModeButton);
//	    toolbar.add(showStudyParamsButton);	
//	    toolbar.add(scaleRulerButton);
//	    toolbar.add(twoSeriesButton);
//	    toolbar.add(openCaseButton);
	
	    toolbar.addSeparator();	
	    toolbar.add(new ToolBarSeparator(sepIcon));
		
//		if(!supervisorOpened && !onlyPastCase && !consultancy && !workScrutiny)
	    	toolbar.add(openEditorButton);
//		else	
//	    	toolbar.add(viewReportButton);

	    	
    	toolbar.add(addCommentButton);
//		if(onlyPastCase || supervisorOpened)
//    		addCommentButton.setEnabled(false);
	
    	
//		if(consultancy)
//			toolbar.add(writeConsultancy);

//		if(workScrutiny)
//	    	toolbar.add(writeScrutinyReport);
	    	
		
		//((BoxLayout)toolbar.getLayout()).
		
		
		toolbar.addSeparator();	
		gbc.weightx=1;
		gbc.gridx=ct+37;

	    toolbar.add(new JLabel(fillerIcon),gbc);

	    
		gbc.weightx=0;
		toolbar.add(consultButton);
		toolbar.add(troubleshootButton);
		toolbar.addSeparator();	
	    
		
		//initial settings
		
		viewButtons[displayFieldLayout].setSelected(true);
		handButton.setSelected(true);
		
		if(ReportEditor.isEditorUp())
			{
				openEditorButton.setSelected(true);			
				openEditorButton.setEnabled(false);		
			}
	}
	
	
	
	
	
	private void populateSeriesScrollPanes()
	{
		int SERIESTREEWIDTH = 240;	//TODO place to front
		
		JPanel scrollField1 = new JPanel();
		
		series1Panel.getViewport().setView(scrollField1);
		series1Panel.setMinimumSize(new Dimension(SERIESTREEWIDTH+3+3,20));

		//BoxLayout bLayout1 = new BoxLayout(scrollField1,BoxLayout.Y_AXIS);
//		scrollField1.setLayout(bLayout1);
		scrollField1.setLayout(new BorderLayout());
		scrollField1.setBorder( new EmptyBorder(14,3,15,3));
		scrollField1.setBackground(DISPLAYBACKGROUNDCOLOR);
//		Color scrollBackground = new Color(150,180,200);

		JPanel widthFiller = new JPanel();
		widthFiller.setAlignmentX(LEFT_ALIGNMENT);
		widthFiller.setSize(new Dimension(SERIESTREEWIDTH,1));
		widthFiller.setMaximumSize(new Dimension(SERIESTREEWIDTH,1));
		widthFiller.setMinimumSize(new Dimension(SERIESTREEWIDTH,1));
		widthFiller.setPreferredSize(new Dimension(SERIESTREEWIDTH,1));
//		widthFiller.setBackground(Color.RED);
		widthFiller.setOpaque(false);
//		scrollField1.add(widthFiller);
		

/*		JTree caseTree = new JTree();
		caseTree.setBackground(DISPLAYBACKGROUNDCOLOR);
		caseTree.setAlignmentX(LEFT_ALIGNMENT);
		caseTree.setMinimumSize(new Dimension(SERIESTREEWIDTH,30));
		//TODO fix width
		//TODO open all routes
		//TODO load actual tree 
		//			- root name
		//			- Study numbers/names
		//			- Series numbers
		//			- Series images (with Mod, BR and number of images)
		//TODO drag and drop 
		scrollField1.add(caseTree);
				
		
		scrollField1.add(new JLabel(" "));
		
		
//		if (referenceCase!=null)
		{
			JTree refCaseTree = new JTree();
			refCaseTree.setBackground(DISPLAYBACKGROUNDCOLOR);
			refCaseTree.setAlignmentX(LEFT_ALIGNMENT);
			scrollField1.add(refCaseTree);
		}*/
		//150,150,150
		caseTree = new TDSNavigationTree(new Color(150, 150, 150), Color.black, Color.cyan,
                        new Color(150, 150, 150),
                        new Font("Serif", Font.PLAIN, 12),
                        new EmptyBorder(5, 5, 5, 5),
                        new Color(150, 150, 150),
                        new Font("Serif", Font.PLAIN, 12),
                        new EmptyBorder(10, 0, 10, 0)
                        );
		//caseTree.setBackground(DISPLAYBACKGROUNDCOLOR);
		caseTree.setBackground(Color.RED);
		//caseTree.setAlignmentX(LEFT_ALIGNMENT);
		//caseTree.setMinimumSize(new Dimension(SERIESTREEWIDTH,30));		
		scrollField1.add(caseTree,BorderLayout.CENTER);
		
/*		
		JTree pastITSCaseTrees[] = new JTree[itsHistoriesList.size];
		for(int i=0; i<singleStudyHistoriesList.size;i++)
		{
			JTree singleStudyHistoriesTrees[i] = new JTree();
			singleStudyHistoriesTrees[i].setBackground(DISPLAYBACKGROUNDCOLOR);
			scrollField1.add(singleStudyHistoriesTrees[i]);
		}

		
		JTree pastITSCaseTrees[] = new JTree[itsHistoriesList.size];
		for(int i=0; i<itsHistoriesList.size;i++)
		{
			JTree pastITSCaseTrees[i] = new JTree();
			pastITSCaseTrees[i].setBackground(DISPLAYBACKGROUNDCOLOR);
			scrollField1.add(pastITSCaseTrees[i]);
		}		
*/
		
		
	}
	
	
	private void setupDisplayField(){
		
		System.out.println("\n***SETTING UP DISPLAYFIELD\n");
		
		GridBagConstraints gbc=new GridBagConstraints();
		displayField.setLayout(new GridBagLayout());
		gbc.fill=GridBagConstraints.BOTH;
		gbc.weightx=1;
		gbc.weighty=1;
		gbc.insets = new Insets(0,0,0,0);
		
/*		gbc.gridx=0;
		gbc.gridy=0;
		TestLayer testLayer = new TestLayer();
		testLayer.setTransferHandler(new TransferHandler("seriesTest"));
		testLayer.setBackground(Color.BLUE);
		testLayer.setOpaque(true);
		displayField.add(testLayer,gbc);*/
		
		switch(displayFieldLayout){
		
			case 0:	//empty
				{break;}
				
			case 1:	//single display
				{	
					gbc.gridx=0;
					gbc.gridy=0;
					displayField.add(mainDisplayFrames[0],gbc);
					validateDisplaySelections(1);
					break;
				}
				
			case 2:	//1+1 displays vert
				{
					gbc.gridx=0;
					gbc.gridy=0;
					displayField.add(mainDisplayFrames[0],gbc);
					gbc.gridx=1;
					gbc.gridy=0;
					displayField.add(mainDisplayFrames[1],gbc);	
					validateDisplaySelections(2);
					break;
				}
				
			case 3:	//1+1 displays horiz
				{
					gbc.gridx=0;
					gbc.gridy=0;
					displayField.add(mainDisplayFrames[0],gbc);
					gbc.gridx=0;
					gbc.gridy=1;
					displayField.add(mainDisplayFrames[1],gbc);
					validateDisplaySelections(2);
					break;
				}
				
			case 4:	//2+1 displays vert
				{
					gbc.gridx=0;
					gbc.gridy=0;
					gbc.gridheight=2;
					displayField.add(mainDisplayFrames[0],gbc);
					gbc.gridheight=1;
					gbc.gridx=1;
					gbc.gridy=0;
					displayField.add(mainDisplayFrames[1],gbc);						
					gbc.gridheight=1;
					gbc.gridx=1;
					gbc.gridy=1;
					displayField.add(mainDisplayFrames[2],gbc);						
					validateDisplaySelections(3);
					break;
				}
				
			case 5:	//2+1 displays horiz
				{
					gbc.gridx=0;
					gbc.gridy=0;
					gbc.gridwidth=2;
					displayField.add(mainDisplayFrames[0],gbc);
					gbc.gridwidth=1;
					gbc.gridx=0;
					gbc.gridy=1;
					displayField.add(mainDisplayFrames[1],gbc);						
					gbc.gridwidth=1;
					gbc.gridx=1;
					gbc.gridy=1;
					displayField.add(mainDisplayFrames[2],gbc);	
					validateDisplaySelections(3);
					break;
				}

			case 6:	//2x2
				{
					int num = 2;
			
					for (int i=0;i<num;i++)
						for (int j=0;j<num;j++)
							{
							gbc.gridx=j;
							gbc.gridy=i;
							displayField.add(mainDisplayFrames[i*num+j],gbc);									
							}	
					validateDisplaySelections(4);
					break;
				}
				
			case 7:	//2x3
				{
					int row = 2;
					int col = 3;
			
					for (int i=0;i<row;i++)
						for (int j=0;j<col;j++)
							{
							gbc.gridx=j;
							gbc.gridy=i;
							displayField.add(mainDisplayFrames[i*col+j],gbc);									
							}
					validateDisplaySelections(6);
					break;					
				}

			case 8:	//3x2
			{
				int row = 3;
				int col = 2;
		
				for (int i=0;i<row;i++)
					for (int j=0;j<col;j++)					{
						gbc.gridx=j;
						gbc.gridy=i;
						displayField.add(mainDisplayFrames[i*col+j],gbc);									
						}		
				validateDisplaySelections(6);
				break;					
			}
			
			case 9:	//3x3
			{
				int num = 3;
		
				for (int i=0;i<num;i++)
					for (int j=0;j<num;j++)
						{
						gbc.gridx=j;
						gbc.gridy=i;
						displayField.add(mainDisplayFrames[i*num+j],gbc);									
						}
				validateDisplaySelections(9);
				break;					
			}
			

			
			case 10:	//4x4
				{
					int num = 4;
			
					for (int i=0;i<num;i++)
						for (int j=0;j<num;j++)
							{
							gbc.gridx=j;
							gbc.gridy=i;
							displayField.add(mainDisplayFrames[i*num+j],gbc);									
							}	
					validateDisplaySelections(16);
					break;
				}				
		}		/* */
	}
	
	
	
	
	private void validateDisplaySelections(int numDisplaysUp)
	{		
		for(int i=0; i<numDisplaysUp; i++)
		{
			//mainDisplayFrames[i].();		
		}
		for(int i=numDisplaysUp; i<mainDisplayFrames.length; i++)
		{
			mainDisplayFrames[i].unselect();
			mainDisplayFrames[i].refUnselect();
		}		
	}
	
	

	

	
	
	
	private void setDisplayFieldLayout(int layout){
		displayFieldLayout = layout;
		displayField.removeAll();
		setupDisplayField();
		System.out.println("Repaint with mode "+displayFieldLayout);
		displayField.revalidate();
		displayField.repaint();
/*		for(int i=0; i<mainDisplayFrames.length; i++)
			mainDisplayFrames[i].centerImage();*/
	}
	
	
/*	private void resetDisplayPanel(int newMode){		
		if (newMode != seriesMode)
			{
				seriesMode = newMode;
				displayPanel.removeAll();
				populateDisplayPanel();
				displayPanel.revalidate();
				displayPanel.repaint();
			}
	}*/
	
	

	
/*	private void openEditor(){
		
		//caseID = case1.getCaseNaming();
		//CaseDownloader caseDownloader = new CaseDownloader(caseID); //TODO		
		
		reportEditor = new ReportEditor(caseDataDownloader, this);		
		reportEditor.repaint();				
		reportEditor.setVisible(true);
		reportEditor.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent arg0) 
				{	if(reportEditor.closing())	closeEditor();		}
		} );
		
		ReportEditor.setEditorIsUp();
		
		if(viewer1!=null)
		{
			viewer1.openEditorButton.setSelected(true);
			viewer1.openEditorButton.setEnabled(false);	
		}
		if(viewer2!=null)
		{
			viewer2.openEditorButton.setSelected(true);
			viewer2.openEditorButton.setEnabled(false);	
		}		
		//openEditorButton.setEnabled(false);			
	}*/
	
	
	
/*	private static void closeEditor(){

		reportEditor.setVisible(false);
		reportEditor.dispose();
		reportEditor = null;
		ReportEditor.closeReportEditor();
		if(viewer1!=null)
		{
			viewer1.openEditorButton.setSelected(false);
			viewer1.openEditorButton.setEnabled(true);	
		}
		if(viewer2!=null)
		{
			viewer2.openEditorButton.setSelected(false);
			viewer2.openEditorButton.setEnabled(true);	
		}
		refreshViewers();
			
//		parentContainer..refresh();
	}*/
	
	
	
	
	public boolean  closingViewer(){
		
		//TODO check window
	
		return true;
	}
	
	

	
		
	public static boolean isViewerUp(int viewer)
	{
		if(viewer ==1)		
			return	viewer1 != null;		
		else if(viewer ==2)		
			return	viewer2 != null;			
		return false;		
	}
	
	
	public boolean isBothViewersUp(){ return  isViewerUp(1) && isViewerUp(2);	};

	
	public static CaseViewer getViewer(int viewer)
	{
		if(viewer ==1)		
			return	viewer1;		
		else if(viewer ==2)		
			return	viewer2;			
		return null;		
	}
	
		
	
	
	
	public void performOperationOnOne(int operation, int[] params, DisplayImageFrame frameToWorkOn)
	{	
		boolean[] indicator= new boolean[mainDisplayFrames.length];
		for(int i=0; i<mainDisplayFrames.length; i++)
			indicator[i] = false;		
		indicator[frameToWorkOn.getFrameNumber()] = true;
		System.out.println("OPERATION "+operation+" is to be performed on display "+frameToWorkOn.getFrameNumber());
//		System.out.println("\n>>> Operation initiated for display "+frameToWorkOn.getFrameNumber());

//		performOperationOnGivenDisplays(operation, param1, param2, indicator);
		performOperationOnGivenDisplays3(operation, params, indicator);

	}
	
	
	public void performOperationOnAll(int operation, int[] params)
	{	
		boolean[] fullTrueIndicator= new boolean[mainDisplayFrames.length];
		for(int i=0; i<mainDisplayFrames.length; i++)
			fullTrueIndicator[i] = true;		
		System.out.println("\n>>> Operation initiated for all displays ");
		
//		performOperationOnGivenDisplays(operation, param1, param2, fullTrueIndicator);
		performOperationOnGivenDisplays3(operation, params, fullTrueIndicator);
	}
	
	
	public void performOperationOnSelected(int operation, int[] params)
	{
		int ct=0;
//		boolean changeMade = false;
		boolean[] indicator= new boolean[mainDisplayFrames.length];
		for(int i=0; i<mainDisplayFrames.length; i++)
			indicator[i]=mainDisplayFrames[i].isSelected();
		System.out.println("OPERATION "+operation+" is to be performed on Selected");
/*//		performOperationOnOne(operation, param1, param2, mainDisplayFrames[i]);
		DisplayImageFrame[] listToWorkOn = new DisplayImageFrame[ct];
		for(int i=0; i<mainDisplayFrames.length; i++)
			if(mainDisplayFrames[i].isSelected())
				listToWorkOn[i]=mainDisplayFrames[i];*/
		
//		performOperationOnGivenDisplays(operation, param1, param2, indicator);
		performOperationOnGivenDisplays3(operation, params, indicator);
	}


	
	public void performOperationOnGivenDisplays3(int operation, int params[], boolean[] intendedOpSubjectIndicator){
		
		System.out.print("\n\n\n***\nOPERATION "+operation+" IS INTENDED TO BE PERFORMED ON DISPLAYS: \n\t");
		for(int i=0; i<NUMMAINFRAMES; i++)
			if(intendedOpSubjectIndicator[i])
				System.out.print( mainDisplayFrames[i].getFrameNumber() +", ");
		System.out.println();
		
		
		boolean operationConditionOk = false;

		//check basic operation conditions		
		switch(operation)			// these are all covered in the change checking function (except for reset), so are mainly to speed it up 
		{		
			case SELECTOP: 	case UNSELECTOP:	{	operationConditionOk = true;	break;			}
			
			case MOVEOP:	case WINDOWOP:	case ZOOMOP:	case CHANGEIMAGEOP:	{	operationConditionOk = true;	break;			}
			
			case RESETOP: 		{	if( ( lastOperation != RESETOP || indicatorChanged(lastOpIndicator,intendedOpSubjectIndicator) ) &&  startingState != null) operationConditionOk = true;	break;			}

			case DELETEOP:		{	if(lastOperation != DELETEOP || indicatorChanged(lastOpIndicator,intendedOpSubjectIndicator) )	operationConditionOk = true;		break;			}
			case CLEAROP:		{	if(lastOperation != CLEAROP || indicatorChanged(lastOpIndicator,intendedOpSubjectIndicator) )	operationConditionOk = true;		break;			}					
			case CENTEROP:		{	if(lastOperation != CENTEROP || indicatorChanged(lastOpIndicator,intendedOpSubjectIndicator) )	operationConditionOk = true;		break;			}
			case FITDISPOP:		{	if(lastOperation != FITDISPOP || indicatorChanged(lastOpIndicator,intendedOpSubjectIndicator) )	operationConditionOk = true;		break;			}
			case ZOOM100OP:		{	if(lastOperation != ZOOM100OP || indicatorChanged(lastOpIndicator,intendedOpSubjectIndicator) )	operationConditionOk = true;		break;			}
			case RESETWINDOWOP:	{	if(lastOperation != RESETWINDOWOP || indicatorChanged(lastOpIndicator,intendedOpSubjectIndicator) )	operationConditionOk = true;	break;	}
			case AUTOWINDOWOP:	{	if(lastOperation != AUTOWINDOWOP || indicatorChanged(lastOpIndicator,intendedOpSubjectIndicator) )	operationConditionOk = true;	break;	}
			
			case SETWINDOWOP:		{	operationConditionOk = true;	break;		}
			
			case SELECTTOOLOP:  case UNSELECTALLTOOLSOP: 
									{ 	operationConditionOk = true;	break; }
			
			case DELETETOOLOP: 		{	operationConditionOk = true;	break;		}
			
			case MOVETOOLOP:  		{ 	operationConditionOk = true; break; }	//TODO check
			
			case ADD_M_POINTDENS:	{	operationConditionOk = true;	break;		}
			case ADD_M_DIST:		{	operationConditionOk = true;	break;		}
		}
		
		
		if(operationConditionOk)
			System.out.println("THE OPERATION CONDITIONS ARE OK ");
		else
			System.out.println("THE OPERATION CONDITIONS ARE !!NOT!! OK ");

		//if basic conditions are ok
		if(operationConditionOk)
		{
			boolean[] actualOpSubjectIndicator = null;
			int[] commonApplicableChange = null;
			double[] toolMovementApplicableChange = null;
			

			//check what change the operation would make
			switch(operation)
			{
				case SELECTOP: 	case UNSELECTOP:	{	actualOpSubjectIndicator = intendedOpSubjectIndicator;	break;	}	//we don't bother to check
								
				case MOVEOP:	case WINDOWOP:	case ZOOMOP:	case CHANGEIMAGEOP:		
				{	
					System.out.println("THE INTENDED OPERATION PARAMETERS ARE: ("+params+")");

					
					//find the applicable part of each proposed change separately
					int[][] applicableChanges = new int[NUMMAINFRAMES][];
					for(int i=0; i<NUMMAINFRAMES; i++)
						if(intendedOpSubjectIndicator[i])
							applicableChanges[i] = mainDisplayFrames[i].findApplicablePartOfChange(operation,params);
						else	
							//applicableChanges[i] = null;
							applicableChanges[i] = new int[]{Integer.MAX_VALUE,Integer.MAX_VALUE};		//sentinel values
					      
					//derive the indicators	
					actualOpSubjectIndicator = new boolean[NUMMAINFRAMES];
					for(int i=0; i<NUMMAINFRAMES; i++)
						actualOpSubjectIndicator[i] = intendedOpSubjectIndicator[i] && applicableChanges[i]!=null;

					
					
					//derive the common change applicable to ALL of the displays
					commonApplicableChange = new int[]{Integer.MAX_VALUE,Integer.MAX_VALUE};
					for(int i=0; i<NUMMAINFRAMES; i++)
						if(intendedOpSubjectIndicator[i])
							{
								if(actualOpSubjectIndicator[i])
									{
										System.out.println("The applicable parameters for Display "+mainDisplayFrames[i].getFrameNumber()+" are: ("+applicableChanges[i][0]+","+applicableChanges[i][1]+")");
										if( Math.abs(applicableChanges[i][0]) < Math.abs(commonApplicableChange[0]))
											commonApplicableChange[0] = applicableChanges[i][0];
										if( Math.abs(applicableChanges[i][1]) < Math.abs(commonApplicableChange[1]))
											commonApplicableChange[1] = applicableChanges[i][1];
									}
								else
								{
									commonApplicableChange[0] = 0;
									commonApplicableChange[1] = 0;
								}
								
							}
					
					if(commonApplicableChange[0] == 0)	//if no change to be made, don't change
					{						
						if(operation == ZOOMOP || operation == CHANGEIMAGEOP)
							for(int i=0; i<NUMMAINFRAMES; i++)
								actualOpSubjectIndicator[i] = false;
						else
							if(commonApplicableChange[1] == 0)		 
								for(int i=0; i<NUMMAINFRAMES; i++)
									actualOpSubjectIndicator[i] = false;	
					}
					
					//TODO check how it can happen
/*					if( commonApplicableChange[0] == Integer.MAX_VALUE)	commonApplicableChange[0] = 0;
					if( commonApplicableChange[1] == Integer.MAX_VALUE)	commonApplicableChange[1] = 0;
*/					
					
					System.out.println("THE COMMONLY APPLICABLE PARAMETERS ARE: ("+commonApplicableChange[0]+","+commonApplicableChange[1]+")");

						
					break;
				}

				
/*				case CENTEROP:	case FITDISPOP:	case ZOOM100OP:	case AUTOWINDOWOP:		
				{	//find the applicable part of each proposed change separately	//derive the indicators		break;			}
				case RESETOP: case DELETEOP: case CLEAROP: 	case RESETWINDOWOP:	case SETWINDOWOP:		 	
				{	//find the change indicators			break;		}		*/
				
				case CENTEROP:	case FITDISPOP:	case ZOOM100OP:	case AUTOWINDOWOP:		
				case RESETOP: case DELETEOP: case CLEAROP: 	case RESETWINDOWOP:	case SETWINDOWOP:		 	
				{	
					//find the change indicators
					actualOpSubjectIndicator = new boolean[NUMMAINFRAMES];
					for(int i=0; i<NUMMAINFRAMES; i++)
						actualOpSubjectIndicator[i] = intendedOpSubjectIndicator[i] && mainDisplayFrames[i].wouldTheOperationMakeAChange(operation,params);

					break;
				}		
				
				
				case SELECTTOOLOP: 	case UNSELECTALLTOOLSOP:	{	actualOpSubjectIndicator = intendedOpSubjectIndicator;	break;	}	//we don't bother to check
				
				case DELETETOOLOP: {	actualOpSubjectIndicator = intendedOpSubjectIndicator;	break;	}	//we don't bother to check
						
				case MOVETOOLOP:   //TODO check   
				{	
					//find the change indicators
					actualOpSubjectIndicator = new boolean[NUMMAINFRAMES];
					toolMovementApplicableChange = new double[]{Double.MAX_VALUE,Double.MAX_VALUE};

					
					for(int i=0; i<NUMMAINFRAMES; i++)
						if(intendedOpSubjectIndicator[i])
							{
								toolMovementApplicableChange = mainDisplayFrames[i].findApplicablePartOfToolMovement( params[0],params[1],params[2],params[3],params[4],params[5],params[6],params[7] );
								if(toolMovementApplicableChange[0]!=0 || toolMovementApplicableChange[1]!=0)
									actualOpSubjectIndicator[i] = true;
							}
						else	
							{
							actualOpSubjectIndicator[i] = false;
//							toolMovementApplicableChange = new double[]{Double.MAX_VALUE,Double.MAX_VALUE};
							}
					
					System.out.println("\n\n\n\n***************\n toolMovementApplicableChange = ["+toolMovementApplicableChange[0]+","+toolMovementApplicableChange[1]+"]\n******************\n\n\n\n\n");

//					for(int i=0; i<NUMMAINFRAMES; i++)
//						actualOpSubjectIndicator[i] = intendedOpSubjectIndicator[i] && mainDisplayFrames[i].wouldTheToolMovementOnTheActImageHappen(params[0],params[1],params[2],params[3],params[4],params[5]);
					
					break;					
				}	
				
				case ADD_M_POINTDENS:	{	actualOpSubjectIndicator = intendedOpSubjectIndicator;	break;	}	//we don't bother to check, as addition is checked in DisplayImageFrame
				case ADD_M_DIST:		{	actualOpSubjectIndicator = intendedOpSubjectIndicator;	break;	}	//we don't bother to check, as addition is checked in DisplayImageFrame
				
			}	
			
			
			
			System.out.print("OPERATION "+operation+" IS TO BE ACTUALLY PERFORMED ON DISPLAYS: \n\t");
			for(int i=0; i<NUMMAINFRAMES; i++)
				if(actualOpSubjectIndicator[i])
					System.out.print( mainDisplayFrames[i].getFrameNumber() +", ");
			System.out.println();
			
			
			
			//find out from individual change assessments whether there would be any displays to change or none
			boolean thereWouldBeChange = false;
			for(int i=0; i<NUMMAINFRAMES; i++)
				if(actualOpSubjectIndicator[i])	{	thereWouldBeChange = true;	break;	}
			
			
			if(thereWouldBeChange)
				System.out.println("THE OPERATION WILL MAKE CHANGE \n");
			else
				System.out.println("THE OPERATION WILL MAKE !!NO!!CHANGE \n");
			
			
			//proceed if a change would be made
			if(thereWouldBeChange)
			{				
				//save state if needed			
				switch(operation)
				{
					case SELECTOP: 	case UNSELECTOP:	
						{	break;	}	//don't save
						
					case MOVEOP:	case WINDOWOP:	case ZOOMOP://	case CHANGEIMAGEOP:		
						{//save if condition applies
							long now = System.currentTimeMillis();
							if(lastOperation != operation ||  now-lastOpTime > OPERATIONRELAXTIME || indicatorChanged(lastOpIndicator,intendedOpSubjectIndicator) )
								saveActDisplayStates(actualOpSubjectIndicator);
							break;
						}
						
					case CENTEROP:	case FITDISPOP:	case ZOOM100OP:	case AUTOWINDOWOP:	case RESETOP: case DELETEOP: case CLEAROP: 	case RESETWINDOWOP:	case SETWINDOWOP:		 	
						{	saveActDisplayStates(actualOpSubjectIndicator);	break; }//basic condition applies (operationConditionOk), and a change would be made, so save
						
					case SELECTTOOLOP: 	case UNSELECTALLTOOLSOP:	{ break;} //don't save
						
					case DELETETOOLOP: 
						{	saveActDisplayStates(actualOpSubjectIndicator);	break;	}//basic condition applies (operationConditionOk), and a change would be made, so save
									
					case MOVETOOLOP: //TODO
						{//save if condition applies
							long now = System.currentTimeMillis();
							if(lastOperation != operation ||  now-lastOpTime > OPERATIONRELAXTIME || indicatorChanged(lastOpIndicator,intendedOpSubjectIndicator) )
								saveActDisplayStates(actualOpSubjectIndicator);	
							break;	
						}//basic condition applies (operationConditionOk), and a change would be made, so save
					
					case ADD_M_POINTDENS:	
						{	saveActDisplayStates(actualOpSubjectIndicator);	break;	}//basic condition applies (operationConditionOk), and a change would be made, so save
					case ADD_M_DIST:	
					{	saveActDisplayStates(actualOpSubjectIndicator);	break;	}//basic condition applies (operationConditionOk), and a change would be made, so save
				}
				
				
				//perform operation
				if (operation == RESETOP)
				{
					DisplayModel[] partialStartingState = new DisplayModel[NUMMAINFRAMES];
					for(int j=0; j<NUMMAINFRAMES;j++)
					{
						if(actualOpSubjectIndicator[j])		partialStartingState[j] = startingState[j].getCopy();									
						else								partialStartingState[j] = null;						
/*						System.out.println("->startingState["+j+"]="+startingState[j]);
						System.out.println("->opSubjectIndicator["+j+"]="+actualOpSubjectIndicator[j]);
						System.out.println("->partialStartingState["+j+"]="+partialStartingState[j]);
						System.out.println();*/
					}
					loadExDisplayStates(partialStartingState);					
				}
				else
					for(int i=0; i<NUMMAINFRAMES; i++)
						if(actualOpSubjectIndicator[i])
						{
							int fn = mainDisplayFrames[i].getFrameNumber();	
							System.out.println("\tOperation "+operation+" is to be performed on Display "+fn );
							switch(operation)
							{
								case SELECTOP: 			{ mainDisplayFrames[i].select(); 	break;}
								case UNSELECTOP:		{ mainDisplayFrames[i].unselect();	break;}
								
								case MOVEOP:			{ mainDisplayFrames[i].moveImageWithNoCheck(commonApplicableChange[0],commonApplicableChange[1]);	break;}
								case WINDOWOP:			{ mainDisplayFrames[i].windowImageWithNoCheck(commonApplicableChange[0],commonApplicableChange[1]);	break;}
								case ZOOMOP:			{ mainDisplayFrames[i].zoomImageWithNoCheck(commonApplicableChange[0]);			break;}
								case CHANGEIMAGEOP:		{ mainDisplayFrames[i].changeImageWithNoCheck(commonApplicableChange[0]);			break;}	
	
								case CENTEROP:			{ mainDisplayFrames[i].centerImage();				break;}
								case FITDISPOP:			{ mainDisplayFrames[i].fitImage2Display();			break;}
								case ZOOM100OP:			{ mainDisplayFrames[i].zoom100();					break;}
								case AUTOWINDOWOP:		{ mainDisplayFrames[i].autoWindow();				break;}
	
	//							case RESETOP:			{	break;	}
								case DELETEOP:			{ mainDisplayFrames[i].disposeSeriesFromdisplay();	/*this.validate();*/   break;}
								case CLEAROP:			{ mainDisplayFrames[i].clearAnnotMeasLayerForActImage(); break;}
								case RESETWINDOWOP:		{ mainDisplayFrames[i].resetWindow();				break;}	 	
								case SETWINDOWOP:		{ mainDisplayFrames[i].presetWindow(params[0],params[1]); break;}
								
								case UNSELECTALLTOOLSOP:{ mainDisplayFrames[i].unselectAllToolsForAllImages(); break;}
								case SELECTTOOLOP:		{ mainDisplayFrames[i].selectToolXForActImage(params[0],params[1]); break;}
								case DELETETOOLOP: 		{ mainDisplayFrames[i].deleteSelectedToolOfActImage(params[0],params[1]); break;}
								case MOVETOOLOP: 		{ mainDisplayFrames[i].moveSelectedToolOfActImage(params[0],params[1],params[2],params[3], toolMovementApplicableChange[0], toolMovementApplicableChange[1]);  break; }
															//TODO mainDisplayFrames[i].selectToolXForActImage(params[0],params[1],params[2],params[3],params[4]); break;}
								case ADD_M_POINTDENS:	{ 
															mainDisplayFrames[i].addMPointDensToActImage(params[0],params[1]); 
															//printToolLists(); //TODO delete this
															break;
														}
								case ADD_M_DIST:	{ 
															mainDisplayFrames[i].addMDistanceToActImage(params[0],params[1]); 
															//printToolLists(); //TODO delete this
															break;
														}
							}
						}
				
				//update last operation variables
				lastOperation = operation;
				lastOpIndicator = intendedOpSubjectIndicator;
				lastOpTime = System.currentTimeMillis();				
			}		
			
		}		
		System.out.print("\n***\n\n\n");
	}	
	
	

	
	private DisplayModel[] getCopyOfCurrentViewerStates(boolean[] opSubjectIndicator){
			
			DisplayModel[] newStateObject = new DisplayModel[mainDisplayFrames.length];
			for(int i=0; i < mainDisplayFrames.length; i++)			
				if(opSubjectIndicator[i]==true)
					newStateObject[i] = mainDisplayFrames[i].getDisplayModelCopy();
				else
					newStateObject[i] = null;
			
			return newStateObject;		
		}
	
	
	
	private void saveActDisplayStates(boolean[] opSubjectIndicator)
		{
			if(startingState == null)
				{
					System.out.println("Saving state to startingStates");
					boolean[] fullTrueIndicator = new boolean[NUMMAINFRAMES];
					for(int i=0; i< NUMMAINFRAMES; i++)
						fullTrueIndicator[i] = true;		
					startingState = getCopyOfCurrentViewerStates(fullTrueIndicator);
				
					for(int i=0; i< NUMMAINFRAMES; i++)
						System.out.println("\t->startingState["+i+"]="+startingState[i]);				
				}
	
			DisplayModel[] stateCopies = getCopyOfCurrentViewerStates(opSubjectIndicator);
				
			undoRedoModel.pushNewState(stateCopies);
		}
	
	
	private void loadExDisplayStates(DisplayModel[] dispStates)
		{
			System.out.println("CaseViewer.loadExDisplayStates()");
			for(int i=0; i<NUMMAINFRAMES; i++)
				if(dispStates[i]!=null)
					mainDisplayFrames[i].loadDisplayModel(dispStates[i]);		
		}
	
	
	
	public static void refreshViewers(){
			if(isViewerUp(1))
			{
				viewer1.validate();
				viewer1.repaint();
			}
			if(isViewerUp(2))
			{
				viewer2.validate();
				viewer2.repaint();
			}
		}
		
	
	
	public boolean indicatorChanged(boolean[] prevIndicator, boolean[] opSubjectIndicator){
			for(int i =0; i<prevIndicator.length ; i++)
				if (opSubjectIndicator[i] != prevIndicator[i]) return true;
		
			return false;
		}
		
	
	
	public void clearIndicator(){
		for(int i =0; i<NUMMAINFRAMES ; i++)
			lastOpIndicator[i] = false;	
		}
	
	
	
	public boolean noDisplaySelected(){
			for(int i =0; i<NUMMAINFRAMES ; i++)
				if (mainDisplayFrames[i].isSelected()) return false;
	
			return true;
		}
	
	public String getToolStateCommandString(){
		return actionToolButtons.getSelection().getActionCommand();
	}
	
	

	public void printToolLists(){	//TODO delete thiod function !!!
		
    	for(int i =0; i<2/*NUMMAINFRAMES*/ ; i++)
		{
/*			System.out.println("\n\n\n");
			System.out.println("**************************************************");
			System.out.println("**************************************************");
			System.out.println("**************************************************");
			System.out.println("**************************************************");
			System.out.println("\nFrame ["+i+"]:");
   			System.out.println("\t\t DisplayModel - "+mainDisplayFrames[i].getModel());
   			System.out.println("\t\t AnnotMeasLayerModel 1 - "+mainDisplayFrames[i].getModel().getAnnotMeasLayerModel());
   			System.out.println("\t\t AnnotMeasLayerModel 2 - "+mainDisplayFrames[i].getImageDisplay().getAnnotMeasLayer().getToolLayerModel());
   			System.out.println("\t\t ToolLists");
   			mainDisplayFrames[i].getModel().getAnnotMeasLayerModel().listToolsForAllImages();
			System.out.println("**************************************************");
			System.out.println("**************************************************");
			System.out.println("**************************************************");
			System.out.println("**************************************************");
			System.out.println("\n\n\n");*/
				
		}

	}
	
	
/*        N E W   P A R T    */
	
	
	
	
	
	private static ScreenAssignment screenAssignments;
	
	public static void setDefaultScreenPreferences()
	{
		int startScreen = 0;
		int viewerScreen = 1;
		int reportScreen = 0;

		setScreenPreferences(startScreen,viewerScreen,reportScreen);
	}
		
	public static void setScreenPreferences(int startScreen, int viewerScreen, int reportScreen)
		{	screenAssignments = new ScreenAssignment(startScreen,viewerScreen,reportScreen);	}	
	public static ScreenAssignment getScreenInfo()	{	return screenAssignments;	}

	
	public static CaseViewer createCaseViewer(Container newParentContainer){
		CaseDownloader newCaseDataDownloader = CaseDownloader.getInstance();
		if(numViewersUp>0)
		{
			if(newParentContainer != parentContainer)
				//TODO throw exception
				return null;
			if(newCaseDataDownloader != caseDataDownloader)
				//TODO throw exception
				return null;	
		}
			
		if(!CaseViewer.isViewerUp(1) )
			{
				caseDataDownloader = newCaseDataDownloader;
				if (numViewersUp==0) 
					parentContainer = newParentContainer;
				CaseViewer caseViewer1 = new CaseViewer(1, caseDataDownloader);//, menuNames, menuActions, separators, tabChangeListener, icon, testSeries );
				if( getScreenInfo().getStartScreenNum() == getScreenInfo().getViewerScreenNum() && (parentContainer.getLocation().x!=0 || parentContainer.getLocation().y!=0))
					parentContainer.setLocation(0,0);		
				return CaseViewer.getViewer(1);
			}
		else
			if(!CaseViewer.isViewerUp(2))
				{
					//TODO
					return CaseViewer.getViewer(2);
				}
			else return null;		
	}
	
	
	
	public static void  closeViewer(int viewer){
		numViewersUp--;
			
		if (numViewersUp==0 && ReportEditor.isEditorUp())
		{
			//TODO close editor !!!
			parentContainer = null;
			caseDataDownloader = null;
		}
		if(viewer ==1)
		{
			viewer1.setVisible(false);
			viewer1.dispose();
			viewer1 = null;
		}		
		else if(viewer ==2)
		{
			viewer2.setVisible(false);
			viewer2.dispose();
			viewer2 = null;
		}
		refreshViewers();
	}
	
	

	
	
	
	public void closeThisViewer(){
		closeViewer(this.viewerNum);
	}
	

	private String findMainCaseName(){ 
		return caseDataDownloader.getTdsMainCase().getHospitalCaseIdDicomAttributeValue();	
	}
	
	
	
	private void loadMainCase(){
		
		//find out if there is more than 1 study in the case
			//multipleStudies = 
		
		//put 1st images of series to thumbnails
			//for all series of study 1
			//for all series of study 2
		//find out how many displays are visible with the current setting
			//numVisibleDisplays =  
		
		//
			//for(int i=0; i<numVisibleDisplays; i++)
		
		//load study 0
		StudyDTO study0 = caseDataDownloader.getTdsMainCase().getReferralInfoList().get(0).getStudyList().get(0);
		for(int i=0; i<study0.getSeriesList().size(); i++)
				mainDisplayFrames[i].assignSeriesToDisplay(
						study0.getSeriesList().get(i),true);
	
		//TODO
		
	}
	
	
	
	public void setPermanentMessage(String permanentMessage){
		this.permanentMessagesLabel.setText(permanentMessage);
	}
	
	
	public void setTemporaryMessage(String temporaryMessage){
		this.temporaryMessagesLabel.setText(temporaryMessage);		
	}
	
	
	/*	public static BufferedImage loadImage(String imageFileName, Color transparentColor){
	
	File imageFile = new File(imageFileName);
	BufferedImage image;
			
	try {
		image = ImageIO.read(imageFile);			


		int h = image.getHeight();
		int w = image.getWidth();
//		System.out.println("Icon loading");
		int[] pixel = new int[4];//, pixel2;
		WritableRaster raster = image.getRaster();
//		WritableRaster alphaRaster  = image.getAlphaRaster() ;
		for (int i=0; i<h; i++)
		{
			for (int j=0; j<w; j++)
			{
				pixel = raster.getPixel(j, i, pixel);
//				pixel2 = new int[]{
//									raster.getSample(i, j, 0),
//									raster.getSample(i, j, 1),
//									raster.getSample(i, j, 2)
//									};
////				System.out.println("Pixel = ["+pixel[0]+","+pixel[1]+","+pixel[2]+","+pixel[3]+"]");
//				System.out.println("Pixel = ["+pixel[0]+","+pixel[1]+","+pixel[2]+"]");

				if( pixel[0]==transparentColor.getRed() && 
					pixel[1]==transparentColor.getGreen() && 
					pixel[2]==transparentColor.getBlue() )
//						raster.setPixel(i, j, new float[]{1,0,0,0});
						//raster.setPixel(i,j,new int[]{255,255,255});
	//					alphaRaster.setPixel(i,j,new int[]{255});
						//raster.setSample(i, j, 2, 0);
						;
			}
		}			
			
		return image;
		
	} catch (IOException e) {
		e.printStackTrace();
		return null;
	}	
}*/
	
	
}

	