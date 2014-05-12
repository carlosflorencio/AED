package problemaSerie1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MaiorNrOcorrencias {
	
	public static void main(String[] args) throws IOException{
		
		if(validateArgs(args)) {
			
			String output = args[0];
			int words = Integer.parseInt(args[1]);
			
			Streams streams = new Streams(args.length - 2);
			MinHeap<Word> heap = new MinHeap<Word>(words); //special min heap - if full can replace elements
			
			try {
				for (int i = 2; i < args.length; i++) { //add files to create streams
					streams.add(args[i]); //files should be in classpath
				}
			} catch(Exception e) {
				System.out.println("Files not found!");
				System.exit(1);
			}
			
			   
			
			ParseFiles core = new ParseFiles(heap, streams.getStreams());
			
			//long startTime = System.nanoTime(); 
			core.parse();
			//long estimatedTime = System.nanoTime() - startTime;
			//System.out.println("Nanosegundos: " + estimatedTime);
			//System.out.println("Milisegundos: " + estimatedTime/1000000);
			
			streams.closeStreams();
			
			writeToFile(output, heap);
			
		}
	}
	
	private static boolean validateArgs(String[] args) {
		if(args.length < 3) return false;
		
		return true;
	}
	
	private static void writeToFile(String path, MinHeap<Word> heap) throws IOException {
          File file = new File(path);
          BufferedWriter output = new BufferedWriter(new FileWriter(file));
          while(heap.peek() != null) {
        	  output.write( heap.poll().toString() + "\n" );
			}
          output.close();
	}

}
