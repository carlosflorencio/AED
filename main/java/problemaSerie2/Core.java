package problemaSerie2;

import problemaSerie2.map.HashTable;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Core {
    private List<String> words;
    private String[] files;

    public Core(List<String> words, String[] files) {
        this.words = words;
        this.files = files;
    }

    /*
    |--------------------------------------------------------------------------
    | Generate the data
    |--------------------------------------------------------------------------
    */
    public HashTable<String, HashTable<String, List<Integer>>> parse() throws IOException {
        HashTable<String, HashTable<String, List<Integer>>> wordHahTable = new HashTable<String, HashTable<String, List<Integer>>>(); //should i put the size?

        for (String file : this.files) { //for each file
            BufferedReader reader = FileIOUtils.getReadBuffer(file);

            String line;
            int lineNumber = 1;
            while((line = reader.readLine()) != null ) {

                for (String word : words) { //for each word
                    if(line.toLowerCase().contains(word.toLowerCase())) {
                        putLine(wordHahTable, word, file, lineNumber);
                    }
                }

                lineNumber++;
            }

            reader.close();
        }

        return wordHahTable;
    }

    protected void putLine(HashTable<String, HashTable<String, List<Integer>>> hashtable, String word, String file, int lineNumber) {
        HashTable<String, List<Integer>> fileList = hashtable.get(word);
        if(fileList != null) { //we have a word, we need to update the list with this file and line number

            List<Integer> lineList = fileList.get(file);

            if(lineList != null) { //we already have a list, we need to add the new value
                lineList.add(lineNumber);
            } else { //no list? fine lets create it and add the line number
                lineList = new LinkedList<Integer>();
                lineList.add(lineNumber);
            }
            fileList.put(file, lineList);

        } else { //we need to put the word
            List<Integer> lineList = new LinkedList<Integer>();
            lineList.add(lineNumber);
            HashTable<String, List<Integer>> table = new HashTable<String, List<Integer>>();
            table.put(file, lineList);
            hashtable.put(word, table);
        }
    }
}
