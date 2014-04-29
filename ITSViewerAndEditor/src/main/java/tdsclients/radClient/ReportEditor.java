package tdsclients.radClient;

import casemodule.downloading.CaseDownloader;
import casemodule.downloading.RemoteCaseDownloader;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.ScrollPaneConstants;

import casemodule.downloading.LocalCaseDownloader;


import tdsclients.client_general.FrameWithMenu;
import tdsclients.client_general.SimpleToolBarButton;
import tdsclients.client_general.ToolBarSeparator;
import tdsclients.client_general.ToolBarToggleButton;
import tdsclients.client_general.ToolBarWithBottomBorder;


public class ReportEditor extends FrameWithMenu {

	
	private static final String FRAMETITLEPREFIX = "[Report Editor] TDS Radiologist - ";
	private static final int SIZEX = 701;
	private static final int SIZEY = 890;
	public static final Color EDITORBACKGROUNDCOLOR = new Color(90,108,122);

	public static final String ICONDIR = /*File.separator+*/"Icons"+File.separator;
	public static final String TBIsepFN = ICONDIR+"separatorStickIcon4.png";
	public static final String TBIfillerFN = ICONDIR+"filler2.png";
	public static final String TBIclearReportFN = ICONDIR+"clearReportIcon2.png";
	public static final String TBIopenReportFN = ICONDIR+"openReportIcon2.png";
	public static final String TBIsaveFN = ICONDIR+"saveIcon.png";
	public static final String TBIpreviewFN = ICONDIR+"previewIcon4.png";
	
	public static final String TBIfindFN = ICONDIR+"clearReportIcon2.png";
	public static final String TBIzoomInFN = ICONDIR+"zoomInIcon.png";
	public static final String TBIzoomOutFN = ICONDIR+"zoomOutIcon2.png";
	public static final String TBIcutFN = ICONDIR+"cutIcon4.png";
	public static final String TBIcopyFN = ICONDIR+"copyIcon.png";
	public static final String TBIpasteFN = ICONDIR+"pasteIcon2.png";
	public static final String TBIboldFN = ICONDIR+"boldIcon.png";
	public static final String TBItitleFN = ICONDIR+"titleStyleIcon.png";
	public static final String TBInormalFN = ICONDIR+"normalStyleIcon.png";
	public static final String TBIconfirmFN = ICONDIR+"confirmReportIcon7.png";
	private static final Dimension EDITORFIELDSIZE = new Dimension(570,730);
	
	private static final Dimension FONTCHOOSERSIZE = new Dimension(50,18);
	
	
	//model
	private static ReportEditor editor;
	private static boolean editorUp;//=false;
	private double zoom;
	private boolean currentReportSaved;
	
	
	
	
	//components
	
	private JScrollPane editorScrollPanel;
	private JEditorPane editorPane;
	private ToolBarWithBottomBorder toolbar;
	
