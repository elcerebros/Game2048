package graphics;

import java.util.HashMap;

class GraphicsImageSystem {
    private final HashMap<Integer, GraphicsImage> imageByNumber = new HashMap<>();

    GraphicsImageSystem() {
        for (GraphicsImage image : GraphicsImage.values()) {
            if (image.getImageNumber() != null) {
                imageByNumber.put(image.getImageNumber(), image);
            }
        }
    }

    public GraphicsImage getSpriteByNumber(int n) {
        return imageByNumber.getOrDefault(n, GraphicsImage.EMPTY);
    }
}
