package problemaSerie1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class Streams {
	
	private BufferedReader[] streams;
	private int count;

	public Streams(int size) {
		this.streams = new BufferedReader[size];
		this.count = 0;
	}
	
	public void add(String file) throws IOException {
		if(this.count == this.streams.length) return;
		
		BufferedReader br = null;
		URL absPath = Streams.class.getResource("/" + file); //files should be in classpath
		
		BufferedReader in = new BufferedReader(new InputStreamReader(absPath.openStream()));
		br = new BufferedReader(in);
		this.streams[this.count++] = br;
 
	}
	
	public void closeStreams() throws IOException {
		for (int i = 0; i < this.streams.length; i++) {
			if(streams[i] != null) {
				streams[i].close();
			}
		}
	}
	
	public BufferedReader[] getStreams() {
		return this.streams;
	}
	
	public int getLenght() {
		return this.streams.length;
	}
}
