package drawing.pane;

//创建类
public class creat {
    public static BasePane createPane(String type) {
        BasePane pane = null;
        if (type.equals("MenuBar")) {
            pane = new MenuPane();
        } else if (type.equals("ToolBar")) {
            pane = new ToolPane();
        }  else if (type.equals("BoardPane")) {
            pane = new BoardPane();
        }
        return pane;
    }
}
