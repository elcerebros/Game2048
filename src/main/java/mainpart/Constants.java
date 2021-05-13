package mainpart;

public class Constants {
    public static final String NAME = "2048";

    public static final int CELL_SIZE = 200; //Размер одной ячейки

    //Количество ячеек по вертикали и горизонтали
    public static final int COUNT_CELLS_X = 4;
    public static final int COUNT_CELLS_Y = 4;

    //Ширина и высота окна с игрой
    public static final int SCREEN_WIDTH = COUNT_CELLS_X * CELL_SIZE;
    public static final int SCREEN_HEIGHT = COUNT_CELLS_Y * CELL_SIZE;

    public static final int INITIAL_CELL_NUMBER = 2; //Изначальное значение первых ячеек на поле
    public static final int COUNT_INITIAL_CELLS = 2; //Изначальное количество ячеек на поле

    public static final int LUCKY_INITIAL_CELL_NUMBER = 4; //Значение ячейки при удачном исходе событий
    public static final int CHANCE_OF_LUCKY_SPAWN = 17; //Шанс появления ячейки со значением LUCKY_INITIAL_CELL_NUMBER
}
