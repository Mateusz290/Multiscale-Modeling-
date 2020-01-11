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
    private int grainId;

    public int getGrainId() {
        return grainId;
    }

    public void setGrainId(int grainId) {
        this.grainId = grainId;
    }

    public Grain(Paint color, int grainId) {
        this.color = color;
        this.grainId = grainId;
    }

    public Grain() {
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

    public boolean isEmptyColorState() {
        if (this.getColor() == Color.GRAY)
            return true;
        else return false;
    }

    public void changeGrainColor(Paint color) {
        this.color = color;
    }

}
