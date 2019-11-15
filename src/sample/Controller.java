package sample;

import javafx.scene.layout.AnchorPane;

import java.awt.*;

public class Controller {

    Button button;

    AnchorPane output;

    public void createImage() {

        output.getChildren().add(Main.createSimpleImage());

    }
}
