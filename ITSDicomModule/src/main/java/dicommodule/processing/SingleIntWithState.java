package dicommodule.processing;


public class SingleIntWithState  extends SingleValueWithState{

	public Integer value;

	public SingleIntWithState(Integer value){	this.value = value;	}
	
	public static SingleIntWithState createEmpty(){	return new SingleIntWithState(null); }

	@Override
	public boolean isEmpty() { return value ==null;	}

	@Override
	public String toString() {
		if(isEmpty())
			return "Empty SingleIntWithState";
		else
			return value.toString();
	}

}
