import java.util.ArrayList;
import java.util.Scanner;
/*
Tony Le 10/4/21
Minesweeper class

Takes in row and column numbers, gameboard layout and
presents a new gameboard which shows the numbers of
nearby mines.
 */


public class Minesweeper {
    private int pointer; //Keeps track of the current number in arraylist
    private int row;
    private int column;
    private String[][] game; //2d array for game
    private ArrayList<String> input; //input passed on from another class
    private final String mine = "*"; //final variables. these cannot be changed.
    private final String open = ".";

    public Minesweeper (ArrayList<String> input) {
        this.input = input; //copies input
        pointer = 0; //sets pointer to 0
        setRowsAndColumns(); //sets private int row and columns
        fillGameBoard(); //fills the 2d array
        completeGameBoard(); //fills in open spaces with number of nearby mines
    }

    //checks for nearby mines at the coordinates passed
    public int getNearbyMines(int i, int j) {
        int numberOfMines = 0;

        if (i > 0) { //IF there is space above
            if (game[i-1][j].equals(mine)) { //checks top
                numberOfMines++;
            }
            if (j > 0) {
                if (game[i-1][j-1].equals(mine)) { //checks top left
                    numberOfMines++;
                }
            }
            if (j < column-1) {
                if ((game[i-1][j+1].equals(mine))) { //checks top right
                    numberOfMines++;
                }
            }
        }

        if (i < row-1) { //IF there is space below
            if (game[i+1][j].equals(mine)) { //checks bottom
                numberOfMines++;
            }
            if (j > 0) {
                if (game[i+1][j-1].equals(mine)) { //checks bottom left
                    numberOfMines++;
                }
            }
            if (j < column-1) {
                if (game[i+1][j+1].equals(mine)) { //checks bottom right
                    numberOfMines++;
                }
            }
        }

        if (j > 0) { //IF there is space to the left
            if (game[i][j-1].equals(mine)) { //checks left
                numberOfMines++;
            }
        }
        if (j < column-1) { //IF there is space to the right
            if (game[i][j+1].equals(mine)) {
                numberOfMines++; //checks right
            }
        }
        return numberOfMines;
    }

    private void setRowsAndColumns() {
        String next = input.get(pointer);
        pointer++;
        readIntLine(next); //reads the initial numbers in text file for rows and columns and sets them
        game = new String[row][column]; //initialize the gameboard
    }

    //reads the initial line of int
    private void readIntLine(String next) {
        Scanner read = new Scanner(next);
        if (read.hasNextInt()) {
            row = read.nextInt();
            column = read.nextInt();
        }
    }

    //*FOR JUNIT* use for testing of rows and columns
    public int getRow() {
        return row;
    }
    public int getColumn() {
        return column;
    }

    //uses nested loops, method called after rows and columns are set
    private void fillGameBoard() {
        for (int i = 0; i < row; i++) {
            String next = input.get(pointer);
            for (int j = 0; j < column; j++) {
                game[i][j] = String.valueOf(next.charAt(j)); //takes char from string next, this is where columns are filled
            }
            pointer++;
        }
    }

    //fills in open spaces with numbers pertaining to nearby mines
    private void completeGameBoard() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (game[i][j].equals(open)) {
                    int replaceOpen = getNearbyMines(i, j); //calls to get nearby mines
                    game[i][j] = String.valueOf(replaceOpen);
                }
            }
        }
    }

    //*FOR JUNIT* Use this to test the 2d array
    public String[][] getGame() {
        return game;
    }

    @Override
    public String toString() { //this prints the gameboard with spacing in between
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                sb.append(game[i][j]);
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
