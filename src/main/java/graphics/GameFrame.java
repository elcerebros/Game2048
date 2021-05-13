package graphics;

import mainpart.Constants;
import mainpart.ExceptionsCatcher;
import mainpart.Field;

import javax.swing.*;
import java.awt.*;

public class GameFrame implements GraphicsMain {
    public GameFrame() {
        //initial();
        //SpriteSystem = new
    }

    private static void initial() {
        try {
            JFrame frame = new JFrame(Constants.NAME);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            frame.setPreferredSize(new Dimension(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT));

            frame.pack();
            frame.setVisible(true);
        } catch (Exception e) {
            ExceptionsCatcher.graphicsFail(e);
        }

    }


    public void draw(Field field) {

    }

    public boolean isClosed() {
        return true;
    }

    public void destroy() {

    }

    public static void main(String[] args) {

        JFrame.setDefaultLookAndFeelDecorated(true);

    }
}
