package graphics;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import mainpart.Constants;
import mainpart.Direction;
import mainpart.Field;
import mainpart.Main;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.InputStream;

import static javafx.application.Application.launch;

public class GameFrame extends Application {
    private GraphicsSpriteSystem spriteSystem;

    private final javafx.scene.canvas.Canvas canvas = new Canvas(Constants.CELL_SIZE * 4, Constants.CELL_SIZE * 4);
    private final GraphicsContext gc = canvas.getGraphicsContext2D();

    private Direction lastDirectionKeyPressed;
    private boolean wasEscPressed;

    public GameFrame() {
        spriteSystem = new GraphicsSpriteSystem();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
        primaryStage.setTitle(Constants.NAME);
        Group root = new Group();

        InputStream iconStream = getClass().getResourceAsStream("/icon.png");
        Image image = new Image(iconStream);
        primaryStage.getIcons().add(image);

        GridPane gridPane = new GridPane();
        for (int i = 0; i < 4; i++) {
            gridPane.getColumnConstraints().add(new ColumnConstraints(Constants.CELL_SIZE));
            gridPane.getRowConstraints().add(new RowConstraints(Constants.CELL_SIZE));
        }

        /*
        InputStream ImageStream = getClass().getResourceAsStream("/textures/4.png");
        Image image1 = new Image(ImageStream);
        ImageView image2 = new ImageView(image1);
        gridPane.add(image2, 1, 1);
         */

        gridPane.setGridLinesVisible(true);

        root.getChildren().add(canvas);
        root.getChildren().add(gridPane);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void draw(Field field) {
        for (int i = 0; i < Constants.COUNT_CELLS_X; i++) {
            for (int j = 0; j < Constants.COUNT_CELLS_Y; j++) {
                drawCell(i * Constants.CELL_SIZE, j * Constants.CELL_SIZE, field.getNumber(i, j));
            }
        }
    }

    private void drawCell(int x, int y, int number) {
        gc.drawImage(spriteSystem.getSpriteByNumber(number).getImage(), x, y);
    }

    private void resetValues() {
        lastDirectionKeyPressed = Direction.WAIT;
        wasEscPressed = false;
    }

    public Direction lastDirectionKeyPressed() {
        return lastDirectionKeyPressed;
    }

    public boolean wasEscPressed() {
        return wasEscPressed;
    }
}
