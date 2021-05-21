package graphics;

import java.util.HashMap;

class ImagesBase {
    private final HashMap<Integer, Image> imageByNumber = new HashMap<>();

    ImagesBase() {
        for (Image image : Image.values()) {
            if (image.getImageNumber() != null) {
                imageByNumber.put(image.getImageNumber(), image);
            }
        }
    }

    public Image getImageByNumber(int n) {
        return imageByNumber.getOrDefault(n, Image.EMPTY);
    }
}
