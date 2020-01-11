package sample;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;


public class Grain {

    private int x;

    public int getPhase() {
        return phase;
    }

    public void setPhase(int phase) {
        this.phase = phase;
    }

    private int phase;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    private int y;

    public Grain(int x, int y,  int phase, Paint color) {
        this.x = x;
        this.phase = phase;
        this.y = y;
        this.color = color;
    }

    public Paint getGrainColor() {
        return color;
    }

    private Paint color;

    public AnchorPane createBasicImage(){
//        System.out.println("Kolorowanie");
        AnchorPane a=new AnchorPane();
        Circle circle;
        a.setBackground(new Background(new BackgroundFill(this.color, CornerRadii.EMPTY, Insets.EMPTY)));

        if(this.phase != 0) {

            circle = new Circle(0.1, 0.1, this.phase);
            circle.setFill(Color.BLACK);
            a.getChildren().add(circle);
        }

        return a;
    }



    public boolean isGrayColor(){
//        System.out.println("Czy " + this.color + " = " + Color.GRAY);
        if(this.color.equals(Color.GRAY)) {
            return true;
        }


        else {
            return false;
        }
    }
    public void changeGrainColor(Paint color){
        this.color=color;
    }
}
