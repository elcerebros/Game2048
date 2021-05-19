package mainpart;

public class ExceptionsCatcher {
    public static void cellCreationFail() { //Исключение при полном заполнении поля и отрицательного флага reached2048
        System.err.println("Main class failed to create new cell");
        System.exit(-1);
    }

    public static void shiftFail() { //Исключение при сдвиге
        System.err.println("Shifting of cells failed");
        System.exit(-2);
    }
}
