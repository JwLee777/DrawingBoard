package drawing.pane;

import drawing.config.ShapeP;
import drawing.config.Size;
import drawing.model.*;
import drawing.model.Circle;
import drawing.model.Line;
import drawing.model.Rectangle;
import drawing.tool.*;
import javafx.scene.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import java.util.*;

//画板
public class BoardPane implements BasePane {
    //画板属性初始化
    private static boolean startPaint = false;
    private static boolean isChosedShape = false;
    private static int clickedTimes = 0;
    // 鼠标点击位置
    private double x;
    private double y;
    private TextArea input = null;
    //各种情况下的图形
    private static Shape shape = null;
    private static Shape chosedShape = null;
    private static ArrayList<Shape> allChosedShapes;
    //保存的图形
    private static ArrayList<Shape> saveShapes;
    //右键的菜单
    ContextMenu contextMenu = RMenuPane.getInstance(this);

    private static MainStage stage;
    private Parent zoomPane;
    private VBox layout;
    private Canvas drawingCanvas;
    private Group group;
    //图形上下文（画笔）
    GraphicsContext gc;
    public static int drawingCanvasWidth;
    public static int drawingCanvasHeight;
    // 绘画类型
    private String shapeType;
    private double lineWidth;
    private Color fillColor = null;
    private Color lineColor;
    //选择后保存的shape
    private boolean singleChoiceUndoShapes = false;
    private boolean dashLine = false;
    private shapetxt shapetxt;
    private Scene scene;

    //创建窗口，初始化参数
    public void createPane(MainStage mainStage) {

        this.stage = mainStage;
        allChosedShapes = new ArrayList<>();
        saveShapes = new ArrayList<>();

        layout = new VBox();
        scene = new Scene(layout);
        initPane();
    }

    // 初始化画板
    public void initPane() {
        drawingCanvas = new Canvas(Size.CANVAS_WIDTH.getValue(), Size.CANVAS_HEIGHT.getValue());
        group = new Group();
        group.getChildren().add(drawingCanvas);
        zoomPane = ZoomPaneinit.createZoomPane(group);
        layout.getChildren().add(zoomPane);
        VBox.setVgrow(zoomPane, Priority.ALWAYS);

        // 画笔绘制画板
        gc = drawingCanvas.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, Size.CANVAS_WIDTH.getValue(), Size.CANVAS_HEIGHT.getValue());
        gc.restore();

        // 初始化画板宽高
        drawingCanvasWidth = (int) drawingCanvas.getWidth();
        drawingCanvasHeight = (int) drawingCanvas.getHeight();

