package mainpart;

import graphics.GameFrame;
import javafx.application.Application;

import static mainpart.Logic.*;

public class Main {
    public static void main(String[] args) {
        init();
        createInitialCells();
        Application.launch(GameFrame.class, args);

        printGameResult();
    }
}
