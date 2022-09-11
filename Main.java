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

        for (int i = 0; i < 2; i++) {
            addRandomTile();
        }

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

    public static int[] getRandomAvailableCell() {
        int availCellCount = 0;
        for (int i = 0; i < gameBoard.length; i++) {
            int[] row = gameBoard[i];
            for (int j = 0; j < row.length; j++) {
                if (row[j] == 0) {
                    availCellCount++;
                }
            }
        }
        int[][] cellsAvailable = new int[availCellCount][2];
        int cCounter = 0;
        for (int i = 0; i < gameBoard.length; i++) {
            int[] row = gameBoard[i];
            for (int j = 0; j < row.length; j++) {
                if (row[j] == 0) {
                    cellsAvailable[cCounter][0] = i;
                    cellsAvailable[cCounter][1] = j;
                    cCounter++;
                }
            }
        }
        return cellsAvailable[(new Double(Math.floor(Math.random() * availCellCount))).intValue()];
    }

    public static void addRandomTile() {
        int cellValue = (Math.random() < 0.9)?2:4;
        int[] cellToFill = getRandomAvailableCell();
        gameBoard[cellToFill[0]][cellToFill[1]] = cellValue;
    }
}