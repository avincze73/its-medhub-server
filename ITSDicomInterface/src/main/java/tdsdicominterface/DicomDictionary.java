package tdsdicominterface;

import java.util.HashMap;

/**
 *
 * @author Rob
 */
public class DicomDictionary
{
	private static final char[] HexNums = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};

	private static HashMap<Integer, DDItem> items;		// attributum tag alapjan lehet keresni
	private static HashMap<String, DDItem> items2;		// attributum nev alapjan lehet keresni

	/**
	 * Visszaadja a megadott tag-hez tartozó VR-t.
	 * @param group
	 * @param element
	 * @return a VR. Ha a tag nem található (mert pl. private), akkor null
	 */
	public static String getVR(int group, int element)
	{
		if(items == null) fillItems();
		Integer key = (group << 16) + element;
		DDItem ddi = items.get(key);
		if(ddi != null) return ddi.getVr();
		else return null;
	}

	/**
	 * VR kod meghatarozasa attributum nev alapjan
	 * @param attributeName
	 * @return
	 */
	public static String getVR(String attributeName)
	{
		if(items == null) fillItems();
		DDItem ddi = items2.get(attributeName.toUpperCase());
		if(ddi != null)
		{
			return ddi.getVr();
		}
		else return null;
	}

	public static String getItemName(int group, int element)
	{
		if(items == null) fillItems();
		Integer key = (group << 16) + element;
		DDItem ddi = items.get(key);
		if(ddi != null) return ddi.getName();
		else return null;
	}

	/**
	 * Attributum tag meghatározása attributum név alapján
	 * @param attributeName
	 * @return az Integer tartalma 0xggggeeee, ahol gggg a group number, eeee az element number
	 */
	public static Integer getItemTag(String attributeName)
	{
		if(items == null) fillItems();
		DDItem ddi = items2.get(attributeName.toUpperCase());
		if(ddi != null)
		{
			return (ddi.getGroup() << 16) + ddi.getElement();
		}
		else return null;
	}

	public static DDItem getDDItem(int group, int element)
	{
		if(items == null) fillItems();
		Integer key = (group << 16) + element;
		return items.get(key);
	}

	public static DDItem getDDItem(String attributeName)
	{
		if(items == null) fillItems();
		return items2.get(attributeName.toUpperCase());
	}

	//---------------------------------------------

	private static void fillItems()
	{
		items = new HashMap<Integer, DDItem>();
		items2 = new HashMap<String, DDItem>();
		for(int i = 0; i < DicomDictionaryItems1.DictItems.length; i++)
		{
			String s = DicomDictionaryItems1.DictItems[i][0];
			createItem(s.substring(0,4),
					s.substring(4,8),
					s.substring(8,10),
					DicomDictionaryItems1.DictItems[i][1],
					DicomDictionaryItems1.DictItems[i][2]);
		}
		for(int i = 0; i < DicomDictionaryItems2.DictItems.length; i++)
		{
			String s = DicomDictionaryItems2.DictItems[i][0];
			createItem(s.substring(0,4),
					s.substring(4,8),
					s.substring(8,10),
					DicomDictionaryItems2.DictItems[i][1],
					DicomDictionaryItems2.DictItems[i][2]);
		}
	}

	private static void createItem(String groupStr, String elementStr, String vr, String name, String vm)
	{
		if(groupStr.contains("x"))
		{
			char[] groupCh = groupStr.toCharArray();
			int pos = groupStr.indexOf("x");
			for(int i = 0; i < 16; i++)
			{
				groupCh[pos] = HexNums[i];
				createItem(String.valueOf(groupCh), elementStr, vr, name, vm);
			}
		}
		else
		{
			int group = Integer.parseInt(groupStr, 16);
			int element = Integer.parseInt(elementStr, 16);
			DDItem ddi = new DDItem(group, element, vr,	name, vm);
			Integer key = (group << 16) + element;
			items.put(key, ddi);
			items2.put(name.toUpperCase(), ddi);
		}
	}

	public static class DDItem
	{
		private int group;
		private int element;
		private String vr;
		private String name;
		private String vm;

		public DDItem(int group, int element, String vr, String name, String vm)
		{
			this.group = group;
			this.element = element;
			this.vr = vr;
			this.name = name;
			this.vm = vm;
		}

		/**
		 * @return the group
		 */ public int getGroup() {
			return group;
		}

		/**
		 * @return the element
		 */ public int getElement() {
			return element;
		}

		/**
		 * @return the vr
		 */ public String getVr() {
			return vr;
		}

		/**
		 * @return the name
		 */ public String getName() {
			return name;
		}

		/**
		 * @return the vm
		 */ public String getVm() {
			return vm;
		}
	}
}
