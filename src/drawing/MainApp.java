package drawing;

import drawing.pane.MainStage;
import javafx.application.Application;
import javafx.stage.Stage;


public class MainApp extends Application {

    public void start(Stage primaryStage) throws Exception {
        MainStage.getInstance();
    }



    public static void main(String[] args) {
        launch(args);
    }
}

