package sample;

import javafx.scene.paint.Paint;
import sample.model.Grain;

public class Algorithm2 {





    public static void vonNeuman(Grain grains[][], int count,String boundary) {

        Paint color = null;
        int grainId;

        for (int i=1;i<count - 1; i++){
            for (int j=1;j<count - 1; j++) {
                grainId = -1;
                System.out.println("Pozycja [" + i + "][" + j + " ]" + " - granID = " + grains[i][j].getGrainId());
                if (!grains[i][j].isGrainColor()) {
                            System.out.println("Opuszczam");
                    continue;

                }
                else {
                    if (!grains[i][j + 1].isGrainColor() && grains[i][j + 1].getGrainId() > grainId ) {
                        color = grains[i][j + 1].getColor();
                        grainId = grains[i][j + 1].getGrainId();
                    }
                    if (!grains[i - 1][j].isGrainColor() && grains[i - 1][j].getGrainId() > grainId) {
                        color = grains[i - 1][j].getColor();
                        grainId = grains[i-1][j].getGrainId();
                     }
                     if (!grains[i + 1][j].isGrainColor() && grains[i + 1][j].getGrainId() > grainId) {
                        color = grains[i + 1][j].getColor();
                        grainId = grains[i+1][j].getGrainId();

                     }
                    if (!grains[i][j - 1].isGrainColor() && grains[i][j-1].getGrainId() > grainId) {
                        color = grains[i][j - 1].getColor();
                        grainId = grains[i][j-1].getGrainId();

                    }


                    grains[i][j].changeGrainColor(color);
                    if (grains[i][j + 1].isGrainColor()) grains[i][j + 1].changeGrainColor(color);
                    if (grains[i - 1][j].isGrainColor()) grains[i - 1][j].changeGrainColor(color);
                    if (grains[i + 1][j].isGrainColor()) grains[i + 1][j].changeGrainColor(color);
                    if (grains[i][j - 1].isGrainColor()) grains[i][j - 1].changeGrainColor(color);

                }

            }
        }

    }

    public static void Moorea(Grain grains[][],int count,String boundary) {

        Paint color = null;

        for (int i=1;i<count - 1; i++){
            for (int j=1;j<count - 1; j++) {
                if (!grains[i][j].isGrainColor()) {

                    continue;

                }
                else {
                    if (!grains[i-1][j+1].isGrainColor())
                        color = grains[i-1][j + 1].getColor();
                    else if (!grains[i+1][j + 1].isGrainColor())
                        color = grains[i+1][j + 1].getColor();
                    else if (!grains[i-1][j - 1].isGrainColor())
                        color = grains[i-1][j - 1].getColor();
                    else if (!grains[i+1][j - 1].isGrainColor())
                        color = grains[i+1][j - 1].getColor();
                    else if (!grains[i][j + 1].isGrainColor())
                        color = grains[i][j + 1].getColor();
                    else if (!grains[i - 1][j].isGrainColor())
                        color = grains[i - 1][j].getColor();
                    else if (!grains[i + 1][j].isGrainColor())
                        color = grains[i + 1][j].getColor();
                    else if (!grains[i][j - 1].isGrainColor())
                        color = grains[i][j - 1].getColor();


                    grains[i][j].changeGrainColor(color);
                    if (grains[i][j + 1].isGrainColor()) grains[i][j + 1].changeGrainColor(color);
                    if (grains[i - 1][j].isGrainColor()) grains[i - 1][j].changeGrainColor(color);
                    if (grains[i + 1][j].isGrainColor()) grains[i + 1][j].changeGrainColor(color);
                    if (grains[i][j - 1].isGrainColor()) grains[i][j - 1].changeGrainColor(color);
                    if (grains[i-1][j + 1].isGrainColor()) grains[i-1][j + 1].changeGrainColor(color);
                    if (grains[i+1][j + 1].isGrainColor()) grains[i+1][j + 1].changeGrainColor(color);
                    if (grains[i-1][j - 1].isGrainColor()) grains[i-1][j - 1].changeGrainColor(color);
                    if (grains[i+1][j - 1].isGrainColor()) grains[i+1][j - 1].changeGrainColor(color);


                }

            }
        }

    }


    public static void HexagonalLeft(Grain grains[][],int count,String boundary) {

        Paint color = null;

        for (int i=1;i<count - 1; i++){
            for (int j=1;j<count - 1; j++) {
                if (!grains[i][j].isGrainColor()) {
                    continue;

                }
                else {
                    if (!grains[i-1][j+1].isGrainColor())
                        color = grains[i-1][j + 1].getColor();
                    else if (!grains[i+1][j - 1].isGrainColor())
                        color = grains[i+1][j - 1].getColor();
                    else if (!grains[i][j + 1].isGrainColor())
                        color = grains[i][j + 1].getColor();
                    else if (!grains[i - 1][j].isGrainColor())
                        color = grains[i - 1][j].getColor();
                    else if (!grains[i + 1][j].isGrainColor())
                        color = grains[i + 1][j].getColor();
                    else if (!grains[i][j - 1].isGrainColor())
                        color = grains[i][j - 1].getColor();


                    grains[i][j].changeGrainColor(color);
                    if (grains[i][j + 1].isGrainColor()) grains[i][j + 1].changeGrainColor(color);
                    if (grains[i - 1][j].isGrainColor()) grains[i - 1][j].changeGrainColor(color);
                    if (grains[i + 1][j].isGrainColor()) grains[i + 1][j].changeGrainColor(color);
                    if (grains[i][j - 1].isGrainColor()) grains[i][j - 1].changeGrainColor(color);
                    if (grains[i-1][j + 1].isGrainColor()) grains[i-1][j + 1].changeGrainColor(color);
                    if (grains[i+1][j - 1].isGrainColor()) grains[i+1][j - 1].changeGrainColor(color);


                }

            }
        }

    }

