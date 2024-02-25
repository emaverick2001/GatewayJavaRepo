import java.util.Scanner;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileInputStream;
import java.awt.Color;

/** Uses Rectangle.java class to draw checkerboard given 
    RGB values for color and checkerboard size.
    Displays checkerboard in snake pattern and 
    spiral pattern when printing tiles.
    Maverick Espinosa
    mespin11
    10/31/22
*/
public class Project4 {

   /** Creates grid array for Rectangle objects to be 
       stored and returns array assignment.
       @param row = row size of array
       @param col = column size of array
       @return grid array assignment
   */ 
   public static Rectangle[][] createGrid(int row, int col) {
      Rectangle[][] grid = new Rectangle[row][col];
      return grid;
   }
   
   /** creates checkerboard array and prints colored tiles 
       and white tiles in alternating pattern row by row.
       Each row has differing alternating pattern.
       @param red is amount of red color used in colored tile color
       @param green is amount of green color used in colored tile color
       @param blue is amount of blue color used in colored tile color
       @param checkSize is the size of the row and col of the tile(rectangle) 
       since square has equal
       row and col size 
       @throws IOException
   */ 
   public static void checkerBoard(int red, int green, int blue, int checkSize) 
                                                           throws IOException {
      boolean filled = true;
      int rows = checkSize;
      int cols = checkSize;
      double rectWidth = 1.0 / checkSize;
      double rectHeight = 1.0 / checkSize;
      double xCenter = rectWidth * 0.5;
      double yCenter = rectHeight * 0.5;
      Rectangle grid[][] = createGrid(rows, cols);
      for (int i = 0; i < rows; i++) {
         for (int j = 0; j < cols; j++) {
            if (i % 2 == 0) {
               if (j % 2 == 0) {
                  //prints white square tiles if row and column is even
                  grid[i][j] = new Rectangle(new Color(255, 255, 255), 
                  rectWidth, rectHeight, filled,
                  (j * rectWidth) + xCenter, 1 - ((i * rectHeight) + yCenter));
               }
               else {
                  //prints colored tiles if row is even and column is odd
                  grid[i][j] = new Rectangle(new Color(red, green, blue), 
                  rectWidth, rectHeight, filled,
                  (j * rectWidth) + xCenter, 1 - ((i * rectHeight) + yCenter));
               }
                  
            }
            else if (i % 2 != 0) {
               if (j % 2 == 0) {
                  //prints colored tiles if row is odd and column is even
                  grid[i][j] = new Rectangle(new Color(red, green, blue), 
                  rectWidth, rectHeight, filled,
                  (j * rectWidth) + xCenter, 1 - ((i * rectHeight) + yCenter));
               }
               else {
                  //prints white tiles if row is odd and column is odd
                  grid[i][j] = new Rectangle(new Color(255, 255, 255), 
                  rectWidth, rectHeight, filled,
                  (j * rectWidth) + xCenter, 1 - ((i * rectHeight) + yCenter));
               }
            } 
            //do the drawing
            //draws individual rectangle in each column 
            //of each row of rectangle   
            grid[i][j].draw();
            StdDraw.pause(200);
         }
      }
      // creates output file with checkerboard
      gridToFile(grid, checkSize);
   }
   
   /** reads .txt file and creates and fills grid array with rectangle objects
       who's data is used from the .txt file. Returns array assignment
       @param filename is the user input filename of the .txt file to be read
       @throws IOException
       @return newGrid array assignment 
   */    
   public static Rectangle[][] readFcreateGrid(String filename)
                                           throws IOException {
      FileInputStream fileStream = new FileInputStream(filename);
      Scanner inFS = new Scanner(fileStream);
      int rows = inFS.nextInt();
      int cols = inFS.nextInt();
      //skips next line to capture rectangle info
      inFS.nextLine();
      Rectangle newGrid[][] = createGrid(rows, cols);
      for (int i = 0; i < rows; i++) {
         for (int j = 0; j < cols; j++) {
            newGrid[i][j] = new Rectangle(inFS.nextLine());
         }
      }
      fileStream.close();
      return newGrid;
   }       
   
