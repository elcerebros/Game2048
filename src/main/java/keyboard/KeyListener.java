package keyboard;

import mainpart.Direction;
import mainpart.Main;

public interface KeyListener {
    void update();

    Direction lastDirectionKeyPressed();

    boolean wasEscPressed();
}
