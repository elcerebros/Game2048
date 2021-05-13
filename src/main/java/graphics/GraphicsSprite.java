package graphics;

import com.jogamp.opengl.FBObject;
import com.jogamp.opengl.util.awt.TextureRenderer;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureCoords;
import com.jogamp.opengl.util.texture.TextureData;
import com.jogamp.opengl.util.texture.TextureState;
import com.jogamp.opengl.util.texture.spi.TextureWriter;

import java.awt.*;
import java.io.IOException;

public enum GraphicsSprite {
    CELL2(2), CELL4(4), CELL8(8), CELL16(16), CELL32(32), CELL64(64), CELL128(128), CELL256(256),
    CELL512(512), CELL1024(1024), CELL2048(2048), EMPTY("empty");

    private Texture texture;
    private Integer spriteNumber;

    GraphicsSprite(String textureName) {
        this(textureName, null);
    }

    GraphicsSprite(int spriteNumber) {
        this(String.valueOf(spriteNumber), spriteNumber);
    }

    GraphicsSprite(String textureName, Integer spriteNumber) {
        this.spriteNumber = spriteNumber;
        try {
            texture = Texture(
                    "PNG",
                    getClass().getClassLoader((String.format("texture/%s.png", textureName)))
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Texture getTexture() {
        return this.texture;
    }

    Integer getSpriteNumber() {
        return spriteNumber;
    }
}
