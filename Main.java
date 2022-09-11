import static java.lang.System.*;
import java.util.Scanner;
import java.io.IOException;

public class Main
{
    public static boolean gameOn = true;
    public static int[][] gameBoard = {{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}};

    public static void main (String[] args)
    {
        Scanner keyboard = new Scanner(System.in);

        clearCLI();
        while (gameOn) {
            System.out.print(" > ");
            char myChar = keyboard.next().charAt(0);
            clearCLI();
            tick(myChar);
            System.out.println(boardToStr());
        }

    }

    public static void tick (char uInput)
    {
    }

    public static void clearCLI ()
    {
        System.out.println("\n".repeat(69));
    }

    public static String boardToStr ()
    {
        String outStr = "";
        for (int[] row : gameBoard) {
            for (int cell : row) {
                if (cell < 1) {
                    outStr += "[    ]";
                }
                else if (cell < 10) {
                    outStr += "[  " + cell + " ]";
                }
                else if (cell < 100) {
                    outStr += "[ " + cell + " ]";
                }
                else if (cell < 1000) {
                    outStr += "[ " + cell + "]";
                }
                else {
                    outStr += "[" + cell + "]";
                }
            }
            outStr += "\n";
        }
        outStr += "\b";
        return outStr;
    }
}