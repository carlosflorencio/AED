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
    	
    	if(prefix==null) return 0;
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

	static boolean find(TNode root, String word)
    {
        char[] letters = word.toCharArray();
        int l = letters.length;
        int offset = 97;
        TNode curNode = root;
        
        int i;
        for (i = 0; i < l; i++)
        {
            if (curNode == null)
                return false;
            curNode = curNode.ways[letters[i]-offset];
        }
        
        if (i == l && curNode == null)
            return false;
        
        if (curNode != null && !curNode.isWord)
            return false;
        
        return true;
    }
    
    
    static void printTree(TNode root, int level, char[] branch)
    {
        if (root == null)
            return;
        
        for (int i = 0; i < root.ways.length; i++)
        {
            branch[level] = root.letter;
            printTree(root.ways[i], level+1, branch);    
        }
        
        if (root.isWord)
        {
            for (int j = 1; j <= level; j++)
                System.out.print(branch[j]);
            System.out.println();
        }
    }
    
    public static void main(String[] args) throws IOException
    {
        TNode tree = new TNode();
        tree = loadWordsFromFile(tree, "C:\\Users\\Oksana\\Documents\\ENSINO\\ISEL\\4 Semestre\\AED\\Repositorio\\main\\java\\words.txt");
        
        //String[] words = {"an", "ant", "all", "allot", "alloy", "aloe", "are", "ate", "be"};
        /*for (int i = 0; i < words.length; i++)
            insertWord(tree, words[i]);*/
        
        char[] branch = new char[50];
        printTree(tree, 0, branch);
        
        String searchWord = "setas";
        if (find(tree, searchWord))
        {
            System.out.println("The word was found");
        }
        else
        {
            System.out.println("The word was NOT found");
        }
        
        TNode aux= longestWithPrefix(tree, "b");
        System.out.println(countPossibleWords(tree, "beberes"));
    }
}
