package drawing.pane;


import drawing.tool.FileSR;
import javafx.event.ActionEvent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.VBox;

//菜单窗口
public class MenuPane implements BasePane {

    private VBox vBox;
    private MainStage mainStage;
    private MenuBar menubar;
    private Menu fileMenu;
    private Menu editMenu;
    private MenuItem menuOpen,  menuSaveAs, menuSave;
    private MenuItem  menuDelete, menuClear;


    public void createPane(MainStage mainStage) {
        this.mainStage = mainStage;
        vBox = new VBox();
        initPane();
    }


    public void initPane() {
        menubar = new MenuBar();
        initFileMenu();
        initEdieMenu();
        //添加菜单项
        menubar.getMenus().add(fileMenu);
        menubar.getMenus().add(editMenu);
        vBox.getChildren().add(menubar);
    }


    public VBox getPane() {
        return vBox;
    }




    private void initFileMenu() {

        fileMenu = new Menu();
        fileMenu.setText("菜单");
        fileMenu.setStyle("-fx-font-size:16;");
        {
            menuSaveAs = new MenuItem();
            menuSaveAs.setText("另存为");
            menuSaveAs.setStyle("-fx-font-size:16;");
            menuSaveAs.setOnAction((ActionEvent t) -> {
                FileSR.saveShape(mainStage, mainStage.getBoard().getSaveShapes());
            });
        }

        {
            menuSave = new MenuItem();
            menuSave.setText("保存");
            menuSave.setStyle("-fx-font-size:16;");
            //设置快捷键
            menuSave.setAccelerator((new KeyCodeCombination(KeyCode.S, KeyCombination.SHORTCUT_DOWN)));
            menuSave.setOnAction((ActionEvent t) -> {
                FileSR.writeObjectToFile(mainStage.getBoard().getSaveShapes(), "D:/init.shape");
            });
        }

        {
            menuOpen = new MenuItem();
            menuOpen.setText("打开");
            menuOpen.setStyle("-fx-font-size:16;");
            menuOpen.setOnAction((ActionEvent t) -> {
                mainStage.getBoard().clear();
                FileSR.openShape(mainStage);
            });
        }

        fileMenu.getItems().add(menuOpen);
        fileMenu.getItems().add(menuSaveAs);
        fileMenu.getItems().add(menuSave);
    }

    private void initEdieMenu() {
        editMenu = new Menu();
        editMenu.setText("编辑");
        editMenu.setStyle("-fx-font-size:16;");
        {
            menuDelete = new MenuItem("删除");
            menuDelete.setStyle("-fx-font-size:16;");
            menuDelete.setAccelerator(new KeyCodeCombination(KeyCode.DELETE));
            menuDelete.setOnAction((ActionEvent t) -> {
                mainStage.getBoard().deleteChosedShapes();
            });
        }
        {
            menuClear = new MenuItem("清空");
            menuClear.setStyle("-fx-font-size:16;");
            menuClear.setOnAction((ActionEvent t) -> {
                mainStage.getBoard().clear();
            });
        }

        editMenu.getItems().add(menuDelete);
        editMenu.getItems().add(menuClear);
    }

}
