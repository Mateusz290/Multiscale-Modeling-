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
import java.util.HashMap;
import java.util.Map;
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


    public void algorithmCurvatureStillByOneStep(int propabilities) {


        algorithmCurvature(propabilities);

    }


    public void algorithmCurvature(int propabilities){

        int max=0;
        int pom=0;
        Paint newColor=null;

        Random random = new Random();

        int n;

        Map<Paint, Integer> colorGrains = new HashMap();


        for (Paint paint: alive) {
            colorGrains.put(paint, 0);
        }

        sample.Grain[][] prevGrains = new sample.Grain[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                sample.Grain f = grains[i][j];
                prevGrains[i][j] = new sample.Grain(f.getX(), f.getPhase(), f.getY(), f.getGrainColor());
            }
        }

        for (int i = 1; i < x-1; i++) {
            for (int j = 1; j < y-1; j++) {
                for (Paint paint : alive) {
                    colorGrains.put(paint, 0);
                }
                max = 0;
                if (prevGrains[i][j].isGrayColor()) {

                    if (i == 0 || i == x - 1 || j == 0 || j == y - 1) {
                        if (controller.conditions) {
                            if (i == 0) {
                                if (j == 0) {


                                } else if (j == y - 1) {




                                } else {



                                }

                            } else if (i == x - 1) {
                                if (j == 0) {



                                } else if (j == y - 1) {





                                } else {




                                }

                            } else if (j == 0) {




                            } else if (j == y - 1) {



                            }


                        } else {
                            if (i == 0) {
                                if (j == 0) {



                                } else if (j == y - 1) {



                                } else {



                                }

                            } else if (i == x - 1) {
                                if (j == 0) {





                                } else if (j == y - 1) {




                                } else {



                                }


                            }

                        }
                    } else {

                        // I Rule
                        if (!prevGrains[i - 1][j - 1].isGrayColor()) {
                            colorGrains.put(prevGrains[i - 1][j - 1].getGrainColor(), colorGrains.get(prevGrains[i - 1][j - 1].getGrainColor()) + 1);
                            pom = colorGrains.get(prevGrains[i - 1][j - 1].getGrainColor());
                            if (max < pom) {
                                max = pom;
                                newColor = prevGrains[i - 1][j - 1].getGrainColor();
                            }
                        }
                        if (!prevGrains[i - 1][j].isGrayColor()) {
                            colorGrains.put(prevGrains[i - 1][j].getGrainColor(), colorGrains.get(prevGrains[i - 1][j].getGrainColor()) + 1);
                            pom = colorGrains.get(prevGrains[i - 1][j].getGrainColor());
                            if (max < pom) {
                                max = pom;
                                newColor = prevGrains[i - 1][j].getGrainColor();
                            }
                        }
                        if (!prevGrains[i - 1][j + 1].isGrayColor()) {
                            colorGrains.put(prevGrains[i - 1][j + 1].getGrainColor(), colorGrains.get(prevGrains[i - 1][j + 1].getGrainColor()) + 1);
                            pom = colorGrains.get(prevGrains[i - 1][j + 1].getGrainColor());
                            if (max < pom) {
                                max = pom;
                                newColor = prevGrains[i - 1][j + 1].getGrainColor();
                            }
                        }
                        if (!prevGrains[i][j - 1].isGrayColor()) {
                            colorGrains.put(prevGrains[i][j - 1].getGrainColor(), colorGrains.get(prevGrains[i][j - 1].getGrainColor()) + 1);
                            pom = colorGrains.get(prevGrains[i][j - 1].getGrainColor());
                            if (max < pom) {
                                max = pom;
                                newColor = prevGrains[i][j - 1].getGrainColor();
                            }
                        }
                        if (!prevGrains[i][j + 1].isGrayColor()) {
                            colorGrains.put(prevGrains[i][j + 1].getGrainColor(), colorGrains.get(prevGrains[i][j + 1].getGrainColor()) + 1);
                            pom = colorGrains.get(prevGrains[i][j + 1].getGrainColor());
                            if (max < pom) {
                                max = pom;
                                newColor = prevGrains[i][j + 1].getGrainColor();
                            }
                        }
                        if (!prevGrains[i + 1][j - 1].isGrayColor()) {
                            colorGrains.put(prevGrains[i + 1][j - 1].getGrainColor(), colorGrains.get(prevGrains[i + 1][j - 1].getGrainColor()) + 1);
                            pom = colorGrains.get(prevGrains[i + 1][j - 1].getGrainColor());
                            if (max < pom) {
                                max = pom;
                                newColor = prevGrains[i + 1][j - 1].getGrainColor();
                            }
                        }
                        if (!prevGrains[i + 1][j].isGrayColor()) {
                            colorGrains.put(prevGrains[i + 1][j].getGrainColor(), colorGrains.get(prevGrains[i + 1][j].getGrainColor()) + 1);
                            pom = colorGrains.get(prevGrains[i + 1][j].getGrainColor());
                            if (max < pom) {
                                max = pom;
                                newColor = prevGrains[i + 1][j].getGrainColor();
                            }
                        }
                        if (!prevGrains[i + 1][j + 1].isGrayColor()) {
                            colorGrains.put(prevGrains[i + 1][j + 1].getGrainColor(), colorGrains.get(prevGrains[i + 1][j + 1].getGrainColor()) + 1);
                            pom = colorGrains.get(prevGrains[i + 1][j + 1].getGrainColor());
                            if (max < pom) {
                                max = pom;
                                newColor = prevGrains[i + 1][j + 1].getGrainColor();
                            }
                        }

                        if (max >= 5) {
                            System.out.println("Rule 1 spelnione!");
                            grains[i][j].changeGrainColor(newColor);


                        } else {

                            // II rule
                            for (Paint paint : alive) {
                                colorGrains.put(paint, 0);
                            }
                            max = 0;


                            if (!prevGrains[i - 1][j].isGrayColor()) {
                                colorGrains.put(prevGrains[i - 1][j].getGrainColor(), colorGrains.get(prevGrains[i - 1][j].getGrainColor()) + 1);
                                pom = colorGrains.get(prevGrains[i - 1][j].getGrainColor());
                                if (max < pom) {
                                    max = pom;
                                    newColor = prevGrains[i - 1][j].getGrainColor();
                                }
                            }

                            if (!prevGrains[i][j - 1].isGrayColor()) {
                                colorGrains.put(prevGrains[i][j - 1].getGrainColor(), colorGrains.get(prevGrains[i][j - 1].getGrainColor()) + 1);
                                pom = colorGrains.get(prevGrains[i][j - 1].getGrainColor());
                                if (max < pom) {
                                    max = pom;
                                    newColor = prevGrains[i][j - 1].getGrainColor();
                                }
                            }

                            if (!prevGrains[i][j + 1].isGrayColor()) {
                                colorGrains.put(prevGrains[i][j + 1].getGrainColor(), colorGrains.get(prevGrains[i][j + 1].getGrainColor()) + 1);
                                pom = colorGrains.get(prevGrains[i][j + 1].getGrainColor());
                                if (max < pom) {
                                    max = pom;
                                    newColor = prevGrains[i][j + 1].getGrainColor();
                                }
                            }

                            if (!prevGrains[i + 1][j].isGrayColor()) {
                                colorGrains.put(prevGrains[i + 1][j].getGrainColor(), colorGrains.get(prevGrains[i + 1][j].getGrainColor()) + 1);
                                pom = colorGrains.get(prevGrains[i + 1][j].getGrainColor());
                                if (max < pom) {
                                    max = pom;
                                    newColor = prevGrains[i + 1][j].getGrainColor();
                                }
                            }

                            if (max>=3) {
                                System.out.println("Rule 2 spelnione!");
                                grains[i][j].changeGrainColor(newColor);

                            } else {

                                // III rule

                                System.out.println();
                                for (Paint paint : alive) {
                                    colorGrains.put(paint, 0);
                                }
                                max = 0;


                                if (!prevGrains[i - 1][j - 1].isGrayColor()) {
                                    colorGrains.put(prevGrains[i - 1][j - 1].getGrainColor(), colorGrains.get(prevGrains[i - 1][j - 1].getGrainColor()) + 1);
                                    pom = colorGrains.get(prevGrains[i - 1][j - 1].getGrainColor());
                                    if (max < pom) {
                                        max = pom;
                                        newColor = prevGrains[i - 1][j - 1].getGrainColor();
                                    }
                                }

                                if (!prevGrains[i - 1][j + 1].isGrayColor()) {
                                    colorGrains.put(prevGrains[i - 1][j + 1].getGrainColor(), colorGrains.get(prevGrains[i - 1][j + 1].getGrainColor()) + 1);
                                    pom = colorGrains.get(prevGrains[i - 1][j + 1].getGrainColor());
                                    if (max < pom) {
                                        max = pom;
                                        newColor = prevGrains[i - 1][j + 1].getGrainColor();
                                    }
                                }

                                if (!prevGrains[i + 1][j - 1].isGrayColor()) {
                                    colorGrains.put(prevGrains[i + 1][j - 1].getGrainColor(), colorGrains.get(prevGrains[i + 1][j - 1].getGrainColor()) + 1);
                                    pom = colorGrains.get(prevGrains[i + 1][j - 1].getGrainColor());
                                    if (max < pom) {
                                        max = pom;
                                        newColor = prevGrains[i + 1][j - 1].getGrainColor();
                                    }
                                }

                                if (!prevGrains[i + 1][j + 1].isGrayColor()) {
                                    colorGrains.put(prevGrains[i + 1][j + 1].getGrainColor(), colorGrains.get(prevGrains[i + 1][j + 1].getGrainColor()) + 1);
                                    pom = colorGrains.get(prevGrains[i + 1][j + 1].getGrainColor());
                                    if (max < pom) {
                                        max = pom;
                                        newColor = prevGrains[i + 1][j + 1].getGrainColor();
                                    }
                                }

                                if (max >= 3) {
                                    System.out.println("Rule 3 spelnione!");
                                    grains[i][j].changeGrainColor(newColor);
                                } else {


                                    // Rule IV
                                    n = random.nextInt(100);

                                    for (Paint paint : alive) {
                                        colorGrains.put(paint, 0);
                                    }
                                    max = 0;

                                    if (!prevGrains[i - 1][j - 1].isGrayColor()) {
                                        colorGrains.put(prevGrains[i - 1][j - 1].getGrainColor(), colorGrains.get(prevGrains[i - 1][j - 1].getGrainColor()) + 1);
                                        pom = colorGrains.get(prevGrains[i - 1][j - 1].getGrainColor());
                                        if (max < pom) max = pom;
                                    }
                                    if (!prevGrains[i - 1][j].isGrayColor()) {
                                        colorGrains.put(prevGrains[i - 1][j].getGrainColor(), colorGrains.get(prevGrains[i - 1][j].getGrainColor()) + 1);
                                        pom = colorGrains.get(prevGrains[i - 1][j].getGrainColor());
                                        if (max < pom) max = pom;
                                    }
                                    if (!prevGrains[i - 1][j + 1].isGrayColor()) {
                                        colorGrains.put(prevGrains[i - 1][j + 1].getGrainColor(), colorGrains.get(prevGrains[i - 1][j + 1].getGrainColor()) + 1);
                                        pom = colorGrains.get(prevGrains[i - 1][j + 1].getGrainColor());
                                        if (max < pom) max = pom;
                                    }
                                    if (!prevGrains[i][j - 1].isGrayColor()) {
                                        colorGrains.put(prevGrains[i][j - 1].getGrainColor(), colorGrains.get(prevGrains[i][j - 1].getGrainColor()) + 1);
                                        pom = colorGrains.get(prevGrains[i][j - 1].getGrainColor());
                                        if (max < pom) max = pom;
                                    }
                                    if (!prevGrains[i][j + 1].isGrayColor()) {
                                        colorGrains.put(prevGrains[i][j + 1].getGrainColor(), colorGrains.get(prevGrains[i][j + 1].getGrainColor()) + 1);
                                        pom = colorGrains.get(prevGrains[i][j + 1].getGrainColor());
                                        if (max < pom) max = pom;
                                    }
                                    if (!prevGrains[i + 1][j - 1].isGrayColor()) {
                                        colorGrains.put(prevGrains[i + 1][j - 1].getGrainColor(), colorGrains.get(prevGrains[i + 1][j - 1].getGrainColor()) + 1);
                                        pom = colorGrains.get(prevGrains[i + 1][j - 1].getGrainColor());
                                        if (max < pom) max = pom;
                                    }
                                    if (!prevGrains[i + 1][j].isGrayColor()) {
                                        colorGrains.put(prevGrains[i + 1][j].getGrainColor(), colorGrains.get(prevGrains[i + 1][j].getGrainColor()) + 1);
                                        pom = colorGrains.get(prevGrains[i + 1][j].getGrainColor());
                                        if (max < pom) max = pom;
                                    }
                                    if (!prevGrains[i + 1][j + 1].isGrayColor()) {
                                        colorGrains.put(prevGrains[i + 1][j + 1].getGrainColor(), colorGrains.get(prevGrains[i + 1][j + 1].getGrainColor()) + 1);
                                        pom = colorGrains.get(prevGrains[i + 1][j + 1].getGrainColor());
                                        if (max < pom) max = pom;
                                    }

                                    if (n < propabilities) {
                                        for (Map.Entry<Paint, Integer> entry : colorGrains.entrySet()) {
                                            if (entry.getValue() != 0) {
                                                grains[i][j].changeGrainColor(entry.getKey());
                                                break;
                                            }

                                        }
                                    }

                                }
                            }
                        }
                    }
                }
            }
        }

        checkAlive();


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
