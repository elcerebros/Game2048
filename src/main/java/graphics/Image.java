package graphics;

import java.io.InputStream;
import java.util.Objects;

public enum Image {
    CELL2(2), CELL4(4), CELL8(8), CELL16(16), CELL32(32),
    CELL64(64), CELL128(128), CELL256(256), CELL512(512),
    CELL1024(1024), CELL2048(2048), EMPTY("empty");

    private final javafx.scene.image.Image image;

    private final Integer imageNumber;

    Image(String textureName) { this(textureName, null); }

    Image(int imageNumber) { this(String.valueOf(imageNumber), imageNumber); }

    Image(String ImageName, Integer imageNumber) {
        this.imageNumber = imageNumber;
        InputStream ImageStream = getClass().getResourceAsStream(String.format("/textures/%s.png", ImageName));
        image = new javafx.scene.image.Image(Objects.requireNonNull(ImageStream));
    }

    public javafx.scene.image.Image getImage() {
        return this.image;
    }

    Integer getImageNumber() {
        return this.imageNumber;
    }
}
