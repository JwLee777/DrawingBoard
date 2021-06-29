package drawing.model;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;

import java.io.Serializable;
import java.util.Date;

//画圆
public class Circle extends javafx.scene.shape.Circle implements Serializable, MyShape {
    private double[] pos;
    private Date createDate;
    protected double radius, lineWidth;
    protected String lineColor;
    protected String fillColor;
    private boolean dashLine;

    public Circle(boolean dashLine, double x, double y, double radius, double lineWidth, Paint lineColor, Paint fillColor) {
        super(x, y, radius);

        this.setStrokeWidth(lineWidth);
        this.setStroke(lineColor);
        this.setFill(fillColor);
        if (dashLine) {
            this.getStrokeDashArray().addAll(8.0);
            this.setStrokeDashOffset(1.0);
        }
        this.dashLine = dashLine;
        this.pos = new double[]{x, y};
        this.createDate = new Date();
        this.radius = radius;
        this.lineColor = ((Color) lineColor).toString();
        this.fillColor = fillColor == null ? "" : ((Color) fillColor).toString();
        this.lineWidth = lineWidth;
    }


    public double getPosX() {
        return this.pos[0];
    }

    public double getPosY() {
        return this.pos[1];
    }


    public Shape reply() {
        Paint fill = this.fillColor.equals("") ? null : Color.valueOf(this.fillColor);
        Circle newShape = new Circle(dashLine, this.getPosX(), this.getPosY(), this.radius, this.lineWidth,
                Color.valueOf(this.lineColor), fill);
        newShape.createDate = this.createDate;
        return newShape;
    }
}
