package drawing.tool;


import drawing.config.Size;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

//界面大小初始化
public class Screeninit {

    public static void setScreen() {
        Rectangle2D screenRectangle = Screen.getPrimary().getBounds();
        double width = screenRectangle.getWidth();
        double height = screenRectangle.getHeight();

        //窗口相对于屏幕的比例
        double widthper = 0.7;
        double heightper = 0.8;

        //画布相对于窗口的比例
        double canvansWidPer = 0.7;
        double canvansHeiPer = 0.9;

        Size.STAGE_WIDTH.setValue((int) (width * widthper));
        Size.STAGE_HEIGHT.setValue((int) (height * heightper));
        Size.CANVAS_HEIGHT.setValue((int) (Size.STAGE_HEIGHT.getValue() * canvansHeiPer));
        Size.CANVAS_WIDTH.setValue((int) (Size.STAGE_WIDTH.getValue() * canvansWidPer));

    }


}
