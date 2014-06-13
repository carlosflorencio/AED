package serie3.structures;

public class TNode {
	
	public boolean isWord;
	public char letter;
	public TNode [] ways;
	

	
	public TNode(char c){
		ways = new TNode[26];
		letter = c;
		isWord=false;
	}
	
	public TNode(char c, boolean word){
		ways = new TNode[26];
		letter = c;
		isWord=word;
	}

	public TNode() {
		ways = new TNode[26];
	}

}
