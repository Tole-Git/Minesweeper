import java.util.concurrent.ThreadLocalRandom;

public class inputGenerator {
    private String inputText = "";
    private int row;
    private int column;
    private final String mine = "*";
    private final String open = ".";

    public inputGenerator() {
        for (int i = 0; i < 10; i++) {
            int determineMines = ThreadLocalRandom.current().nextInt(1, 3 + 1);
            randomRowsAndColumns();
            displayRowsAndColumns();
            if (determineMines == 1) {
                allMines();
            } else if (determineMines == 2) {
                someMines();
            } else if (determineMines == 3) {
                noMines();
            }
        }
        row = 0;
        column = 0;
        displayRowsAndColumns();
        someMines();
        row = 1;
        column = 1;
        displayAllTypes();
        row = 100;
        column = 100;
        displayAllTypes();
        row = 1;
        column = 100;
        displayAllTypes();
        row = 100;
        column = 1;
        displayAllTypes();

    }

    private void displayRowsAndColumns() {
        inputText += row + " " + column +"\n";
    }

    private void displayAllTypes() {
        displayRowsAndColumns();
        allMines();
        displayRowsAndColumns();
        noMines();
        displayRowsAndColumns();
        someMines();
    }

    private void allMines() {
        for (int i = 0; i < row; i++){
            for (int j = 0; j < column; j++) {
                inputText += mine;
            }
            inputText += "\n";
        }
    }

    private void someMines() {
        for (int i = 0; i < row; i++){
            for (int j = 0; j < column; j++) {
                int random = ThreadLocalRandom.current().nextInt(1, 2 + 1);
                if (random == 2){
                    inputText += open;
                } else if (random == 1) {
                    inputText += mine;
                }

            }
            inputText += "\n";
        }
    }

    private void noMines() {
        for (int i = 0; i < row; i++){
            for (int j = 0; j < column; j++) {
                inputText += open;
            }
            inputText += "\n";
        }
    }

    private void randomRowsAndColumns() {
        row = ThreadLocalRandom.current().nextInt(0, 10 + 1);
        column = ThreadLocalRandom.current().nextInt(0, 10 + 1);
    }

    @Override
    public String toString() {
        return inputText;
    }
}