        // 绑定事件监听
        bindCanvasEvents();
    }


    public VBox getPane() {
        return layout;
    }

    //绘制属性
    private void setProperty() {
        dashLine = ShapeP.isIsDashLine();
        shapeType = ShapeP.toolName;
        lineColor = ShapeP.color;
            if (shapeType == "PEN" || shapeType == "LINE") {
                lineWidth = ShapeP.lineSize;
            } else
                lineWidth = Double.parseDouble(ShapeP.borderSize);
            fillColor = null;
    }
    private javafx.scene.shape.Rectangle rectangle = new javafx.scene.shape.Rectangle();

    //选中的图形
    private LinkedList<Shape> selected = new LinkedList<>();
    private Shape nowShape1;


    public void addSelected(Shape ashape) {
        if (!selected.contains(ashape))
            selected.add(ashape);
        if (selected.size() == 1)
            nowShape1 = ashape;
        else
            nowShape1 = null;
    }

    public void deleteSelected(Shape ashape) {
        selected.remove(ashape);
        if (selected.size() == 1)
            nowShape1 = selected.get(0);
        else
            nowShape1 = null;
    }

    //框选图形
    private void choose(double x1, double y1, double x2, double y2) {
        for (int i = 1; i < group.getChildren().size(); i++) {
            if (Geometry.inRange(x1, y1, x2, y2, group.getChildren().get(i))) {
                addSelected((Shape) group.getChildren().get(i));
            } else {
                deleteSelected((Shape) group.getChildren().get(i));
                group.getChildren().get(i).setStyle(null);
                group.getChildren().get(i).setEffect(null);
            }
        }
    }

    public void change() {
        if (selected.size() == 0) {
            group.getChildren().remove(rectangle);
        }
        allChosedShapes = new ArrayList<>();
        if (selected.size() == 1) {
            chosedShape = selected.get(0);
            allChosedShapes.add(chosedShape);
            shape = null;
        } else if (selected.size() > 1) {
            for (Shape s : selected) {
                allChosedShapes.add(s);
            }
        }
    }

    //鼠标监听
    protected void bindCanvasEvents() {

        drawingCanvas.setOnMousePressed(mousePressedEvt -> {

            // 隐藏右键菜单
            contextMenu.hide();
            // 显示右键菜单
            if (mousePressedEvt.isSecondaryButtonDown()) {
                drawingCanvas.setOnContextMenuRequested(event -> contextMenu.show(drawingCanvas,
                        mousePressedEvt.getScreenX(), mousePressedEvt.getScreenY()));
                return;
            }

            setProperty();


            this.startPaint = true;
            this.x = mousePressedEvt.getX();
            this.y = mousePressedEvt.getY();


        });

        // 鼠标离开事件
        drawingCanvas.setOnMouseExited(mouseExitedEvt -> {
            return;
        });

        // 鼠标拖动事件
        drawingCanvas.setOnMouseDragged(mouseDraggedEvt -> {
            if (this.startPaint && !isChosedShape) {
                if (group.getChildren().size() >= 1 && this.shape != null) {
                    group.getChildren().remove(this.shape);
                }

                Shape shapeObj = null;

                double startX = Math.min(this.x, mouseDraggedEvt.getX());
                double startY = Math.min(this.y, mouseDraggedEvt.getY());

                boolean isPen = false;

                if (shapeType.equals("MOUSE") && mouseDraggedEvt.getButton().equals(MouseButton.PRIMARY)) {
                    for(Shape s : allChosedShapes) {
                        s.setEffect(null);
                    }
                    allChosedShapes = new ArrayList<>();
                    shapeObj = new Rectangle(dashLine, 0.0,
                            false, startX, startY,
                            Math.abs(mouseDraggedEvt.getX() - this.x),
                            Math.abs(mouseDraggedEvt.getY() - this.y),
                            lineWidth, lineColor, Color.SKYBLUE);
                    shapeObj.setOpacity(0.3);

                    rectangle = (javafx.scene.shape.Rectangle) shapeObj;
                }

                if (shapeType.equals("PEN")) {
                    // 画笔
                    isPen = true;
                    shapetxt = new shapetxt(new Pen(
                            dashLine,
                            this.x,
                            this.y,
                            mouseDraggedEvt.getX(),
                            mouseDraggedEvt.getY(),
                            lineWidth,
                            lineColor,
                            fillColor));
                    shapeObj = shapetxt.executeShape();
                    x = mouseDraggedEvt.getX();
                    y = mouseDraggedEvt.getY();
                    group.getChildren().add(shapeObj);

                }

                if (shapeType.equals("SQUARE")) {
                        //画长方形
                        shapetxt = new shapetxt(new Rectangle(dashLine, 0.0,
                                false, startX, startY,
                                Math.abs(mouseDraggedEvt.getX() - this.x),
                                Math.abs(mouseDraggedEvt.getY() - this.y),
                                lineWidth, lineColor, fillColor));
                        shapeObj = shapetxt.executeShape();

                }

                if (shapeType.equals("OVAL")) {
                        //画圆形
                        shapeObj = new Circle(
                                dashLine,
                                this.x,
                                this.y,
                                Math.max(Math.abs(mouseDraggedEvt.getX() - this.x), Math.abs(mouseDraggedEvt.getY() - this.y)),
                                lineWidth, lineColor, fillColor);
                }

                if (shapeType.equals("LINE")) {
                    //画直线
                    shapeObj = new Line(
                            dashLine,
                            this.x,
                            this.y,
                            mouseDraggedEvt.getX(), mouseDraggedEvt.getY(),
                            lineWidth, lineColor, fillColor);
                }

                if (shapeObj != null) {
                    if (isPen) {
                        return;
                    }
                    group.getChildren().add(shapeObj);
                    this.shape = shapeObj;
                }
            }

        });

        // 鼠标释放事件
        drawingCanvas.setOnMouseReleased(mouseReleasedEvt -> {

            if (this.startPaint && shapeType.equals("MOUSE") && mouseReleasedEvt.getButton().equals(MouseButton.PRIMARY)) {

                group.getChildren().remove(rectangle);
                selected.remove(rectangle);

                choose(x, y, mouseReleasedEvt.getX(), mouseReleasedEvt.getY());
                change();
                selected = new LinkedList<>();
                return;
            }


            this.startPaint = false;
            if (this.shape == null) {
                return;
            }
            if (this.shape == null) {
                group.getChildren().add(this.shape);
            }

            Node tmp = group.getChildren().get(group.getChildren().size() - 1);
            System.out.println(tmp);
            this.bindShapeEvents(tmp);
            this.shape = null;
        });

    }

    //图形监听
    protected void bindShapeEvents(Node shape) {

        // 图形选择事件
        shape.setOnMousePressed(mousePressedEvt -> {
            if (this.chosedShape != shape) {
                this.clickedTimes = 0;
            }

            BoardPane.clickedTimes++;
            if (BoardPane.clickedTimes == 1) {
                this.x = mousePressedEvt.getX();
                this.y = mousePressedEvt.getY();
                if (!this.allChosedShapes.contains(shape)) {
                    this.allChosedShapes.add((Shape) shape);
                }
                this.chosedShape = (Shape) shape;
                return;
            }

            if (!singleChoiceUndoShapes) {
                singleChoiceUndoShapes = true;
            }

            if (BoardPane.clickedTimes == 2) {
                BoardPane.clickedTimes = 0;
                BoardPane.isChosedShape = true;
                this.chosedShape = (Shape) shape;
                return;
            }

            if (input != null) {
                group.getChildren().remove(input);
            }

        });

    }

    //加载图形
    public void addShapes(ArrayList<Shape> shapes) {
        for (Shape s : shapes) {
            group.getChildren().add(s);
            this.bindShapeEvents(s);
        }
    }


    //删除所选图形
    protected void deleteChosedShapes() {
        if (getAllChosedShapes().size() == 0) {
            return;
        }
        for (Shape s : this.allChosedShapes) {
            group.getChildren().remove(s);
        }
    }


    //清空画板
    public void clear() {
        group.getChildren().clear();
        selected = new LinkedList<>();
        saveShapes = new ArrayList<>();
        allChosedShapes = new ArrayList<>();
        chosedShape = null;
        singleChoiceUndoShapes = false;
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, Size.CANVAS_WIDTH.getValue(), Size.CANVAS_HEIGHT.getValue());
        gc.restore();
        group.getChildren().add(drawingCanvas);
    }


    public static ArrayList<Shape> getAllChosedShapes() {
        return allChosedShapes;
    }

    public Scene getScene() {
        return scene;
    }

    public ArrayList<Shape> getSaveShapes() {
        saveShapes = new ArrayList<>();
        for (int i = 1; i < group.getChildren().size(); i++) {
            saveShapes.add((Shape) group.getChildren().get(i));
        }
        return saveShapes;
    }

    public static Stage getStage() {
        return stage;
    }

}


//右键菜单
class RMenuPane extends ContextMenu {


    private static RMenuPane instance = null;
    private RMenuPane(BoardPane boardPane) {



        MenuItem deleteBtn = new MenuItem("删除");
        deleteBtn.setOnAction(e -> {
            boardPane.deleteChosedShapes();
        });

        MenuItem clearBtn = new MenuItem("清屏");
        clearBtn.setOnAction(e -> {
            boardPane.clear();
        });


        getItems().add(deleteBtn);
        getItems().add(clearBtn);
    }

    public static RMenuPane getInstance(BoardPane boardPane) {
        if (instance == null) {
            instance = new RMenuPane(boardPane);
        }
        return instance;
    }
}
