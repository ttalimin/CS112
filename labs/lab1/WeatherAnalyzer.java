import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.lang.ArrayIndexOutOfBoundsException;

public class WeatherAnalyzer {

    static int invalidDataCount = 0;
    static int totalDataPointsCount = 0;


    public static void main(String[] args) {
        // Main program logic
        Scanner input = new Scanner(System.in);
        boolean programRun = true;
        String file;
        if (args.length != 1){
            System.err.println("Usage: java WeatherAnalyzer.java <filename>");
            System.exit(1);
        }

        file = args[0];
        ArrayList<String[]> splitDataArray = readCSV(file);
        double[] numericColArray;
        
        if (splitDataArray.size() == 0){
            System.out.println("No valid data found or no readable file.");
            System.exit(1);
        }

        while (programRun){
            try{
                System.out.println();
                System.out.print("What column would you like to analyze? \nHigh temp [1]\nLow Temp [2]\nHumidity[3]\nWind Speed [4]\nPrecipitation[5]\nExit[6]\nEnter response: ");
                int user_input = input.nextInt();
                if (user_input == 1){
                    numericColArray = extractNumericColumn(splitDataArray, 1);
                    displayStatistics(numericColArray, "HighTempF");
                }else if (user_input == 2){
                    numericColArray = extractNumericColumn(splitDataArray, 2);
                    displayStatistics(numericColArray, "LowTempF");
                }else if (user_input == 3){
                    numericColArray = extractNumericColumn(splitDataArray, 3);
                    displayStatistics(numericColArray, "Humidity");
                }else if (user_input == 4){
                    numericColArray = extractNumericColumn(splitDataArray, 4);
                    displayStatistics(numericColArray, "WindSpeedMPH");
                }else if (user_input == 5){
                    numericColArray = extractNumericColumn(splitDataArray, 5);
                    displayStatistics(numericColArray, "PrecipitationIN");
                }else if(user_input == 6){
                    System.out.println("Closing program.");
                    System.exit(1);
                }else{
                    throw new Exception("Please input a number from 1-6.");
                }
            }catch(InputMismatchException e){
                System.out.println("Enter an integer from 1-6.");
                input.nextLine();
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            } 
        }
    }

