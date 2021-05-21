package graphics;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;

import java.io.InputStream;
import java.util.Objects;

import mainpart.Field;
import mainpart.Logic;

import static mainpart.Constants.*;
import static mainpart.Logic.*;

public class GameApp extends Application {
    private final MenuBar menuBar = new MenuBar();

    private final GridPane gp = new GridPane();

    private final Label labelScore = new Label("There will be your score!");

    private final ImagesBase imagesBase = new ImagesBase();

    private boolean start = false;

    private boolean wasEscPressed = false;

    public void start(Stage primaryStage) {
        Stage newStage = initInfoStage();
        initMenuBar(primaryStage);
        initGridPane();

        BorderPane bp = new BorderPane();
        bp.setTop(menuBar);
        bp.setCenter(gp);
        bp.setBottom(labelScore);

        Scene scene = new Scene(bp);
        primaryStage.setScene(scene);
        InputStream iconStream = getClass().getResourceAsStream("/icons/main_icon.png");
        Image imageIcon = new Image(Objects.requireNonNull(iconStream));
        primaryStage.getIcons().add(imageIcon);
        primaryStage.setTitle(NAME);
        primaryStage.show();
        newStage.show();

        scene.setOnKeyPressed(
                keyEvent -> {
                    if (start) {
                        switch (keyEvent.getCode()) {
                            case UP -> direction = "UP";
                            case DOWN -> direction = "DOWN";
                            case LEFT -> direction = "LEFT";
                            case RIGHT -> direction = "RIGHT";
                            case ESCAPE -> wasEscPressed = true;
                        }
                    }
                    if (keyEvent.getCode() == KeyCode.ENTER) {
                        Logic.init();
                        createInitialCells();
                        start = true;
                    }
                }
        );

        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                if (start) {
                    if (!wasEscPressed && !endOfGame && !reached2048) {
                        logicOfShifting();
                        draw(field);
                        labelScore.setText("Score: " + score);
                    } else {
                        if (reached2048) {
                            labelScore.setText("You won!!!");
                        } else { primaryStage.close();}
                    }
                }
            }
        }.start();
    }

    private Stage initInfoStage() {
        Label label = new Label("Click Start in the menu column Main or press ENTER to start the game");
        label.setTextAlignment(TextAlignment.CENTER);
        Button button = new Button("OK");
        button.setAlignment(Pos.BOTTOM_CENTER);

        BorderPane.setAlignment(label, Pos.CENTER);
        BorderPane.setAlignment(button, Pos.BOTTOM_CENTER);
        BorderPane bp = new BorderPane();
        bp.setCenter(label);
        bp.setBottom(button);

        Scene secondScene = new Scene(bp, 400, 65);
        Stage newStage = new Stage();
        button.setOnAction(e -> newStage.close());
        InputStream iconStream = getClass().getResourceAsStream("/icons/info_icon.png");
        Image imageIcon = new Image(Objects.requireNonNull(iconStream));
        newStage.getIcons().add(imageIcon);
        newStage.setScene(secondScene);
        newStage.setTitle("Info");

        return newStage;
    }

    private void initGridPane() {
        for (int i = 0; i < COUNT_CELLS_X; i++) {
            gp.getColumnConstraints().add(new ColumnConstraints(CELL_SIZE));
        }
        for (int i = 0; i < COUNT_CELLS_Y; i++) {
            gp.getRowConstraints().add(new RowConstraints(CELL_SIZE));
        }
        gp.setAlignment(Pos.CENTER);
        gp.setGridLinesVisible(true);
    }

    private void initMenuBar(Stage primaryStage) {
        SeparatorMenuItem separator = new SeparatorMenuItem();

        Menu main = new Menu("Main");
        MenuItem startGame = new MenuItem("Start");
        startGame.setOnAction(e -> {
            Logic.init();
            createInitialCells();
            start = true;
        });
        MenuItem reset = new MenuItem("Reset");
        reset.setOnAction(e -> {
            Logic.init();
            createInitialCells();
        });
        MenuItem exit = new MenuItem("Exit");
        exit.setOnAction(e -> wasEscPressed = true);
        main.getItems().addAll(startGame, reset, separator, exit);

        Menu game = new Menu("Game");
        MenuItem play = new MenuItem("Play");
        play.setOnAction(e -> start = true);
        MenuItem pause = new MenuItem("Pause");
        pause.setOnAction(e -> start = false);
        game.getItems().addAll(play, pause);

        Menu config = new Menu("Configurations");
        Menu fieldConfig = new Menu("Field dimension");
        CheckMenuItem mode4 = new CheckMenuItem("4 x 4");
        CheckMenuItem mode5 = new CheckMenuItem("5 x 5");
        mode4.setOnAction(e -> {
            mode5.setSelected(false);
            COUNT_CELLS_X = 4;
            COUNT_CELLS_Y = 4;
            Logic.init();
            primaryStage.setHeight(COUNT_CELLS_Y * CELL_SIZE + 79);
            primaryStage.setWidth(COUNT_CELLS_X * CELL_SIZE + 14);
            draw(field);
        });
        mode5.setOnAction(e -> {
            mode4.setSelected(false);
            COUNT_CELLS_X = 5;
            COUNT_CELLS_Y = 5;
            Logic.init();
            primaryStage.setHeight(COUNT_CELLS_Y * CELL_SIZE + 79);
            primaryStage.setWidth(COUNT_CELLS_X * CELL_SIZE + 14);
            draw(field);
        });
        Menu initialCellNumber = new Menu("initial number of cells");
        CheckMenuItem initialCellNumber2 = new CheckMenuItem("2");
        CheckMenuItem initialCellNumber4 = new CheckMenuItem("4");
        CheckMenuItem initialCellNumber8 = new CheckMenuItem("8");
        initialCellNumber2.setOnAction(e -> {
            initialCellNumber4.setSelected(false);
            initialCellNumber8.setSelected(false);
            INITIAL_CELL_NUMBER = 2;
            Logic.init();
            draw(field);
        });
        initialCellNumber4.setOnAction(e -> {
            initialCellNumber2.setSelected(false);
            initialCellNumber8.setSelected(false);
            INITIAL_CELL_NUMBER = 4;
            Logic.init();
            draw(field);
        });
        initialCellNumber8.setOnAction(e -> {
            initialCellNumber2.setSelected(false);
            initialCellNumber4.setSelected(false);
            INITIAL_CELL_NUMBER = 8;
            Logic.init();
            draw(field);
        });
        Menu countInitialCells = new Menu("Count of Initial cells");
        CheckMenuItem countOfInitialCells2 = new CheckMenuItem("2");
        CheckMenuItem countOfInitialCells3 = new CheckMenuItem("3");
        CheckMenuItem countOfInitialCells4 = new CheckMenuItem("4");
        countOfInitialCells2.setOnAction(e -> {
            countOfInitialCells3.setSelected(false);
            countOfInitialCells4.setSelected(false);
            COUNT_INITIAL_CELLS = 2;
            Logic.init();
            draw(field);
        });
        countOfInitialCells3.setOnAction(e -> {
            countOfInitialCells2.setSelected(false);
            countOfInitialCells4.setSelected(false);
            COUNT_INITIAL_CELLS = 3;
            Logic.init();
            draw(field);
        });
        countOfInitialCells4.setOnAction(e -> {
            countOfInitialCells3.setSelected(false);
            countOfInitialCells2.setSelected(false);
            COUNT_INITIAL_CELLS = 4;
            Logic.init();
            draw(field);
        });
        config.getItems().addAll(fieldConfig, initialCellNumber, countInitialCells);
        fieldConfig.getItems().addAll(mode4, mode5);
        countInitialCells.getItems().addAll(countOfInitialCells2, countOfInitialCells3, countOfInitialCells4);
        initialCellNumber.getItems().addAll(initialCellNumber2, initialCellNumber4, initialCellNumber8);

        menuBar.getMenus().add(main);
        menuBar.getMenus().add(game);
        menuBar.getMenus().add(config);
        mode4.setSelected(true);
        initialCellNumber2.setSelected(true);
        countOfInitialCells2.setSelected(true);
    }

    public void draw(Field field) {
        for (int i = 0; i < COUNT_CELLS_X; i++) {
            for (int j = 0; j < COUNT_CELLS_Y; j++) {
                drawCell (field.getNumber(i, j), i, j);
            }
        }
    }

    private void drawCell(int number, int x, int y) {
        gp.add(new ImageView(imagesBase.getImageByNumber(number).getImage()), x, y);
    }
}