package sample.model;

import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Grain {

    private Paint color;

    public Grain() {
    }

    public Grain(Paint color) {

        this.color = color;
    }


    public Paint getColor() {
        return color;
    }

    public void setColor(Paint color) {
        this.color = color;
    }

    public AnchorPane createGrainImage() {
        AnchorPane anchorPane =new AnchorPane();
        anchorPane.setBackground(new Background(new BackgroundFill(this.color, CornerRadii.EMPTY, Insets.EMPTY)));

        return anchorPane;
    }

    public boolean isGrainColor() {
        if (this.getColor() == Color.GRAY)
            return true;
        else return false;
    }

    public void changeGrainColor(Paint color) {
        this.color = color;
    }

}
