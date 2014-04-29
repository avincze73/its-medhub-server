package tdsclients.radClient;

import tdsclients.radClient.imaging.DisplayModel;

public class UndoRedoModel {

	
	/*	STRUCTURE:
	 * 
	 * the RP (reference point is: physicalPointerOfUndoTop)
	 * 
	 * 	[          Undo list (stack)         ][    Redo list (upward stack)     ]
	 * 	[      oldest    .. newest = undotop ][ first to redo ..  last to redo  ]
	 *  [ RP-undoDepth+1 ..        RF        ][     RF+1      ..  RF+redoDepth  ]
	 * */
	
	//model
	
	public static final int MAXSTACKDEPTH = 5;
	

	private int numDisplays;
	private DisplayModel[][] undoRedoStack; 	// undo stack grows downwards, redo stack grows upwards
	private int undoDepth, redoDepth;				// logical indexes
	private int physicalPointerOfUndoTop; 			// for physical mapping
	
	
	//constructor
	
/*	public UndoRedoModel(DisplayImageFrame[] actImDispFrames){
		undoRedoStack = new DisplayImageFrame[MAXSTACKDEPTH][actImDispFrames.length];
		physicalPointerOfUndoTop = 0;
		undoDepth = 0;
		redoDepth = 0;		
		undoRedoStack[physicalPointerOfUndoTop] = actImDispFrames;		
	}*/
	
	public UndoRedoModel(int numDispFrames){
		numDisplays = numDispFrames;
		undoRedoStack = new DisplayModel[MAXSTACKDEPTH][numDisplays];
		physicalPointerOfUndoTop = 0;
		undoDepth = 0;
		redoDepth = 0;	
	}

	
	
	//main methods	
	
	public void pushNewState(DisplayModel[] newStateToSave){		//updating the stack
		
		int prevUndoDepth = undoDepth;
		if(redoDepth==0)    // normal case: new state added, when the undoTop is shown
		{	if(undoDepth < MAXSTACKDEPTH) undoDepth++;		}
		else				// special case: new state added after browsing the undo stack
		{
			redoDepth =0;
			undoDepth++;
		}		
		
		if(prevUndoDepth > 0)				//save at position if no undolist, but save at next position if there is at least one element of the undo list
			incrementActPointer();
		
		//freeMemory(physicalPointerOfUndoTop);
		System.gc();
		
		undoRedoStack[physicalPointerOfUndoTop] = newStateToSave;		
		printURStack();
	}
	

	public DisplayModel[] undoBrowsingStep(DisplayModel[] actStateToSave){
		if(undoDepth>0) 
		{
			redoDepth++;
			undoDepth--;
			
			int positionToPutNewStateIn = mapUndoLtoP(1);		//this is the previous undo top, which is going to be the next redo top
			
			DisplayModel[] ans = undoRedoStack[positionToPutNewStateIn];
			//freeMemory = ;
			undoRedoStack[positionToPutNewStateIn] = actStateToSave;
			decrementActPointer();
			//System.gc();
			printURStack();
			return ans;	
		}
		else 
		{
			printURStack();			
			return null;
		}
	}	
	
	
	public DisplayModel[] redoBrowsingStep(DisplayModel[] actStateToSave){
		
		if(redoDepth>0) 
		{
			undoDepth++;
			redoDepth--;
			
			int positionToPutNewStateIn = mapRedoLtoP(1);		//this is the previous redo top, which is going to be the next undo top

			DisplayModel[] ans = undoRedoStack[positionToPutNewStateIn];
			//freeMemory = ;
			undoRedoStack[positionToPutNewStateIn] = actStateToSave;
			incrementActPointer();
			//System.gc();
			printURStack();
			return ans;	
		}
		else 
		{
			printURStack();			
			return null;
		}
	}	
	
	

	
	
	
	//auxiliary methods	
	public boolean isUndoPossible(){return undoDepth > 0;}
	public boolean isRedoPossible(){return redoDepth > 0;}
		
	private void incrementActPointer(){
		if ( physicalPointerOfUndoTop == MAXSTACKDEPTH-1)  physicalPointerOfUndoTop = 0;
		else physicalPointerOfUndoTop++;
	}
	
	private void decrementActPointer(){
		if ( physicalPointerOfUndoTop == 0)  physicalPointerOfUndoTop = MAXSTACKDEPTH-1;
		else physicalPointerOfUndoTop--;
	}
	
	
	
	
	public boolean[] getFirstUndoChangeIndicators(){
		
		boolean[] indicator = new boolean[numDisplays];
		
		for (int i=0; i<numDisplays; i++)
			indicator[i] = (undoRedoStack[mapUndoLtoP(1)][i]!=null);
				
		return indicator;
	}
	
	
	
	public boolean[] getFirstRedoChangeIndicators(){
		
		boolean[] indicator = new boolean[numDisplays];
		
		for (int i=0; i<numDisplays; i++)
			indicator[i] = (undoRedoStack[mapRedoLtoP(1)][i]!=null);
				
		return indicator;
	}
	
	
	
	private void printURStack()
	{
		System.out.println("***\nThe act state of the undo-redo stack:");
		System.out.println("\tActPhysicalOfUndoTop = "+physicalPointerOfUndoTop+", undoDepth = "+undoDepth+", redoDepth = "+redoDepth);
/*		System.out.print("\tThe Physical List (top first, bottom last): [");
		for(int i=MAXSTACKDEPTH-1; i>=0; i--)
			System.out.print(undoRedoStack[i]+",");		
		System.out.print("]\n\tThe Redo List (top first, bottom last): [");
		for(int i=1; i<=redoDepth; i++)
			System.out.print(undoRedoStack[mapRedoLtoP(i)]+",");
		System.out.println("]\n\tActual shown: "+undoRedoStack[physicalPointerOfUndoTop] );
		System.out.print("\tThe Undo List (top first, bottom last): [");
		for(int i=1; i<=undoDepth; i++)
			System.out.print(undoRedoStack[mapUndoLtoP(i)]+",");*/
		System.out.println("]\n***");
	}
	
	
	private int mapUndoLtoP(int uLogInd)
	{
		int ans;
		if(physicalPointerOfUndoTop-uLogInd+1 <0)
			ans = physicalPointerOfUndoTop-uLogInd+1+MAXSTACKDEPTH;
		else
			ans = physicalPointerOfUndoTop-uLogInd+1;
		return ans;
	}
	
	

