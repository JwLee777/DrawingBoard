package drawing.pane;


import drawing.config.ShapeP;
import drawing.config.Size;
import drawing.model.toolButton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;

//颜色条
public class ColorBar implements Builders {

    private VBox vBox;
    private TilePane tilePane;
    private Label colorLabel;
    private GridPane gridPane;


    private Color presentColor = Color.BLACK;

    //颜色表
    private List<toolButton> colorButton = new ArrayList<>();
    private final String[] colors = new String[]{
            "#000000", "#ec1c24", "#0ed145", "#3f48cc",  "#ffaec8","#fff200"
    };


    public void createBar() {
        vBox = new VBox();
        colorLabel = new Label();
    }


    public void initBar() {
        intiShowColor();
        initColorBar();
    }


    public VBox getBar() {
        return vBox;
    }

    private void intiShowColor() {
        gridPane = new GridPane();
        gridPane.setPadding(new Insets(5, 5, 10, 5));
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        colorLabel.setPrefSize(295, 40);
        gridPane.add(colorLabel, 0, 0, 2, 1);
        vBox.getChildren().add(gridPane);
    }


    private void initColorBar() {
        tilePane = new TilePane();
        tilePane.setAlignment(Pos.CENTER);
        tilePane.setPadding(new Insets(5, 5, 10, 2));
        tilePane.setPrefColumns(6);
        tilePane.setHgap(5);
        tilePane.setVgap(5);

        for (int i = 0; i < colors.length; i++) {
            toolButton cButton = new toolButton(colors[i]);
            cButton.setStyle("-fx-base: " + colors[i] + ";");
            cButton.setPrefSize(Size.COLOR_BUTTON_WIDTH.getValue(), Size.COLOR_BUTTON_HEIGHT.getValue());
            cButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
                presentColor = Color.web(((toolButton) e.getSource()).getName());
                ShapeP.setColor(presentColor);
            });
            colorButton.add(cButton);
        }
        tilePane.getChildren().addAll(colorButton);
        vBox.getChildren().add(tilePane);

    }


}
