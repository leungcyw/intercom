/*
 * Filename: FindCustomers.java
 * Author: Christina Leung
 * Sources of help: Java Documentation, simple-json
*/
import java.io.FileReader; 
import java.util.*;
import org.json.simple.JSONArray; 
import org.json.simple.JSONObject; 
import org.json.simple.parser.*;
import java.lang.Math.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

/*
   Parses a file to find nearby customers
*/
public class FindCustomers {
  private static Map<Integer, String> valid; // Stores customers close by

  /*
     Parses a single customer entry and inserts into the map if valid

     @param j is the customer entry
  */
  public static void objParse(JSONObject j) {
    // Current coordinates
    double lon = Double.parseDouble((String)j.get(Constants.LONG_KEY));
    double lat = Double.parseDouble((String)j.get(Constants.LAT_KEY));
    if (inRange(lon, lat)) { // If in range
      int id = Integer.parseInt(String.valueOf(j.get(Constants.ID_KEY)));
      String name = (String)j.get(Constants.NAME_KEY);
      valid.put(id, name); // Inserts into map
    }
  }

  /*
     Checks to see if the coordinates are in range

     @param lon is the current longitude
     @param lat is the current latitude
     @return returns true if in range, false otherwise
  */
  public static boolean inRange(double lon, double lat) {
    // Coordinates of office and current location in radians
    double lon1 = Math.toRadians(lon);
    double lat1 = Math.toRadians(lat);
    double lon2 = Constants.OFFICE_LONG;
    double lat2 = Constants.OFFICE_LAT;

    // Uses the haversine formula to calculate distance
    double deltaLon = Math.abs(lon1 - lon2);
    double deltaLat = Math.abs(lat1 - lat2);
    double num1 = Math.sin(deltaLat/Constants.SQUARE);
    num1 = Math.pow(num1, Constants.SQUARE);
    double num2 = Math.sin(deltaLon/Constants.SQUARE);
    num2 = Math.pow(num2, Constants.SQUARE);
    num2 = num2 * Math.cos(lat1) * Math.cos(lat2);
    double num = num1 + num2;
    num = Math.sqrt(num);
    num = Math.asin(num);
    num *= Constants.SQUARE;
    double dist = num * Constants.RADIUS;    
    return (dist <= Constants.MAX); // Compares it to max distance
  }

  /*
     Writes the valid customers into a formatted file

     @param outputFile is the given output file name
  */
  @SuppressWarnings("unchecked")
  public static void writeFile(String outputFile) {
    try (FileWriter file = new FileWriter(outputFile)) { // Creates file
      file.write(Constants.OPEN_SQUARE); // Begin array
      for(Map.Entry<Integer, String> entry : valid.entrySet()) { // Iterates names
        int id = entry.getKey();
        String name = entry.getValue();
        String cust = String.format(Constants.ENTRY, id, name);
        file.write(cust); // Writes formatted string to file
      }
      file.write(Constants.CLOSE_SQUARE); // Closes the array
      file.flush(); // Flushes buffer
    } catch (IOException e) { // Case of exception
      e.printStackTrace();
    }
  }
  
  /*
     Driver for program to parse given input and create output file

     @param args is the command line arguments
  */
  @SuppressWarnings("unchecked")  
  public static void main(String[] args) {
    if (args.length < 1) { // No input file given
      System.out.println(Constants.ERROR);
      return;
    }
    String input = args[0]; // Stores input name
    String output; // Stores output name
    if (args.length == Constants.OUTPUT_LEN) { // Given output name
      output = args[1];
    } else { // Default output name
      output = Constants.DEFAULT_NAME; 
    }
    valid = new TreeMap<Integer, String>(); // Initializes data structure

    JSONParser parser = new JSONParser();
    try (FileReader reader = new FileReader(input)) { // Parses through file
      Object obj = parser.parse(reader);
      JSONArray customerList = (JSONArray)obj; // Gets array of customers
      customerList.forEach(cus->objParse((JSONObject)cus)); // Iterates
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ParseException e) {
      e.printStackTrace();
    }
    writeFile(output); // Writes results to the output file
    return;
  }
}
