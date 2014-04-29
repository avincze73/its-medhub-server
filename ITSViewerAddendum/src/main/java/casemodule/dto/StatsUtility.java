package casemodule.dto;

public class StatsUtility {

	
	public static double sum(double[] v){
		double sum=0;
		for(int i=0; i<v.length; i++)
			sum+=v[i];
		return sum;
	}

	public static double mean(double[] v){
		return sum(v)/v.length;
	}

	public static int sum(short[] v){
		int sum=0;
		for(int i=0; i<v.length; i++)
			sum+=v[i];
		return sum;
	}

	public static int sum(int[] v){
		int sum=0;
		for(int i=0; i<v.length; i++)
			sum+=v[i];
		return sum;
	}

	public static double mean(short[] v){
		return sum(v)/v.length;
	}
	
	public static double mean(int[] v){
		return sum(v)/v.length;
	}
	

	public static double var(short[] v, double mean){
		double sumSqrDiff = 0;
		for (int i=0; i< v.length; i++)
			sumSqrDiff += Math.pow( v[i]-mean, 2);
		return sumSqrDiff/v.length;
	}
	
	public static double var(int[] v, double mean){
		double sumSqrDiff = 0;
		for (int i=0; i< v.length; i++)
			sumSqrDiff += Math.pow( v[i]-mean, 2);
		return sumSqrDiff/v.length;
	}

	public static double var(double[] v, double mean){
		double sumSqrDiff = 0;
		for (int i=0; i< v.length; i++)
			sumSqrDiff += Math.pow( v[i]-mean, 2);
		return sumSqrDiff/v.length;
	}

	public static double stdev(short[] v, double mean){
		return Math.sqrt(var(v,mean));
	}
	
	
	public static double stdev(int[] v, double mean){
		return Math.sqrt(var(v,mean));
	}

	public static double stdev(double[] v, double mean){
		return Math.sqrt(var(v,mean));
	}
	

	public static double var(int[] v){
		double mean = mean(v);
		double sumSqrDiff = 0;
		for (int i=0; i< v.length; i++)
			sumSqrDiff += Math.pow( v[i]-mean, 2);
		return sumSqrDiff/v.length;
	}

	public static double var(double[] v){
		double mean = mean(v);
		double sumSqrDiff = 0;
		for (int i=0; i< v.length; i++)
			sumSqrDiff += Math.pow( v[i]-mean, 2);
		return sumSqrDiff/v.length;
	}
	
	public static double stdev(int[] v){
		return Math.sqrt(var(v));
	}

	public static double stdev(double[] v){
		return Math.sqrt(var(v));
	}

	
	
	
}
