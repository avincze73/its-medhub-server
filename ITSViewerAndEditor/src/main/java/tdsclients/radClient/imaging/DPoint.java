package tdsclients.radClient.imaging;

import java.awt.Point;
import java.awt.geom.Point2D;

public class DPoint extends Point2D.Double{
	
//	public double x,y;
	public DPoint(double x, double y) { super(x,y);	}
	
	
//	public double distanceFrom(double pX, double pY){	return Point.distance(x, y, pX, pY);	}
	
//	public Point round(){	return new Point((int)Math.round(x), (int)Math.round(y));	}
//	public Point roundIntoImageGrid(){	return new Point((int)Math.round(x-0.5), (int)Math.round(y-0.5));	}
//	public Point roundIntoImageGrid(){	return new Point(Math.max(0,(int)Math.floor(x-0.5)), Math.max(0,(int)Math.floor(y-0.5)));	}
//	public Point roundIntoImageGrid(){	return new Point((int)Utility.roundTowardsZero(x-0.5), (int)Utility.roundTowardsZero(y-0.5));	}
	public Point roundIntoImageGrid(){	return Utility.roundPointIntoImageGrid(this);	}
//	public Point roundIntoImageGrid(){	return new Point((int)Math.round(x), (int)Math.round(y));	}
//	public Point floor(){	return new Point((int)Math.floor(x), (int)Math.floor(y));	}
	
/*	public static Point round(DPoint dpoint){
		return new Point((int)Math.round(dpoint.x), (int)Math.round(dpoint.y));
	}
*/	
}
