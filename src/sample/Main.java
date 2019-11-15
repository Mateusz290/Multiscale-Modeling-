package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.model.Grain;

public class Main extends Application {

    private Grain grains[][];

    @Override
    public void start(Stage primaryStage) throws Exception{


        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");

        AnchorPane root= new AnchorPane();

        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    public void pullTable() {
        grains = new Grain[500][500];
        for (int i=0; i<500; i++) {
            for (int j=0; j<500; j++) {
//                grains[i][j]
            }
        }

    }


    public static void main(String[] args) {
        launch(args);
    }

}
