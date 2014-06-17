package serie3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import serie3.structures.TNode;

public class AutoCompleteUtilsTest {
	List<String> words = new ArrayList<String>();
	String filename= "C:\\Users\\Oksana\\Documents\\ENSINO\\ISEL\\4 Semestre\\AED\\Repositorio\\test\\java\\test.txt";
	
	public void createStream() throws IOException{
		words.add("ana");
		words.add("abba");
		words.add("amor");
		words.add("andar");
		words.add("xarope");
		words.add("comer");
		words.add("beber");
		words.add("brincar");
		words.add("estudar");
		words.add("bola");
		words.add("jogo");
		words.add("tv");
		words.add("trabalho");
		words.add("programar");
		words.add("software");
		words.add("dormir");
		words.add("acordar");
		words.add("setas");
		words.add("correr");
		words.add("parar");
		PrintStream ps = new PrintStream(new File(filename));
		for(String s : words){
			ps.println(s);
		}
		ps.close();
		
	}
	
	@Test
	public void testLoadWordsFirstLetters() throws IOException{
		createStream();
		TNode tn = new TNode();
		tn = AutoCompleteUtils.loadWordsFromFile(tn,filename);
		assertFalse(tn.ways[0].equals(null));//a
		assertFalse(tn.ways[1].equals(null));//b
		assertFalse(tn.ways[2].equals(null));//c
		assertFalse(tn.ways[3].equals(null));//d
		assertFalse(tn.ways[4].equals(null));//e
		assertFalse(tn.ways[9].equals(null));//J
		assertFalse(tn.ways[15].equals(null));//p
		assertFalse(tn.ways[18].equals(null));//s
		assertFalse(tn.ways[19].equals(null));//t
		assertFalse(tn.ways[23].equals(null));//x
	}
	
	@Test
	public void testLongestWithPrefix() throws IOException{
		TNode tree = new TNode();
		tree=AutoCompleteUtils.loadWordsFromFile(tree, filename);
		TNode aux1= AutoCompleteUtils.longestWithPrefix(tree, "beb");
		assertEquals('b', aux1.letter);
		TNode aux2= AutoCompleteUtils.longestWithPrefix(tree, "an");
		assertEquals('n', aux2.letter);
		TNode aux3= AutoCompleteUtils.longestWithPrefix(tree, "software");
		assertEquals('e', aux3.letter);
		TNode aux4= AutoCompleteUtils.longestWithPrefix(tree, null);
		assertEquals(null, aux4);
		TNode aux5= AutoCompleteUtils.longestWithPrefix(null, null);
		assertEquals(null, aux5);
	}
	
	@Test
	public void testCountPossibleWords() throws IOException{
		TNode tree = new TNode();
		tree=AutoCompleteUtils.loadWordsFromFile(tree, filename);
		int n1=AutoCompleteUtils.countPossibleWords(tree, "an");
		assertEquals(n1,2);
		int n2=AutoCompleteUtils.countPossibleWords(tree, "a");
		assertEquals(n2,5);
		int n3=AutoCompleteUtils.countPossibleWords(tree, "beber");
		assertEquals(n3,1);
		int n4=AutoCompleteUtils.countPossibleWords(tree, "");
		assertEquals(n4,20);
		int n5=AutoCompleteUtils.countPossibleWords(tree, null);
		assertEquals(n5,0);
	}

}
