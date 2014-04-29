package dicommodule.processing;


public class SingleDoubleWithState  extends SingleValueWithState{
	public Double value;

	public SingleDoubleWithState(Double value){	this.value = value;	}
	
	public static SingleDoubleWithState createEmpty(){	return new SingleDoubleWithState(null); }

	@Override
	public boolean isEmpty() { return value ==null;	}

	@Override
	public String toString() {
		if(isEmpty())
			return "Empty SingleDoubleWithState";
		else
			return value.toString();
	}

}
