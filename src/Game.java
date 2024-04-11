import java.util.Arrays;
import java.util.Scanner;

public class Game {

    public static final char SHIP = '*';
    private static final char EMPTY = '.';
    public static final int SHIP_COUNT = 3;
    public static char[][] gameBoard;
    public static int columnsSize;
    public static int rowsSize;
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        setGameBoard();
        for (int i = 0; i < SHIP_COUNT; i++) {
            try {
                addShip();
                System.out.println("Ship was added to the board.");
                printBoard();
            } catch (IllegalArgumentException e) {
                System.out.println("Ship wasn't added to the board.");
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    // Set the game board.
    public static void setGameBoard() {
        columnsSize = getNumberValue("the number of columns");
        rowsSize = getNumberValue("the number of rows");
        initBoard(columnsSize, rowsSize);
    }

    // Initialize the game board.
    public static void initBoard(int columns, int rows) {
        gameBoard = new char[rows][columns];
        for (char[] row : gameBoard) {
            Arrays.fill(row, EMPTY);
        }
    }

    // Print the game board.
    public static void printBoard() {
        for (char[] row : gameBoard) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    // Add a ship to the game board.
    public static void addShip() {
        int column = getNumberValue("ship start position column");
        int row = getNumberValue("ship start position row");
        int shipLength = getNumberValue("ship length");
        String shipType;
        System.out.println("Enter ship type (v = vertical or h = horizontal):");
        shipType = scanner.next();
        placeShip(column, row, shipType, shipLength);
    }

    // Place a ship on the game board.
    public static void placeShip(int startColumn, int startRow, String shipType, int shipLength) {
        if (startColumn < 0 || startRow < 0 || startColumn >= columnsSize || startRow >= rowsSize) {
            throw new IllegalArgumentException("Position of the ship is outside the game map");
        }

        if (shipType.equalsIgnoreCase("v")) {
            if (startRow + shipLength > rowsSize) {
                throw new IllegalArgumentException("Ship is too long");
            }
            for (int i = 0; i < shipLength; i++) {
                if (gameBoard[startRow + i][startColumn] == SHIP) {
                    throw new IllegalArgumentException("Cell is not empty");
                } else {
                    gameBoard[startRow + i][startColumn] = SHIP;
                }
            }
        } else if (shipType.equalsIgnoreCase("h")) {
            if (startColumn + shipLength > columnsSize) {
                throw new IllegalArgumentException("Ship is too long");
            }
            for (int i = 0; i < shipLength; i++) {
                if (gameBoard[startRow][startColumn + i] == SHIP) {
                    throw new IllegalArgumentException("Cell is not empty");
                }
            }
            for (int i = 0; i < shipLength; i++) {
                gameBoard[startRow][startColumn + i] = SHIP;
            }
        } else {
            throw new IllegalArgumentException("Invalid ship type");
        }
    }

    // Get an integer value from the user.
    public static int getNumberValue(String message) {
        int value = 0;
        while (true) {
            System.out.printf("Enter %s:", message);
            if (scanner.hasNextInt()) {
                value = scanner.nextInt();
                break;
            } else {
                System.out.println("Invalid input. Please enter an integer value.");
                scanner.next();
            }
        }
        return value;
    }
}