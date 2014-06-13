package serie3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import serie3.structures.TNode;

public class AutoCompleteUtils {
	
	public static TNode loadWordsFromFile(TNode root, String fileName) throws IOException{
		
		BufferedReader br;
		br = new BufferedReader(new FileReader(fileName));
		root = catchWords(root,br);
		br.close();  
		return root;
	}

	private static TNode catchWords(TNode root, BufferedReader br) throws IOException {
		
		String word;
		
		 // while can find words in stream  
	     while ((word=br.readLine()) != null){
	    	 insertWord(root, word);
	     }
	     
	     return root;
	}
    
    private static void insertWord(TNode root, String word){
    	int offset = 'a';
    	         TNode curNode = root;
    	         
    	         for (int i = 0; i < word.length(); i++)
    	         {
    	             if (curNode.ways[word.charAt(i)-offset] == null)
    	                 curNode.ways[word.charAt(i)-offset] = new TNode(word.charAt(i));
    	             curNode = curNode.ways[word.charAt(i)-offset];
    	        }
    	      curNode.isWord = true;
    }
    
    
    public static TNode longestWithPrefix(TNode root, String prefix){
    	if(prefix==null || root==null) return null;
    	
        int offset = 'a';
        TNode curNode = root;
        
        for (int i = 0; i < prefix.length(); i++)
        {
        	if(curNode.ways[prefix.charAt(i)-offset] == null) break;
            curNode = curNode.ways[prefix.charAt(i)-offset];
        }
        
        return curNode;
    }
    
    public static int countPossibleWords(TNode root, String prefix){
    	
    	if(prefix==null || root == null) return 0;
        int offset = 'a';
        TNode curNode = root;
        
        for (int i = 0; i < prefix.length(); i++){
            if (curNode == null)
                return 0;
            curNode = curNode.ways[prefix.charAt(i)-offset];
        }
        
        return getAll(curNode);
    }
    
    private static int getAll(TNode root) {
    	int c = 0;
    	TNode curNode = root;
    	TNode next=null;
    	boolean first=true;
    	
    	for(int i = 0; i<curNode.ways.length; ++i){
    		if(first && curNode.isWord) c++;
    		if(curNode.ways[i] != null){
    			next=curNode.ways[i];
    			c+=getAll(next);
    		}
    		else first=false;
    	}
		return c;
	}
}
