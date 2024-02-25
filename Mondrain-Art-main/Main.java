import java.util.Scanner;

/**
 * Starter file for Project A, EN.500.112 Gateway Computing: Java.
 * Mondrian Art
 * 09/18/2022
 *
 * @author Joanne Selinski
 * @author Maverick Espinosa
 * Program draws a Mondrian styled artwork using user input parameters
 */

public class Main {

    /**
     * Main method.
     *
     * @param args not used
     */

    public static void main(String[] args) {

        Scanner kb = new Scanner(System.in);  // this allows us to collect input

        // variable declarations
        double blueBlockWidth;
        double blueBlockHeight;
        double topLeftHeight;
        double bottomRightWidth;


        // fixed-value "constants" needed by the program
        final int SIZE = 512;  // can be increase if you want
        StdDraw.setCanvasSize(SIZE, SIZE);

        // Prompt for and collect parameters from the user
        //blue block
        System.out.println("Enter percent for blue block width: ");
        blueBlockWidth = kb.nextDouble() / 100;
        System.out.println("Enter percent for blue block height: ");
        blueBlockHeight = kb.nextDouble() / 100;
        System.out.println("Enter percent for top left block height: ");
        //top left block
        topLeftHeight = kb.nextDouble() / 100;
        //yellow block
        System.out.println("Enter percent for bottom right block width: ");
        bottomRightWidth = kb.nextDouble() / 100;

        // Make the output based on the inputs

        //blocks
        StdDraw.setPenColor(StdDraw.BLUE);
        //blue block
        StdDraw.filledRectangle(0, 0, blueBlockWidth, blueBlockHeight);

        StdDraw.setPenColor(StdDraw.BLACK);
        //top left block
        StdDraw.rectangle(0, 1, blueBlockWidth, topLeftHeight);


        StdDraw.setPenColor(StdDraw.YELLOW);
        //yellow block
        StdDraw.filledRectangle(1, 0, bottomRightWidth, .5 * blueBlockHeight);

        StdDraw.setPenColor(StdDraw.RED);
        //top redBlock
        StdDraw.filledRectangle(1, 1, 1 - blueBlockWidth, 1 - blueBlockHeight);

        //Circles/Arcs

        StdDraw.setPenColor(StdDraw.WHITE);
        //white half circle
        StdDraw.filledCircle(blueBlockWidth, 1 - topLeftHeight, .2 * blueBlockWidth);

        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.01);
        // right black arc
        StdDraw.arc(blueBlockWidth, 1 - topLeftHeight, .2 * blueBlockWidth, -90, 90);
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.setPenRadius(0.01);
        //top left blue arc
        StdDraw.arc(blueBlockWidth, 1 - topLeftHeight, .2 * blueBlockWidth, 90, 180);
        StdDraw.setPenColor(StdDraw.YELLOW);
        //bottom left yellow arc
        StdDraw.arc(blueBlockWidth, 1 - topLeftHeight, .2 * blueBlockWidth, 180, 270);


        //Lines
        StdDraw.setPenRadius(0.03);
        StdDraw.setPenColor(StdDraw.BLACK);
        //Line below topleftbox
        StdDraw.line(0, 1 - topLeftHeight, blueBlockWidth, 1 - topLeftHeight);
        StdDraw.setPenColor(StdDraw.WHITE);
        //white square covering rounding
        StdDraw.filledRectangle(blueBlockWidth + .05 * blueBlockWidth, 1 - topLeftHeight, .06 * blueBlockWidth, .06 * blueBlockWidth);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.01);
        //Line ontop of blue box
        StdDraw.line(0, blueBlockHeight, 1, blueBlockHeight);

        StdDraw.setPenRadius(0.01);
        //middle vertical line
        StdDraw.line(blueBlockWidth, 0, blueBlockWidth, 1);
        StdDraw.setPenRadius(0.02);
        //bottom right vertical line
        StdDraw.line(1 - bottomRightWidth, 0, 1 - bottomRightWidth, blueBlockHeight);
        StdDraw.setPenRadius(0.02);
        // bottom right middle line
        StdDraw.line(1 - bottomRightWidth, .5 * blueBlockHeight, 1, .5 * blueBlockHeight);

    }
}