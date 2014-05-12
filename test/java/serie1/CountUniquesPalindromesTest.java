package serie1;

import org.junit.Test;

import static org.junit.Assert.*;
import static serie1.Arrays.countUniquesPalindromes;

public class CountUniquesPalindromesTest {
	
	static String e = "";
	static String c1 = "x", c2 = "y", c3 = "z";
	static String s1 = "Hope for the best, but prepare for the worst", 
			s2 = "You can't make an omelet without breaking a few eggs",
			s3 = "Quem parte e reparte e fica com a pior parte, ou e tolo ou nao tem arte";
	static String p1 = "Madam, I'm Adam", 
			p2 = "A man, a plan, a canal - Panama!",
			p3 = "Socorram-me subi no onibus em Marrocos";

	static String[] empty_sentences = {e,e,e};
	static String[] empty_one_char_sentences = {e,c1,c2,c3};
	static String[] empty_duplicate_one_char_sentences = {e,c1,c2,c1,c3};
	static String[] unique_sentences = {s1,s2,s3},
			unique_palindromes = {p1,p2,p3},
			mixed_unique_sentences_palindromes = {s1,p1,s2,p2,s3,p3},
			duplicate_sentences = {s1,s3,s2,s2,s1,s1,s2,s2},
			duplicate_polindromes = {p1,p1,p2,p3,p1,p3},
			mixed_duplicate_sentences_palindromes = {p1,p3,p2,s2,s1,s3,p3,p3,s2,s3}; 
	
	@Test
	public void countUniquesPalindromes_onDuplicateEmptySentences() {
		assertEquals(0, countUniquesPalindromes(empty_sentences, 0, empty_sentences.length - 1));
	}
	
	@Test
	public void countUniquesPalindromes_onEmptyAndOneCharSentences() {
		assertEquals(4, countUniquesPalindromes(empty_one_char_sentences, 0, empty_one_char_sentences.length - 1));
	}
	
	@Test
	public void countUniquesPalindromes_onEmptyAndDuplicateOneCharSentences() {
		assertEquals(3, countUniquesPalindromes(empty_duplicate_one_char_sentences, 0, empty_duplicate_one_char_sentences.length - 1));
	}
	
	@Test
	public void countUniquesPalindromes_onUniqueSentences() {
		assertEquals(0, countUniquesPalindromes(unique_sentences, 0, unique_sentences.length - 1));
	}
	
	@Test
	public void countUniquesPalindromes_onUniquePalindromes() {
		assertEquals(3, countUniquesPalindromes(unique_palindromes, 0, unique_palindromes.length - 1));
	}
	
	@Test
	public void countUniquesPalindromes_onMixedUniqueSentencesPalindromes() {
		assertEquals(3, countUniquesPalindromes(mixed_unique_sentences_palindromes, 0, mixed_unique_sentences_palindromes.length - 1));
	}
	
	@Test
	public void countUniquesPalindromes_onDuplicateSentences() {
		assertEquals(0, countUniquesPalindromes(duplicate_sentences, 0, duplicate_sentences.length - 1));
	}
	
	@Test
	public void countUniquesPalindromes_onDuplicatePolindromes() {
		assertEquals(1, countUniquesPalindromes(duplicate_polindromes, 0, duplicate_polindromes.length - 1));
	}
	
	@Test
	public void countUniquesPalindromes_onMixedDuplicateSentencesPalindromes() {
		assertEquals(2, countUniquesPalindromes(mixed_duplicate_sentences_palindromes, 0, mixed_duplicate_sentences_palindromes.length - 1));
	}

}
