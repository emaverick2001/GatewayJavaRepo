import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.PrintWriter;

/**
 * Car Data Analysis: Project 3 Starter Code.
 * Maverick Espinosa
 * mespin11
 * 10/16/22
 * Gateway Programming: Java
 * Johns Hopkins University
 * Fall 2022
 */
public class CarDataAnalysis {

    // menu options
   static final int BRAND_QUERY = 1;
   static final int TWO_HIGHEST_PRICES_QUERY = 2;
   static final int RANGE_QUERY = 3;
   static final int BEST_VALUE_QUERY = 4;
   static final int QUIT = 5;

   // column index constants for car data file
   static final int BRAND = 2;
   static final int YEAR = 4;
   static final int MILEAGE = 6;
   static final int PRICE = 1;

   /**
    * Counts the number of lines in a given plain-text file.
    * @param filename The file whose lines are to be counted.
    * @return the number of lines in the file.
    * @throws FileNotFoundException
    */
   public static int countFileLines(String filename)
                                    throws FileNotFoundException {
      FileInputStream fileByteStream = new FileInputStream(filename);
      Scanner inFS = new Scanner(fileByteStream);
      int count = 0;
      while (inFS.hasNextLine()) {
         count++;
         inFS.nextLine();
      }
      return count;
   }  
    
   /**
    *loads data in each category on csv file into its respective array and data type.
    *@param filename is the name file user inputs
    *@param price is the array of prices from the csv file
    *@param brand is the array of brand names from the csv file
    *@param year is the array of years from the csv file
    *@param mileage is the array of miles from the csv file
    *@returns nothing since it modifies parameters' arrays elements
    */
   
   public static void loadColumn(String filename, int[] price, String[] brand, int[] year, double[] mileage)
                                                                           throws FileNotFoundException {
      FileInputStream fileByteStream = new FileInputStream(filename);
      Scanner inFS = new Scanner(fileByteStream);
      String firstRow = inFS.nextLine();
      String rowInfo = "";
      int counter = 0;
      while (inFS.hasNextLine()) {
         rowInfo = inFS.nextLine();
         String[] rowInfoSplit = rowInfo.split(",");
         price[counter] = Integer.parseInt(rowInfoSplit[PRICE]);
         brand[counter] = rowInfoSplit[BRAND];
         year[counter] = Integer.parseInt(rowInfoSplit[YEAR]);
         mileage[counter] = Double.parseDouble(rowInfoSplit[MILEAGE]);
         counter++;
      }
      inFS.close();
   }
   
   /**
    *prints the total entries and avg price for a brand name inputted by the user.
    *creates a file with index for inputted brand name and respective data for brand name, year, mileage, and price.
    *@param brand is the brand name inputted by the user
    *@param fileNameOut is the name of the file created
    *@param price is the array of prices from the csv file
    *@param brandN is the array of brand names from the csv file
    *@param year is the array of years from the csv file
    *@param mileage is the array of miles from the csv file
    */
   public static void avgPriceBrand(String brand, String fileNameOut, String[] brandN, int[] price, int[] year, double[] mileage)
                                                                                 throws FileNotFoundException {
      double avgPrice;
      int totalRows = 0;
      double sum = 0;
      String row = "";
      FileOutputStream fileStream = new FileOutputStream(fileNameOut);
      PrintWriter outFS = new PrintWriter(fileStream);
      for (int i = 0; i < brandN.length; i++) {
         //checks to see which rows/indexes correspond to ones with user specified brand name
         if (brandN[i].equals(brand)) {
            totalRows++;
            sum += price[i];
            //row combines relative info for each data category related to index @ brand name specified by user
            row = i + "," + brandN[i] + "," + year[i] + "," + mileage[i] + "," + price[i];
            //prints each row on new output file
            outFS.println(row);   
         }
      }
      outFS.close();
      
      avgPrice = sum / (totalRows);
      if (sum == 0) {
         System.out.println("There are no matching entries for brand " + brand + ".");
      }
      else {
         System.out.printf("There are %d matching entries for brand %s with an average price of $%.2f.", totalRows, brand, avgPrice);
      }
   }
   
   /**
    *finds two highest prices in array prices which hold all prices in csv file.
    *returns nothing since  only prints 2 highest prices.
    *@param price is the array of prices from the csv file
    */
   public static void twoHighestPrices(int[] price) {
      double price1 = 0;
      double price2 = 0;
      
      for (int i = 0; i < price.length; i++) {
         double current = price[i];
         if (price1 < current) {
            price2 = price1;
            price1 = current;
         }
         else if (price2 < current) {
            price2 = current;
         }
      }
      
      System.out.printf("The two highest prices are $%.2f and $%.2f.", price1, price2); 
   }
    
   /**
    *prints average price within a range of years and mileages.
    *@param lowerBound is the user specified lower year value
    *@param upperBound is the user specified upper year value
    *@param mLowerBound is the user specified lower mileage value
    *@param mUpperBound is the user specified upper mileage value
    *@param price is the array of prices from the csv file
    *@param year is the array of years from the csv file
    *@param mileage is the array of miles from the csv file
    *returns void since only prints specified values.
    */
   public static void avgPriceYearAndMileage(int lowerBound, int upperBound, int mLowerBound, int mUpperBound, int[] price, double[] mileage, int[] year) {      
      int entry = 0;
      double avgPrice;
      double sum = 0;
      for (int i = 0; i < year.length; i++) {
         if (year[i] >= lowerBound && year[i] <= upperBound) {
            if (mileage[i] >= mLowerBound && mileage[i] <= mUpperBound) { 
               sum += price[i];
               entry++;
            }
         }
      }
      avgPrice = sum / entry;
      System.out.printf("There are %d matching entries for year range[%d, %d] and mileage range [%d, %d] with an average price of $%.2f.", entry, lowerBound, upperBound, mLowerBound, mUpperBound, avgPrice);
   }
   
