package keyboard;

import mainpart.Main;

import java.awt.event.KeyEvent;

public class KeyListenerEvent implements KeyListener {
    private KeyEvent event;
    private Main.Direction lastDirectionKeyPressed;
    private boolean wasEscPressed;

    public void update() {
        resetValues();
        lastDirectionKeyPressed = Main.Direction.WAIT;

        while (event.isActionKey()) {
            if (event.getKeyCode() == KeyEvent.VK_LEFT) lastDirectionKeyPressed = Main.Direction.LEFT;
            if (event.getKeyCode() == KeyEvent.VK_RIGHT) lastDirectionKeyPressed = Main.Direction.RIGHT;
            if (event.getKeyCode() == KeyEvent.VK_UP) lastDirectionKeyPressed = Main.Direction.UP;
            if (event.getKeyCode() == KeyEvent.VK_DOWN) lastDirectionKeyPressed = Main.Direction.DOWN;
            if (event.getKeyCode() == KeyEvent.VK_ESCAPE) wasEscPressed = true;
        }
    }

    private void resetValues() {
        lastDirectionKeyPressed = Main.Direction.WAIT;
        wasEscPressed = false;
    }

    @Override
    public Main.Direction lastDirectionKeyPressed() {
        return lastDirectionKeyPressed;
    }

    @Override
    public boolean wasEscPressed() {
        return wasEscPressed();
    }
}
