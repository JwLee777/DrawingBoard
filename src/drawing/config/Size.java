package drawing.config;
//各类大小尺寸
public enum Size {
    TOOLBAR_BUTTON_WIDTH(35),
    TOOLBAR_BUTTON_HEIGHT(35),  //工具按钮大小
    COLOR_BUTTON_WIDTH(45),
    COLOR_BUTTON_HEIGHT(45),    //颜色按钮大小
    CANVAS_WIDTH(0),
    CANVAS_HEIGHT(0),         //画布大小
    STAGE_WIDTH(0),
    STAGE_HEIGHT(0);          //窗口大小
    int value;

    Size(int value) {
        this.value = value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
