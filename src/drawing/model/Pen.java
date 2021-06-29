package drawing.model;


import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

import java.io.Serializable;
import java.util.Date;

//画笔
public class Pen extends Line implements Serializable, MyShape {
    protected Date createDate;
    protected double[] pos;
    protected double lineWidth;
    protected String lineColor;
    protected String fillColor;
    private boolean dashLine;

    public Pen(boolean dashLine, double startX, double startY, double endX, double endY,
               double lineWidth, Paint lineColor, Paint fillColor) {
        super(startX, startY, endX, endY);
        this.setStrokeWidth(lineWidth);
        this.setStroke(lineColor);
        this.setFill(fillColor);
        if (dashLine) {
            this.getStrokeDashArray().addAll(8.0);
            this.setStrokeDashOffset(1.0);
        }
        this.dashLine = dashLine;
        this.pos = new double[]{startX, startY, endX, endY};
        this.createDate = new Date();
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
        Pen newShape = new Pen(dashLine, this.getPosX(), this.getPosY(), this.pos[2], this.pos[3],
                this.lineWidth, lineColor, fill);
        newShape.createDate = this.createDate;
        return newShape;
    }
}
