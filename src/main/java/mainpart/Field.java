package mainpart;

import java.util.Arrays;

public class Field {
    private int[][] field;

    public Field(){
        field = new int[Constants.COUNT_CELLS_X][Constants.COUNT_CELLS_Y];

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
        int[] res = new int[Constants.COUNT_CELLS_X];
        for(int j = 0; j < Constants.COUNT_CELLS_X; j++) {
            res[j] = field[j][i];
        }
        return res;
    }

    public void setLine(int i, int[] newLine) {
        for(int j = 0; i < Constants.COUNT_CELLS_X; j++) {
            field[j][i] = newLine[j];
        }
    }
}