   /** prints checkboard in snake animation.
       @param filename is the user input filename of the .txt file to be read
       @throws IOException
   */       
   public static void snakeCheckerBoard(String filename) throws IOException { 
      Rectangle snakeGrid[][] = readFcreateGrid(filename);
      FileInputStream fileStream = new FileInputStream(filename);
      Scanner inFS = new Scanner(fileStream);
      int rows = inFS.nextInt();
      int cols = inFS.nextInt();
      //col inc since we want to print each column
      for (int j = 0; j < cols; j++) { 
         if (j % 2 == 0) {
            // prints column of alternating tiles from top to bottom
            // if column index is pos 
            for (int i = 0; i < rows; i++) {
               snakeGrid[i][j].draw();
               StdDraw.pause(200);
            }
         }
         else {
            //prints column of alt tiles from bottom to top
            //if column index is negative
            for (int i = (rows - 1); i >= 0; i--) {
               snakeGrid[i][j].draw();
               StdDraw.pause(200);
            }
         }
      }
      fileStream.close();
   }
   
   /** prints checkboard in spiral animation.
       @param filename is the user input filename of the .txt file to be read
       @throws IOException
   */  
   public static void spiralCheckerBoard(String filename) throws IOException {
      Rectangle[][] spiralGrid = readFcreateGrid(filename);
      FileInputStream fileStream = new FileInputStream(filename);
      Scanner inFS = new Scanner(fileStream);
      int rows = inFS.nextInt();
      int cols = inFS.nextInt();
      int i = 0;
      int j = 0;
      int minj = 0;
      int mini = 0;
      int maxj = cols - 1;
      int maxi = rows - 1;
      while (maxj > 0) {
      
         i = maxi;
         for (j = maxj; j >= minj; j--) {
            spiralGrid[i][j].draw();
            StdDraw.pause(200);
         }
         maxi--;      
         j = minj;
         for (i = maxi; i >= mini; i--) {
            spiralGrid[i][j].draw();
            StdDraw.pause(200);
         }
         minj++;
         i = mini;
         for (j = minj; j <= maxj; j++) {
            spiralGrid[i][j].draw();
            StdDraw.pause(200);
         }
         mini++;
         j = maxj;
         for (i = mini; i <= maxi; i++) {
            spiralGrid[i][j].draw();
            StdDraw.pause(200);
         }
         maxj--;
      }
   }
   
   /** Creates outputfile with checkerboard size and prints row and col 
       size on first line and each tile info on each line.
       @param grid is the array with each rectangle tile object stored in 
       its idexes
       @param checkSize is the size of the row and col of the tile(rectangle)
       since square has equal
       row and col size 
       @ throws IOException
   */  
   public static void gridToFile(Rectangle[][] grid, int checkSize) 
                      throws IOException {
                      
      int rows = checkSize;
      int cols = checkSize;
      PrintWriter outfile = new PrintWriter("checkerboard" 
                                    + checkSize + ".txt");
      outfile.println(checkSize + " " + checkSize);
      for (int i = 0; i < rows; i++) {
         for (int j = 0; j < cols; j++) {
            outfile.println(grid[i][j]);
         }
      }
      // puts buffer (stream + memory) into file
      outfile.flush();
      //closes outfile object
      outfile.close();
   }
   
   public static void main(String[] args) throws IOException {      
      Scanner scan = new Scanner(System.in);
      System.out.print("Enter checkerboard size: ");
      int checkSize = scan.nextInt();
      PrintWriter outfile = new PrintWriter("checkerboard" 
                                    + checkSize + ".txt");
      System.out.print("Enter RGB values, each [0,255]: ");
      int red = Integer.parseInt(scan.next());
      int green = Integer.parseInt(scan.next());
      int blue = Integer.parseInt(scan.next());
      String filename;
      
      StdDraw.clear(StdDraw.LIGHT_GRAY);
      checkerBoard(red, green, blue, checkSize);
      
      StdDraw.clear(StdDraw.LIGHT_GRAY);
      
      System.out.print("Enter snake input filename: ");
      filename = scan.next();
      snakeCheckerBoard(filename);
      
      StdDraw.clear(StdDraw.LIGHT_GRAY);
     
      System.out.print("Enter spiral input filename: ");
      filename = scan.next();
      spiralCheckerBoard(filename);
   }
}
      