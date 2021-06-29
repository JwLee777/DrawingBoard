package drawing.pane;

import drawing.config.PngPath;
import drawing.config.Size;
import drawing.tool.FileSR;
import drawing.tool.Screeninit;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

import java.util.ArrayList;


public class MainStage extends Stage {


    private BorderPane rootLayout;
    //绘制窗口
    private BoardPane boardPane;
    //工具窗口
    private ToolPane toolPane;
    //菜单窗口
    private MenuPane menuPane;
    private Scene scene;

    //静态实例
    private static MainStage instance = null;

    //各窗口实现
    private MainStage() {

        rootLayout = new BorderPane();
        scene = new Scene(rootLayout);
        Screeninit.setScreen();     //初始化大小参数
        rootLayout.setPrefSize(Size.STAGE_WIDTH.getValue(), Size.STAGE_HEIGHT.getValue());

        //工厂方式创建
        creat creat = new creat();
        menuPane = (MenuPane) creat.createPane("MenuBar");
        toolPane = (ToolPane) creat.createPane("ToolBar");
        boardPane = (BoardPane) creat.createPane("BoardPane");

        menuPane.createPane(this);
        toolPane.createPane(this);
        boardPane.createPane(this);

        menuPane.getPane().prefWidthProperty().bind(this.widthProperty());
        rootLayout.setTop(menuPane.getPane());
        rootLayout.setCenter(boardPane.getPane());
        rootLayout.setLeft(toolPane.getPane());

        ArrayList<Shape> shapes = FileSR.readFromFile("D:/init.shape");
        this.getBoard().addShapes(shapes);

        this.setOnCloseRequest(event -> {
                FileSR.writeObjectToFile(this.getBoard().getSaveShapes(), "D:/init.shape");
        });


        setTitle("画板");
        getIcons().add(new Image(PngPath.LOGO.toString()));
        setScene(scene);
        setResizable(true);
        show();
    }

    public BoardPane getBoard() {
        return boardPane;
    }


    public static MainStage getInstance() {
        if (instance == null)
            instance = new MainStage();
        return instance;
    }

}

