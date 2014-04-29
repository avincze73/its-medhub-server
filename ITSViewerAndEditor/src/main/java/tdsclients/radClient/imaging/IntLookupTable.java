package tdsclients.radClient.imaging;

import java.awt.image.LookupTable;

public class IntLookupTable extends LookupTable {

	private final int[][]table;
	
	public IntLookupTable(int offset, int[] data){
		super(offset,1);
		this.table = new int[][]{data};
	}

	public IntLookupTable(int offset, int[][] data){
		super(offset,data.length);
		this.table = data;
	}
	
	public final int[][] getTable(){	return table;	}
	
		
	@Override
	public int[] lookupPixel(int[] src, int[] dest) {
		int lsrc = src.length;

		if(dest==null) dest = new int[lsrc];
		
		if(table.length == 1)
			for(int i=0; i<lsrc;i++)
			{
//				System.out.println("Lookupindex:"+(src[i]-getOffset()));
				dest[i]=table[0][src[i]-getOffset()];
			}
		else
			for(int i=0; i<lsrc;i++)
			{
//				System.out.println("Lookupindex:"+(src[i]-getOffset()));
				dest[i]=table[i][src[i]-getOffset()];
			}
			
		return dest;
	}
	
	

}
