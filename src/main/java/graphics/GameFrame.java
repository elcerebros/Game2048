package graphics;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.io.InputStream;

import mainpart.Field;

import static mainpart.Constants.*;
import static mainpart.Logic.*;

public class GameFrame extends Application {
    private boolean wasEscPressed = false;

    private final Group root = new Group();

    private final Scene scene = new Scene(root);

    private final GridPane gridPane = new GridPane();

    private final GraphicsImageSystem spriteSystem;

    public GameFrame() { spriteSystem = new GraphicsImageSystem(); }

    public void start(Stage primaryStage) {
        primaryStage.setScene(scene);
        primaryStage.setTitle(NAME);

        InputStream iconStream = getClass().getResourceAsStream("/icon.png");
        Image imageIcon = new Image(iconStream);
        primaryStage.getIcons().add(imageIcon);

        root.getChildren().add(gridPane);
        for (int i = 0; i < COUNT_CELLS_X; i++) {
            gridPane.getColumnConstraints().add(new ColumnConstraints(CELL_SIZE));
        }
        for (int i = 0; i < COUNT_CELLS_Y; i++) {
            gridPane.getRowConstraints().add(new RowConstraints(CELL_SIZE));
        }
        gridPane.setGridLinesVisible(true);

        primaryStage.show();
        draw(field);

        scene.setOnKeyPressed(
                keyEvent -> {
                    switch (keyEvent.getCode().toString()) {
                        case "UP" -> direction = "UP";
                        case "DOWN" -> direction = "DOWN";
                        case "LEFT" -> direction = "LEFT";
                        case "RIGHT" -> direction = "RIGHT";
                        case "ESCAPE" -> wasEscPressed = true;
                    }
                }
        );

        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                if (!wasEscPressed && !endOfGame) {
                    logicOfGame();
                    draw(field);
                } else { primaryStage.close(); }
            }
        }.start();
    }

    public void draw(Field field) {
        for (int i = 0; i < COUNT_CELLS_X; i++) {
            for (int j = 0; j < COUNT_CELLS_Y; j++) {
                drawCell (field.getNumber(i, j), i, j);
            }
        }
    }

    private void drawCell(int number, int x, int y) {
        gridPane.add(new ImageView(spriteSystem.getSpriteByNumber(number).getImage()), x, y);
    }
}
