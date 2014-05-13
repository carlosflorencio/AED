package serie2;

import static org.junit.Assert.*;
import static serie2.ListUtils.interleaved;

import java.util.ArrayList;

import static serie2.ListUtilTest.*;
import org.junit.Test;

public class InterleavedTest {
	
	static String s1 = "Touro", s2 = "Sagitario", s3 = "Peixes", s4 = "Capricornio";
	static String e1 = "Terra", e2 = "Fogo", e3 = "agua", e4 = "Terra";
	static String c1 = "Perserveranca", c2 = "Sabedoria", c3 = "Fantasia", c4 = "Ambicao";
	
	@Test
	public void  interleave_empty_list(){
		Node<Node<String>> list = ListUtilTest.<Node<String>>emptyListWithSentinel();
		assertTrue( isEmptyListWithSentinel( interleaved(list) ) );
	}

	@Test
	public void  interleave_empty_lists(){
		Node<Node<String>> list = new Node<Node<String>>();
		Node<Node<String>> current = list;
		for (int i = 0; i < 3; ++i ) {
		  current.next =  newNode( ListUtilTest.<String>emptyListWithSentinel(), current, list );
		  current= current.next;
		}
		list.previous = list.next;
		assertTrue( isEmptyListWithSentinel( interleaved(list) ) );
		assertTrue( isEmptyListWithSentinel( list ) );
	}
	
	@Test
	public void  interleave_lists_same_length(){
		Node<String> ns1 = newNode(s1), ns2 = newNode(s2), ns3 = newNode(s3), ns4 = newNode(s4);
		Node<String> ne1 = newNode(e1), ne2 = newNode(e2), ne3 = newNode(e3), ne4 = newNode(e4);
		Node<String> nc1 = newNode(c1), nc2 = newNode(c2), nc3 = newNode(c3), nc4 = newNode(c4);
		Node<String> listS = emptyListWithSentinel(); addNode(ns1, listS); addNode(ns2, listS); addNode(ns3, listS); addNode(ns4, listS);
		Node<String> listE = emptyListWithSentinel(); addNode(ne1, listE); addNode(ne2, listE); addNode(ne3, listE); addNode(ne4, listE);
		Node<String> listC = emptyListWithSentinel(); addNode(nc1, listC); addNode(nc2, listC); addNode(nc3, listC); addNode(nc4, listC);
		int numberOfLists = 3, lengthOfLists = 4;
		Node<Node<String>> list = emptyListWithSentinel(); addNewNode(listS, list); addNewNode(listE, list); addNewNode(listC, list);
	
		ArrayList<Node<String>> array = new ArrayList<Node<String>>(); 
		array.add(ns1); array.add(ne1); array.add(nc1); 
		array.add(ns2); array.add(ne2); array.add(nc2); 
		array.add(ns3); array.add(ne3); array.add(nc3); 
		array.add(ns4); array.add(ne4); array.add(nc4); 
	
		Node<String> result = interleaved(list), currResult = result.next;
		for (int i = 0; i < numberOfLists * lengthOfLists; i++) {
			assertTrue(array.get(i).value == currResult.value);
			assertEquals(array.get(i),currResult);
			currResult = currResult.next;
		}
		assertTrue(currResult == result);
		assertTrue(isEmptyListWithSentinel(list));
	}
	
	@Test
	public void  interleave_lists_different_length(){
		Node<String> ns1 = newNode(s1), ns2 = newNode(s2), ns3 = newNode(s3), ns4 = newNode(s4);
		Node<String> ne1 = newNode(e1), ne2 = newNode(e2), ne3 = newNode(e3);
		Node<String> nc1 = newNode(c1), nc2 = newNode(c2);
		Node<String> listS = emptyListWithSentinel(); addNode(ns1, listS); addNode(ns2, listS); addNode(ns3, listS); addNode(ns4, listS);
		Node<String> listE = emptyListWithSentinel(); addNode(ne1, listE); addNode(ne2, listE); addNode(ne3, listE);
		Node<String> listC = emptyListWithSentinel(); addNode(nc1, listC); addNode(nc2, listC); ;
		int numberOfElements = 9;
		Node<Node<String>> list = emptyListWithSentinel(); addNewNode(listS, list); addNewNode(listE, list); addNewNode(listC, list);
	
		ArrayList<Node<String>> array = new ArrayList<Node<String>>(); 
		array.add(ns1); array.add(ne1); array.add(nc1); 
		array.add(ns2); array.add(ne2); array.add(nc2); 
		array.add(ns3); array.add(ne3); 
		array.add(ns4); 

		Node<String> result = interleaved(list), currResult = result.next;
		for (int i = 0; i < numberOfElements; i++) {
			assertTrue(array.get(i).value == currResult.value);
			assertEquals(array.get(i),currResult);
			currResult = currResult.next;
		}
		assertTrue(currResult == result);
		assertTrue(isEmptyListWithSentinel(list));
	}
}
