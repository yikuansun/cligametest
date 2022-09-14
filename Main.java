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
            int[] columnData = getCol(gameBoard, col);
            moveAllZerosToEnd(columnData);
            boolean doneMerging = false;
            for (int i = 0; i < columnData.length - 1; i++) {
                if (columnData[i] == columnData[i + 1] && !doneMerging) {
                    columnData[i] = columnData[i] * 2;
                    columnData[i + 1] = 0;
                    doneMerging = true;
                }
            }
            moveAllZerosToEnd(columnData);
            insertCol(gameBoard, col, columnData);
        }
    }

    public static void moveDown() {
        for (int col = 0; col < gameBoard[0].length; col++) {
            int[] columnData = getCol(gameBoard, col);
            moveAllZerosToBeginning(columnData);
            boolean doneMerging = false;
            for (int i = columnData.length - 1; i > 0; i -= 1) {
                if (columnData[i] == columnData[i - 1] && !doneMerging) {
                    columnData[i] = columnData[i] * 2;
                    columnData[i - 1] = 0;
                    doneMerging = true;
                }
            }
            moveAllZerosToBeginning(columnData);
            insertCol(gameBoard, col, columnData);
        }
    }

    public static void moveLeft() {
        for (int row = 0; row < gameBoard.length; row++) {
            int[] rowData = gameBoard[row];
            moveAllZerosToEnd(rowData);
            boolean doneMerging = false;
            for (int i = 0; i < rowData.length - 1; i++) {
                if (rowData[i] == rowData[i + 1] && !doneMerging) {
                    rowData[i] = rowData[i] * 2;
                    rowData[i + 1] = 0;
                    doneMerging = true;
                }
            }
            moveAllZerosToEnd(rowData);
            gameBoard[row] = rowData;
        }
    }

    public static void moveRight() {
        for (int row = 0; row < gameBoard.length; row++) {
            int[] rowData = gameBoard[row];
            moveAllZerosToBeginning(rowData);
            boolean doneMerging = false;
            for (int i = rowData.length - 1; i > 0; i -= 1) {
                if (rowData[i] == rowData[i - 1] && !doneMerging) {
                    rowData[i] = rowData[i] * 2;
                    rowData[i - 1] = 0;
                    doneMerging = true;
                }
            }
            gameBoard[row] = rowData;
        }
    }

    public static int[] getCol(int[][] arr, int colIndex) {
        int[] column = new int[arr.length];
        for (int i = 0; i < column.length; i++) {
            column[i] = arr[i][colIndex];
        }
        return column;
    }

    public static void insertCol(int[][] arr, int colIndex, int[] column) {
        for (int i = 0; i < column.length; i++) {
            arr[i][colIndex] = column[i];
        }
    }

    public static void moveAllZerosToEnd(int[] arr) {
        // https://www.youtube.com/watch?v=T_bJPgKBgSU
        int len = arr.length;
        int count = 0;
        for (int i = 0; i < len; i++) {
            if (arr[i] > 0) arr[count++] = arr[i];
        }
        while (count < len) arr[count++] = 0;
    }

    public static void moveAllZerosToBeginning(int[] arr) {
        moveAllZerosToEnd(arr);
        int firstZero = arr.length;
        for (var i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                firstZero = i;
                break;
            }
        }
        if (firstZero == arr.length) return;
        int startIndex = arr.length - firstZero;
        for (int i = startIndex; i < arr.length; i++) {
            arr[i] = arr[i - startIndex];
        }
        for (int i = 0; i < startIndex; i++) {
            arr[i] = 0;
        }
    }
}