package tdsdicomimage;

public class ImageWindow
{
	private String name;
	private double center;
	private double width;

	public ImageWindow(String name, double center, double width)
	{
		this.name = name;
		this.center = center;
		this.width = width;
	}

	/**
	 * @return the name
	 */ public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */ public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the center
	 */ public double getCenter() {
		return center;
	}

	/**
	 * @param center the center to set
	 */ public void setCenter(double center) {
		this.center = center;
	}

	/**
	 * @return the width
	 */ public double getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */ public void setWidth(double width) {
		this.width = width;
	}

}
