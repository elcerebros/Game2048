package mainpart;

import java.util.Arrays;

import static mainpart.Constants.*;

public class Field {
    private final int[][] field;

    public Field(){
        field = new int[COUNT_CELLS_X][COUNT_CELLS_Y];

        for (int[] ints : field) {
            Arrays.fill(ints, 0);
        }
    }

    public int getNumber(int x, int y) {
        return field[x][y];
    }

    public void setNumber(int x, int y, int number) {
        field[x][y] = number;
    }

    public int[] getColumn(int i) {
        return field[i];
    }

    public void setColumn(int i, int[] newColumn) {
        field[i] = newColumn;
    }

    public int[] getLine(int i) {
        int[] res = new int[COUNT_CELLS_X];

        for(int j = 0; j < COUNT_CELLS_X; j++) {
            res[j] = field[j][i];
        }

        return res;
    }

    public void setLine(int i, int[] newLine) {
        for(int j = 0; j < COUNT_CELLS_X; j++) {
            field[j][i] = newLine[j];
        }
    }
}
