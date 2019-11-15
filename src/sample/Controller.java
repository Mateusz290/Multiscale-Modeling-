package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;


import static sample.Main.colorPixelsAfterChange;
import static sample.Main.createSimpleImage;

public class Controller {

    Button button;

    @FXML
    TextField textField;

    @FXML
    AnchorPane output;

    public void createImage() {
        System.out.println(textField.getText());

        try {
            int count = Integer.parseInt(textField.getText());
            output.getChildren().add(createSimpleImage(count));

        } catch (NumberFormatException exc) {
            System.out.println("Blad przy"  + textField.getText());
        }

    }

    public void painGrains() {
        System.out.println("maluje");
        Main.changeGrainColor(50, 5);
        output.getChildren().clear();

        try {
            int count = Integer.parseInt(textField.getText());

            for (int i=0;i<count; i++) {
                for (int j=0; j<count; j++) {
                    output.getChildren().add(colorPixelsAfterChange(i, j));
                }
            }



        } catch (NumberFormatException exc) {
            System.out.println("Blad przy"  + textField.getText());
        }

    }

}
