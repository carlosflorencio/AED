package problemaSerie3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileOperator {

    public static void load(String filename) throws IOException {
        BufferedReader reader = null;

        try {
            String line;

            reader = new BufferedReader( new FileReader( filename ) );

            line = reader.readLine();

        } catch (IOException e) {
            throw e;
        } finally {
            //Close stream
            try {
                if(reader != null)
                    reader.close();
            } catch(IOException e) {
                //Failed to close? hum
                throw e;
            }

        }
    }

    private static void parseLine(String line) {

    }

}
