import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class TesterTest {

    @Test
    void getFieldNumber() throws IOException {
        Tester myTester = new Tester();
        String input = "jUnitInput.txt";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        Assertions.assertEquals(true, myTester.readFile()); //inserts txt file into scanner
        Assertions.assertEquals(1, myTester.getFieldNumber()); //test expected and actual ammount of fields
    }

    @Test
    void createMinesweeper() {
        Tester myTester = new Tester();
        ArrayList<String> input = new ArrayList<>();
        String expectedGame =
                "*100\n" +
                "2210\n" +
                "1*10\n" +
                "1110\n";
        input.add("4 4");
        input.add("*...");
        input.add("....");
        input.add(".*..");
        input.add("....");
        Minesweeper myMinesweeper = myTester.createMinesweeper(input);
        String actualGame = myMinesweeper.toString();

        Assertions.assertEquals(expectedGame, actualGame); //expected and actual output of game
    }

    @Test
    void createInput() throws FileNotFoundException {
        Tester myTester = new Tester();
        File file = new File("jUnitInput.txt");
        Scanner sc = new Scanner(file);
        ArrayList<String> expectedList = new ArrayList<>();
        expectedList.add("4 4");
        expectedList.add("*...");
        expectedList.add("....");
        expectedList.add(".*..");
        expectedList.add("....");
        ArrayList<String> actualList = myTester.createInput(sc);

        Assertions.assertEquals(expectedList, actualList); //compares two lists. true if the file is read in right
    }
}