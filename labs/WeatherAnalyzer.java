import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class WeatherAnalyzer {

    public static void main(String[] args) {
        // Main program logic
        String file = args[0];
        // System.out.println(fileName);
        ArrayList<String[][]> splitDataArray = readCSV(file);
        double[] numericColArray = extractNumericColumn(splitDataArray, 1);
        displayStatistics(numericColArray, "HighTemp");
    }

    public static ArrayList<String[][]> readCSV(String filename) {
        // Read and parse CSV file
        ArrayList<String[][]> data = new ArrayList<>();
        // Scanner input = new Scanner(System.in);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(filename));
            String line;

            while ((line = reader.readLine()) != null){
                try{ 
                    String[] split_data = line.split(",");
                    data[index] = split_data;

                    index++;

                    // System.out.println(date + " + " + highTemp);
                }catch (Exception e){
                    System.out.println(e);
                }

            }
        }catch (FileNotFoundException e){
            System.out.println("File was not found!");
        }catch (IOException e){
            System.out.println(e);
        }finally {
            // Close the reader in the finally block
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.err.println("Error closing file: " + e.getMessage());
                }
            }
        }

        return data;


    }

    public static double[] extractNumericColumn(String[][] data, int columnIndex) {
        // Extract and validate numeric data from specified column
        // return avg val (C)
        double[] results = new double[data.length];
        int resultsIndex = 0;
        // For a B grade:
        int totalDataPointsCount = 0;
        int invalidDataCount = 0;

        for (int i = 1; i <data.length; i++){
            try{
                double val = Double.parseDouble(data[i][columnIndex]);
                results[resultsIndex] = val;
                resultsIndex++;
                // System.out.println(val); 
                totalDataPointsCount++;  
            }catch(NumberFormatException e){
                System.out.println("Invalid data format detected.");
                invalidDataCount++;
                totalDataPointsCount++;
                results[resultsIndex] = 0;
            }
            
        }
        return results;

    }

    public static void displayStatistics(double[] values, String columnName) {
        // Calculate and display all required statistics
        for (int i = 0; i < values.length; i++){
            System.out.println(values[i]);
        }
    }
}
