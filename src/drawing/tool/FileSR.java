package drawing.tool;


import drawing.model.MyShape;
import drawing.pane.MainStage;
import javafx.scene.shape.Shape;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

//文件保存及读取
public final class FileSR {
    //打开文件中图形
    public static void openShape(MainStage mainStage) {
        FileChooser filePicker = new FileChooser();
        filePicker.setTitle("读取图形");
        filePicker.getExtensionFilters().add(new FileChooser.ExtensionFilter("Shape", "*.s", "*.shape", "*.SHAPE", "*.Shape"));
        File f = filePicker.showOpenDialog(mainStage);
        ArrayList<Shape> shapes = FileSR.readFromFile(f.getPath());
        mainStage.getBoard().addShapes(shapes);
    }
    //保存图形
    public static void saveShape(Stage stage, ArrayList<Shape> objList) {
        if (objList.size() == 0) {
            return;
        }
        FileChooser filePicker = new FileChooser();
        filePicker.setTitle("保存图形");
        filePicker.getExtensionFilters().add(new FileChooser.ExtensionFilter("*.shape", "*.shape", "*.SHAPE", "*.Shape"));
        File savePath = filePicker.showSaveDialog(stage);
        if(savePath == null) {
            return;
        }
        FileSR.writeObjectToFile(objList, savePath.getPath());
    }

    //读出的图形
    public static ArrayList<Shape> readFromFile(String filePath) {
        ArrayList<Shape> objList = new ArrayList<>();
        try (FileInputStream inputStream = new FileInputStream(filePath);
             ObjectInputStream objReader = new ObjectInputStream(inputStream)) {
            MyShape obj;
            while (true) {
                try {
                    obj = (MyShape) objReader.readObject();
                    try {
                        objList.add(obj.reply());
                    } catch (ClassCastException e) {
                        System.out.println(e.getMessage());
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            System.out.println("readShape done...");
        }

        return objList;
    }

    //将图形写入文件
    public static void writeObjectToFile(ArrayList<Shape> objList, String filePath) {
        try (FileOutputStream outfile = new FileOutputStream(filePath);
             ObjectOutputStream objWriter = new ObjectOutputStream(outfile)) {
            for (int i = 0; i < objList.size(); i++) {
                System.out.println("writing to file:" + objList.get(i));
                objWriter.writeObject(objList.get(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("write shape done...");
    }

}
