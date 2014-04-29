package tdsdicominterface;

import java.io.Serializable;
import java.util.ArrayList;
import tdsdicominterface.exception.DIInternalErrorException;

/**
 *
 * @author Rob
 */
public class EncapsulatedData implements Serializable, Cloneable
{
	private long[] basicOffsetTable;
	private ArrayList<DataFrame> frames;
	private long length;

	public EncapsulatedData()
	{
		frames = new ArrayList<DataFrame>();
		length = 0;
	}

	@Override
	public Object clone()
	{
		try
		{
			EncapsulatedData encData = (EncapsulatedData) super.clone();
			if(basicOffsetTable != null)
				encData.setBasicOffsetTable(basicOffsetTable.clone());
			ArrayList<DataFrame> frames2 = new ArrayList<DataFrame>();
			encData.setFrames(frames2);
			for(int i = 0; i < frames.size(); i++)
			{
				frames2.add((DataFrame) frames.get(i).clone());
			}
			return encData;
		}
		catch (CloneNotSupportedException ex)
		{
			throw new DIInternalErrorException("Clone problem.");
		}
	}

	//----------------------------------------------------------------------------

	/**
	 * @return the basicOffsetTable
	 */ public long[] getBasicOffsetTable() {
		return basicOffsetTable;
	}

	/**
	 * @param basicOffsetTable the basicOffsetTable to set
	 */ public void setBasicOffsetTable(long[] basicOffsetTable) {
		this.basicOffsetTable = basicOffsetTable;
	}

	/**
	 * @return the length
	 */ public long getLength() {
		return length;
	}

	/**
	 * @param length the length to set
	 */ public void setLength(long length) {
		this.length = length;
	}

	/**
	 * @return the frames
	 */ public ArrayList<DataFrame> getFrames() {
		return frames;
	}

	/**
	 * @param frames the frames to set
	 */ public void setFrames(ArrayList<DataFrame> frames) {
		this.frames = frames;
	}

	//===============================================================================================

	public static class DataFrame implements Serializable, Cloneable
	{
		private ArrayList<DataFragment> fragments;

		public DataFrame()
		{
			fragments = new ArrayList<DataFragment>();
		}

		@Override
		public Object clone()
		{
			try
			{
				DataFrame dframe = (DataFrame) super.clone();
				ArrayList<DataFragment> fragments2 = new ArrayList<DataFragment>();
				dframe.setFragments(fragments2);
				for(int i = 0; i < fragments.size(); i++)
				{
					fragments2.add((DataFragment) fragments.get(i).clone());
				}
				return dframe;
			}
			catch (CloneNotSupportedException ex)
			{
				throw new DIInternalErrorException("Clone problem.");
			}
		}

		/**
		 * @return the fragments
		 */ public ArrayList<DataFragment> getFragments() {
			return fragments;
		}

		/**
		 * @param fragments the fragments to set
		 */ public void setFragments(ArrayList<DataFragment> fragments) {
			this.fragments = fragments;
		}
	}

	//===============================================================================================

	public static class DataFragment implements Serializable, Cloneable
	{
		private byte[] data;

		@Override
		public Object clone()
		{
			try
			{
				DataFragment df = (DataFragment) super.clone();
				df.setData(data.clone());
				return df;
			}
			catch (CloneNotSupportedException ex)
			{
				throw new DIInternalErrorException("Clone problem.");
			}
		}

		/**
		 * @return the data
		 */ public byte[] getData() {
			return data;
		}

		/**
		 * @param data the data to set
		 */ public void setData(byte[] data) {
			this.data = data;
		}
	}
}
