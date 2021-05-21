package mainpart;

import graphics.GameApp;
import javafx.application.Application;

import static mainpart.Logic.*;

public class Main {
    public static void main(String[] args) {
        init();
        Application.launch(GameApp.class, args);
    }
}