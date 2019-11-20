package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import sample.model.Grain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main extends Application {

    private static Grain grains[][];
    private Stage stage;
    private Controller controller;
    private static Random random = new Random();
    private static int size;
    private static List<Paint> usedColors = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) throws Exception{

        stage = primaryStage;
        primaryStage.setTitle("Multiscale Modeling");

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        primaryStage.setScene(new Scene(root, 900, 800));
        primaryStage.show();
    }

    public static AnchorPane createSimpleImage(int count) {

        size = (int) 1.0 * 700 / count;

        AnchorPane root = new AnchorPane();


        grains = new Grain[count][count];
        for (int i=0; i<count; i++) {
            for (int j=0; j<count; j++) {
                AnchorPane anchorPane = colorPixels(i,j);
                root.getChildren().add(anchorPane);
            }
        }

        return root;

    }

    public static AnchorPane colorPixels(int i, int j) {


        grains[i][j] = new Grain(Color.GRAY, random.nextInt(255));

        AnchorPane anchorPane = grains[i][j].createGrainImage();
        anchorPane.setPrefSize(size,size);
        anchorPane.setMaxSize(size,size);

        AnchorPane.setTopAnchor(anchorPane, j*size*1.0);
        AnchorPane.setLeftAnchor(anchorPane, i*size*1.0);

        return anchorPane;

    }

    public static AnchorPane colorPixelsAfterChange(int i, int j) {




        AnchorPane anchorPane = grains[i][j].createGrainImage();
        anchorPane.setPrefSize(size,size);
        anchorPane.setMaxSize(size,size);

        AnchorPane.setTopAnchor(anchorPane, i*size*1.0);
        AnchorPane.setLeftAnchor(anchorPane, j*size*1.0);

        return anchorPane;
    }

    public static boolean isPlace(int count) {
        for (int i=0; i<count; i++) {
            for (int j=0; j<count; j++) {
                if (grains[i][j].isEmptyColorState())
                    return true;
            }
        }
        return false;
    }


    public static Paint paintGrain() {

        boolean state = false;

        Color color = null;

        while(!state) {
            float a = random.nextFloat();
            float b = random.nextFloat();
            float c = random.nextFloat();

            color = new Color(a,b,c, 1.0);

            if (color == Color.GRAY)
                state = false;
            else if (usedColors.isEmpty())
                state = true;
            else {
                boolean isused = false;
                for (Paint paint: usedColors) {
                    if (color == paint)
                        isused = true;
                }
                if (!isused)
                    state = true;
            }


        }
        usedColors.add(color);
        return color;
    }

    public static List<Paint> checkColor(int count) {
        List<Paint> colors = new ArrayList<>();

        for (int i=0; i<count; i++) {
            for (int j=0; j<count; j++) {

                if (!colors.contains(grains[i][j].getColor()))
                    colors.add(grains[i][j].getColor());
            }
        }
        return colors;
    }


    public static void changeGrainColor(int count,int iterator) {

        int i,j;

        while(iterator>0) {
            i = random.nextInt(count);
            j = random.nextInt(count);
            if (grains[i][j].isEmptyColorState()) {
                grains[i][j].changeGrainColor(paintGrain());
            }

            iterator--;
        }

    }


    public static void changeGrainColorByAlgorithm(String neighbourhood,int count, String boundary, AnchorPane output) throws InterruptedException {

        switch (neighbourhood) {
            case "VonNeumann":
                System.out.println("MetodaVonNeumann");
                Algorithm2.vonNeuman(grains,count,boundary, output);
                break;
            case  "Moorea":
                System.out.println("MetodaMoorea");
                Algorithm2.Moorea(grains,count,boundary);
                break;
            case "HexagonalRight":
                System.out.println("MetodaHexagonalRight");
                Algorithm2.HexagonalRight(grains,count,boundary);
                break;
            case "HexagonalLeft":
                System.out.println("MetodaHexagonalLeft");
                Algorithm2.HexagonalLeft(grains,count,boundary);
                break;
            case "HexagonalRandom":
                break;
            case "PentagonalLeft":
                System.out.println("MetodaPentagonalLeft");
                Algorithm2.PentagonalLeft(grains,count,boundary);
                break;
            case "PentagonalRight":
                System.out.println("MetodaPentagonalRight");
                Algorithm2.PentagonalRight(grains,count,boundary);
                break;
            case "PentagonalRandom":
                    break;

        }

    }

    public static void main(String[] args) {
        launch(args);
    }

}
