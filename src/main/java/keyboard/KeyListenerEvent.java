package keyboard;

import mainpart.Direction;

import java.awt.event.KeyEvent;

public class KeyListenerEvent implements KeyListener {
    private KeyEvent e;
    private Direction lastDirectionKeyPressed;
    private boolean wasEscPressed;

    public void update() {
        java.awt.event.KeyListener listener = new java.awt.event.KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                resetValues();
                lastDirectionKeyPressed = Direction.WAIT;

                while (e.isActionKey()) {
                    if (e.getKeyCode() == KeyEvent.VK_LEFT) lastDirectionKeyPressed = Direction.LEFT;
                    if (e.getKeyCode() == KeyEvent.VK_RIGHT) lastDirectionKeyPressed = Direction.RIGHT;
                    if (e.getKeyCode() == KeyEvent.VK_UP) lastDirectionKeyPressed = Direction.UP;
                    if (e.getKeyCode() == KeyEvent.VK_DOWN) lastDirectionKeyPressed = Direction.DOWN;
                    if (e.getKeyCode() == KeyEvent.VK_ESCAPE) wasEscPressed = true;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        };
    }

    private void resetValues() {
        lastDirectionKeyPressed = Direction.WAIT;
        wasEscPressed = false;
    }

    @Override
    public Direction lastDirectionKeyPressed() {
        return lastDirectionKeyPressed;
    }

    @Override
    public boolean wasEscPressed() {
        return wasEscPressed;
    }
}
