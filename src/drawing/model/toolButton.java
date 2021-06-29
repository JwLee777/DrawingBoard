package drawing.model;


import javafx.scene.control.Button;

public class toolButton extends Button {
    String name;

    public toolButton(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
