package serie3;

public class Pair{
	
	int key;
	Edge elem;
	public Pair(int key, Edge elem){
		this.key = key;
		this.elem = elem;
	}
	
	public int getKey(){
		return this.key;
	}
	
	public Edge getValue(){
		return this.elem;
	}

}
