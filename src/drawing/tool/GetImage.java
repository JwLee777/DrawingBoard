package drawing.tool;


import drawing.config.Size;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public final class GetImage {
    public static ImageView getImageView(Image image) {
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(Size.TOOLBAR_BUTTON_WIDTH.getValue());
        imageView.setFitWidth(Size.TOOLBAR_BUTTON_HEIGHT.getValue());
        return imageView;
    }
}
