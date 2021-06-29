package drawing.pane;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
//工具窗口
public class ToolPane implements BasePane {

    private MainStage stage;
    private VBox vBox;
    private VBox colorBarVbox;
    private VBox detailBarVbox;
    private VBox toolBarVbox;



    public void createPane(MainStage mainStage) {
        this.stage = mainStage;
        initVBox();
        initPane();
    }




    public void initPane() {
        ColorBar colorBar = new ColorBar();
        DetailBar detailBar = new DetailBar();
        ToolBar toolBar = new ToolBar();

        colorBar.createBar();
        colorBar.initBar();
        detailBar.createBar();
        detailBar.initBar();
        toolBar.createBar();
        toolBar.initBar();

        toolBar.setDetailBar(detailBar);

        colorBarVbox = colorBar.getBar();
        detailBarVbox = detailBar.getBar();
        toolBarVbox = toolBar.getBar();


        toolBarVbox.setPadding(new Insets(0, 5, 0, 5));
        colorBarVbox.setPadding(new Insets(0, 5, 20, 5));

        vBox.getChildren().add(toolBarVbox);
        vBox.getChildren().add(detailBarVbox);
        vBox.getChildren().add(colorBarVbox);
    }

    public VBox getPane() {
        return vBox;
    }

    private void initVBox() {
        vBox = new VBox();
        vBox.setSpacing(12);
        vBox.setAlignment(Pos.TOP_RIGHT);
        vBox.prefHeightProperty().bind(stage.heightProperty());
    }
}
