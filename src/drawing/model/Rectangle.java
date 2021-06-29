package drawing.model;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;

import java.io.Serializable;
import java.util.Date;


//矩形
public class Rectangle extends javafx.scene.shape.Rectangle implements Serializable, MyShape {
    protected Date createDate;
    protected double[] pos;
    protected double width, height, lineWidth;
    protected String lineColor;
    protected String fillColor;
    private boolean isArc;
    private double rotate;
    private boolean dashLine;

    public Rectangle(boolean dashLine, Double rotate, boolean isArc, double x, double y, double width, double height, double lineWidth, Paint lineColor, Paint fillColor) {
        super(x, y, width, height);
        this.setFill(fillColor);
        this.setStrokeWidth(lineWidth);
        this.setStroke(lineColor);
        this.isArc = isArc;
        if (isArc) {
            this.setArcWidth(20);
            this.setArcHeight(20);
        }
        if (dashLine) {
            this.getStrokeDashArray().addAll(8.0);
            this.setStrokeDashOffset(1.0);
        }
        this.dashLine = dashLine;
        this.setRotate(rotate);
        this.rotate = rotate;
        this.pos = new double[]{x, y};
        this.createDate = new Date();
        this.width = width;
        this.height = height;
        this.lineWidth = lineWidth;
        this.lineColor = (lineColor == null ? "" : ((Color) lineColor).toString());
        this.fillColor = (fillColor == null ? "" : ((Color) fillColor).toString());
    }


    public double getPosX() {
        return this.pos[0];
    }


    public double getPosY() {
        return this.pos[1];
    }


    public Shape reply() {
        Paint fill = this.fillColor.equals("") ? null : Color.valueOf(this.fillColor);
        Paint lineColor = this.lineColor.equals("") ? null : Color.valueOf(this.lineColor);
        Rectangle newShape = new Rectangle(dashLine, rotate, isArc, this.getPosX(), this.getPosY(), this.width, this.height,
                this.lineWidth, lineColor, fill);
        newShape.createDate = this.createDate;
        return newShape;
    }

}