	private int mapRedoLtoP(int rLogInd)
	{
		int ans;
		if(physicalPointerOfUndoTop+rLogInd >MAXSTACKDEPTH-1)
			ans = physicalPointerOfUndoTop+rLogInd-MAXSTACKDEPTH;
		else
			ans = physicalPointerOfUndoTop+rLogInd;
		return ans;
	}
	
}


/*	private void freeMemory(int indexToFree){
for(int i=0; i< undoRedoStack[indexToFree].length; i++)
{
	if (undoRedoStack[indexToFree][i]!=null)
	{
		undoRedoStack[indexToFree][i].finalize();
		undoRedoStack[indexToFree][i]=null;
	}			
}
undoRedoStack[indexToFree] = null;

}*/


/*
 //FOR TESTING
public class UndoRedoModel {

	
	//model
	
	public static final int MAXSTACKDEPTH = 6;
	
	private String[] undoRedoStack;
//	private DisplayImageFrame[][] undoRedoStack; 	// undo stack grows downwards, redo stack grows upwards
	private int undoDepth, redoDepth;				// logical indexes
	private int physicalPointerOfActual; 			// for physical mapping
	
	//constructor
	
//	public UndoRedoModel(DisplayImageFrame[] actImDispFrames){
	public UndoRedoModel(String actImDispFrames){
//		undoRedoStack = new DisplayImageFrame[MAXSTACKDEPTH][actImDispFrames.length];
		undoRedoStack = new String[MAXSTACKDEPTH];
		physicalPointerOfActual = 0;
		undoDepth = 0;
		redoDepth = 0;		
		undoRedoStack[physicalPointerOfActual] = actImDispFrames;		
	}
	

	
	
	//main methods	
	
	public void pushNewState(String newState){
//	public void pushNewState(DisplayImageFrame[] newState){
		
		if(redoDepth==0)    // normal case: new state added, when the undoTop is shown
		{	if(undoDepth < MAXSTACKDEPTH-1) undoDepth++;		}
		else				// special case: new state added after browsing the undo stack
		{
			redoDepth =0;
			undoDepth++;
		}
		
		incrementActPointer();
		undoRedoStack[physicalPointerOfActual] = newState;		
		printURStack();
	}
	

	public String undoBrowsingStep(){
//	public DisplayImageFrame[] undoBrowsingStep(){
		if(undoDepth>0) 
		{
			redoDepth++;
			undoDepth--;
			decrementActPointer();
		}
		printURStack();
		return undoRedoStack[physicalPointerOfActual];		
	}	
	
	
	public String redoBrowsingStep(){
//	public DisplayImageFrame[] redoBrowsingStep(){
		if(redoDepth>0) 
		{
			undoDepth++;
			redoDepth--;
			incrementActPointer();
		}
		printURStack();
		return undoRedoStack[physicalPointerOfActual];
	}	
	
	
	//auxiliary methods	
	public boolean isUndoPossible(){return undoDepth > 0;}
	public boolean isRedoPossible(){return redoDepth > 0;}
	
	
	private void incrementActPointer(){
		if ( physicalPointerOfActual == MAXSTACKDEPTH-1)  physicalPointerOfActual = 0;
		else physicalPointerOfActual++;
	}
	
	private void decrementActPointer(){
		if ( physicalPointerOfActual == 0)  physicalPointerOfActual = MAXSTACKDEPTH-1;
		else physicalPointerOfActual--;
	}
	
	
	
	//for testing:

	private void printURStack()
	{
		System.out.println("***\nThe act state of the undo-redo stack:");
		System.out.println("\tActPhysical = "+physicalPointerOfActual+", undoDepth = "+undoDepth+", redoDepth = "+redoDepth);
		System.out.print("\tThe Physical List (top first, bottom last): [");
		for(int i=MAXSTACKDEPTH-1; i>=0; i--)
			System.out.print(undoRedoStack[i]+",");		
		System.out.print("]\n\tThe Redo List (top first, bottom last): [");
		for(int i=1; i<=redoDepth; i++)
			System.out.print(undoRedoStack[mapRedoLtoP(i)]+",");
		System.out.println("]\n\tActual shown: "+undoRedoStack[physicalPointerOfActual] );
		System.out.print("\tThe Undo List (top first, bottom last): [");
		for(int i=1; i<=undoDepth; i++)
			System.out.print(undoRedoStack[mapUndoLtoP(i)]+",");
		System.out.println("]\n***");
	}
	
	
	private int mapUndoLtoP(int uLogInd)
	{
		int ans;
		if(physicalPointerOfActual-uLogInd <0)
			ans = physicalPointerOfActual-uLogInd+MAXSTACKDEPTH;
		else
			ans = physicalPointerOfActual-uLogInd;
		return ans;
	}

	private int mapRedoLtoP(int rLogInd)
	{
		int ans;
		if(physicalPointerOfActual+rLogInd >MAXSTACKDEPTH-1)
			ans = physicalPointerOfActual+rLogInd-MAXSTACKDEPTH;
		else
			ans = physicalPointerOfActual+rLogInd;
		return ans;
	}
	
	
}*/
