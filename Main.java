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
        System.out.println(boardToStr());
        while (gameOn) {
            System.out.print(" > ");
            char myChar = keyboard.next().charAt(0);
            clearCLI();
            //System.out.println((int)myChar); // getkey
            tick(myChar);
            System.out.println(boardToStr());
        }

    }

    public static void tick (int uInput)
    {
        switch (uInput) {
            case 119:
                moveUp();
                break;
            case 97:
                moveLeft();
                break;
            case 115:
                moveDown();
                break;
            case 100:
                moveRight();
                break;
        }
        addRandomTile();
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

    public static void moveUp() {
        for (int col = 0; col < gameBoard[0].length; col++) {
            boolean merged = false;
            for (int row = gameBoard.length - 1; row > 0; row -= 1) {
                if (gameBoard[row - 1][col] == 0 && gameBoard[row][col] > 0) {
                    gameBoard[row - 1][col] = gameBoard[row][col];
                    gameBoard[row - 1][col] = 0;
                }
                else if (gameBoard[row - 1][col] == gameBoard[row][col] && !merged) {
                    gameBoard[row - 1][col] = gameBoard[row][col] * 2;
                    gameBoard[row][col] = 0;
                    merged = true;
                }
            }
        }
    }

    public static void moveDown() {
        for (int col = 0; col < gameBoard[0].length; col++) {
            boolean merged = false;
            for (int row = 0; row < gameBoard.length - 1; row++) {
                if (gameBoard[row + 1][col] == 0 && gameBoard[row][col] > 0) {
                    gameBoard[row + 1][col] = gameBoard[row][col];
                    gameBoard[row][col] = 0;
                }
                else if (gameBoard[row + 1][col] == gameBoard[row][col] && !merged) {
                    gameBoard[row + 1][col] = gameBoard[row][col] * 2;
                    gameBoard[row][col] = 0;
                    merged = true;
                }
            }
        }
    }

    public static void moveLeft() {
        for (int row = 0; row < gameBoard.length; row++) {
            boolean merged = false;
            for (int col = gameBoard[0].length - 1; col > 0; col -= 1) {
                if (gameBoard[row][col - 1] == 0 && gameBoard[row][col] > 0) {
                    gameBoard[row][col - 1] = gameBoard[row][col];
                    gameBoard[row][col] = 0;
                }
                else if (gameBoard[row][col - 1] == gameBoard[row][col] && !merged) {
                    gameBoard[row][col - 1] = gameBoard[row][col] * 2;
                    gameBoard[row][col] = 0;
                    merged = true;
                }
            }
        }
    }

    public static void moveRight() {
        for (int row = 0; row < gameBoard.length; row++) {
            boolean merged = false;
            for (int col = 0; col < gameBoard[0].length - 1; col++) {
                if (gameBoard[row][col + 1] == 0 && gameBoard[row][col] > 0) {
                    gameBoard[row][col + 1] = gameBoard[row][col];
                    gameBoard[row][col] = 0;
                }
                else if (gameBoard[row][col + 1] == gameBoard[row][col] && !merged) {
                    gameBoard[row][col + 1] = gameBoard[row][col] * 2;
                    gameBoard[row][col] = 0;
                    merged = true;
                }
            }
        }
    }
}