import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CS112FileReader {
    public static void main(String[] args) {

        // Check if filename argument is provided
        if (args.length != 1) {
            System.err.println("Usage: java CS112FileReader <filename>");
            System.exit(1);
        }
        
        String filename = args[0];
        BufferedReader reader = null;
        
        try {
            // Create a BufferedReader to read the file
            reader = new BufferedReader(new FileReader(filename));
            String line;
            
            // Read and print each line
            while ((line = reader.readLine()) != null) {
                System.out.println(line);   
            }
            
        }  catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            System.exit(1);
        } finally {
            // Close the reader in the finally block
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.err.println("Error closing file: " + e.getMessage());
                }
            }
        }
    }
}