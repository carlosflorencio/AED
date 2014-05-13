package problemaSerie2;

import problemaSerie2.map.HashTable;

import java.io.IOException;
import java.util.Formatter;
import java.util.List;

public class ListOcorrencies {

    private static String outputFile;
    private static String keywordsFile;
    private static String[] files;

    public static void main(String[] args) {
        if(valid(args) == false) {
            System.out.println("You need at least 3 files.");
            return;
        }

        outputFile = args[0];
        keywordsFile = args[1];

        files = new String[args.length-2];
        for (int i = 2; i < args.length; i++) {
            files[i-2] = args[i];
        }

        try {
            List<String> words = FileIOUtils.getWords(keywordsFile);
            Core core = new Core(words, files);

            HashTable<String, HashTable<String, List<Integer>>> result = core.parse();

            String output = output(result, words, files);

            FileIOUtils.writeToFile(outputFile, output);
            System.out.println(output);

        } catch (IOException e) {
            System.out.println("Something failed whith IO operations.");
            System.out.println("Error: " + e.getMessage());
        }
    }

    /*
    |--------------------------------------------------------------------------
    | Format result
    |--------------------------------------------------------------------------
    */
    public static String output(HashTable<String, HashTable<String, List<Integer>>> table, List<String> words, String[] files) {
        StringBuilder sb = new StringBuilder();

        Formatter formatter = new Formatter(sb);
        int maxSize = biggestFileName(files) + 5;
        String format = "%-"+maxSize+"s %s\n";

        for (String word : words) {
            formatter.format(format, word, "");

            HashTable<String, List<Integer>> fileList = table.get(word);

            if(fileList != null) {
                for (String file : files) {
                    List<Integer> lineList = fileList.get(file);

                    if(lineList != null) {
                        String lines = "";
                        for (Integer integer : lineList) {
                            lines += integer + "\t";
                        }
                        formatter.format(format, file, lines);
                    } else {
                        formatter.format(format, file, "");
                    }
                }
            } else {
                for (String file : files) {
                    formatter.format(format, file, "");
                }
            }
        }
        formatter.close();

        return sb.toString();
    }

    protected static int biggestFileName(String[] files) {
        int biggest = files[0].length();

        for (int i = 1; i < files.length; i++) {
            if(files[i].length() > biggest)
                biggest = files[i].length();
        }

        return biggest;
    }

    /*
    |--------------------------------------------------------------------------
    | Validation
    |--------------------------------------------------------------------------
    */
    private static boolean valid(String[] args) {
        return args.length >= 3;
    }

    /*
    |--------------------------------------------------------------------------
    | Getters
    |--------------------------------------------------------------------------
    */
    public static String getOutputFile() {
        return outputFile;
    }

    public static String getKeywordsFile() {
        return keywordsFile;
    }

    public static String[] getFiles() {
        return files;
    }
}
