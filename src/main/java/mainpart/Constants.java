package mainpart;

public class Constants {
    public static final String NAME = "2048: Like Edition";

    public static final int CELL_SIZE = 151; //Размер одной ячейки

    //Количество ячеек по вертикали и горизонтали
    public static int COUNT_CELLS_X = 4;
    public static int COUNT_CELLS_Y = 4;

    public static int INITIAL_CELL_NUMBER = 2; //Изначальное значение первых ячеек на поле
    public static int COUNT_INITIAL_CELLS = 2; //Изначальное количество ячеек на поле

    public static final int LUCKY_INITIAL_CELL_NUMBER = 4; //Значение ячейки при удачном исходе событий
    public static final int CHANCE_OF_LUCKY_SPAWN = 17; //Шанс появления ячейки со значением LUCKY_INITIAL_CELL_NUMBER
}
