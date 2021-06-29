package drawing.config;

public enum PngPath {
    //各图标路径
    LOGO("/resource/icon.jpg"),
    PEN("/resource/pen.png"),
    LINE("/resource/line.png"),
    RECTANGLEZ("/resource/square.png"),
    OVAL("/resource/oval.png"),
    MOUSE("/resource/mouse.png");


    String path;

    PngPath(String path) {
        this.path = path;
    }

    public String toString() {
        return path;
    }
}
