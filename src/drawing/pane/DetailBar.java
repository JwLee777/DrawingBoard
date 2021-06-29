package drawing.pane;

import drawing.config.ShapeP;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

//线性条
public class DetailBar implements Builders {
    private VBox vBox;
    //宽度设置
    private Label tipLabel;
    //滑动条
    private Slider sliderPen;
    private CheckBox dashLine;
    private GridPane gridPane;


    public void createBar() {
        vBox = new VBox();
        sliderPen = new Slider();
        tipLabel = new Label("  粗细");
    }


    public void initBar() {
        vBox.setAlignment(Pos.CENTER);

        tipLabel.setTextFill(Color.web("#000000"));
        tipLabel.setFont(new Font("Microsoft YaHei", 16));
        initPenPane(-1);
        vBox.getChildren().add(gridPane);
    }

    public VBox getBar() {
        return vBox;
    }

    private void initPenPane(int type) {

        vBox.setPadding(new Insets(0, 5, 40, 10));

        gridPane = new GridPane();
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        dashLine = new CheckBox("虚线");
        dashLine.setSelected(false);

        sliderPen.setMin(1);
        sliderPen.setMax(100);
        sliderPen.setValue(3);
        sliderPen.setShowTickLabels(true);
        sliderPen.setPrefWidth(300);

        sliderPen.setDisable(false);
        dashLine.setDisable(true);
        if (type == 0 ) {
            dashLine.setVisible(true);
            dashLine.setDisable(false);
            sliderPen.setMax(40);
            tipLabel.setPrefWidth(160);
            dashLine.setPrefWidth(60);
            gridPane.add(tipLabel, 0, 0);
            gridPane.add(dashLine, 1, 0);
            gridPane.add(sliderPen, 0, 1, 3, 1);
        }
        else if (type == -1) {
            sliderPen.setMax(40);
            tipLabel.setPrefWidth(160);
            dashLine.setPrefWidth(60);
            gridPane.add(tipLabel, 0, 0);
            gridPane.add(sliderPen, 0, 1, 3, 1);

        }
        else if (type == 1) {
            sliderPen.setMax(40);
            tipLabel.setPrefWidth(90);
            dashLine.setPrefWidth(60);
            gridPane.add(tipLabel, 0, 0);
            gridPane.add(sliderPen, 0, 1, 4, 1);

        } else if (type == 2) {
            tipLabel.setPrefWidth(225);
            sliderPen.setDisable(true);
            gridPane.add(tipLabel, 0, 0);
            gridPane.add(sliderPen, 0, 1, 2, 1);

        }

        handlePenSlider(type);
    }


    private void handlePenSlider(int type) {
        sliderPen.valueProperty().addListener((
                ObservableValue<? extends Number> ov, Number old_val,
                Number new_val) -> {
            if (type == 1 || type == 3 || type == 4) {
                ShapeP.setBorderSize(new_val.intValue() + "");
            } else if (type == 0 || type == -1) {
                ShapeP.setLineSize(new_val.intValue());
            }
        });

        // 设置复选框的勾选监听器

        dashLine.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                sliderPen.setMax(5);
                ShapeP.setIsDashLine(true);
            } else if (oldValue) {
                ShapeP.setIsDashLine(false);
            }
        });

    }



    public void setSize(int type) {
        vBox.getChildren().clear();
        ShapeP.setIsDashLine(false);
        ShapeP.setBorderSize("3");
        ShapeP.setColor(Color.BLACK);
        initPenPane(type);
        vBox.getChildren().add(gridPane);
    }

}
