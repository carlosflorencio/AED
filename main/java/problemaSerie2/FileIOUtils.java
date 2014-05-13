package problemaSerie2;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class FileIOUtils {


    public static List<String> getWords(String file) throws IOException {
        List<String> list = new LinkedList<String>();

        BufferedReader reader = null;
        try {
            reader = getReadBuffer(file);

            String line;
            while ((line = reader.readLine()) != null) {
                list.add(line.trim());
            }

            return list;
        } catch (IOException e) {
            throw e;
        } finally {
            if(reader != null) {
                reader.close();
            }
        }
    }

    public static void writeToFile(String filename, String content) throws IOException {
        BufferedWriter bw = getWriteBuffer(filename);

        bw.write(content);
        bw.close();
    }

    /*
    |--------------------------------------------------------------------------
    | Buffers
    |--------------------------------------------------------------------------
    */
    public static BufferedWriter getWriteBuffer(String filename) throws IOException {
        FileWriter fw = new FileWriter(filename);
        BufferedWriter bw = new BufferedWriter(fw);

        return bw;
    }

    public static BufferedReader getReadBuffer(String filename) throws IOException {
        FileReader fr = new FileReader( filename );
        BufferedReader reader = new BufferedReader( fr );

        return reader;
    }
}
