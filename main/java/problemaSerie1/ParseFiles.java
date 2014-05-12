package problemaSerie1;

import java.io.BufferedReader;
import java.io.IOException;

public class ParseFiles {
	
	private boolean[] endedStreams;
	
	private MinHeap<Word> heap;
	private BufferedReader[] stream;
	
	
	//helpers
	private String lastWord;
	private int counter = 1;
	
	private int smaller = -1;
	private int i = 0;

	public ParseFiles(MinHeap<Word> heap, BufferedReader[] rd) {
		this.endedStreams = new boolean[rd.length];
		this.heap = heap;
		this.stream = rd;
	}
	
	public void parse() throws IOException {
		
		while( hasActiveStream() ) {
 			String word = readLine(stream[i]);
			
			if(word == null) { //ended stream?
				if(endedStreams[i] == false)
					endedStreams[i] = true; //mark it
				
				incrementStream();
				continue;
			} else { //we have a valid stream!
				
				if(i == smaller) { //we want to move if the currentStream is the smallerStream
					
					if(this.i == lastActiveStream()) { //smaller is the last stream? let's move it
						moveSmallerStream();
					}
					
					incrementStream();
					continue;
				}
				
				if(smaller == -1) { //setup smallerStream
					setupSmaller(word);
					continue;
				}
				
				int comparison = readLine(stream[smaller]).compareTo( word ); 
				
				if(comparison == 0 ){ //equals
					moveEqual(stream[i]);
					
					incrementStream();
					continue;
				}
				
				if( comparison > 0 ) { //we have a new smaller
					setupSmaller(word);
					continue;
				}
				
				if(i == lastActiveStream()) { //we have reached the last stream
					moveSmallerStream();
					
					incrementStream();
				} else {
					incrementStream();
				}
					
			}
		}
	}
	
	
	
	/*
	|--------------------------------------------------------------------------
	| Helpers
	|--------------------------------------------------------------------------
	 */
	private void setupSmaller(String word) {
		this.smaller = i;
		this.lastWord = word;
		
		incrementStream();
	}
	
	private void moveSmallerStream() throws IOException {
		moveStream(stream[smaller]);
		String sm = readLine(stream[smaller]);
		if(sm != null && sm.equals(lastWord)) {
			moveEqual(stream[smaller]);
		}
		
		addWord();
		
		if(readLine(stream[smaller]) == null) { //smaller stream has ended, we have to setup a new smaller
			smaller = -1;
		}
	}
	
	private void addWord() throws IOException {
		this.heap.add(new Word(lastWord, counter)); //add last word because there are no more of this kind
		this.counter = 1; //reset counter
		this.lastWord = readLine(stream[smaller]);
	}
	
	
	private int lastActiveStream() {
		for (int i = this.endedStreams.length-1; i >= 0 ; i--) {
			if(endedStreams[i] == false) {
				return i;
			}
		}
		return -1;
	}
	
	private void incrementStream() {
		this.i = ++this.i % this.endedStreams.length;
	}
	
	private void moveEqual(BufferedReader rd) throws IOException {
		this.counter++;
		this.moveStream(rd);
		
		while(readLine(rd) != null && readLine(rd).equals(this.lastWord)) {
			this.counter++;
			this.moveStream(rd);
		}
		
	}
	
	private void moveStream(BufferedReader rd) throws IOException {
		rd.readLine();
	}
	
	private String readLine(BufferedReader rd) throws IOException { //read line without moving
		if(rd == null) return null;
		rd.mark(1000); //1000 is the buffer
		String word = rd.readLine();
		rd.reset();
		return word;
	}
	
	private boolean hasActiveStream() {
		for (int i = 0; i < this.endedStreams.length; i++) {
			if(this.endedStreams[i] == false) {
				return true;
			}
		}
		
		return false;
	}
	
}
