package drawing.model;

import javafx.scene.shape.Shape;

public class shapetxt {

    private Shape myShape;

    public shapetxt(Shape myShape){
        this.myShape = myShape;
    }

    public Shape executeShape() {
        return myShape;
    }

}
