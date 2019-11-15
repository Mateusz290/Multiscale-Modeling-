package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.model.Grain;

import java.util.Random;

public class Main extends Application {

    private static Grain grains[][];
    private Stage stage;
    private Controller controller;
    private static int size = (int) 1.0 * 700 / 500;

    @Override
    public void start(Stage primaryStage) throws Exception{

        stage = primaryStage;
        primaryStage.setTitle("Hello World");

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

//        AnchorPane root = createSimpleImage();

        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    public static AnchorPane createSimpleImage() {

        Random r = new Random(255);
        AnchorPane root = new AnchorPane();


        grains = new Grain[500][500];
        for (int i=0; i<500; i++) {
            for (int j=0; j<500; j++) {
                grains[i][j] = new Grain(r.nextInt(255), r.nextInt(255), Color.rgb(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
                AnchorPane anchorPane = colorPixels(i,j);
                root.getChildren().add(anchorPane);

            }
        }

        return root;

    }

    public static AnchorPane colorPixels(int i, int j) {

        AnchorPane anchorPane = grains[i][j].createGrainImage();
        anchorPane.setPrefSize(size,size);
        anchorPane.setMaxSize(size,size);

        AnchorPane.setTopAnchor(anchorPane, j*size*1.0);
        AnchorPane.setLeftAnchor(anchorPane, i*size*1.0);

        return anchorPane;

    }

    public static void main(String[] args) {
        launch(args);
    }

}