    public static void HexagonalRight(Grain grains[][],int count,String boundary) {

        Paint color = null;

        for (int i=1;i<count - 1; i++){
            for (int j=1;j<count - 1; j++) {
                if (!grains[i][j].isGrainColor()) {

                    continue;

                }
                else {
                    if (!grains[i+1][j + 1].isGrainColor())
                        color = grains[i+1][j + 1].getColor();
                    else if (!grains[i-1][j - 1].isGrainColor())
                        color = grains[i-1][j - 1].getColor();
                    else if (!grains[i][j + 1].isGrainColor())
                        color = grains[i][j + 1].getColor();
                    else if (!grains[i - 1][j].isGrainColor())
                        color = grains[i - 1][j].getColor();
                    else if (!grains[i + 1][j].isGrainColor())
                        color = grains[i + 1][j].getColor();
                    else if (!grains[i][j - 1].isGrainColor())
                        color = grains[i][j - 1].getColor();


                    grains[i][j].changeGrainColor(color);
                    if (grains[i][j + 1].isGrainColor()) grains[i][j + 1].changeGrainColor(color);
                    if (grains[i - 1][j].isGrainColor()) grains[i - 1][j].changeGrainColor(color);
                    if (grains[i + 1][j].isGrainColor()) grains[i + 1][j].changeGrainColor(color);
                    if (grains[i][j - 1].isGrainColor()) grains[i][j - 1].changeGrainColor(color);
                    if (grains[i+1][j + 1].isGrainColor()) grains[i+1][j + 1].changeGrainColor(color);
                    if (grains[i-1][j - 1].isGrainColor()) grains[i-1][j - 1].changeGrainColor(color);


                }

            }
        }



    }



    public static void PentagonalLeft(Grain grains[][],int count,String boundary) {

        Paint color = null;

        for (int i=1;i<count - 1; i++){
            for (int j=1;j<count - 1; j++) {
                if (!grains[i][j].isGrainColor()) {

                    continue;

                }
                else {
                    if (!grains[i-1][j+1].isGrainColor())
                        color = grains[i-1][j + 1].getColor();
                    else if (!grains[i-1][j - 1].isGrainColor())
                        color = grains[i-1][j - 1].getColor();
                    else if (!grains[i][j + 1].isGrainColor())
                        color = grains[i][j + 1].getColor();
                    else if (!grains[i - 1][j].isGrainColor())
                        color = grains[i - 1][j].getColor();
                    else if (!grains[i][j - 1].isGrainColor())
                        color = grains[i][j - 1].getColor();


                    grains[i][j].changeGrainColor(color);
                    if (grains[i][j + 1].isGrainColor()) grains[i][j + 1].changeGrainColor(color);
                    if (grains[i - 1][j].isGrainColor()) grains[i - 1][j].changeGrainColor(color);
                    if (grains[i][j - 1].isGrainColor()) grains[i][j - 1].changeGrainColor(color);
                    if (grains[i-1][j + 1].isGrainColor()) grains[i-1][j + 1].changeGrainColor(color);
                    if (grains[i-1][j - 1].isGrainColor()) grains[i-1][j - 1].changeGrainColor(color);

                }

            }
        }

    }



    public static void PentagonalRight(Grain grains[][],int count,String boundary) {

        Paint color = null;

        for (int i=1;i<count - 1; i++){
            for (int j=1;j<count - 1; j++) {
                if (!grains[i][j].isGrainColor()) {

                    continue;

                }
                else {
                    if (!grains[i+1][j + 1].isGrainColor())
                        color = grains[i+1][j + 1].getColor();
                    else if (!grains[i+1][j - 1].isGrainColor())
                        color = grains[i+1][j - 1].getColor();
                    else if (!grains[i][j + 1].isGrainColor())
                        color = grains[i][j + 1].getColor();
                    else if (!grains[i + 1][j].isGrainColor())
                        color = grains[i + 1][j].getColor();
                    else if (!grains[i][j - 1].isGrainColor())
                        color = grains[i][j - 1].getColor();


                    grains[i][j].changeGrainColor(color);
                    if (grains[i][j + 1].isGrainColor()) grains[i][j + 1].changeGrainColor(color);
                    if (grains[i + 1][j].isGrainColor()) grains[i + 1][j].changeGrainColor(color);
                    if (grains[i][j - 1].isGrainColor()) grains[i][j - 1].changeGrainColor(color);
                    if (grains[i+1][j + 1].isGrainColor()) grains[i+1][j + 1].changeGrainColor(color);
                    if (grains[i+1][j - 1].isGrainColor()) grains[i+1][j - 1].changeGrainColor(color);


                }

            }
        }

    }







}