	private ButtonGroup fontButtons;
	private ToolBarToggleButton /*boldButton,*/titleButton,normalButton;	
	private SimpleToolBarButton clearReportButton, openReportButton, saveReportButton, previewButton,
								undoButton, redoButton, cutButton, copyButton, pasteButton,zoomInButton,zoomOutButton,
								confirmButton;
	private JComboBox fontSizeChooser;	
	private JLabel zoomLabel;
//	private Action actionOnClose;
	
	
	public ReportEditor(CaseDownloader caseDataDownloaderIn, CaseViewer caseViewer/*,  String[] menuNames,Action[][] menuActions,int[][] separators,BufferedImage icon/*   , Action actionOnCloseIn*/){
		super();
		
		Action clearAct,openAct,saveAct,saveAsAct,previewAct,confirmAct,closeAct,undoAct,redoAct,cutAct,copyAct,pasteAct,findAct,zoomInAct,zoomOutAct,helpAct,guideAct,editorCloseAction;
		Action[][] menuActs;
		String[] menuNames;
		int[][] separators;	
		menuNames=new String[]{"Report","Edit","View","Help"/*, "Worklist","Admin","Connection","Options"*/};
	
//		loadCaseAct = new AbstractAction("Load case for reference") {public void actionPerformed(ActionEvent ae){/*newGame();*/}}; 
		clearAct = new AbstractAction(" Clear",GUIUtility.loadIcon(ReportEditor.TBIclearReportFN, new Color(0,255,0))) {public void actionPerformed(ActionEvent ae){/*exit();*/}}; 
		openAct = new AbstractAction(" Open Alternative Version  ",GUIUtility.loadIcon(ReportEditor.TBIopenReportFN, new Color(0,255,0))) {public void actionPerformed(ActionEvent ae){/*exit();*/}}; 
		saveAct = new AbstractAction(" Save Current",GUIUtility.loadIcon(ReportEditor.TBIsaveFN, new Color(0,255,0))) {public void actionPerformed(ActionEvent ae){/*exit();*/}}; 
		saveAsAct = new AbstractAction(" Save As Alternative") {public void actionPerformed(ActionEvent ae){/*exit();*/}}; 
		previewAct = new AbstractAction(" Preview Current",GUIUtility.loadIcon(ReportEditor.TBIpreviewFN, new Color(0,255,0))) {public void actionPerformed(ActionEvent ae){/*exit();*/}}; 
		confirmAct = new AbstractAction(" Confirm Current",GUIUtility.loadIcon(ReportEditor.TBIconfirmFN, new Color(0,255,0))) {public void actionPerformed(ActionEvent ae){/*exit();*/}}; 
		closeAct = new AbstractAction(" Close") {public void actionPerformed(ActionEvent ae){/*exit();*/}}; 
		
		undoAct = new AbstractAction(" Undo",GUIUtility.loadIcon(CaseViewer.TBIundoFN, new Color(0,255,0))) {public void actionPerformed(ActionEvent ae){/*exit();*/}}; 
		redoAct = new AbstractAction(" Redo",GUIUtility.loadIcon(CaseViewer.TBIredoFN, new Color(0,255,0))) {public void actionPerformed(ActionEvent ae){/*exit();*/}}; 
		cutAct = new AbstractAction(" Cut",GUIUtility.loadIcon(ReportEditor.TBIcutFN, new Color(0,255,0))) {public void actionPerformed(ActionEvent ae){/*exit();*/}}; 
		copyAct = new AbstractAction(" Copy",GUIUtility.loadIcon(ReportEditor.TBIcopyFN, new Color(0,255,0))) {public void actionPerformed(ActionEvent ae){/*exit();*/}}; 
		pasteAct = new AbstractAction(" Paste",GUIUtility.loadIcon(ReportEditor.TBIpasteFN, new Color(0,255,0))) {public void actionPerformed(ActionEvent ae){/*exit();*/}}; 
		findAct = new AbstractAction(" Search     ") {public void actionPerformed(ActionEvent ae){/*exit();*/}}; 

		zoomInAct = new AbstractAction(" Zoom In",GUIUtility.loadIcon(ReportEditor.TBIzoomInFN, new Color(0,255,0))) {public void actionPerformed(ActionEvent ae){/*exit();*/}}; 
		zoomOutAct = new AbstractAction(" Zoom Out",GUIUtility.loadIcon(ReportEditor.TBIzoomOutFN, new Color(0,255,0))) {public void actionPerformed(ActionEvent ae){/*exit();*/}}; 

		
		helpAct = new AbstractAction(" Help",GUIUtility.loadIcon(CaseViewer.TBIhelpFN, new Color(0,255,0))) {public void actionPerformed(ActionEvent ae){/*exit();*/}}; 
		guideAct = new AbstractAction(" Guide and Tips  ",GUIUtility.loadIcon(CaseViewer.TBIguideFN, new Color(0,255,0))) {public void actionPerformed(ActionEvent ae){/*exit();*/}}; 
		
//		editorCloseAction = new AbstractAction(" Guide and Tips  ",GUIUtility.loadIcon(this.TBIguideFN, new Color(0,255,0))) {public void actionPerformed(ActionEvent ae){closeEditor();}};
		
		menuActs=new Action[][]{
						new Action[]{clearAct,openAct,saveAct,saveAsAct,previewAct,confirmAct,closeAct},
						new Action[]{undoAct,redoAct,cutAct,copyAct,pasteAct,findAct},
						new Action[]{zoomInAct,zoomOutAct},
						new Action[]{helpAct,guideAct}/*,
						new Action[]{}*/
					   };
		
		separators=new int[][]{ 
				new int[]{1,4},
				new int[]{1,6},
				new int[]{2,2},
				new int[]{2,5}
				     };
		
		
		setupFrameWithMenu(FRAMETITLEPREFIX+"", SIZEX, SIZEY, menuNames,  menuActs, separators, true);
//		setIconImage(icon);	//TODO
		
		editor = this;
		editorUp=true;
		
//		actionOnClose = actionOnCloseIn;
		zoom=100;
		
		
		//TODO this.setMaximumSize(maximumSize);
				

/*		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		int maxx = ge.getMaximumWindowBounds().width, my = ge.getCenterPoint().y;//ge.getCenterPoint().x, my = ge.getCenterPoint().y;		
		this.setLocation(maxx-SIZEX, my-SIZEY/2);*/

		this.setLocation(CaseViewer.getScreenInfo().getReportScreenCenter().x - SIZEX/2, CaseViewer.getScreenInfo().getReportScreenCenter().y - SIZEY/2);
		this.setIgnoreRepaint(false);
		this.addWindowFocusListener(new WindowFocusListener(){	
			public void windowGainedFocus(WindowEvent arg0) {
				validate();
				repaint();
			}
	
			public void windowLostFocus(WindowEvent arg0) {					
			}	
		});	
		this.addWindowStateListener(new WindowStateListener(){
			@Override
			public void windowStateChanged(WindowEvent arg0) {
				validate();
				repaint();				
			}
			});
		toolbar = new ToolBarWithBottomBorder(); 
		setupToolbar();
				
		
		JPanel editorBackPanel = getBackPane();
		
		editorScrollPanel = new JScrollPane();
		editorScrollPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		editorScrollPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
//		editorScrollPanel.setBorder(BorderFactory.createEmptyBorder());
		
		JPanel scrollField = new JPanel();
		editorScrollPanel.getViewport().setView(scrollField);
		//scrollField.setBackground(new Color(255,255,255));
		scrollField.setBackground(EDITORBACKGROUNDCOLOR);
//		scrollField.setBorder(BorderFactory.createLoweredBevelBorder());
		
		editorPane = new JEditorPane();
//		editorPane.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(3,3,3,3),BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(),//.createLineBorder(new Color(150,150,150)),//.createRaisedBevelBorder(),//.createEtchedBorder(),
//				BorderFactory.createEmptyBorder(0,0,0,0))));
/*		editorPane.setBorder(BorderFactory.createCompoundBorder(
								BorderFactory.createLineBorder(new Color(170,170,170),1),
								BorderFactory.createEmptyBorder(10,10,10,10) )	);		
		editorPane.setBackground(new Color(253,253,253));*/
/*		editorPane.setBorder(BorderFactory.createCompoundBorder(
								BorderFactory.createEtchedBorder(),
								BorderFactory.createEmptyBorder(10,10,10,10) )	);	*/	
		editorPane.setBorder(BorderFactory.createCompoundBorder(																		
								BorderFactory.createMatteBorder(0,0,0,0,CaseViewer.DISPLAYBACKGROUNDCOLOR),
								BorderFactory.createCompoundBorder(
										BorderFactory.createLineBorder(Color.DARK_GRAY,1),										
										BorderFactory.createEmptyBorder(45,30,45,30) ) ) );
		editorPane.setSize(EDITORFIELDSIZE);
		editorPane.setPreferredSize(EDITORFIELDSIZE);
		editorPane.setMinimumSize(EDITORFIELDSIZE);
		editorPane.setMaximumSize(EDITORFIELDSIZE);
		
		
		GridBagConstraints gbc=new GridBagConstraints();
		scrollField.setLayout(new GridBagLayout());
		gbc.fill=GridBagConstraints.NONE;		
		gbc.gridheight=1;
		gbc.gridwidth=1;
		gbc.weightx=1;
		gbc.weighty=1;		
		gbc.gridx=0;
		gbc.gridy=0;	
		gbc.insets = new Insets(15,25,15,25);
		scrollField.add(editorPane,gbc);
		
		
		
		editorBackPanel.setLayout(new GridBagLayout());
		gbc.fill=GridBagConstraints.BOTH;		
		gbc.gridheight=1;
		gbc.weightx=1;
		gbc.gridx=0;
		gbc.gridy=0;
		
		gbc.weighty=0;
		gbc.insets = new Insets(0,0,0,0);
		editorBackPanel.add(toolbar,gbc);

		gbc.gridy=1;
		gbc.weighty=1;
		gbc.insets = new Insets(5,5,5,5);

		editorBackPanel.add(editorScrollPanel ,gbc);
		
		
	}
	
	
	
	
	
