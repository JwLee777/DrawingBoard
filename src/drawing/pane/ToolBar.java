package drawing.pane;

import drawing.config.PngPath;
import drawing.config.ShapeP;
import drawing.model.toolButton;
import drawing.tool.GetImage;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

//工具条
public class ToolBar implements Builders {

    public void setDetailBar(DetailBar detailBar) {
        this.detailBar = detailBar;
    }


    private VBox vBox;
    private DetailBar detailBar;
    private TilePane tilePane;
    private List<drawing.model.toolButton> toolButton = new ArrayList<>();


    public void createBar() {
        vBox = new VBox();
        tilePane = new TilePane();
    }


    public void initBar() {
        tilePane.setAlignment(Pos.CENTER);
        tilePane.setStyle("-fx-background-color: #f0f2f3");
        tilePane.setPrefColumns(5);
        tilePane.setHgap(8);
        tilePane.setVgap(12);

        //设置各工具按钮
        Image ImgBarrel = new Image(PngPath.MOUSE.toString());
        drawing.model.toolButton barrelButton = new toolButton("MOUSE");
        barrelButton.setGraphic(GetImage.getImageView(ImgBarrel));
        toolButton.add(barrelButton);

        Image ImgPen = new Image(PngPath.PEN.toString(), false);
        drawing.model.toolButton penButton = new toolButton("PEN");
        penButton.setGraphic(GetImage.getImageView(ImgPen));
        toolButton.add(penButton);

        Image ImgLine = new Image(PngPath.LINE.toString());
        drawing.model.toolButton lineButton = new toolButton("LINE");
        lineButton.setGraphic(GetImage.getImageView(ImgLine));
        toolButton.add(lineButton);

        Image ImgSquare = new Image(PngPath.RECTANGLEZ.toString());
        drawing.model.toolButton squareButton = new toolButton("SQUARE");
        squareButton.setGraphic(GetImage.getImageView(ImgSquare));
        toolButton.add(squareButton);


        Image ImgOval = new Image(PngPath.OVAL.toString());
        drawing.model.toolButton ovalButton = new toolButton("OVAL");
        ovalButton.setGraphic(GetImage.getImageView(ImgOval));
        toolButton.add(ovalButton);


        for (Button curBt : toolButton) {
            curBt.setStyle("-fx-base: #f0f2f3;-fx-background-insets: 0;");
            curBt.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
                        String name = ((drawing.model.toolButton) e.getSource()).getName();
                        ShapeP.setToolName(name);
                        if (name == "LINE") {
                            detailBar.setSize(0);
                        } else if (name == "PEN") {
                            detailBar.setSize(-1);
                        } else if (name == "MOUSE") {
                            detailBar.setSize(2);
                        } else if (name == "OVAL") {
                            detailBar.setSize(1);
                        } else if (name == "SQUARE") {
                            detailBar.setSize(1);
                        } else {
                            detailBar.setSize(1);
                        }
            });
        }
        tilePane.getChildren().addAll(toolButton);
        vBox.getChildren().add(tilePane);
    }

    public VBox getBar() {
        return vBox;
    }
}
