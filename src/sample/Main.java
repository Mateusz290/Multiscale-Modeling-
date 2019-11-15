package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.model.Grain;

import java.util.Arrays;
import java.util.Random;

public class Main extends Application {

    private Grain grains[][];
    private Stage stage;
    private Controller controller;

    @Override
    public void start(Stage primaryStage) throws Exception{

        stage = primaryStage;
        pullTable();
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");

        AnchorPane root=  generateBasicImage();

        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    public void pullTable() {
        grains = new Grain[100][100];
        for (int i=0; i<100; i++) {
            for (int j=0; j<100; j++) {
                grains[i][j] = new Grain(i,j, Color.GRAY);

            }
        }

//        Arrays.asList(grains).forEach(e -> System.out.println(e));

    }

    public AnchorPane generateBasicImage() {

        AnchorPane image = new AnchorPane();
        for (int i=0; i<100; i++) {
            for (int j=0; j<100; j++) {
                int x = new Random().nextInt(255);
                int y = new Random().nextInt(255);
                int z = new Random().nextInt(255);
                grains[i][j].setColor(Color.rgb(x,y,z));
                AnchorPane anchorPane = grains[i][j].createGrainImage();
                anchorPane.setPrefSize(500,500);
                anchorPane.setMaxSize(500,500);

                AnchorPane.setTopAnchor(anchorPane, j*500*1.0);
                AnchorPane.setLeftAnchor(anchorPane, i*500*1.0);

                image.getChildren().add(anchorPane);

            }
        }

        return image;
    }


    public static void main(String[] args) {
        launch(args);
    }

}