	private void setupToolbar(){
		
	    toolbar.setFloatable(false);
//		toolbar.setRollover(true);
		boolean smoothButtons = true;
		
		
		
		GridBagConstraints gbc=new GridBagConstraints();
		toolbar.setLayout(new GridBagLayout());
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridy=0;
		gbc.weighty=0;
		gbc.weightx=0;
		
		
		
		Icon sepIcon = GUIUtility.loadIcon(TBIsepFN, new Color(0,255,0));
		Icon fillerIcon = GUIUtility.loadIcon(TBIfillerFN, new Color(0,255,0));
		
		Icon  clearReportIcon = GUIUtility.loadIcon(TBIclearReportFN, new Color(0,255,0));		
		Icon  openReportIcon = GUIUtility.loadIcon(TBIopenReportFN, new Color(0,255,0));		
		Icon  saveIcon = GUIUtility.loadIcon(TBIsaveFN, new Color(0,255,0));		
		Icon  findIcon = GUIUtility.loadIcon(TBIfindFN, new Color(0,255,0));
		Icon  redoIcon = GUIUtility.loadIcon(CaseViewer.TBIredoFN, new Color(0,255,0));
		Icon  undoIcon = GUIUtility.loadIcon(CaseViewer.TBIundoFN, new Color(0,255,0));
		
		Icon  zoomInIcon = GUIUtility.loadIcon(TBIzoomInFN, new Color(0,255,0));		
		Icon  zoomOutIcon = GUIUtility.loadIcon(TBIzoomOutFN, new Color(0,255,0));		
		Icon  cutIcon = GUIUtility.loadIcon(TBIcutFN, new Color(0,255,0));		
		Icon  copyIcon = GUIUtility.loadIcon(TBIcopyFN, new Color(0,255,0));		
		Icon  pasteIcon = GUIUtility.loadIcon(TBIpasteFN, new Color(0,255,0));		
//		Icon  boldIcon = GUIUtility.loadIcon(TBIboldFN, new Color(0,255,0));		
		Icon  titleIcon = GUIUtility.loadIcon(TBItitleFN, new Color(0,255,0));
		Icon  normalIcon = GUIUtility.loadIcon(TBInormalFN, new Color(0,255,0));	
		Icon  confirmIcon = GUIUtility.loadIcon(TBIconfirmFN, new Color(0,255,0));
		Icon  previewIcon = GUIUtility.loadIcon(TBIpreviewFN, new Color(0,255,0));
		
		clearReportButton = new SimpleToolBarButton(clearReportIcon, "Clear Report", "Clear",  null, smoothButtons);		
		openReportButton = new SimpleToolBarButton(openReportIcon, "Open Report", "Open: open an alternative version of the report",  null, smoothButtons);		
		saveReportButton = new SimpleToolBarButton(saveIcon, "Save Report", "Save",  null, smoothButtons);		
		previewButton = new SimpleToolBarButton(pasteIcon, "Preview", "Preview",  null, smoothButtons);
//		findButton = new SimpleToolBarButton(clearReportIcon, "Clear Report", "Clear",  null, smoothButtons);	
		
		undoButton = new SimpleToolBarButton(undoIcon, "Undo", "Undo", null, smoothButtons);  
		redoButton = new SimpleToolBarButton(redoIcon, "Redo", "Redo",  null, smoothButtons);       
		cutButton = new SimpleToolBarButton(cutIcon, "Cut", "Cut",  null, smoothButtons);		
		copyButton = new SimpleToolBarButton(copyIcon, "Copy", "Copy",  null, smoothButtons);		
		pasteButton = new SimpleToolBarButton(pasteIcon, "Paste", "Paste",  null, smoothButtons);		
		previewButton = new SimpleToolBarButton(previewIcon, "Preview", "Preview",  null, smoothButtons);

		zoomInButton = new SimpleToolBarButton(zoomInIcon, "Zoom In", "Zoom In",  null, smoothButtons);		
		zoomOutButton = new SimpleToolBarButton(zoomOutIcon, "Zoom Out", "Zoom Out",  null, smoothButtons);		

		/*boldButton = new ToolBarToggleButton(boldIcon, "Bold", "Bold",  null, smoothButtons);*/
		titleButton = new ToolBarToggleButton(titleIcon, "Title", "Title",  null, smoothButtons);
		normalButton = new ToolBarToggleButton(normalIcon, "Normal", "Normal text",  null, smoothButtons);
		
		confirmButton = new SimpleToolBarButton(confirmIcon, "Confirm", "Confirm & Submit: declare validity and correctness and submit",  null, smoothButtons);
		
		fontButtons = new ButtonGroup(); 
		fontButtons.add(titleButton);	
		fontButtons.add(normalButton);			
		
//		toolbar.add(new ApplCanvas(sepIcon));
		toolbar.add(new ToolBarSeparator(sepIcon));

		toolbar.add(clearReportButton);
		toolbar.add(openReportButton);
		toolbar.add(saveReportButton);
		toolbar.add(previewButton);

	    toolbar.addSeparator();	
	    toolbar.add(new ToolBarSeparator(sepIcon));	
		
		toolbar.add(undoButton);
		toolbar.add(redoButton);
		toolbar.add(cutButton);
		toolbar.add(copyButton);
		toolbar.add(pasteButton);
		
	    toolbar.addSeparator();	
	    toolbar.add(new ToolBarSeparator(sepIcon));
	    
		toolbar.add(zoomInButton);
		toolbar.add(zoomOutButton);
	    
	    
	    toolbar.addSeparator();
	    zoomLabel = new JLabel((int)zoom+"%");
	    zoomLabel.setFont(new Font("Arial",Font.PLAIN,12));
	    zoomLabel.setToolTipText("The current zoom");
	    toolbar.add(zoomLabel);
		
		
	    toolbar.addSeparator();	
	    toolbar.add(new ToolBarSeparator(sepIcon));	
	    
/*	    fontSizeChooser = new JComboBox();
	    fontSizeChooser.setMaximumSize(FONTCHOOSERSIZE);
	    fontSizeChooser.setMinimumSize(FONTCHOOSERSIZE);
	    fontSizeChooser.setSize(FONTCHOOSERSIZE);
	    fontSizeChooser.setPreferredSize(FONTCHOOSERSIZE);
	    fontSizeChooser.setToolTipText("Preset Windoews: choose a preset window for the selected displays");
	    toolbar.add(fontSizeChooser); //TODO
	    
	    toolbar.addSeparator();		    
	    
	    toolbar.add(boldButton);*/
	    toolbar.add(titleButton);
	    toolbar.add(normalButton);	    
	    
//	    toolbar.addSeparator();	
//	    toolbar.add(new ToolBarSeparator(sepIcon));	
	    
		toolbar.add(confirmButton);
	    
		gbc.weightx=1;
	    toolbar.add(new JLabel(fillerIcon),gbc);
		
//		gbc.weightx=0;
		toolbar.add(confirmButton);
		toolbar.addSeparator();
		
		
		titleButton.setSelected(true);
		
	}
	
	
	
	
	public boolean closing(){
		
		//TODO check window
	
		return true;
	}
	
	
/*	private void close(){
		actionOnClose.actionPerformed(new ActionEvent(this, 0, ""));		
	}*/
	
	public static boolean isEditorUp(){return editorUp;	}
//	public static void setEditorUp(boolean editorOpen){ editorUp = editorOpen;	}
	public static void setEditorIsUp(){ editorUp = true;	}
	public static void closeReportEditor()
	{ 
		editor.dispose(); 
		editor = null; 
		editorUp = false;	
	}

	
	
	public static void refreshEditor(){
		editor.validate();
		editor.repaint();		
	}
	
	
}
