package problemaSerie1;

public class Word implements Comparable<Word> {

	private String word;
	private int count;
	
	public Word(String word, int count) {
		this.word = word;
		this.count = count;
	}
	
	public String getWord() {
		return this.word;
	}
	
	public int getCount() {
		return this.count;
	}

	@Override
	public int compareTo(Word w) {
		
		if(w == null)
			return 1;
		
		int n = w.getCount();
		if(this.count > n) {
			return 1;
		} else {
			if(this.count == n) {
				return 0;
			} else {
				return -1;
			}
		}
	}
	
	public String toString() {
		return this.word + " - " + this.count;
	}
	
}