   /**
    *given a lower threshold for mileage and price, prints best value's entry year, brand miles, and price from among those exceeding the given thresholds.
    *@param mLowerThresh is the mileage lower threshold
    *@param pLowerThresh is the price lower threshold
    *@param priceVal is the array of prices from the csv file
    *@param brand is the array of brand names from the csv file
    *@param year is the array of years from the csv file
    *@param mileage is the array of miles from the csv file
    */
   public static void bestValue(double mLowerThresh, int pLowerThresh, int[] year, double[] mileage, int[] priceVal, String[] brand) {
      
      String brandAtCond = "";
      double milesAtCond = 0;
      int priceAtCond = 0;
      int yearAtCond = 0;
      double currentBestVal = 0;
      int indexAtBestVal = 0;
      double bestVal = 0;
      
      for (int i = 0; i < mileage.length; i++) {
         if (mileage[i] > mLowerThresh && priceVal[i] > pLowerThresh) {
            brandAtCond = brand[i];
            milesAtCond = mileage[i];
            priceAtCond = priceVal[i];
            yearAtCond = year[i];
            //equation to determine bestValue and stores index at best value
            bestVal = yearAtCond - ((milesAtCond) / 13500) - (double) priceAtCond / 1900;
            //checks if current bestval is better than calculated best value and stores it
            if (currentBestVal < bestVal) {
               currentBestVal = bestVal;
               indexAtBestVal = i;
            }
         }
      }
      int yearAtBestVal = year[indexAtBestVal];
      String brandAtBestVal = brand[indexAtBestVal];
      double milesAtBestVal = mileage[indexAtBestVal];
      int priceAtBestVal = priceVal[indexAtBestVal];
            
      System.out.printf("The best-value entry with more than %.1f miles and a price higher than $%d is a %d %s with %.1f miles for a price of $%d.", mLowerThresh, pLowerThresh, yearAtBestVal, brandAtBestVal, milesAtBestVal, priceAtBestVal);
   }
      
      


   /**
    * Print the program menu to the console.
    */
   public static void printMenu() {

      System.out.printf("[%d]: Average price of brand.\n", BRAND_QUERY);
      System.out.printf("[%d]: Two highest prices.\n",
             TWO_HIGHEST_PRICES_QUERY);
      System.out.printf("[%d]: Average price in year and mileage range.\n",
             RANGE_QUERY);
      System.out.printf("[%d]: Best value.\n", BEST_VALUE_QUERY);
      System.out.printf("[%d]: Quit.\n", QUIT);
      System.out.print("Please select an option: ");

   }

   /**
    * Drive the Car Data Analysis program.
    * @param args This program does not take commandline arguments.
    * @throws FileNotFoundException
    */
   public static void main(String[] args) throws FileNotFoundException {

      // output purpose
      System.out.println("Welcome to the car dataset analysis program.");

      // get input filename (e.g. "USA_cars_datasets.csv")
      System.out.print("Please enter input csv filename: ");
      Scanner keyboard = new Scanner(System.in);
      String filename = keyboard.nextLine();

      // count the number of rows in the file (ignore headers line)
      int rowCount = countFileLines(filename) - 1;
      System.out.println("File has " + rowCount + " entries.");
      System.out.println();

      //declare and allocate parallel arrays for each column of interest

      int[] priceVal = new int[rowCount];
      String[] brand = new String[rowCount];
      double[] mileage = new double[rowCount];
      int[] year = new int[rowCount];
      //load columns from file
      loadColumn(filename, priceVal, brand, year, mileage);
      // while the user doesn't choose to quit...
      int option = 0;
      while (option != QUIT) {

         // display the menu and get an option
         printMenu();
         option = keyboard.nextInt();

         // handle chosen option
         switch (option) {
            case BRAND_QUERY:
               System.out.print("Please enter a car brand: ");
               String brandName = keyboard.next();
               brandName = brandName.toLowerCase();
               System.out.print("Please enter an output filename: ");
               String fileNameOut = keyboard.next();
               avgPriceBrand(brandName, fileNameOut, brand, priceVal, year, mileage);
               break;
            case TWO_HIGHEST_PRICES_QUERY:
               twoHighestPrices(priceVal);
               break;
            case RANGE_QUERY:
               System.out.print("Please enter the year lower bound: ");
               int lowerBound = keyboard.nextInt();
               System.out.print("Please enter the year upper bound: ");
               int upperBound = keyboard.nextInt();
               System.out.print("Please enter the mileage lower bound: ");
               int mLowerBound = keyboard.nextInt();
               System.out.print("Please enter the mileage upper bound: ");
               int mUpperBound = keyboard.nextInt();
               avgPriceYearAndMileage(lowerBound, upperBound, mLowerBound, mUpperBound, priceVal, mileage, year);
               break;
            case BEST_VALUE_QUERY:
               System.out.print("Please enter the lower mileage threshold: ");
               int mLowerThresh = keyboard.nextInt();
               System.out.print("Please enter the lower price threshold:  ");
               int pLowerThresh = keyboard.nextInt();
               bestValue(mLowerThresh, pLowerThresh, year, mileage, priceVal, brand);
               break;
            case QUIT:
               System.out.println("Thank you for using the program!");
               break;
            default:
               System.out.println("Invalid option.");

         }

         // leave empty line for next printing of menu
         System.out.println();

      }

   }

}
