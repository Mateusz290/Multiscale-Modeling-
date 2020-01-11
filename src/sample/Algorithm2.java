package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import sample.model.Grain;

import java.io.IOException;
import java.util.Random;

public class Algorithm2 {





    public static void vonNeuman(Grain grains[][], int count,String boundary, AnchorPane output) throws InterruptedException {

        Paint color = null;
        int grainId;

        for (int i=1;i<count - 1; i++){
            for (int j=1;j<count - 1; j++) {
                color = Color.GRAY;
                grainId = -1;
                if (!grains[i][j].isEmptyColorState()) {
                    System.out.println("Opuszczam");
                    continue;

                }
                else {
                    if (!grains[i-1][j].isEmptyColorState() && grains[i-1][j].getGrainId() > grainId) {
                        color = grains[i-1][j].getColor();
                        grainId = grains[i-1][j].getGrainId();
                    }
                    if (!grains[i][j - 1].isEmptyColorState() && grains[i][j - 1].getGrainId() > grainId) {
                        color = grains[i][j-1].getColor();
                        grainId = grains[i][j-1].getGrainId();
                    }
                    if (!grains[i][j+1].isEmptyColorState()  && grains[i][j - 1].getGrainId() > grainId) {
                        color = grains[i][j+1].getColor();
                        grainId = grains[i][j+1].getGrainId();
                    }
                    if (!grains[i+1][j].isEmptyColorState()  && grains[i][j - 1].getGrainId() > grainId) {
                        color = grains[i+1][j].getColor();
                        grainId = grains[i+1][j].getGrainId();
                    }

                    if (color != Color.GRAY) {
                        System.out.println("Kolor bedzie zmieniony");

                        grains[i][j].changeGrainColor(color);
                        System.out.println("Zmieniam kolor środkowego kwadratu na " + color);
                        if (grains[i][j - 1].isEmptyColorState() || grains[i][j - 1].getGrainId() <grainId) {
                            System.out.println("Zmieniam kolor górnego kwadratu na " + color);
                            grains[i][j - 1].changeGrainColor(color);
                        }
                        if (grains[i - 1][j].isEmptyColorState() || grains[i-1][j].getGrainId() <grainId) {
                            System.out.println("Zmieniam kolor lewego kwadratu na " + color);
                            grains[i - 1][j].changeGrainColor(color);
                        }
                        if (grains[i + 1][j].isEmptyColorState()  || grains[i+1][j].getGrainId() <grainId) {
                            System.out.println("Zmieniam kolor prawego kwadratu na " + color);
                            grains[i + 1][j].changeGrainColor(color);
                        }
                        if (grains[i][j + 1].isEmptyColorState()  || grains[i][j+1].getGrainId() <grainId) {
                            System.out.println("Zmieniam kolor dolnego kwadratu na " + color);
                            grains[i][j + 1].changeGrainColor(color);
                        }
                    }
                    else {
                        System.out.println("Brak zmiany koloru");

                    }

//                    output.getChildren().clear();
//                    output.getChildren().add(Main.colorPixelsAfterChange(i, j));

                }
//                output.getChildren().add(Main.colorPixelsAfterChange(i, j));
            }
        }

    }


    public void generateInclusions(int inclusionsNumber, int minRadius, int maxRadius) {   // radius = 1-7       min - max stałe = 0.1

        Circle circle;
        Random random = new Random();
        AnchorPane a,b;

        int radiusSize;

        int i = random.nextInt(x*x);
        int widthCircle = random.nextInt(x);
        int heightCircle =  random.nextInt(x);

        a = (AnchorPane) controller.output.getChildren().get(0);

        while(inclusionsNumber>0) {
            i = random.nextInt(x*x);
            radiusSize = minRadius + random.nextInt(maxRadius - minRadius + 1);
            b = (AnchorPane) a.getChildren().get(i);
            circle = new Circle(0.1, 0.1, radiusSize);
            circle.setFill(Color.BLACK);
            b.getChildren().add(circle);
            inclusionsNumber--;
        }
    }

