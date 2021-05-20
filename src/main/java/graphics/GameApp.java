package graphics;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.scene.control.*;

import java.awt.*;
import java.io.InputStream;

import mainpart.Field;
import mainpart.Logic;

import static mainpart.Constants.*;
import static mainpart.Logic.*;
import static mainpart.Logic.init;

public class GameApp extends Application {
    private boolean start = false;

    private boolean flagOfReset = false;

    private boolean wasEscPressed = false;

    private final GridPane gridPane = new GridPane();

    private final GraphicsImageSystem spriteSystem;

    public GameApp() { spriteSystem = new GraphicsImageSystem(); }

    public void start(Stage primaryStage) {
        VBox vBox = new VBox();

        InputStream iconStream = getClass().getResourceAsStream("/icon.png");
        Image imageIcon = new Image(iconStream);
        primaryStage.getIcons().add(imageIcon);

        for (int i = 0; i < COUNT_CELLS_X; i++) {
            gridPane.getColumnConstraints().add(new ColumnConstraints(CELL_SIZE));
        }
        for (int i = 0; i < COUNT_CELLS_Y; i++) {
            gridPane.getRowConstraints().add(new RowConstraints(CELL_SIZE));
        }
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setGridLinesVisible(true);


        MenuBar menuBar = new MenuBar();
        SeparatorMenuItem separator1 = new SeparatorMenuItem();
        SeparatorMenuItem separator2 = new SeparatorMenuItem();

        Menu main = new Menu("Main");
        MenuItem startGame = new MenuItem("Start");
        startGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Logic.init();
                createInitialCells();
                start = true;
            }
        });
        MenuItem reset = new MenuItem("Reset");
        reset.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Logic.init();
                createInitialCells();
            }
        });
        MenuItem exit = new MenuItem("Exit");
        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                wasEscPressed = true;
            }
        });
        main.getItems().addAll(startGame, reset, separator1, exit);

        Menu game = new Menu("Game");
        MenuItem play = new MenuItem("Play");
        play.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                start = true;
            }
        });
        MenuItem pause = new MenuItem("Pause");
        pause.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                start = false;
            }
        });
        game.getItems().addAll(play, pause);

        Menu settings = new Menu("Configurations");
        Menu fieldConfig = new Menu("Field dimension");
        CheckMenuItem mode4 = new CheckMenuItem("4 x 4");
        CheckMenuItem mode5 = new CheckMenuItem("5 x 5");
        mode4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                mode5.setSelected(false);
                COUNT_CELLS_X = 4;
                COUNT_CELLS_Y = 4;
                Logic.init();
                primaryStage.setHeight(COUNT_CELLS_X * CELL_SIZE + 64);
                primaryStage.setWidth(COUNT_CELLS_Y * CELL_SIZE + 14);
                draw(field);
            }
        });
        mode5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                mode4.setSelected(false);
                COUNT_CELLS_X = 5;
                COUNT_CELLS_Y = 5;
                Logic.init();
                primaryStage.setHeight(COUNT_CELLS_X * CELL_SIZE + 64);
                primaryStage.setWidth(COUNT_CELLS_Y * CELL_SIZE + 14);
                draw(field);
            }
        });
        Menu initialCellNumber = new Menu("initial number of cells");
        CheckMenuItem initialCellNumber2 = new CheckMenuItem("2");
        CheckMenuItem initialCellNumber4 = new CheckMenuItem("4");
        CheckMenuItem initialCellNumber8 = new CheckMenuItem("8");
        initialCellNumber2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                initialCellNumber4.setSelected(false);
                initialCellNumber8.setSelected(false);
                INITIAL_CELL_NUMBER = 2;
                Logic.init();
                draw(field);
            }
        });
        initialCellNumber4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                initialCellNumber2.setSelected(false);
                initialCellNumber8.setSelected(false);
                INITIAL_CELL_NUMBER = 4;
                Logic.init();
                draw(field);
            }
        });
        initialCellNumber8.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                initialCellNumber2.setSelected(false);
                initialCellNumber4.setSelected(false);
                INITIAL_CELL_NUMBER = 8;
                Logic.init();
                draw(field);
            }
        });
        Menu countInitialCells = new Menu("Count of Initial cells");
        CheckMenuItem countOfInitialCells2 = new CheckMenuItem("2");
        CheckMenuItem countOfInitialCells3 = new CheckMenuItem("3");
        CheckMenuItem countOfInitialCells4 = new CheckMenuItem("4");
        countOfInitialCells2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                countOfInitialCells3.setSelected(false);
                countOfInitialCells4.setSelected(false);
                COUNT_INITIAL_CELLS = 2;
                Logic.init();
                draw(field);
            }
        });
        countOfInitialCells3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                countOfInitialCells2.setSelected(false);
                countOfInitialCells4.setSelected(false);
                COUNT_INITIAL_CELLS = 3;
                Logic.init();
                draw(field);
            }
        });
        countOfInitialCells4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                countOfInitialCells3.setSelected(false);
                countOfInitialCells2.setSelected(false);
                COUNT_INITIAL_CELLS = 4;
                Logic.init();
                draw(field);
            }
        });
        settings.getItems().addAll(fieldConfig, initialCellNumber, countInitialCells);
        fieldConfig.getItems().addAll(mode4, mode5);
        countInitialCells.getItems().addAll(countOfInitialCells2, countOfInitialCells3, countOfInitialCells4);
        initialCellNumber.getItems().addAll(initialCellNumber2, initialCellNumber4, initialCellNumber8);

        menuBar.getMenus().add(main);
        menuBar.getMenus().add(game);
        menuBar.getMenus().add(settings);


        Label label = new Label("To start the game, click Start in the menu column Main");
        label.setTextAlignment(TextAlignment.CENTER);
        Button button = new Button("OK");

        GridPane gridPaneInfo = new GridPane();
        gridPaneInfo.getRowConstraints().add(new RowConstraints(30));
        gridPaneInfo.getRowConstraints().add(new RowConstraints(40));
        gridPaneInfo.setHalignment(button, HPos.CENTER);
        gridPaneInfo.setValignment(button, VPos.CENTER);
        gridPaneInfo.setHalignment(label, HPos.CENTER);
        gridPaneInfo.setValignment(label, VPos.BOTTOM);
        gridPaneInfo.add(label, 0, 0);
        gridPaneInfo.add(button, 0, 1);
        Scene secondScene = new Scene(gridPaneInfo, 300, 70);

        Stage newWindow = new Stage();

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                newWindow.close();
            }
        });

        newWindow.setTitle("Info");
        newWindow.setScene(secondScene);

        Logic.init();
        draw(field);
        mode4.setSelected(true);
        initialCellNumber2.setSelected(true);
        countOfInitialCells2.setSelected(true);

        vBox.getChildren().addAll(menuBar, gridPane);
        Scene scene = new Scene(vBox);
        primaryStage.setScene(scene);
        primaryStage.setTitle(NAME);
        primaryStage.show();
        newWindow.show();

        scene.setOnKeyPressed(
                keyEvent -> {
                    if (start) {
                        switch (keyEvent.getCode().toString()) {
                            case "UP" -> direction = "UP";
                            case "DOWN" -> direction = "DOWN";
                            case "LEFT" -> direction = "LEFT";
                            case "RIGHT" -> direction = "RIGHT";
                            case "ESCAPE" -> wasEscPressed = true;
                        }
                    }
                }
        );

        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                if (start) {
                    if (!wasEscPressed && !endOfGame) {
                        logicOfGame();
                        draw(field);
                    } else {
                        primaryStage.close();
                    }
                }
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
