package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;


import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

import static sample.Main.colorPixelsAfterChange;
import static sample.Main.createSimpleImage;

public class Controller {

    Button button;
    private int count;

    @FXML
    TextField textField;

    @FXML
    AnchorPane output;

    @FXML
    ComboBox<String> comboBox;

    @FXML
    ComboBox<String>  comboBox2;

    @FXML
    TextField grainCount;

    ObservableList<String> neighboorhoods = FXCollections.observableArrayList("VonNeumann", "Moorea",
            "HexagonalRight" ,"HexagonalLeft", "HexagonalRandom", "PentagonalLeft", "PentagonalRight", "PentagonalRandom"
            );

    ObservableList<String> boundaries = FXCollections.observableArrayList("Periodic", "NonPeriodic"
    );

    public void initialize() {
        comboBox.setValue("VonNeumann");
        comboBox.setItems(neighboorhoods);

        comboBox2.setValue("NonPeriodic");
        comboBox2.setItems(boundaries);
    }

    public void createImage() {
        System.out.println(textField.getText());

        try {
            count = Integer.parseInt(textField.getText());
            output.getChildren().add(createSimpleImage(count));

        } catch (NumberFormatException exc) {
            System.out.println("Blad przy"  + textField.getText());
        }

    }

    public void painGrains() {
        output.getChildren().clear();

        try {
            int count = Integer.parseInt(textField.getText());
            int grains = Integer.parseInt(grainCount.getText());
            Main.changeGrainColor(count, grains);

            for (int i=0;i<count; i++) {
                for (int j=0; j<count; j++) {
                    output.getChildren().add(colorPixelsAfterChange(i, j));

                }
            }



        } catch (NumberFormatException exc) {
            System.out.println("Blad przy"  + textField.getText());
        }

    }

    public void useAlgorithm() throws InterruptedException {
        System.out.println("Algorithm");
        System.out.println(comboBox.getValue());

//        output.getChildren().clear();

//        Main.changeGrainColor(count, 5);

        Main.changeGrainColorByAlgorithm(comboBox.getValue(),count,comboBox2.getValue(), output);


       for (int i=0;i< count; i++) {
            for (int j = 0; j < count; j++) {
                output.getChildren().add(Main.colorPixelsAfterChange(i, j));
            }
        }

    }

    public void saveImage() {

        System.out.println("Zapisywanie obrazka");
        WritableImage image = output.snapshot(new SnapshotParameters(), null);

        File file = new File("GeneratedImage.png");

        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);

        } catch (IOException e) {
            System.out.println("Blad" + e.getMessage());

        }
    }

}
