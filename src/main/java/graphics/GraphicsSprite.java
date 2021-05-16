package graphics;

import javafx.scene.image.Image;

import java.io.InputStream;

public enum GraphicsSprite {
    CELL2(2), CELL4(4), CELL8(8), CELL16(16), CELL32(32), CELL64(64), CELL128(128), CELL256(256),
    CELL512(512), CELL1024(1024), CELL2048(2048), EMPTY("empty");

    private final Image image;
    private final Integer spriteNumber;

    GraphicsSprite(String textureName) {
        this(textureName, null);
    }

    GraphicsSprite(int spriteNumber) {
        this(String.valueOf(spriteNumber), spriteNumber);
    }

    GraphicsSprite(String textureName, Integer spriteNumber) {
        this.spriteNumber = spriteNumber;
        InputStream ImageStream = getClass().getResourceAsStream(String.format("/textures/%s.png", textureName));
        image = new Image(ImageStream);
    }

    public Image getImage() {
        return this.image;
    }

    Integer getSpriteNumber() {
        return spriteNumber;
    }
}
