package sample;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import sample.model.Grain;

import java.io.IOException;

public class Algorithm2 {





    public static void vonNeuman(Grain grains[][], int count,String boundary, AnchorPane output) throws InterruptedException {

        Paint color = null;
        int grainId;
//        int x=0;


        System.out.println("Kolory");
        for (int i=0;i<count; i++){
            for (int j=0;j<count; j++) {
                System.out.print(grains[i][j].getColor() + " ");
            }
            System.out.println();
            }

        for (int i=1;i<count - 1; i++){
            for (int j=1;j<count - 1; j++) {

                System.out.println("Pozycja [" + i + "][" + j + "]");
                if (!grains[i][j].isEmptyColorState()) {
                            System.out.println("Opuszczam");
                    continue;

                }
                else {
                    if (!grains[i][j - 1].isEmptyColorState()) {
                        color = grains[i][j - 1].getColor();
                        System.out.println("Górny kwadrat ma kolor " + color);
                    }
                    if (!grains[i - 1][j].isEmptyColorState()) {
                        color = grains[i - 1][j].getColor();
                        System.out.println("Lewy kwadrat ma kolor " + color);
                    }
                    if (!grains[i + 1][j].isEmptyColorState()) {
                        color = grains[i + 1][j].getColor();
                        System.out.println("Prawy kwadrat ma kolor " + color);
                    }
                    if (!grains[i][j + 1].isEmptyColorState()) {
                        color = grains[i][j + 1].getColor();
                        System.out.println("Dolny kwadrat ma kolor " + color);
                    }



                    grains[i][j].changeGrainColor(color);
                    System.out.println("Zmieniam kolor środkowego kwadratu na " + color);
                    if (grains[i][j - 1].isEmptyColorState()) {
                        System.out.println("Zmieniam kolor górnego kwadratu na " + color);
                        grains[i][j - 1].changeGrainColor(color);
                    }
                    if (grains[i - 1][j].isEmptyColorState()) {
                        System.out.println("Zmieniam kolor lewego kwadratu na " + color);
                        grains[i - 1][j].changeGrainColor(color);
                    }
                    if (grains[i + 1][j].isEmptyColorState()) {
                        System.out.println("Zmieniam kolor prawego kwadratu na " + color);
                        grains[i + 1][j].changeGrainColor(color);
                    }
                    if (grains[i][j + 1].isEmptyColorState()) {
                        System.out.println("Zmieniam kolor dolnego kwadratu na " + color);
                        grains[i][j + 1].changeGrainColor(color);
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