    public void algorithmCurvatureStill(int propabilities) {



        timeline = new Timeline(
                new KeyFrame(Duration.millis(1000),
                        (evt) -> {
                            algorithmCurvature(propabilities);
                            controller.output.getChildren().clear();
                            controller.output.getChildren().add(this.generate());
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        })
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();


    }


    public static void Moorea(Grain grains[][],int count,String boundary) {

        Paint color = null;

        for (int i=1;i<count - 1; i++){
            for (int j=1;j<count - 1; j++) {
                if (!grains[i][j].isEmptyColorState()) {

                    continue;

                }
                else {
                    if (!grains[i-1][j+1].isEmptyColorState())
                        color = grains[i-1][j + 1].getColor();
                    else if (!grains[i+1][j + 1].isEmptyColorState())
                        color = grains[i+1][j + 1].getColor();
                    else if (!grains[i-1][j - 1].isEmptyColorState())
                        color = grains[i-1][j - 1].getColor();
                    else if (!grains[i+1][j - 1].isEmptyColorState())
                        color = grains[i+1][j - 1].getColor();
                    else if (!grains[i][j + 1].isEmptyColorState())
                        color = grains[i][j + 1].getColor();
                    else if (!grains[i - 1][j].isEmptyColorState())
                        color = grains[i - 1][j].getColor();
                    else if (!grains[i + 1][j].isEmptyColorState())
                        color = grains[i + 1][j].getColor();
                    else if (!grains[i][j - 1].isEmptyColorState())
                        color = grains[i][j - 1].getColor();


                    grains[i][j].changeGrainColor(color);
                    if (grains[i][j + 1].isEmptyColorState()) grains[i][j + 1].changeGrainColor(color);
                    if (grains[i - 1][j].isEmptyColorState()) grains[i - 1][j].changeGrainColor(color);
                    if (grains[i + 1][j].isEmptyColorState()) grains[i + 1][j].changeGrainColor(color);
                    if (grains[i][j - 1].isEmptyColorState()) grains[i][j - 1].changeGrainColor(color);
                    if (grains[i-1][j + 1].isEmptyColorState()) grains[i-1][j + 1].changeGrainColor(color);
                    if (grains[i+1][j + 1].isEmptyColorState()) grains[i+1][j + 1].changeGrainColor(color);
                    if (grains[i-1][j - 1].isEmptyColorState()) grains[i-1][j - 1].changeGrainColor(color);
                    if (grains[i+1][j - 1].isEmptyColorState()) grains[i+1][j - 1].changeGrainColor(color);


                }

            }
        }

    }


    public static void HexagonalLeft(Grain grains[][],int count,String boundary) {

        Paint color = null;

        for (int i=1;i<count - 1; i++){
            for (int j=1;j<count - 1; j++) {
                if (!grains[i][j].isEmptyColorState()) {
                    continue;

                }
                else {
                    if (!grains[i-1][j+1].isEmptyColorState())
                        color = grains[i-1][j + 1].getColor();
                    else if (!grains[i+1][j - 1].isEmptyColorState())
                        color = grains[i+1][j - 1].getColor();
                    else if (!grains[i][j + 1].isEmptyColorState())
                        color = grains[i][j + 1].getColor();
                    else if (!grains[i - 1][j].isEmptyColorState())
                        color = grains[i - 1][j].getColor();
                    else if (!grains[i + 1][j].isEmptyColorState())
                        color = grains[i + 1][j].getColor();
                    else if (!grains[i][j - 1].isEmptyColorState())
                        color = grains[i][j - 1].getColor();


                    grains[i][j].changeGrainColor(color);
                    if (grains[i][j + 1].isEmptyColorState()) grains[i][j + 1].changeGrainColor(color);
                    if (grains[i - 1][j].isEmptyColorState()) grains[i - 1][j].changeGrainColor(color);
                    if (grains[i + 1][j].isEmptyColorState()) grains[i + 1][j].changeGrainColor(color);
                    if (grains[i][j - 1].isEmptyColorState()) grains[i][j - 1].changeGrainColor(color);
                    if (grains[i-1][j + 1].isEmptyColorState()) grains[i-1][j + 1].changeGrainColor(color);
                    if (grains[i+1][j - 1].isEmptyColorState()) grains[i+1][j - 1].changeGrainColor(color);


                }

            }
        }

    }

    public static void HexagonalRight(Grain grains[][],int count,String boundary) {

        Paint color = null;

        for (int i=1;i<count - 1; i++){
            for (int j=1;j<count - 1; j++) {
                if (!grains[i][j].isEmptyColorState()) {

                    continue;

                }
                else {
                    if (!grains[i+1][j + 1].isEmptyColorState())
                        color = grains[i+1][j + 1].getColor();
                    else if (!grains[i-1][j - 1].isEmptyColorState())
                        color = grains[i-1][j - 1].getColor();
                    else if (!grains[i][j + 1].isEmptyColorState())
                        color = grains[i][j + 1].getColor();
                    else if (!grains[i - 1][j].isEmptyColorState())
                        color = grains[i - 1][j].getColor();
                    else if (!grains[i + 1][j].isEmptyColorState())
                        color = grains[i + 1][j].getColor();
                    else if (!grains[i][j - 1].isEmptyColorState())
                        color = grains[i][j - 1].getColor();


                    grains[i][j].changeGrainColor(color);
                    if (grains[i][j + 1].isEmptyColorState()) grains[i][j + 1].changeGrainColor(color);
                    if (grains[i - 1][j].isEmptyColorState()) grains[i - 1][j].changeGrainColor(color);
                    if (grains[i + 1][j].isEmptyColorState()) grains[i + 1][j].changeGrainColor(color);
                    if (grains[i][j - 1].isEmptyColorState()) grains[i][j - 1].changeGrainColor(color);
                    if (grains[i+1][j + 1].isEmptyColorState()) grains[i+1][j + 1].changeGrainColor(color);
                    if (grains[i-1][j - 1].isEmptyColorState()) grains[i-1][j - 1].changeGrainColor(color);


                }

            }
        }



    }



    public static void PentagonalLeft(Grain grains[][],int count,String boundary) {

        Paint color = null;

        for (int i=1;i<count - 1; i++){
            for (int j=1;j<count - 1; j++) {
                if (!grains[i][j].isEmptyColorState()) {

                    continue;

                }
                else {
                    if (!grains[i-1][j+1].isEmptyColorState())
                        color = grains[i-1][j + 1].getColor();
                    else if (!grains[i-1][j - 1].isEmptyColorState())
                        color = grains[i-1][j - 1].getColor();
                    else if (!grains[i][j + 1].isEmptyColorState())
                        color = grains[i][j + 1].getColor();
                    else if (!grains[i - 1][j].isEmptyColorState())
                        color = grains[i - 1][j].getColor();
                    else if (!grains[i][j - 1].isEmptyColorState())
                        color = grains[i][j - 1].getColor();


                    grains[i][j].changeGrainColor(color);
                    if (grains[i][j + 1].isEmptyColorState()) grains[i][j + 1].changeGrainColor(color);
                    if (grains[i - 1][j].isEmptyColorState()) grains[i - 1][j].changeGrainColor(color);
                    if (grains[i][j - 1].isEmptyColorState()) grains[i][j - 1].changeGrainColor(color);
                    if (grains[i-1][j + 1].isEmptyColorState()) grains[i-1][j + 1].changeGrainColor(color);
                    if (grains[i-1][j - 1].isEmptyColorState()) grains[i-1][j - 1].changeGrainColor(color);

                }

            }
        }

    }



    public static void PentagonalRight(Grain grains[][],int count,String boundary) {

        Paint color = null;

        for (int i=1;i<count - 1; i++){
            for (int j=1;j<count - 1; j++) {
                if (!grains[i][j].isEmptyColorState()) {

                    continue;

                }
                else {
                    if (!grains[i+1][j + 1].isEmptyColorState())
                        color = grains[i+1][j + 1].getColor();
                    else if (!grains[i+1][j - 1].isEmptyColorState())
                        color = grains[i+1][j - 1].getColor();
                    else if (!grains[i][j + 1].isEmptyColorState())
                        color = grains[i][j + 1].getColor();
                    else if (!grains[i + 1][j].isEmptyColorState())
                        color = grains[i + 1][j].getColor();
                    else if (!grains[i][j - 1].isEmptyColorState())
                        color = grains[i][j - 1].getColor();


                    grains[i][j].changeGrainColor(color);
                    if (grains[i][j + 1].isEmptyColorState()) grains[i][j + 1].changeGrainColor(color);
                    if (grains[i + 1][j].isEmptyColorState()) grains[i + 1][j].changeGrainColor(color);
                    if (grains[i][j - 1].isEmptyColorState()) grains[i][j - 1].changeGrainColor(color);
                    if (grains[i+1][j + 1].isEmptyColorState()) grains[i+1][j + 1].changeGrainColor(color);
                    if (grains[i+1][j - 1].isEmptyColorState()) grains[i+1][j - 1].changeGrainColor(color);


                }

            }
        }

    }







}
