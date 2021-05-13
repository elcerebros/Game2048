package mainpart;

public class ExceptionsCatcher {
    public static void cellCreationFail() {
        System.err.println("Main class failed to create new cell");
        System.exit(-1);
    }

    public static void shiftFail() {
        System.err.println("Shifting of cells failed");
        System.exit(-2);
    }

    public static void graphicsFail(Exception e) {
        System.err.println("Graphics failed");
        System.exit(-3);
        e.printStackTrace();
    }
}