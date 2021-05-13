package graphics;

import mainpart.Field;

public interface GraphicsMain {
    void draw(Field field);

    boolean isClosed();

    void destroy();
}
