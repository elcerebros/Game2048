package mainpart;

import java.util.Random;

import static mainpart.Constants.*;

public class Logic {
    public static Field field; //Поле - матричное представление игры

    public static String direction; //Направление сдвига в поле

    public static boolean endOfGame; //Флаг конца игры

    public static boolean reached2048; //Флаг достижения ячейки с числом 2048

    public static int score; //Счёт игрока

    private static class ShiftRowRes { //Вспомогательный класс при сдвиге ячеек
        boolean existenceOfZeroOrSameCells;
        int[] shiftedRow;
    }

    //Алгоритм сдвига ячеек
    public static void logicOfShifting() {
        if (!direction.equals("WAIT")) {
            if (shift(direction)) generateNewCell();
            else ExceptionsCatcher.cellCreationFail();
            direction = "WAIT";
        }
    }

    //Инициатор полей программы
    public static void init() {
        field = new Field();
        direction = "WAIT";
        endOfGame = false;
        reached2048 = false;
        score = 0;
    }

    //Инициация первых ячеек при запуске программы
    public static void createInitialCells() {
        for (int i = 0; i < COUNT_INITIAL_CELLS; i++) {
            generateNewCell();
        }
    }

    //Генерация ячеек
    private static void generateNewCell() {
        int number = (new Random().nextInt(100) <= CHANCE_OF_LUCKY_SPAWN)
                ? LUCKY_INITIAL_CELL_NUMBER
                : INITIAL_CELL_NUMBER;
        int randomX = new Random().nextInt(COUNT_CELLS_X);
        int currentX = randomX;
        int randomY = new Random().nextInt(COUNT_CELLS_Y);
        int currentY = randomY;

        boolean placed = false;
        while (!placed) {
            if (field.getNumber(currentX, currentY) == 0) {
                field.setNumber(currentX, currentY, number);
                placed = true;
            } else {
                if (currentX + 1 < COUNT_CELLS_X) currentX++;
                else {
                    currentX = 0;
                    if (currentY + 1 < COUNT_CELLS_Y) currentY++;
                    else currentY = 0;
                }
                if (currentX == randomX && currentY == randomY) ExceptionsCatcher.cellCreationFail();
            }
        }
        score += number;
    }

    //Главная ф-ия сдвига ячеек
    private static boolean shift(String direction) {
        boolean ret = false;

        switch (direction) {
            case "UP", "DOWN" -> {
                for (int i = 0; i < COUNT_CELLS_X; i++) {
                    int[] arg = field.getColumn(i);

                    if (direction.equals("DOWN")) {
                        int[] tmp = new int[arg.length];

                        for (int j = 0; j < tmp.length; j++) {
                            tmp[j] = arg[tmp.length - j - 1];
                        }

                        arg = tmp;
                    }

                    ShiftRowRes res = shiftRow(arg);

                    if (direction.equals("DOWN")) {
                        int[] tmp = new int[res.shiftedRow.length];

                        for (int j = 0; j < tmp.length; j++) {
                            tmp[j] = res.shiftedRow[tmp.length - j - 1];
                        }

                        res.shiftedRow = tmp;
                    }

                    field.setColumn(i, res.shiftedRow);
                    ret = ret || res.existenceOfZeroOrSameCells;
                }
            }
            case "LEFT", "RIGHT" -> {
                for (int i = 0; i < COUNT_CELLS_Y; i++) {
                    int[] arg = field.getLine(i);

                    if (direction.equals("RIGHT")) {
                        int[] tmp = new int[arg.length];

                        for (int j = 0; j < tmp.length; j++) {
                            tmp[j] = arg[tmp.length - j - 1];
                        }

                        arg = tmp;
                    }

                    ShiftRowRes res = shiftRow(arg);

                    if (direction.equals("RIGHT")) {
                        int[] tmp = new int[res.shiftedRow.length];

                        for (int j = 0; j < tmp.length; j++) {
                            tmp[j] = res.shiftedRow[tmp.length - j - 1];
                        }

                        res.shiftedRow = tmp;
                    }

                    field.setLine(i, res.shiftedRow);
                    ret = ret || res.existenceOfZeroOrSameCells;
                }
            }
            default -> ExceptionsCatcher.shiftFail();
        }

        return ret;
    }

    //Ф-ия сдвига ячеек, которая сдвигает нули и складывает одинаковые  соседние ячейки
    private static ShiftRowRes shiftRow(int[] oldRow) {
        ShiftRowRes ret = new ShiftRowRes();
        int[] oldRowWithoutZeroes = new int[oldRow.length];

        { //Сдвиг нулей
            int q = 0;

            for (int j : oldRow) {
                if (j != 0) {
                    oldRowWithoutZeroes[q] = j;
                    q++;
                } else ret.existenceOfZeroOrSameCells = true;
            }

            for (int i = q; i < oldRowWithoutZeroes.length; i++){
                oldRowWithoutZeroes[i] = 0;
            }
        }

        ret.shiftedRow = new int[oldRowWithoutZeroes.length];

        { //Складывание одинаковых  соседних ячеек
            int q = 0;

                int i = 0;

                while (i < oldRowWithoutZeroes.length) {
                    if ((i + 1 < oldRowWithoutZeroes.length) && (oldRowWithoutZeroes[i] == oldRowWithoutZeroes[i + 1])
                            && (oldRowWithoutZeroes[i] != 0)) {
                        ret.existenceOfZeroOrSameCells = true;
                        ret.shiftedRow[q] = oldRowWithoutZeroes[i] * 2;

                        if (ret.shiftedRow[q] == 2048) flagEndOfGame();
                        i++;
                    } else ret.shiftedRow[q] = oldRowWithoutZeroes[i];

                    q++;
                    i++;
                }


            for (int j = q; j < ret.shiftedRow.length; j++) {
                ret.shiftedRow[j] = 0;
            }
        }

        return ret;
    }

    private static void flagEndOfGame() {
        endOfGame = true;
        reached2048 = true;
    }
}
