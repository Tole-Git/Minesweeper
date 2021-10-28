import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
/*
Tony Le 10/4/21
Minesweeper tester class

This class takes input file from user and replaces their
minesweeper gameboards with numbers showing nearby mines.
 */

public class Tester {
    private static int fieldNumber;
    private static PrintWriter in;
    private static PrintWriter out;

    //*FOR JUNIT* use this method to test field number
    public static int getFieldNumber() {
        return fieldNumber;
    }

    //*FOR JUNIT* use this method to verify public methods in game
    public static Minesweeper createMinesweeper(ArrayList<String> input) {
        Minesweeper game = new Minesweeper(input);
        return game;
    }

    //*FOR JUNIT* use this method to verify the ArrayList<String> input
    public static ArrayList<String> createInput(Scanner sc) {
        ArrayList<String> input = new ArrayList<>();
        String initialLine = sc.nextLine(); //reads line with int and places into intialLine
        Scanner initialLineScan = new Scanner(initialLine);
        input.add(initialLine);
        int tempRow = Integer.parseInt(String.valueOf(initialLineScan.nextInt())); //takes initial row number

        for (int i = 0; i < tempRow; i++) { //uses row number to scan the same number of lines.
            input.add(sc.nextLine());
        }
        return input;
    }

    public static boolean readFile() throws IOException {
        inputGenerator generateInput = new inputGenerator();
        Scanner console = new Scanner(System.in);
        String inputFileName = console.nextLine();
        System.out.print("Please enter output file name: ");
        String outputFileName = console.nextLine();

        File file = new File(inputFileName); //creates file
        File outfile = new File(outputFileName);

        in = new PrintWriter(new FileWriter(inputFileName, true), true);
        out = new PrintWriter(new FileWriter(outputFileName, true), true);

        in.write(generateInput.toString());
        in.close();

        Scanner sc = new Scanner(file);
        while (sc.hasNextInt()) { //this while loop reads each initial number and processes corresponding rows.
            fieldNumber++;
            ArrayList<String> input = createInput(sc);
            Minesweeper game = createMinesweeper(input); //each minesweeper class has its own gameboard separate from others.
            String output = "Field #" + getFieldNumber() + "\n" + game.toString() + "\n";
            out.write(output);
            System.out.print(output); //the complete minesweeper is now printed, ending this loop.
        }
        out.close();
        return true;
    }

    public static void main(String args[]) {
        fieldNumber = 0;
        System.out.print("Please enter input file name: "); //prompts user to enter file name
        try {
            readFile();
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
