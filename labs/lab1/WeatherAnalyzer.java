import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;


public class WeatherAnalyzer {

    public static void main(String[] args) {
        // Main program logic
        String file = args[0];
        ArrayList<String[][]> splitDataArray = readCSV(file);
        ArrayList<Double[]> numericColArray = extractNumericColumn(splitDataArray, 1);
    }

    public static ArrayList<String[][]> readCSV(String filename) {
        // Read and parse CSV file
        ArrayList<String[][]> data = new ArrayList<>();
        BufferedReader reader = null;
        // int index = 0;
        try {
            reader = new BufferedReader(new FileReader(filename));
            String line;

            while ((line = reader.readLine()) != null){
                try{
                    ArrayList<String[]> split_data = line.split(","); 
                    // data[index] = split_data;
                    // data.add(split_data);

                    // index++;
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

    public static ArrayList<Double[]> extractNumericColumn(ArrayList<String[][]> data, int columnIndex) {
        // Extract and validate numeric data from specified column
        // return avg val (C)
        // Double[] results = new double[data.length];
        ArrayList<Double[]> results = new ArrayList<>();
        int resultsIndex = 0;
        // For a B grade:
        int totalDataPointsCount = 0;
        int invalidDataCount = 0;

        for (int i = 1; i <data.size(); i++){
            String[] row = data.get(i);
            try{
    //         double val = Double.parseDouble(data[i][columnIndex]);
                double val = Double.parseDouble(row[columnIndex]);
    //         // results[resultsIndex] = val;
    //         // resultsIndex++;
                results.add(val);
                System.out.println(val); 
    //         totalDataPointsCount++;  
            }catch(NumberFormatException e){
        //         System.out.println("Invalid data format detected.");
        //         invalidDataCount++;
        //         totalDataPointsCount++;
        //         // results[resultsIndex] = 0;
            }
            
        }
        return results;

    }

    public static void displayStatistics(ArrayList<Double[]> values, String columnName) {
        // Calculate and display all required statistics
        for (int i = 0; i < values.size(); i++){
            System.out.println(values.get(i));
        }
    }
}
