package sample;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import sample.model.Grain;

import java.io.IOException;

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