package drawing.config;

import javafx.scene.paint.Color;

//图形类，纪录当前图形需要的属性
public class ShapeP {
    public static String toolName = "PEN";
    public static String borderSize = "3";      //图形类边界宽度
    public static int lineSize = 3;
    public static Color color = Color.BLACK;
    public static boolean isDashLine = false;

    public static boolean isIsDashLine() {
        return isDashLine;
    }

    public static void setIsDashLine(boolean isDashLine) {
        ShapeP.isDashLine = isDashLine;
    }

    public static void setToolName(String toolName) {
        ShapeP.toolName = toolName;
    }

    public static void setBorderSize(String borderSize) {
        ShapeP.borderSize = borderSize;
    }

    public static void setLineSize(int lineSize) {
        ShapeP.lineSize = lineSize;
    }

    public static void setColor(Color color) {
        ShapeP.color = color;
    }

}