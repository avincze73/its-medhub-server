package dicommodule.processing;


public class SingleBooleanWithState extends SingleValueWithState{
	public Boolean value;

	public SingleBooleanWithState(Boolean value){	this.value = value;	}
	
	public static SingleBooleanWithState createEmpty(){	return new SingleBooleanWithState(null); }

	@Override
	public boolean isEmpty() { return value ==null;	}

	@Override
	public String toString() {
		if(isEmpty())
			return "Empty SingleBooleanWithState";
		else
			return value.toString();
	}

	
}
