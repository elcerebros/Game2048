package graphics;

import java.util.HashMap;

class GraphicsSpriteSystem {
    private final HashMap<Integer, GraphicsSprite> spriteByNumber = new HashMap<>();

    GraphicsSpriteSystem() {
        for (GraphicsSprite sprite : GraphicsSprite.values()) {
            if (sprite.getSpriteNumber() != null) {
                spriteByNumber.put(sprite.getSpriteNumber(), sprite);
            }
        }
    }

    public GraphicsSprite getSpriteByNumber(int n) {
        return spriteByNumber.getOrDefault(n, GraphicsSprite.EMPTY);
    }
}