    public static ArrayList<String[]> readCSV(String filename) {
        // Read and parse CSV file
        ArrayList<String[]> data = new ArrayList<>();
        BufferedReader reader = null;
        // int index = 0;
        try {
            reader = new BufferedReader(new FileReader(filename));
            String line;

            while ((line = reader.readLine()) != null){
                try{
                    String[] split_data = line.split(","); 
                    data.add(split_data);
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

    public static double[] extractNumericColumn(ArrayList<String[]> data, int columnIndex) {
        // Extract and validate numeric data from specified column
        ArrayList<Double> retrieveData = new ArrayList<>();

        for (int i = 1; i <data.size(); i++){
            String[] row = data.get(i);
            try{
                double val = Double.parseDouble(row[columnIndex]);
                retrieveData.add(val);
            }catch(NumberFormatException e){
                // System.out.println("Invalid data format detected.");  
                invalidDataCount++;
                totalDataPointsCount++;             
            }catch(ArrayIndexOutOfBoundsException e){
                System.out.println("Error while parsing on row " + i + ". Incomplete array of numbers.");
                invalidDataCount++;
                totalDataPointsCount++;
            }
        }
        double[] results = new double[retrieveData.size()];
        for (int i = 0; i < retrieveData.size(); i++){
            results[i] = retrieveData.get(i);
        }
        return results;
    }


    public static void displayStatistics(double[] values, String columnName) {
            // Calculate and display all required statistics
            double average = 0.0;
            double amountOfNums = 0.0;
            double sum = 0.0;
            String tempRound = "%.1f";
            String precipRound = "%.2f";
            if (values.length > 0){

                // Find High/Low Temp Values
                if (columnName.equals("HighTempF") || columnName.equals("LowTempF")){ // for temp only

                // Finding Temp Avg
                for (int i = 0; i < values.length; i++){
                    // System.out.println(values[i]);
                    sum += values[i];
                    amountOfNums++;
                    totalDataPointsCount++;
                }
                System.out.print("Average: ");
                System.out.printf(tempRound,(sum/amountOfNums));
                System.out.print("°F");
                System.out.println();
                // Finding Temp Min
                double min = values[0];
                for (int i = 1; i < values.length; i++){
                    if (min > values[i]){
                        min = values[i];
                    }
                }
                System.out.print("Min: ");
                System.out.printf(tempRound,min);
                System.out.print("°F");
                System.out.println();
                // Finding Temp Max
                double max = values[0];
                for (int i = 0; i < values.length; i++){
                    if (max < values[i]){
                        max = values[i];
                    }
                }
                System.out.print("Max: ");
                System.out.printf(tempRound,max);
                System.out.print("°F");
                System.out.println();
                System.out.println("Rows of invalid data: " + invalidDataCount + "\n" + "Total Data Points Processed: " + totalDataPointsCount);
                } 

                // Finding Precip Values
                else if (columnName.equals("PrecipitationIN")){
                for (int i = 0; i < values.length; i++){
                    // System.out.println(values[i]);
                    sum += values[i];
                    amountOfNums++;
                    totalDataPointsCount++;
                }
                System.out.print("Average: ");
                System.out.printf(precipRound,(sum/amountOfNums));
                System.out.print("in");
                System.out.println();
                // Finding Temp Min
                double min = values[0];
                for (int i = 1; i < values.length; i++){
                    if (min > values[i]){
                        min = values[i];
                    }
                }
                System.out.print("Min: ");
                System.out.printf(precipRound,min);
                System.out.print("in");
                System.out.println();
                // Finding Precip Max
                double max = values[0];
                for (int i = 0; i < values.length; i++){
                    if (max < values[i]){
                        max = values[i];
                    }
                }
                System.out.print("Max: ");
                System.out.printf(precipRound,max);
                System.out.print("in");
                System.out.println();
                System.out.println("Rows of invalid data: " + invalidDataCount + "\n" + "Total Data Points Processed: " + totalDataPointsCount);
                }

                // Finding Humidity Values
                else if (columnName.equals("Humidity")){

                    for (int i = 0; i < values.length; i++){
                    // System.out.println(values[i]);
                    sum += values[i];
                    amountOfNums++;
                    totalDataPointsCount++;
                }
                System.out.print("Average: ");
                System.out.printf(tempRound,(sum/amountOfNums));
                System.out.print("%");
                System.out.println();
                // Finding Humidity Min
                double min = values[0];
                for (int i = 1; i < values.length; i++){
                    if (min > values[i]){
                        min = values[i];
                    }
                }
                System.out.print("Min: ");
                System.out.printf(tempRound,min);
                System.out.print("%");
                System.out.println();
                // Finding Humiditiy Max
                double max = values[0];
                for (int i = 0; i < values.length; i++){
                    if (max < values[i]){
                        max = values[i];
                    }
                }
                System.out.print("Max: ");
                System.out.printf(tempRound,max);
                System.out.print("%");
                System.out.println();
                System.out.println("Rows of invalid data: " + invalidDataCount + "\n" + "Total Data Points Processed: " + totalDataPointsCount);
                }
                // Finding WindSpeed Values
                else if(columnName.equals("WindSpeedMPH")){

                // Finding WindSpeed Avg
                for (int i = 0; i < values.length; i++){
                    // System.out.println(values[i]);
                    sum += values[i];
                    amountOfNums++;
                    totalDataPointsCount++;
                }
                System.out.print("Average: ");
                System.out.printf(tempRound,(sum/amountOfNums));
                System.out.print("MPH");
                System.out.println();
                // Finding Wind Speed Min
                double min = values[0];
                for (int i = 1; i < values.length; i++){
                    if (min > values[i]){
                        min = values[i];
                    }
                }
                System.out.print("Min: ");
                System.out.printf(tempRound,min);
                System.out.print("MPH");
                System.out.println();
                // Finding Wind Speed Max
                double max = values[0];
                for (int i = 0; i < values.length; i++){
                    if (max < values[i]){
                        max = values[i];
                    }
                }
                System.out.print("Max: ");
                System.out.printf(tempRound,max);
                System.out.print("MPH");
                System.out.println();
                System.out.println("Rows of invalid data: " + invalidDataCount + "\n" + "Total Data Points Processed: " + totalDataPointsCount);

        }
            // Reset counter for next iteration
            invalidDataCount = 0; 
            totalDataPointsCount = 0;
        }
}
}