package drawing.pane;

import javafx.scene.layout.VBox;

//基础接口
public interface BasePane {
    void createPane(MainStage mainStage);
    void initPane();
    VBox getPane();
}
