import java.util.*;
import java.io.*;
public class reading_files {
    public static void main(String[] args) throws IOException {
        File file = new File("/Users/kartikaypandey/IdeaProjects/HuffmanEncoder/src/textfile");

        // Note:  Double backquote is to avoid compiler
        // interpret words
        // like \test as \t (ie. as a escape sequence)

        // Creating an object of BufferedReader class
        BufferedReader br
                = new BufferedReader(new FileReader(file));

        // Declaring a string variable
        String st;
        // Condition holds true till
        // there is character in a string
        while ((st = br.readLine()) != null)

            // Print the string
            System.out.println(st);
    }
}
