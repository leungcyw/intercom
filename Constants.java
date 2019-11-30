/*
 * Filename: Constants.java
 * Author: Christina Leung
 * Sources of help: None.
 */


/*
 Collection of constants used by FindCustomers.java
*/
public class Constants {

  // Used in objParse method
  public static final String LONG_KEY = "longitude";
  public static final String LAT_KEY = "latitude";
  public static final String SPACE = " ";
  public static final String ID_KEY = "user_id";
  public static final String NAME_KEY = "name";

  // Used in inRange method
  public static final double OFFICE_LAT = 0.659539015283;
  public static final double OFFICE_LONG = -2.1363267546041;
  public static final int SQUARE = 2;
  public static final int RADIUS = 6371;
  public static final double MAX = 100.0;

  // Used in writeFile method
  public static final String OPEN_SQUARE = "[\n";
  public static final String ENTRY = "{ \"user_id\": %d, \"name\": \"%s\" }\n";
  public static final String CLOSE_SQUARE = "]";

  // Used in main method
  public static final String ERROR = "Usage: java FindCustomer [input] [output]\n" +
                                     "  input -- input file (JSON formatted) name\n" +
                                     "  output -- optional output file name, defaults to output.txt";                                   
  public static final int OUTPUT_LEN = 2;
  public static final String DEFAULT_NAME = "output.txt";  
}
