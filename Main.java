import static java.lang.System.*;
import java.util.Scanner;
import java.io.IOException;

public class Main
{
    public static boolean gameOn = true;
    public static int[][] gameBoard = new int[4][4];
    public static int score = 0;

    public static void main (String[] args)
    {
        Scanner keyboard = new Scanner(System.in);

        for (int i = 0; i < 2; i++) {
            addRandomTile();
        }

        clearCLI();
        System.out.println("SCORE: 0");
        System.out.println(boardToStr());
        while (gameOn) {
            System.out.print(" > ");
            char myChar = keyboard.next().charAt(0);
            clearCLI();
            //System.out.println((int)myChar); // getkey
            tick(myChar);
            System.out.println("SCORE: " + score);
            System.out.println(boardToStr());
        }

    }

    public static void tick (int uInput)
    {
        String boardBefore = boardToStr();
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
        if (!boardBefore.equals(boardToStr())) addRandomTile();
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
        return cellsAvailable[(int)Math.round(Math.floor(Math.random() * availCellCount))];
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
            merge1D(columnData, 1);
            moveAllZerosToEnd(columnData);
            insertCol(gameBoard, col, columnData);
        }
    }

    public static void moveDown() {
        for (int col = 0; col < gameBoard[0].length; col++) {
            int[] columnData = getCol(gameBoard, col);
            moveAllZerosToBeginning(columnData);
            merge1D(columnData, -1);
            moveAllZerosToBeginning(columnData);
            insertCol(gameBoard, col, columnData);
        }
    }

    public static void moveLeft() {
        for (int row = 0; row < gameBoard.length; row++) {
            int[] rowData = gameBoard[row];
            moveAllZerosToEnd(rowData);
            merge1D(rowData, 1);
            moveAllZerosToEnd(rowData);
            gameBoard[row] = rowData;
        }
    }

    public static void moveRight() {
        for (int row = 0; row < gameBoard.length; row++) {
            int[] rowData = gameBoard[row];
            moveAllZerosToBeginning(rowData);
            merge1D(rowData, -1);
            moveAllZerosToBeginning(rowData);
            gameBoard[row] = rowData;
        }
    }

    public static void merge1D(int[] arr, int direction) {
        boolean doneMerging = false;
        switch (direction) {
            case -1:
                for (int i = arr.length - 1; i > 0; i -= 1) {
                    if (arr[i] == arr[i - 1] && arr[i] > 0 && !doneMerging) {
                        arr[i] = arr[i] * 2;
                        score += arr[i];
                        arr[i - 1] = 0;
                        doneMerging = true;
                    }
                }
                break;
            case 1:
                for (int i = 0; i < arr.length - 1; i++) {
                    if (arr[i] == arr[i + 1] && arr[i] > 0 && !doneMerging) {
                        arr[i] = arr[i] * 2;
                        score += arr[i];
                        arr[i + 1] = 0;
                        doneMerging = true;
                    }
                }
                break;
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
        int[] cloneArr = new int[arr.length];
        for (int i = startIndex; i < arr.length; i++) {
            cloneArr[i] = arr[i - startIndex];
        }
        for (int i = 0; i < cloneArr.length; i++) {
            arr[i] = cloneArr[i];
        }
    }
}