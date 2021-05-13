package keyboard;

import mainpart.Main;

public interface KeyListener {
    void update();

    Main.Direction lastDirectionKeyPressed();

    boolean wasEscPressed();
}
