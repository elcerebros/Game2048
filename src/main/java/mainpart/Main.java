package mainpart;

import java.util.Random;

public class Main {
    public static int score;
    private static boolean endOfGame;
    private static boolean flag2048;
    private static Field field;
    private static Direction direction;


    public enum Direction {
        UP, DOWN, LEFT, RIGHT, WAIT
    }

    public static void main(String[] args) {
        initFields();
        createInitialCells();

        while (!endOfGame) {
            input();
            Logic();

            //
        }

        //

        printGameResult();
    }

    private static void initFields() {
        score = 0;
        endOfGame = false;
        flag2048 = false;
        field = new Field();
        direction = Direction.WAIT;
        //
    }

    private static void createInitialCells() {
        for (int i = 0; i < Constants.COUNT_INITIAL_CELLS; i++) {
            generateNewCell();
        }
    }

    private static void Logic() {
        if (direction != Direction.WAIT) {
            if (shift(direction)) generateNewCell();
            direction = Direction.WAIT;
        }
    }

    private static void input() {
        //KeyListener.update();
        //direction = ;
    }

    private static void generateNewCell() {
        int number = (new Random().nextInt(100) <= Constants.CHANCE_OF_LUCKY_SPAWN)
                ? Constants.LUCKY_INITIAL_CELL_NUMBER
                : Constants.INITIAL_CELL_NUMBER;
        int randomX, randomY;

        randomX = new Random().nextInt(Constants.COUNT_CELLS_X);
        int currentX = randomX;
        randomY = new Random().nextInt(Constants.COUNT_CELLS_Y);
        int currentY = randomY;

        boolean placed = false;
        while (!placed) {
            if (field.getNumber(currentX, currentY) == 0) {
                field.setNumber(currentX, currentY, number);
                placed = true;
            } else {
                if (currentX + 1 < Constants.COUNT_CELLS_X) {
                    currentX++;
                } else {
                    currentX = 0;
                    if (currentY + 1 < Constants.COUNT_CELLS_Y) {
                        currentY++;
                    } else {
                        currentY = 0;
                    }
                }
                if ((currentX == randomX) && (currentY == randomY)) {
                    //error
                }
            }
        }

        score += number;
    }

    private static class ShiftRowRes {
        boolean didAnythingMove;
        int[] shiftedRow;
    }

    private static boolean shift(Direction direction) {
        boolean ret = false;

        switch (direction) {
            case UP, DOWN -> {
                for (int i = 0; i < Constants.COUNT_CELLS_Y; i++) {
                    int[] arg = field.getColumn(i);

                    if (direction == Direction.UP) {
                        int[] tmp = new int[arg.length];
                        for (int j = 0; j < tmp.length; j++) {
                            tmp[j] = arg[tmp.length - j - 1];
                        }
                        arg = tmp;
                    }

                    ShiftRowRes res = shiftRow(arg);

                    if (direction == Direction.UP) {
                        int[] tmp = new int[res.shiftedRow.length];
                        for (int j = 0; j < tmp.length; j++) {
                            tmp[j] = res.shiftedRow[tmp.length - j - 1];
                        }
                        res.shiftedRow = tmp;
                    }

                    field.setColumn(i, res.shiftedRow);

                    ret = ret || res.didAnythingMove;
                }
            }
            case LEFT, RIGHT -> {
                for (int i = 0; i < Constants.COUNT_CELLS_X; i++) {
                    int[] arg = field.getLine(i);

                    if (direction == Direction.RIGHT) {
                        int[] tmp = new int[arg.length];
                        for (int j = 0; j < tmp.length; j++) {
                            tmp[j] = arg[tmp.length - j - 1];
                        }
                        arg = tmp;
                    }

                    ShiftRowRes res = shiftRow(arg);

                    if (direction == Direction.RIGHT) {
                        int[] tmp = new int[res.shiftedRow.length];
                        for (int j = 0; j < tmp.length; j++) {
                            tmp[j] = res.shiftedRow[tmp.length - j - 1];
                        }
                        res.shiftedRow = tmp;
                    }

                    field.setLine(i, res.shiftedRow);

                    ret = ret || res.didAnythingMove;
                }
            }
            default -> ExceptionsCatcher.shiftFail();
        }

        return ret;
    }

    private static ShiftRowRes shiftRow(int[] oldRow) {
        ShiftRowRes ret = new ShiftRowRes();

        int[] oldRowWithoutZeroes = new int[oldRow.length];
        {
            int q = 0;

            for (int j : oldRow) {
                if (j == 0) {
                    ret.didAnythingMove = true;
                }

                oldRowWithoutZeroes[q] = j;
                q++;
            }

            for (int i = q; i < oldRowWithoutZeroes.length; i++){
                oldRowWithoutZeroes[i] = 0;
            }
        }

        ret.shiftedRow = new int[oldRowWithoutZeroes.length];

        {
            int q = 0;
            {
                int i = 0;

                while (i < oldRowWithoutZeroes.length) {
                    if ((i + 1 < oldRowWithoutZeroes.length) && (oldRowWithoutZeroes[i] == oldRowWithoutZeroes[i + 1])
                            && (oldRowWithoutZeroes[i] != 0)) {
                        ret.didAnythingMove = true;
                        ret.shiftedRow[q] = oldRowWithoutZeroes[i] * 2;
                        if (ret.shiftedRow[q] == 2048) {
                            merged2048();
                        }
                        i++;
                    } else {
                        ret.shiftedRow[q] = oldRowWithoutZeroes[i];
                    }

                    q++;
                    i++;
                }
            }
            for (int j = q; j < ret.shiftedRow.length; j++) {
                ret.shiftedRow[j] = 0;
            }
        }

        return ret;
    }

    private static void merged2048() {
        endOfGame = true;
        flag2048 = true;
    }

    private static void printGameResult() {
        System.out.println("You " + (flag2048 ? "won!" : "lost(") );
        System.out.println("Your score is " + score);
    }
}
