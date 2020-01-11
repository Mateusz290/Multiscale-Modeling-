package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Algorithm {
    private int x,y;
    private Controller controller;
    private Timeline timeline;
    private boolean substractures = false;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    private int size;
    public double getScale() {
        return scale;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }

    private double scale;
    private sample.Grain grains[][];
    private sample.Grain reserveGrains[][];
    private List<Paint> alive=new ArrayList<>();
    private List<Paint> aliveSubstracture=new ArrayList<>();



    Algorithm(int x, int y, Controller controller){
        this.controller=controller;
        this.x=x;
        this.y=y;
        grains =new sample.Grain[x][y];
        reserveGrains = new sample.Grain[x][y];
        for(int i=0;i<x;i++){
            for(int j=0;j<y;j++){
                grains[i][j]=new sample.Grain(i,j,0, Color.GREY);
            }
        }
        scale=1.0;
    }




    public void importFile() {

        alive.removeAll(alive);
        aliveSubstracture.removeAll(aliveSubstracture);

        String lineFromFile;

        String [] colors;

        int x;

        try {

            System.out.print("Przed scanerem");
            Scanner scanner = new Scanner(new File("ImageColorGrains.txt"));
            System.out.print("Po scanerze");

            lineFromFile = scanner.nextLine();

            colors = lineFromFile.split(" ");

            x = colors.length;



            System.out.println("Odczytana tablica kolorow" + 0);
            for (int i=0;i<x;i++){
                for(int j=0;j<y;j++){
                    grains[i][j] = new sample.Grain(i,j,0, Paint.valueOf(colors[j]));
                    System.out.print(grains[i][j].getGrainColor() + " ");
                }
                System.out.println();
                System.out.println(i);
                if (i!=x-1) lineFromFile = scanner.nextLine();
                colors = lineFromFile.split(" ");
            }

        } catch (IOException e) {
            System.out.print("Blad");
        }


        for (int i=0;i<this.x;i++){
            for(int j=0;j<y;j++){
                if (!alive.contains(grains[i][j].getGrainColor())) alive.add(grains[i][j].getGrainColor());
            }
        }

    }

    public AnchorPane generate(){
//        System.out.println("generate");
        AnchorPane ret= new AnchorPane();
        if (x > y) {
            size = (int) scale *700 / y;
        }
        else{
            size =(int) scale * 700 / x;
        }

        for(int i=0;i<x;i++){
            for(int j=0;j<y;j++){
                AnchorPane temp= grains[i][j].createBasicImage();
                temp.setPrefSize(size,size);
                temp.setPrefSize(size,size);
                temp.setMaxSize(size,size);
                AnchorPane.setTopAnchor(temp, j*size*1.0);
                AnchorPane.setLeftAnchor(temp, i*size*1.0);
                ret.getChildren().add(temp);
            }
        }

        return ret;
    }


    public void random(int numberIncusions, int minRadius, int maxRadius) {

        Random random = new Random();
        int i,j;

        int radiusSize;

        while(numberIncusions>0) {
            i = random.nextInt(x);
            j = random.nextInt(y);
            radiusSize = minRadius + random.nextInt(maxRadius - minRadius + 1);
            grains[i][j].setPhase(radiusSize);
            numberIncusions--;
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



    public void run(){


        timeline = new Timeline(
                new KeyFrame(Duration.millis(600),
                        (evt) -> {
                            cellaraAutomation();
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

    public void run(int steps){

        while(steps>0) {
            cellaraAutomation();
            controller.output.getChildren().clear();
            controller.output.getChildren().add(this.generate());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            steps--;

        }
    }

    public void runSubtractures(){



        timeline = new Timeline(
                new KeyFrame(Duration.millis(1000),
                        (evt) -> {
                            cellaraAutomationSubtructures();
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

    public void runSubtracturesByOneStep(){


//        System.out.println("dzialam");
        cellaraAutomationSubtructures();
        controller.output.getChildren().clear();
        controller.output.getChildren().add(this.generate());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        timeline.setCycleCount(Animation.INDEFINITE);
//        timeline.play();

//            controller.output.getChildren().add(this.generate());




    }

    public void pauseRun() {
        timeline.stop();
    }


    public void saveToFile(PrintWriter printWriterColors, PrintWriter printWriterInclusions) {

        for (int i=0;i<x; i++) {
            for (int j=0;j<y;j++) {
                printWriterColors.print(grains[i][j].getGrainColor() + " ");
                printWriterInclusions.print(grains[i][j].getPhase() + " ");
            }
            printWriterColors.println();
            printWriterInclusions.println();
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


    public boolean isPlace(){
        for(int i=0;i<x;i++) {
            for (int j = 0; j < y; j++) {
                if(grains[i][j].isGrayColor()) return true;
            }
        }
        return false;
    }


    private Paint nextPaintCondition() {
        if (!substractures) return nextPaint();
        else return nextPaintSubstractures();
    }

    private Paint nextPaint(){

        boolean generated=false;
        Random rand=new Random();

        Color randomColor=null;
        while(!generated){
            float r = rand.nextFloat();
            float g = rand.nextFloat();
            float b = rand.nextFloat();

            randomColor= new Color(r,g,b,1.0 );
            if(randomColor==Color.GRAY){
                generated=false;
            }
            else if(alive.isEmpty()){
                generated=true;
            }
            else{
                boolean isused=false;
                for (Paint anUsed : alive) {
                    if (randomColor == anUsed) {
                        isused = true;
                    }
                }
                if(!isused)
                    generated=true;
            }
        }
        alive.add(randomColor);
        return randomColor;

    }


    private Paint nextPaintSubstractures(){

        boolean generated=false;
        Random rand=new Random();

        Color randomColor=null;
        while(!generated){
            float r = rand.nextFloat();
            float g = rand.nextFloat();
            float b = rand.nextFloat();

            randomColor= new Color(r,g,b,1.0 );
            if(randomColor==Color.GRAY){
                generated=false;
            }
            else if(aliveSubstracture.isEmpty()){
                generated=true;
            }
            else{
                boolean isused=false;
                for (Paint anUsed : aliveSubstracture) {
                    if (randomColor == anUsed) {
                        isused = true;
                    }
                }
                if(!isused)
                    generated=true;
            }
        }
        aliveSubstracture.add(randomColor);
        return randomColor;

    }

    public void seed(){
        int howmuch=controller.seedCount;
        Random randomizer=new Random();
        int iterator=0;

        switch (controller.seeding){
            case "Random":
                iterator=0;
                while(iterator<howmuch){
                    int xr=randomizer.nextInt(this.x);
                    int yr=randomizer.nextInt(this.y);
                    if(grains[xr][yr].isGrayColor()){
                        grains[xr][yr].changeGrainColor(nextPaintCondition());
                    }
                    iterator++;

                }
                break;

        }

        System.out.println("Rozmiar glownej listy = " + alive.size());
        System.out.println("Rozmiar dodatkowej listy = " + aliveSubstracture.size());
    }

    public void seedTest(){
        int howmuch=controller.seedCount;
        Random randomizer=new Random();
        int iterator=0;

        Paint paint = nextPaint();


        grains[0][0].changeGrainColor(paint);
        grains[2][0].changeGrainColor(paint);
        grains[0][2].changeGrainColor(paint);


        // 6 1
        paint = nextPaint();
        grains[5][0].changeGrainColor(paint);
        grains[7][0].changeGrainColor(paint);
        grains[7][2].changeGrainColor(paint);


        // 5 5

        paint = nextPaint();
        grains[6][4].changeGrainColor(paint);
        grains[6][6].changeGrainColor(paint);
        grains[4][6].changeGrainColor(paint);


        // 2 8
        paint = nextPaint();
        grains[1][7].changeGrainColor(paint);
        grains[1][9].changeGrainColor(paint);
        grains[3][9].changeGrainColor(paint);



    }


    public void checkAlive(){
        List<Paint> a=new ArrayList<>();

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if(!a.contains(grains[i][j].getGrainColor())){
                    a.add(grains[i][j].getGrainColor());
                }
            }
        }

        alive=a;
    }

    public void checkAliveSubstractures(){
        List<Paint> a=new ArrayList<>();

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if(!a.contains(grains[i][j].getGrainColor()) && !alive.contains(grains[i][j])){
                    a.add(grains[i][j].getGrainColor());
                }
            }
        }

        aliveSubstracture=a;
    }



    public void subsctructures(List<Paint>grainsToDelete,String dPStructure) {

        substractures = true;

//        System.out.println();
//        System.out.println();

//        System.out.println("Rozmiar Glowna lista przed usunieciu " + alive.size());
//        System.out.println("Rozmiar dodatkowej listy przed usunieciem " + aliveSubstracture.size());



////        System.out.println();
//        System.out.println("Lista przed usunieciem");
//        for (int i=0;i<alive.size();i++) {
//            System.out.print(alive.get(i) + " ");
//        }

        alive.removeAll(grainsToDelete);
        /*System.out.println();
        System.out.println("Nowy rozmiar listy" + alive.size());
        System.out.print("Lista po usunieciu");
        System.out.println();
        for (int i=0;i<alive.size();i++) {
            System.out.print(alive.get(i) + " ");

        }*/


        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {

                if (!alive.contains(grains[i][j].getGrainColor())) {
                    grains[i][j].changeGrainColor(Color.GRAY);
                }

            }
        }

        if (dPStructure.equals("Yes")) {
            System.out.println("Funkcja DPStructure wlaczaona");

            Paint paint = nextPaint();

            for (int i = 0; i < x; i++) {
                for (int j = 0; j < y; j++) {
                    if (alive.contains(grains[i][j].getGrainColor())) {

                        grains[i][j].changeGrainColor(paint);
                    }
                }
            }

        }



    }


    private void cellaraAutomation(){

        sample.Grain[][] prevGrains = new sample.Grain[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                sample.Grain f = grains[i][j];
                prevGrains[i][j] = new sample.Grain(f.getX(), f.getPhase(), f.getY(), f.getGrainColor());
            }
        }

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (!prevGrains[i][j].isGrayColor()) {
//                    System.out.println("Dzialam");
                    switch (controller.neighborhood) {
                        case "VonNeumann":
                            vonNeumanMethod(prevGrains, i,j);
                            break;
                        case "HexagonalRight":
                            hexagonalRight(prevGrains, i,j);
                            break;
                        case "Moore":
                            mooreMethod(prevGrains, i,j);
                            break;
                        case "HexagonalLeft":
                            hexagonalLeftMethod(prevGrains, i, j);
                            break;
                        case "PentagonalRight":
                            pentagonalRightMethod(prevGrains,i,j);
                            break;
                        case "PentagonalLeft":
                            pentagonalLeftMethod(prevGrains,i,j);
                            break;
                        case "PentagonalRandom":
                            pentagonalRandomMethod(prevGrains, i,j);
                            break;
                    }
                }
            }
        }

        checkAlive();
    }


    private void cellaraAutomationSubtructures(){



       /* System.out.println("Główna tabela z kolorami. Rozmiar " + alive.size());
        for (Paint paint: alive) {
            System.out.print(paint + " ");
        }

        System.out.println();
        System.out.println("Druga tabela z kolorami. Rozmiar " + aliveSubstracture.size());
        for (Paint paint: aliveSubstracture) {
            System.out.print(paint + " ");
        }
*/
        sample.Grain[][] prevGrains = new sample.Grain[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                sample.Grain f = grains[i][j];
                prevGrains[i][j] = new sample.Grain(f.getX(), f.getPhase(), f.getY(), f.getGrainColor());
            }
        }

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (!prevGrains[i][j].isGrayColor() && aliveSubstracture.contains(prevGrains[i][j].getGrainColor())) {
//                    System.out.println("Dzialam");
                    switch (controller.neighborhood) {
                        case "VonNeumann":
                            vonNeumanMethod(prevGrains, i,j);
                            break;
                        case "HexagonalRight":
                            hexagonalRight(prevGrains, i,j);
                            break;
                        case "Moore":
                            mooreMethod(prevGrains, i,j);
                            break;
                        case "HexagonalLeft":
                            hexagonalLeftMethod(prevGrains, i, j);
                            break;
                        case "PentagonalRight":
                            pentagonalRightMethod(prevGrains,i,j);
                            break;
                        case "PentagonalLeft":
                            pentagonalLeftMethod(prevGrains,i,j);
                            break;
                        case "PentagonalRandom":
                            pentagonalRandomMethod(prevGrains, i,j);
                            break;
                    }
                }
            }
        }

//        checkAliveSubstractures();
    }


    public List<Paint> addGrainsToDelete() {
        return alive;
    }



    private void vonNeumanMethod(sample.Grain[][] prevGrains, int i, int j) {
//        System.out.println("controller conditions = " + controller.conditions);
        if (i == 0 || i == x - 1 || j == 0 || j == y - 1) {
            if (controller.conditions) {
//                System.out.println("controller conditions = true");
                if (i == 0) {
                    if (j == 0) {
                        if (prevGrains[x - 1][j].isGrayColor())
                            grains[x - 1][j].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i + 1][j].isGrayColor())
                            grains[i + 1][j].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][y - 1].isGrayColor())
                            grains[i][y - 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][j + 1].isGrayColor())
                            grains[i][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                    } else if (j == y - 1) {
                        if (prevGrains[x - 1][j].isGrayColor())
                            grains[x - 1][j].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i + 1][j].isGrayColor())
                            grains[i + 1][j].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][j - 1].isGrayColor())
                            grains[i][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][0].isGrayColor())
                            grains[i][01].changeGrainColor(grains[i][j].getGrainColor());
                    } else {
                        if (prevGrains[x - 1][j].isGrayColor())
                            grains[x - 1][j].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i + 1][j].isGrayColor())
                            grains[i + 1][j].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][j - 1].isGrayColor())
                            grains[i][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][j + 1].isGrayColor())
                            grains[i][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                    }

                } else if (i == x - 1) {
                    if (j == 0) {
                        if (prevGrains[i - 1][j].isGrayColor())
                            grains[i - 1][j].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[0][j].isGrayColor())
                            grains[0][j].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][y - 1].isGrayColor())
                            grains[i][y - 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][j + 1].isGrayColor())
                            grains[i][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                    } else if (j == y - 1) {
                        if (prevGrains[i - 1][j].isGrayColor())
                            grains[i - 1][j].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[0][j].isGrayColor())
                            grains[0][j].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][j - 1].isGrayColor())
                            grains[i][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][0].isGrayColor())
                            grains[i][0].changeGrainColor(grains[i][j].getGrainColor());
                    } else {
                        if (prevGrains[i - 1][j].isGrayColor())
                            grains[i - 1][j].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[0][j].isGrayColor())
                            grains[0][j].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][j - 1].isGrayColor())
                            grains[i][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][j + 1].isGrayColor())
                            grains[i][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                    }

                } else if (j == 0) {
                    if (prevGrains[i - 1][j].isGrayColor())
                        grains[i - 1][j].changeGrainColor(grains[i][j].getGrainColor());
                    if (prevGrains[i + 1][j].isGrayColor())
                        grains[i + 1][j].changeGrainColor(grains[i][j].getGrainColor());
                    if (prevGrains[i][y - 1].isGrayColor())
                        grains[i][y - 1].changeGrainColor(grains[i][j].getGrainColor());
                    if (prevGrains[i][j + 1].isGrayColor())
                        grains[i][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                } else if (j == y - 1) {
                    if (prevGrains[i - 1][j].isGrayColor())
                        grains[i - 1][j].changeGrainColor(grains[i][j].getGrainColor());
                    if (prevGrains[i + 1][j].isGrayColor())
                        grains[i + 1][j].changeGrainColor(grains[i][j].getGrainColor());
                    if (prevGrains[i][j - 1].isGrayColor())
                        grains[i][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                    if (prevGrains[i][0].isGrayColor()) grains[i][0].changeGrainColor(grains[i][j].getGrainColor());
                }


            } else {
//                System.out.println("controller conditions =  false" + controller.conditions);
                if (i == 0) {
                    if (j == 0) {
                        if (prevGrains[i + 1][j].isGrayColor())
                            grains[i + 1][j].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][j + 1].isGrayColor())
                            grains[i][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                    } else if (j == y - 1) {
                        if (prevGrains[i + 1][j].isGrayColor())
                            grains[i + 1][j].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][j - 1].isGrayColor())
                            grains[i][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                    } else {
                        if (prevGrains[i + 1][j].isGrayColor())
                            grains[i + 1][j].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][j - 1].isGrayColor())
                            grains[i][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][j + 1].isGrayColor())
                            grains[i][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                    }

                } else if (i == x - 1) {
                    if (j == 0) {

                        if (prevGrains[i - 1][j].isGrayColor())
                            grains[i - 1][j].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][j + 1].isGrayColor())
                            grains[i][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                    } else if (j == y - 1) {

                        if (prevGrains[i - 1][j].isGrayColor())
                            grains[i - 1][j].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][j - 1].isGrayColor())
                            grains[i][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                    } else {
                        if (prevGrains[i - 1][j].isGrayColor())
                            grains[i - 1][j].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][j - 1].isGrayColor())
                            grains[i][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][j + 1].isGrayColor())
                            grains[i][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                    }


                }

            }
        } else {
//            System.out.println("prevGrains[ " + "i][ " + j + "] - 1][j].isGrayColor() = " + prevGrains[i - 1][j].isGrayColor());
//            System.out.println("prevGrains[ " + "i][ " + j + "] - 1][j].isGrayColor() = " + prevGrains[i - 1][j].isGrayColor());
            if (prevGrains[i - 1][j].isGrayColor()) {
//                System.out.println("Zmieniam kolor");
                grains[i - 1][j].changeGrainColor(grains[i][j].getGrainColor());
            }
//            System.out.println("prevGrains[i + 1][j].isGrayColor() = " + prevGrains[i + 1][j].isGrayColor());
            if (prevGrains[i + 1][j].isGrayColor()) {
//                System.out.println("Zmieniam kolor");
                grains[i + 1][j].changeGrainColor(grains[i][j].getGrainColor());
            }
//            System.out.println("prevGrains[i][j - 1].isGrayColor() = " + prevGrains[i][j - 1].isGrayColor());
            if (prevGrains[i][j - 1].isGrayColor()) {
//                System.out.println("Zmieniam kolor");
                grains[i][j - 1].changeGrainColor(grains[i][j].getGrainColor());
            }
//            System.out.println("prevGrains[i][j + 1].isGrayColor() = " + prevGrains[i][j + 1].isGrayColor());
            if (prevGrains[i][j + 1].isGrayColor()) {
//                System.out.println("Zmieniam kolor");
                grains[i][j + 1].changeGrainColor(grains[i][j].getGrainColor());
            }
        }
    }


    private void hexagonalRight(sample.Grain[][] prevGrains, int i, int j) {
        if (i == 0 || i == x - 1 || j == 0 || j == y - 1) {
            if (controller.conditions) {
                if (i == 0) {
                    if (j == 0) {
                        if (prevGrains[x - 1][j].isGrayColor())
                            grains[x - 1][j].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i + 1][j].isGrayColor())
                            grains[i + 1][j].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][y - 1].isGrayColor())
                            grains[i][y - 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][j + 1].isGrayColor())
                            grains[i][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[x - 1][j + 1].isGrayColor())
                            grains[x - 1][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i + 1][y - 1].isGrayColor())
                            grains[i + 1][y - 1].changeGrainColor(grains[i][j].getGrainColor());
                    } else if (j == y - 1) {
                        if (prevGrains[x - 1][j].isGrayColor())
                            grains[x - 1][j].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i + 1][j].isGrayColor())
                            grains[i + 1][j].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][j - 1].isGrayColor())
                            grains[i][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][0].isGrayColor())
                            grains[i][01].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[x - 1][0].isGrayColor())
                            grains[x - 1][0].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i + 1][j - 1].isGrayColor())
                            grains[i + 1][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                    } else {
                        if (prevGrains[x - 1][j].isGrayColor())
                            grains[x - 1][j].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i + 1][j].isGrayColor())
                            grains[i + 1][j].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][j - 1].isGrayColor())
                            grains[i][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][j + 1].isGrayColor())
                            grains[i][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[x - 1][j + 1].isGrayColor())
                            grains[x - 1][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i + 1][j - 1].isGrayColor())
                            grains[i + 1][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                    }

                } else if (i == x - 1) {
                    if (j == 0) {
                        if (prevGrains[i - 1][j].isGrayColor())
                            grains[i - 1][j].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[0][j].isGrayColor())
                            grains[0][j].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][y - 1].isGrayColor())
                            grains[i][y - 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][j + 1].isGrayColor())
                            grains[i][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i - 1][j + 1].isGrayColor())
                            grains[i - 1][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[0][y - 1].isGrayColor())
                            grains[0][y - 1].changeGrainColor(grains[i][j].getGrainColor());
                    } else if (j == y - 1) {
                        if (prevGrains[i - 1][j].isGrayColor())
                            grains[i - 1][j].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[0][j].isGrayColor())
                            grains[0][j].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][j - 1].isGrayColor())
                            grains[i][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][0].isGrayColor())
                            grains[i][0].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i - 1][0].isGrayColor())
                            grains[i - 1][0].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[0][j - 1].isGrayColor())
                            grains[0][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                    } else {
                        if (prevGrains[i - 1][j].isGrayColor())
                            grains[i - 1][j].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[0][j].isGrayColor())
                            grains[0][j].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][j - 1].isGrayColor())
                            grains[i][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][j + 1].isGrayColor())
                            grains[i][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i - 1][j + 1].isGrayColor())
                            grains[i - 1][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[0][j - 1].isGrayColor())
                            grains[0][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                    }

                } else if (j == 0) {
                    if (prevGrains[i - 1][j].isGrayColor())
                        grains[i - 1][j].changeGrainColor(grains[i][j].getGrainColor());
                    if (prevGrains[i + 1][j].isGrayColor())
                        grains[i + 1][j].changeGrainColor(grains[i][j].getGrainColor());
                    if (prevGrains[i][y - 1].isGrayColor())
                        grains[i][y - 1].changeGrainColor(grains[i][j].getGrainColor());
                    if (prevGrains[i][j + 1].isGrayColor())
                        grains[i][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                    if (prevGrains[i - 1][j + 1].isGrayColor())
                        grains[i - 1][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                    if (prevGrains[i + 1][y - 1].isGrayColor())
                        grains[i + 1][y - 1].changeGrainColor(grains[i][j].getGrainColor());
                } else if (j == y - 1) {
                    if (prevGrains[i - 1][j].isGrayColor())
                        grains[i - 1][j].changeGrainColor(grains[i][j].getGrainColor());
                    if (prevGrains[i + 1][j].isGrayColor())
                        grains[i + 1][j].changeGrainColor(grains[i][j].getGrainColor());
                    if (prevGrains[i][j - 1].isGrayColor())
                        grains[i][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                    if (prevGrains[i][0].isGrayColor()) grains[i][0].changeGrainColor(grains[i][j].getGrainColor());
                    if (prevGrains[i - 1][0].isGrayColor())
                        grains[i - 1][0].changeGrainColor(grains[i][j].getGrainColor());
                    if (prevGrains[i + 1][j - 1].isGrayColor())
                        grains[i + 1][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                }


            } else {//notperiodic
                if (i == 0) {
                    if (j == 0) {
                        if (prevGrains[i + 1][j].isGrayColor())
                            grains[i + 1][j].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][j + 1].isGrayColor())
                            grains[i][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                    } else if (j == y - 1) {
                        if (prevGrains[i + 1][j].isGrayColor())
                            grains[i + 1][j].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][j - 1].isGrayColor())
                            grains[i][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i + 1][j - 1].isGrayColor())
                            grains[i + 1][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                    } else {
                        if (prevGrains[i + 1][j].isGrayColor())
                            grains[i + 1][j].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][j - 1].isGrayColor())
                            grains[i][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][j + 1].isGrayColor())
                            grains[i][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i + 1][j - 1].isGrayColor())
                            grains[i + 1][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                    }

                } else if (i == x - 1) {
                    if (j == 0) {

                        if (prevGrains[i - 1][j].isGrayColor())
                            grains[i - 1][j].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][j + 1].isGrayColor())
                            grains[i][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i + 1][j - 1].isGrayColor())
                            grains[i + 1][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                    } else if (j == y - 1) {

                        if (prevGrains[i - 1][j].isGrayColor())
                            grains[i - 1][j].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][j - 1].isGrayColor())
                            grains[i][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i - 1][j + 1].isGrayColor())
                            grains[i - 1][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                    } else {
                        if (prevGrains[i - 1][j].isGrayColor())
                            grains[i - 1][j].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][j - 1].isGrayColor())
                            grains[i][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][j + 1].isGrayColor())
                            grains[i][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i - 1][j + 1].isGrayColor())
                            grains[i - 1][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                    }


                }

            }
        } else {
            if (prevGrains[i - 1][j].isGrayColor()) grains[i - 1][j].changeGrainColor(grains[i][j].getGrainColor());
            if (prevGrains[i + 1][j].isGrayColor()) grains[i + 1][j].changeGrainColor(grains[i][j].getGrainColor());
            if (prevGrains[i][j - 1].isGrayColor()) grains[i][j - 1].changeGrainColor(grains[i][j].getGrainColor());
            if (prevGrains[i][j + 1].isGrayColor()) grains[i][j + 1].changeGrainColor(grains[i][j].getGrainColor());
            if (prevGrains[i - 1][j + 1].isGrayColor())
                grains[i - 1][j + 1].changeGrainColor(grains[i][j].getGrainColor());
            if (prevGrains[i + 1][j - 1].isGrayColor())
                grains[i + 1][j - 1].changeGrainColor(grains[i][j].getGrainColor());
        }

    }


    private void mooreMethod(sample.Grain[][] prevGrains, int i, int j) {
        if (i == 0 || i == x - 1 || j == 0 || j == y - 1) {
            if (controller.conditions) {
                if (i == 0) {
                    if (j == 0) {
                        if (prevGrains[x - 1][j].isGrayColor())
                            grains[x - 1][j].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i + 1][j].isGrayColor())
                            grains[i + 1][j].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][y - 1].isGrayColor())
                            grains[i][y - 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][j + 1].isGrayColor())
                            grains[i][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[x - 1][j + 1].isGrayColor())
                            grains[x - 1][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i + 1][y - 1].isGrayColor())
                            grains[i + 1][y - 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[x - 1][y - 1].isGrayColor())
                            grains[x - 1][y - 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i + 1][j + 1].isGrayColor())
                            grains[i + 1][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                    } else if (j == y - 1) {
                        if (prevGrains[x - 1][j].isGrayColor())
                            grains[x - 1][j].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i + 1][j].isGrayColor())
                            grains[i + 1][j].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][j - 1].isGrayColor())
                            grains[i][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][0].isGrayColor())
                            grains[i][0].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[x - 1][0].isGrayColor())
                            grains[x - 1][0].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i + 1][j - 1].isGrayColor())
                            grains[i + 1][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[x - 1][j - 1].isGrayColor())
                            grains[x - 1][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i + 1][0].isGrayColor())
                            grains[i + 1][0].changeGrainColor(grains[i][j].getGrainColor());
                    } else {
                        if (prevGrains[x - 1][j].isGrayColor())
                            grains[x - 1][j].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i + 1][j].isGrayColor())
                            grains[i + 1][j].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][j - 1].isGrayColor())
                            grains[i][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][j + 1].isGrayColor())
                            grains[i][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[x - 1][j + 1].isGrayColor())
                            grains[x - 1][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i + 1][j - 1].isGrayColor())
                            grains[i + 1][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[x - 1][j - 1].isGrayColor())
                            grains[x - 1][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i + 1][j + 1].isGrayColor())
                            grains[i + 1][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                    }

                } else if (i == x - 1) {
                    if (j == 0) {
                        if (prevGrains[i - 1][j].isGrayColor())
                            grains[i - 1][j].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[0][j].isGrayColor())
                            grains[0][j].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][y - 1].isGrayColor())
                            grains[i][y - 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][j + 1].isGrayColor())
                            grains[i][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i - 1][j + 1].isGrayColor())
                            grains[i - 1][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[0][y - 1].isGrayColor())
                            grains[0][y - 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i - 1][y - 1].isGrayColor())
                            grains[i - 1][y - 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[0][j + 1].isGrayColor())
                            grains[0][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                    } else if (j == y - 1) {
                        if (prevGrains[i - 1][j].isGrayColor())
                            grains[i - 1][j].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[0][j].isGrayColor())
                            grains[0][j].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][j - 1].isGrayColor())
                            grains[i][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][0].isGrayColor())
                            grains[i][0].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i - 1][0].isGrayColor())
                            grains[i - 1][0].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[0][j - 1].isGrayColor())
                            grains[0][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i - 1][j - 1].isGrayColor())
                            grains[i - 1][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[0][0].isGrayColor())
                            grains[0][0].changeGrainColor(grains[i][j].getGrainColor());
                    } else {
                        if (prevGrains[i - 1][j].isGrayColor())
                            grains[i - 1][j].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[0][j].isGrayColor())
                            grains[0][j].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][j - 1].isGrayColor())
                            grains[i][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][j + 1].isGrayColor())
                            grains[i][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i - 1][j + 1].isGrayColor())
                            grains[i - 1][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[0][j - 1].isGrayColor())
                            grains[0][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i - 1][j - 1].isGrayColor())
                            grains[i - 1][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[0][j + 1].isGrayColor())
                            grains[0][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                    }

                } else if (j == 0) {
                    if (prevGrains[i - 1][j].isGrayColor())
                        grains[i - 1][j].changeGrainColor(grains[i][j].getGrainColor());
                    if (prevGrains[i + 1][j].isGrayColor())
                        grains[i + 1][j].changeGrainColor(grains[i][j].getGrainColor());
                    if (prevGrains[i][y - 1].isGrayColor())
                        grains[i][y - 1].changeGrainColor(grains[i][j].getGrainColor());
                    if (prevGrains[i][j + 1].isGrayColor())
                        grains[i][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                    if (prevGrains[i - 1][j + 1].isGrayColor())
                        grains[i - 1][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                    if (prevGrains[i + 1][y - 1].isGrayColor())
                        grains[i + 1][y - 1].changeGrainColor(grains[i][j].getGrainColor());
                    if (prevGrains[i - 1][y - 1].isGrayColor())
                        grains[i - 1][y - 1].changeGrainColor(grains[i][j].getGrainColor());
                    if (prevGrains[i + 1][j + 1].isGrayColor())
                        grains[i + 1][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                } else if (j == y - 1) {
                    if (prevGrains[i - 1][j].isGrayColor())
                        grains[i - 1][j].changeGrainColor(grains[i][j].getGrainColor());
                    if (prevGrains[i + 1][j].isGrayColor())
                        grains[i + 1][j].changeGrainColor(grains[i][j].getGrainColor());
                    if (prevGrains[i][j - 1].isGrayColor())
                        grains[i][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                    if (prevGrains[i][0].isGrayColor()) grains[i][0].changeGrainColor(grains[i][j].getGrainColor());
                    if (prevGrains[i - 1][0].isGrayColor())
                        grains[i - 1][0].changeGrainColor(grains[i][j].getGrainColor());
                    if (prevGrains[i + 1][j - 1].isGrayColor())
                        grains[i + 1][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                    if (prevGrains[i - 1][j - 1].isGrayColor())
                        grains[i - 1][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                    if (prevGrains[i + 1][0].isGrayColor())
                        grains[i + 1][0].changeGrainColor(grains[i][j].getGrainColor());
                }


            } else {//nonperiodic
                if (i == 0) {
                    if (j == 0) {
                        if (prevGrains[i + 1][j].isGrayColor())
                            grains[i + 1][j].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][j + 1].isGrayColor())
                            grains[i][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i + 1][j + 1].isGrayColor())
                            grains[i + 1][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                    } else if (j == y - 1) {
                        if (prevGrains[i + 1][j].isGrayColor())
                            grains[i + 1][j].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][j - 1].isGrayColor())
                            grains[i][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i + 1][j - 1].isGrayColor())
                            grains[i + 1][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                    } else {
                        if (prevGrains[i + 1][j].isGrayColor())
                            grains[i + 1][j].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][j - 1].isGrayColor())
                            grains[i][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][j + 1].isGrayColor())
                            grains[i][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i + 1][j - 1].isGrayColor())
                            grains[i + 1][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i + 1][j + 1].isGrayColor())
                            grains[i + 1][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                    }

                } else if (i == x - 1) {
                    if (j == 0) {

                        if (prevGrains[i - 1][j].isGrayColor())
                            grains[i - 1][j].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][j + 1].isGrayColor())
                            grains[i][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                    } else if (j == y - 1) {

                        if (prevGrains[i - 1][j].isGrayColor())
                            grains[i - 1][j].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][j - 1].isGrayColor())
                            grains[i][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i - 1][j - 1].isGrayColor())
                            grains[i - 1][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                    } else {
                        if (prevGrains[i - 1][j].isGrayColor())
                            grains[i - 1][j].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][j - 1].isGrayColor())
                            grains[i][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][j + 1].isGrayColor())
                            grains[i][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i - 1][j + 1].isGrayColor())
                            grains[i - 1][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i - 1][j - 1].isGrayColor())
                            grains[i - 1][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                    }


                }

            }
        } else {
            if (prevGrains[i - 1][j].isGrayColor()) grains[i - 1][j].changeGrainColor(grains[i][j].getGrainColor());
            if (prevGrains[i + 1][j].isGrayColor()) grains[i + 1][j].changeGrainColor(grains[i][j].getGrainColor());
            if (prevGrains[i][j - 1].isGrayColor()) grains[i][j - 1].changeGrainColor(grains[i][j].getGrainColor());
            if (prevGrains[i][j + 1].isGrayColor()) grains[i][j + 1].changeGrainColor(grains[i][j].getGrainColor());
            if (prevGrains[i - 1][j + 1].isGrayColor())
                grains[i - 1][j + 1].changeGrainColor(grains[i][j].getGrainColor());
            if (prevGrains[i + 1][j - 1].isGrayColor())
                grains[i + 1][j - 1].changeGrainColor(grains[i][j].getGrainColor());
            if (prevGrains[i - 1][j - 1].isGrayColor())
                grains[i - 1][j - 1].changeGrainColor(grains[i][j].getGrainColor());
            if (prevGrains[i + 1][j + 1].isGrayColor())
                grains[i + 1][j + 1].changeGrainColor(grains[i][j].getGrainColor());
        }
    }

    private void hexagonalLeftMethod(sample.Grain[][] prevGrains, int i, int j) {
        if (i == 0 || i == x - 1 || j == 0 || j == y - 1) {
            if (controller.conditions) {
                if (i == 0) {
                    if (j == 0) {
                        if (prevGrains[x - 1][j].isGrayColor())
                            grains[x - 1][j].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i + 1][j].isGrayColor())
                            grains[i + 1][j].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][y - 1].isGrayColor())
                            grains[i][y - 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][j + 1].isGrayColor())
                            grains[i][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[x - 1][y - 1].isGrayColor())
                            grains[x - 1][y - 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i + 1][j + 1].isGrayColor())
                            grains[i + 1][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                    } else if (j == y - 1) {
                        if (prevGrains[x - 1][j].isGrayColor())
                            grains[x - 1][j].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i + 1][j].isGrayColor())
                            grains[i + 1][j].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][j - 1].isGrayColor())
                            grains[i][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][0].isGrayColor())
                            grains[i][0].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[x - 1][j - 1].isGrayColor())
                            grains[x - 1][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i + 1][0].isGrayColor())
                            grains[i + 1][0].changeGrainColor(grains[i][j].getGrainColor());
                    } else {
                        if (prevGrains[x - 1][j].isGrayColor())
                            grains[x - 1][j].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i + 1][j].isGrayColor())
                            grains[i + 1][j].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][j - 1].isGrayColor())
                            grains[i][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][j + 1].isGrayColor())
                            grains[i][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[x - 1][j - 1].isGrayColor())
                            grains[x - 1][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i + 1][j + 1].isGrayColor())
                            grains[i + 1][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                    }

                } else if (i == x - 1) {
                    if (j == 0) {
                        if (prevGrains[i - 1][j].isGrayColor())
                            grains[i - 1][j].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[0][j].isGrayColor())
                            grains[0][j].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][y - 1].isGrayColor())
                            grains[i][y - 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][j + 1].isGrayColor())
                            grains[i][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i - 1][y - 1].isGrayColor())
                            grains[i - 1][y - 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[0][j + 1].isGrayColor())
                            grains[0][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                    } else if (j == y - 1) {
                        if (prevGrains[i - 1][j].isGrayColor())
                            grains[i - 1][j].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[0][j].isGrayColor())
                            grains[0][j].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][j - 1].isGrayColor())
                            grains[i][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][0].isGrayColor())
                            grains[i][0].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i - 1][j - 1].isGrayColor())
                            grains[i - 1][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[0][0].isGrayColor())
                            grains[0][0].changeGrainColor(grains[i][j].getGrainColor());
                    } else {
                        if (prevGrains[i - 1][j].isGrayColor())
                            grains[i - 1][j].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[0][j].isGrayColor())
                            grains[0][j].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][j - 1].isGrayColor())
                            grains[i][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][j + 1].isGrayColor())
                            grains[i][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i - 1][j - 1].isGrayColor())
                            grains[i - 1][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[0][j + 1].isGrayColor())
                            grains[0][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                    }

                } else if (j == 0) {
                    if (prevGrains[i - 1][j].isGrayColor())
                        grains[i - 1][j].changeGrainColor(grains[i][j].getGrainColor());
                    if (prevGrains[i + 1][j].isGrayColor())
                        grains[i + 1][j].changeGrainColor(grains[i][j].getGrainColor());
                    if (prevGrains[i][y - 1].isGrayColor())
                        grains[i][y - 1].changeGrainColor(grains[i][j].getGrainColor());
                    if (prevGrains[i][j + 1].isGrayColor())
                        grains[i][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                    if (prevGrains[i - 1][y - 1].isGrayColor())
                        grains[i - 1][y - 1].changeGrainColor(grains[i][j].getGrainColor());
                    if (prevGrains[i + 1][j + 1].isGrayColor())
                        grains[i + 1][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                } else if (j == y - 1) {
                    if (prevGrains[i - 1][j].isGrayColor())
                        grains[i - 1][j].changeGrainColor(grains[i][j].getGrainColor());
                    if (prevGrains[i + 1][j].isGrayColor())
                        grains[i + 1][j].changeGrainColor(grains[i][j].getGrainColor());
                    if (prevGrains[i][j - 1].isGrayColor())
                        grains[i][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                    if (prevGrains[i][0].isGrayColor()) grains[i][0].changeGrainColor(grains[i][j].getGrainColor());
                    if (prevGrains[i - 1][j - 1].isGrayColor())
                        grains[i - 1][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                    if (prevGrains[i + 1][0].isGrayColor())
                        grains[i + 1][0].changeGrainColor(grains[i][j].getGrainColor());
                }


            } else {//nonperiodic
                if (i == 0) {
                    if (j == 0) {
                        if (prevGrains[i + 1][j].isGrayColor())
                            grains[i + 1][j].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][j + 1].isGrayColor())
                            grains[i][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i + 1][j + 1].isGrayColor())
                            grains[i + 1][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                    } else if (j == y - 1) {
                        if (prevGrains[i + 1][j].isGrayColor())
                            grains[i + 1][j].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][j - 1].isGrayColor())
                            grains[i][j - 1].changeGrainColor(grains[i][j].getGrainColor());

                    } else {
                        if (prevGrains[i + 1][j].isGrayColor())
                            grains[i + 1][j].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][j - 1].isGrayColor())
                            grains[i][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][j + 1].isGrayColor())
                            grains[i][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i + 1][j + 1].isGrayColor())
                            grains[i + 1][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                    }

                } else if (i == x - 1) {
                    if (j == 0) {

                        if (prevGrains[i - 1][j].isGrayColor())
                            grains[i - 1][j].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][j + 1].isGrayColor())
                            grains[i][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                    } else if (j == y - 1) {

                        if (prevGrains[i - 1][j].isGrayColor())
                            grains[i - 1][j].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][j - 1].isGrayColor())
                            grains[i][j - 1].changeGrainColor(grains[i][j].getGrainColor());

                        if (prevGrains[i - 1][j - 1].isGrayColor())
                            grains[i - 1][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                    } else {
                        if (prevGrains[i - 1][j].isGrayColor())
                            grains[i - 1][j].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][j - 1].isGrayColor())
                            grains[i][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][j + 1].isGrayColor())
                            grains[i][j + 1].changeGrainColor(grains[i][j].getGrainColor());

                        if (prevGrains[i - 1][j - 1].isGrayColor())
                            grains[i - 1][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                    }


                }

            }
        } else {
            if (prevGrains[i - 1][j].isGrayColor()) grains[i - 1][j].changeGrainColor(grains[i][j].getGrainColor());
            if (prevGrains[i + 1][j].isGrayColor()) grains[i + 1][j].changeGrainColor(grains[i][j].getGrainColor());
            if (prevGrains[i][j - 1].isGrayColor()) grains[i][j - 1].changeGrainColor(grains[i][j].getGrainColor());
            if (prevGrains[i][j + 1].isGrayColor()) grains[i][j + 1].changeGrainColor(grains[i][j].getGrainColor());
            if (prevGrains[i - 1][j - 1].isGrayColor())
                grains[i - 1][j - 1].changeGrainColor(grains[i][j].getGrainColor());
            if (prevGrains[i + 1][j + 1].isGrayColor())
                grains[i + 1][j + 1].changeGrainColor(grains[i][j].getGrainColor());
        }
    }

    private void pentagonalLeftMethod(sample.Grain[][] prevGrains, int i, int j) {


        if (i == 0 || i == x - 1 || j == 0 || j == y - 1) {
            if (controller.conditions) {
                if (i == 0) {
                    if (j == 0) {
                        if (prevGrains[x - 1][j].isGrayColor())
                            grains[x - 1][j].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][y - 1].isGrayColor())
                            grains[i][y - 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][j + 1].isGrayColor())
                            grains[i][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[x - 1][j + 1].isGrayColor())
                            grains[x - 1][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[x - 1][y - 1].isGrayColor())
                            grains[x - 1][y - 1].changeGrainColor(grains[i][j].getGrainColor());
                    } else if (j == y - 1) {
                        if (prevGrains[x - 1][j].isGrayColor())
                            grains[x - 1][j].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][j - 1].isGrayColor())
                            grains[i][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][0].isGrayColor())
                            grains[i][0].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[x - 1][0].isGrayColor())
                            grains[x - 1][0].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[x - 1][j - 1].isGrayColor())
                            grains[x - 1][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                    } else {
                        if (prevGrains[x - 1][j].isGrayColor())
                            grains[x - 1][j].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][j - 1].isGrayColor())
                            grains[i][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][j + 1].isGrayColor())
                            grains[i][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[x - 1][j + 1].isGrayColor())
                            grains[x - 1][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[x - 1][j - 1].isGrayColor())
                            grains[x - 1][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                    }

                } else if (i == x - 1) {
                    if (j == 0) {
                        if (prevGrains[i - 1][j].isGrayColor())
                            grains[i - 1][j].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][y - 1].isGrayColor())
                            grains[i][y - 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][j + 1].isGrayColor())
                            grains[i][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i - 1][j + 1].isGrayColor())
                            grains[i - 1][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i - 1][y - 1].isGrayColor())
                            grains[i - 1][y - 1].changeGrainColor(grains[i][j].getGrainColor());
                    } else if (j == y - 1) {
                        if (prevGrains[i - 1][j].isGrayColor())
                            grains[i - 1][j].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][j - 1].isGrayColor())
                            grains[i][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][0].isGrayColor())
                            grains[i][0].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i - 1][0].isGrayColor())
                            grains[i - 1][0].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i - 1][j - 1].isGrayColor())
                            grains[i - 1][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                    } else {
                        if (prevGrains[i - 1][j].isGrayColor())
                            grains[i - 1][j].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][j - 1].isGrayColor())
                            grains[i][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][j + 1].isGrayColor())
                            grains[i][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i - 1][j + 1].isGrayColor())
                            grains[i - 1][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i - 1][j - 1].isGrayColor())
                            grains[i - 1][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                    }

                } else if (j == 0) {
                    if (prevGrains[i - 1][j].isGrayColor())
                        grains[i - 1][j].changeGrainColor(grains[i][j].getGrainColor());
                    if (prevGrains[i][y - 1].isGrayColor())
                        grains[i][y - 1].changeGrainColor(grains[i][j].getGrainColor());
                    if (prevGrains[i][j + 1].isGrayColor())
                        grains[i][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                    if (prevGrains[i - 1][j + 1].isGrayColor())
                        grains[i - 1][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                    if (prevGrains[i - 1][y - 1].isGrayColor())
                        grains[i - 1][y - 1].changeGrainColor(grains[i][j].getGrainColor());
                } else if (j == y - 1) {
                    if (prevGrains[i - 1][j].isGrayColor())
                        grains[i - 1][j].changeGrainColor(grains[i][j].getGrainColor());
                    if (prevGrains[i][j - 1].isGrayColor())
                        grains[i][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                    if (prevGrains[i][0].isGrayColor())
                        grains[i][0].changeGrainColor(grains[i][j].getGrainColor());
                    if (prevGrains[i - 1][0].isGrayColor())
                        grains[i - 1][0].changeGrainColor(grains[i][j].getGrainColor());
                    if (prevGrains[i - 1][j - 1].isGrayColor())
                        grains[i - 1][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                }


            } else {//nonperiodic
                if (i == 0) {
                    if (j == 0) {
                        if (prevGrains[i][j + 1].isGrayColor())
                            grains[i][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                    } else if (j == y - 1) {
                        if (prevGrains[i][j - 1].isGrayColor())
                            grains[i][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                    } else {
                        if (prevGrains[i][j - 1].isGrayColor())
                            grains[i][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][j + 1].isGrayColor())
                            grains[i][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                    }

                } else if (i == x - 1) {
                    if (j == 0) {

                        if (prevGrains[i - 1][j].isGrayColor())
                            grains[i - 1][j].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][j + 1].isGrayColor())
                            grains[i][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                    } else if (j == y - 1) {

                        if (prevGrains[i - 1][j].isGrayColor())
                            grains[i - 1][j].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][j - 1].isGrayColor())
                            grains[i][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i - 1][j - 1].isGrayColor())
                            grains[i - 1][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                    } else {
                        if (prevGrains[i - 1][j].isGrayColor())
                            grains[i - 1][j].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][j - 1].isGrayColor())
                            grains[i][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][j + 1].isGrayColor())
                            grains[i][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i - 1][j + 1].isGrayColor())
                            grains[i - 1][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i - 1][j - 1].isGrayColor())
                            grains[i - 1][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                    }


                }

            }
        } else {
            if (prevGrains[i - 1][j].isGrayColor())
                grains[i - 1][j].changeGrainColor(grains[i][j].getGrainColor());
            if (prevGrains[i][j - 1].isGrayColor())
                grains[i][j - 1].changeGrainColor(grains[i][j].getGrainColor());
            if (prevGrains[i][j + 1].isGrayColor())
                grains[i][j + 1].changeGrainColor(grains[i][j].getGrainColor());
            if (prevGrains[i - 1][j + 1].isGrayColor())
                grains[i - 1][j + 1].changeGrainColor(grains[i][j].getGrainColor());
            if (prevGrains[i - 1][j - 1].isGrayColor())
                grains[i - 1][j - 1].changeGrainColor(grains[i][j].getGrainColor());
        }




    }

    private void pentagonalRightMethod(sample.Grain[][] prevGrains, int i, int j) {
        if (i == 0 || i == x - 1 || j == 0 || j == y - 1) {
            if (controller.conditions) {
                if (i == 0) {
                    if (j == 0) {
                        if (prevGrains[i + 1][j].isGrayColor())
                            grains[i + 1][j].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][y - 1].isGrayColor())
                            grains[i][y - 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][j + 1].isGrayColor())
                            grains[i][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i + 1][y - 1].isGrayColor())
                            grains[i + 1][y - 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i + 1][j + 1].isGrayColor())
                            grains[i + 1][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                    } else if (j == y - 1) {
                        if (prevGrains[i + 1][j].isGrayColor())
                            grains[i + 1][j].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][j - 1].isGrayColor())
                            grains[i][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][0].isGrayColor())
                            grains[i][0].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i + 1][j - 1].isGrayColor())
                            grains[i + 1][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i + 1][0].isGrayColor())
                            grains[i + 1][0].changeGrainColor(grains[i][j].getGrainColor());
                    } else {
                        if (prevGrains[i + 1][j].isGrayColor())
                            grains[i + 1][j].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][j - 1].isGrayColor())
                            grains[i][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][j + 1].isGrayColor())
                            grains[i][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i + 1][j - 1].isGrayColor())
                            grains[i + 1][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i + 1][j + 1].isGrayColor())
                            grains[i + 1][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                    }

                } else if (i == x - 1) {
                    if (j == 0) {
                        if (prevGrains[0][j].isGrayColor())
                            grains[0][j].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][y - 1].isGrayColor())
                            grains[i][y - 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][j + 1].isGrayColor())
                            grains[i][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[0][y - 1].isGrayColor())
                            grains[0][y - 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[0][j + 1].isGrayColor())
                            grains[0][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                    } else if (j == y - 1) {
                        if (prevGrains[0][j].isGrayColor())
                            grains[0][j].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][j - 1].isGrayColor())
                            grains[i][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][0].isGrayColor())
                            grains[i][0].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[0][j - 1].isGrayColor())
                            grains[0][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[0][0].isGrayColor())
                            grains[0][0].changeGrainColor(grains[i][j].getGrainColor());
                    } else {
                        if (prevGrains[0][j].isGrayColor())
                            grains[0][j].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][j - 1].isGrayColor())
                            grains[i][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][j + 1].isGrayColor())
                            grains[i][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[0][j - 1].isGrayColor())
                            grains[0][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[0][j + 1].isGrayColor())
                            grains[0][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                    }

                } else if (j == 0) {
                    if (prevGrains[i + 1][j].isGrayColor())
                        grains[i + 1][j].changeGrainColor(grains[i][j].getGrainColor());
                    if (prevGrains[i][y - 1].isGrayColor())
                        grains[i][y - 1].changeGrainColor(grains[i][j].getGrainColor());
                    if (prevGrains[i][j + 1].isGrayColor())
                        grains[i][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                    if (prevGrains[i + 1][y - 1].isGrayColor())
                        grains[i + 1][y - 1].changeGrainColor(grains[i][j].getGrainColor());
                    if (prevGrains[i + 1][j + 1].isGrayColor())
                        grains[i + 1][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                } else if (j == y - 1) {
                    if (prevGrains[i + 1][j].isGrayColor())
                        grains[i + 1][j].changeGrainColor(grains[i][j].getGrainColor());
                    if (prevGrains[i][j - 1].isGrayColor())
                        grains[i][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                    if (prevGrains[i][0].isGrayColor())
                        grains[i][0].changeGrainColor(grains[i][j].getGrainColor());
                    if (prevGrains[i + 1][j - 1].isGrayColor())
                        grains[i + 1][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                    if (prevGrains[i + 1][0].isGrayColor())
                        grains[i + 1][0].changeGrainColor(grains[i][j].getGrainColor());
                }


            } else {//nonperiodic
                if (i == 0) {
                    if (j == 0) {
                        if (prevGrains[i + 1][j].isGrayColor())
                            grains[i + 1][j].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][j + 1].isGrayColor())
                            grains[i][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i + 1][j + 1].isGrayColor())
                            grains[i + 1][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                    } else if (j == y - 1) {
                        if (prevGrains[i + 1][j].isGrayColor())
                            grains[i + 1][j].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][j - 1].isGrayColor())
                            grains[i][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i + 1][j - 1].isGrayColor())
                            grains[i + 1][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                    } else {
                        if (prevGrains[i + 1][j].isGrayColor())
                            grains[i + 1][j].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][j - 1].isGrayColor())
                            grains[i][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][j + 1].isGrayColor())
                            grains[i][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i + 1][j - 1].isGrayColor())
                            grains[i + 1][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i + 1][j + 1].isGrayColor())
                            grains[i + 1][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                    }

                } else if (i == x - 1) {
                    if (j == 0) {

                        if (prevGrains[i][j + 1].isGrayColor())
                            grains[i][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                    } else if (j == y - 1) {

                        if (prevGrains[i][j - 1].isGrayColor())
                            grains[i][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                    } else {
                        if (prevGrains[i][j - 1].isGrayColor())
                            grains[i][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                        if (prevGrains[i][j + 1].isGrayColor())
                            grains[i][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                    }


                }

            }
        } else {
            if (prevGrains[i + 1][j].isGrayColor())
                grains[i + 1][j].changeGrainColor(grains[i][j].getGrainColor());
            if (prevGrains[i][j - 1].isGrayColor())
                grains[i][j - 1].changeGrainColor(grains[i][j].getGrainColor());
            if (prevGrains[i][j + 1].isGrayColor())
                grains[i][j + 1].changeGrainColor(grains[i][j].getGrainColor());
            if (prevGrains[i + 1][j - 1].isGrayColor())
                grains[i + 1][j - 1].changeGrainColor(grains[i][j].getGrainColor());
            if (prevGrains[i + 1][j + 1].isGrayColor())
                grains[i + 1][j + 1].changeGrainColor(grains[i][j].getGrainColor());
        }
    }


    private void pentagonalRandomMethod(sample.Grain[][] prevGrains, int i, int j) {    // 0  - kieruje sie w górę      1 - kieruje się w dół     2- kieruje się w prawo    3 - kieruje sie w lewo
        Random r2 = new Random();
        int sidePent = 3;
        System.out.println("Wylosowana liczba pentagonarrandom = " + sidePent);
        switch (sidePent) {
            case 0:
                if (i == 0 || i == x - 1 || j == 0 || j == y - 1) {
                    if (controller.conditions) {
                        if (i == 0) {
                            if (j == 0) {
                                if (prevGrains[x - 1][j].isGrayColor())
                                    grains[x - 1][j].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[i + 1][j].isGrayColor())
                                    grains[i + 1][j].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[i][y - 1].isGrayColor())
                                    grains[i][y - 1].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[i + 1][y - 1].isGrayColor())
                                    grains[i + 1][y - 1].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[x - 1][y - 1].isGrayColor())
                                    grains[x - 1][y - 1].changeGrainColor(grains[i][j].getGrainColor());
                            } else if (j == y - 1) {
                                if (prevGrains[x - 1][j].isGrayColor())
                                    grains[x - 1][j].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[i + 1][j].isGrayColor())
                                    grains[i + 1][j].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[i][j - 1].isGrayColor())
                                    grains[i][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[i + 1][j - 1].isGrayColor())
                                    grains[i + 1][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[x - 1][j - 1].isGrayColor())
                                    grains[x - 1][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                            } else {
                                if (prevGrains[x - 1][j].isGrayColor())
                                    grains[x - 1][j].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[i + 1][j].isGrayColor())
                                    grains[i + 1][j].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[i][j - 1].isGrayColor())
                                    grains[i][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[i + 1][j - 1].isGrayColor())
                                    grains[i + 1][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[x - 1][j - 1].isGrayColor())
                                    grains[x - 1][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                            }

                        } else if (i == x - 1) {
                            if (j == 0) {
                                if (prevGrains[i - 1][j].isGrayColor())
                                    grains[i - 1][j].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[0][j].isGrayColor())
                                    grains[0][j].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[i][y - 1].isGrayColor())
                                    grains[i][y - 1].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[0][y - 1].isGrayColor())
                                    grains[0][y - 1].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[i - 1][y - 1].isGrayColor())
                                    grains[i - 1][y - 1].changeGrainColor(grains[i][j].getGrainColor());
                            } else if (j == y - 1) {
                                if (prevGrains[i - 1][j].isGrayColor())
                                    grains[i - 1][j].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[0][j].isGrayColor())
                                    grains[0][j].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[i][j - 1].isGrayColor())
                                    grains[i][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[0][j - 1].isGrayColor())
                                    grains[0][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[i - 1][j - 1].isGrayColor())
                                    grains[i - 1][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                            } else {
                                if (prevGrains[i - 1][j].isGrayColor())
                                    grains[i - 1][j].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[0][j].isGrayColor())
                                    grains[0][j].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[i][j - 1].isGrayColor())
                                    grains[i][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[0][j - 1].isGrayColor())
                                    grains[0][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[i - 1][j - 1].isGrayColor())
                                    grains[i - 1][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                            }

                        } else if (j == 0) {
                            if (prevGrains[i - 1][j].isGrayColor())
                                grains[i - 1][j].changeGrainColor(grains[i][j].getGrainColor());
                            if (prevGrains[i + 1][j].isGrayColor())
                                grains[i + 1][j].changeGrainColor(grains[i][j].getGrainColor());
                            if (prevGrains[i][y - 1].isGrayColor())
                                grains[i][y - 1].changeGrainColor(grains[i][j].getGrainColor());
                            if (prevGrains[i + 1][y - 1].isGrayColor())
                                grains[i + 1][y - 1].changeGrainColor(grains[i][j].getGrainColor());
                            if (prevGrains[i - 1][y - 1].isGrayColor())
                                grains[i - 1][y - 1].changeGrainColor(grains[i][j].getGrainColor());
                        } else if (j == y - 1) {
                            if (prevGrains[i - 1][j].isGrayColor())
                                grains[i - 1][j].changeGrainColor(grains[i][j].getGrainColor());
                            if (prevGrains[i + 1][j].isGrayColor())
                                grains[i + 1][j].changeGrainColor(grains[i][j].getGrainColor());
                            if (prevGrains[i][j - 1].isGrayColor())
                                grains[i][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                            if (prevGrains[i + 1][j - 1].isGrayColor())
                                grains[i + 1][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                            if (prevGrains[i - 1][j - 1].isGrayColor())
                                grains[i - 1][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                        }


                    } else {//nonperiodic
                        if (i == 0) {
                            if (j == 0) {
                                if (prevGrains[i + 1][j].isGrayColor())
                                    grains[i + 1][j].changeGrainColor(grains[i][j].getGrainColor());
                            } else if (j == y - 1) {
                                if (prevGrains[i + 1][j].isGrayColor())
                                    grains[i + 1][j].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[i][j - 1].isGrayColor())
                                    grains[i][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[i + 1][j - 1].isGrayColor())
                                    grains[i + 1][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                            } else {
                                if (prevGrains[i + 1][j].isGrayColor())
                                    grains[i + 1][j].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[i][j - 1].isGrayColor())
                                    grains[i][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[i + 1][j - 1].isGrayColor())
                                    grains[i + 1][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                            }

                        } else if (i == x - 1) {
                            if (j == 0) {

                                if (prevGrains[i - 1][j].isGrayColor())
                                    grains[i - 1][j].changeGrainColor(grains[i][j].getGrainColor());
                            } else if (j == y - 1) {

                                if (prevGrains[i - 1][j].isGrayColor())
                                    grains[i - 1][j].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[i][j - 1].isGrayColor())
                                    grains[i][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[i - 1][j - 1].isGrayColor())
                                    grains[i - 1][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                            } else {
                                if (prevGrains[i - 1][j].isGrayColor())
                                    grains[i - 1][j].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[i][j - 1].isGrayColor())
                                    grains[i][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[i - 1][j - 1].isGrayColor())
                                    grains[i - 1][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                            }


                        }

                    }
                } else {
                    if (prevGrains[i - 1][j].isGrayColor())
                        grains[i - 1][j].changeGrainColor(grains[i][j].getGrainColor());
                    if (prevGrains[i + 1][j].isGrayColor())
                        grains[i + 1][j].changeGrainColor(grains[i][j].getGrainColor());
                    if (prevGrains[i][j - 1].isGrayColor())
                        grains[i][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                    if (prevGrains[i + 1][j - 1].isGrayColor())
                        grains[i + 1][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                    if (prevGrains[i - 1][j - 1].isGrayColor())
                        grains[i - 1][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                }

                break;
            case 1:
                if (i == 0 || i == x - 1 || j == 0 || j == y - 1) {
                    if (controller.conditions) {
                        if (i == 0) {
                            if (j == 0) {
                                if (prevGrains[x - 1][j].isGrayColor())
                                    grains[x - 1][j].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[i + 1][j].isGrayColor())
                                    grains[i + 1][j].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[i][j + 1].isGrayColor())
                                    grains[i][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[x - 1][j + 1].isGrayColor())
                                    grains[x - 1][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[i + 1][j + 1].isGrayColor())
                                    grains[i + 1][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                            } else if (j == y - 1) {
                                if (prevGrains[x - 1][j].isGrayColor())
                                    grains[x - 1][j].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[i + 1][j].isGrayColor())
                                    grains[i + 1][j].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[i][0].isGrayColor())
                                    grains[i][0].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[x - 1][0].isGrayColor())
                                    grains[x - 1][0].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[i + 1][0].isGrayColor())
                                    grains[i + 1][0].changeGrainColor(grains[i][j].getGrainColor());
                            } else {
                                if (prevGrains[x - 1][j].isGrayColor())
                                    grains[x - 1][j].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[i + 1][j].isGrayColor())
                                    grains[i + 1][j].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[i][j + 1].isGrayColor())
                                    grains[i][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[x - 1][j + 1].isGrayColor())
                                    grains[x - 1][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[i + 1][j + 1].isGrayColor())
                                    grains[i + 1][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                            }

                        } else if (i == x - 1) {
                            if (j == 0) {
                                if (prevGrains[i - 1][j].isGrayColor())
                                    grains[i - 1][j].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[0][j].isGrayColor())
                                    grains[0][j].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[i][j + 1].isGrayColor())
                                    grains[i][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[i - 1][j + 1].isGrayColor())
                                    grains[i - 1][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[0][j + 1].isGrayColor())
                                    grains[0][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                            } else if (j == y - 1) {
                                if (prevGrains[i - 1][j].isGrayColor())
                                    grains[i - 1][j].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[0][j].isGrayColor())
                                    grains[0][j].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[i][0].isGrayColor())
                                    grains[i][0].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[i - 1][0].isGrayColor())
                                    grains[i - 1][0].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[0][0].isGrayColor())
                                    grains[0][0].changeGrainColor(grains[i][j].getGrainColor());
                            } else {
                                if (prevGrains[i - 1][j].isGrayColor())
                                    grains[i - 1][j].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[0][j].isGrayColor())
                                    grains[0][j].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[i][j + 1].isGrayColor())
                                    grains[i][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[i - 1][j + 1].isGrayColor())
                                    grains[i - 1][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[0][j + 1].isGrayColor())
                                    grains[0][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                            }

                        } else if (j == 0) {
                            if (prevGrains[i - 1][j].isGrayColor())
                                grains[i - 1][j].changeGrainColor(grains[i][j].getGrainColor());
                            if (prevGrains[i + 1][j].isGrayColor())
                                grains[i + 1][j].changeGrainColor(grains[i][j].getGrainColor());
                            if (prevGrains[i][j + 1].isGrayColor())
                                grains[i][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                            if (prevGrains[i - 1][j + 1].isGrayColor())
                                grains[i - 1][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                            if (prevGrains[i + 1][j + 1].isGrayColor())
                                grains[i + 1][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                        } else if (j == y - 1) {
                            if (prevGrains[i - 1][j].isGrayColor())
                                grains[i - 1][j].changeGrainColor(grains[i][j].getGrainColor());
                            if (prevGrains[i + 1][j].isGrayColor())
                                grains[i + 1][j].changeGrainColor(grains[i][j].getGrainColor());
                            if (prevGrains[i][0].isGrayColor())
                                grains[i][0].changeGrainColor(grains[i][j].getGrainColor());
                            if (prevGrains[i - 1][0].isGrayColor())
                                grains[i - 1][0].changeGrainColor(grains[i][j].getGrainColor());
                            if (prevGrains[i + 1][0].isGrayColor())
                                grains[i + 1][0].changeGrainColor(grains[i][j].getGrainColor());
                        }


                    } else {//nonperiodic
                        if (i == 0) {
                            if (j == 0) {
                                if (prevGrains[i + 1][j].isGrayColor())
                                    grains[i + 1][j].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[i][j + 1].isGrayColor())
                                    grains[i][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[i + 1][j + 1].isGrayColor())
                                    grains[i + 1][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                            } else if (j == y - 1) {
                                if (prevGrains[i + 1][j].isGrayColor())
                                    grains[i + 1][j].changeGrainColor(grains[i][j].getGrainColor());
                            } else {
                                if (prevGrains[i + 1][j].isGrayColor())
                                    grains[i + 1][j].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[i][j + 1].isGrayColor())
                                    grains[i][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[i + 1][j + 1].isGrayColor())
                                    grains[i + 1][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                            }

                        } else if (i == x - 1) {
                            if (j == 0) {

                                if (prevGrains[i - 1][j].isGrayColor())
                                    grains[i - 1][j].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[i][j + 1].isGrayColor())
                                    grains[i][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                            } else if (j == y - 1) {

                                if (prevGrains[i - 1][j].isGrayColor())
                                    grains[i - 1][j].changeGrainColor(grains[i][j].getGrainColor());
                            } else {
                                if (prevGrains[i - 1][j].isGrayColor())
                                    grains[i - 1][j].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[i][j + 1].isGrayColor())
                                    grains[i][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[i - 1][j + 1].isGrayColor())
                                    grains[i - 1][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                            }


                        }

                    }
                } else {
                    if (prevGrains[i - 1][j].isGrayColor())
                        grains[i - 1][j].changeGrainColor(grains[i][j].getGrainColor());
                    if (prevGrains[i + 1][j].isGrayColor())
                        grains[i + 1][j].changeGrainColor(grains[i][j].getGrainColor());
                    if (prevGrains[i][j + 1].isGrayColor())
                        grains[i][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                    if (prevGrains[i - 1][j + 1].isGrayColor())
                        grains[i - 1][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                    if (prevGrains[i + 1][j + 1].isGrayColor())
                        grains[i + 1][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                }

                break;
            case 2:
                if (i == 0 || i == x - 1 || j == 0 || j == y - 1) {
                    if (controller.conditions) {
                        if (i == 0) {
                            if (j == 0) {
                                if (prevGrains[i + 1][j].isGrayColor())
                                    grains[i + 1][j].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[i][y - 1].isGrayColor())
                                    grains[i][y - 1].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[i][j + 1].isGrayColor())
                                    grains[i][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[i + 1][y - 1].isGrayColor())
                                    grains[i + 1][y - 1].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[i + 1][j + 1].isGrayColor())
                                    grains[i + 1][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                            } else if (j == y - 1) {
                                if (prevGrains[i + 1][j].isGrayColor())
                                    grains[i + 1][j].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[i][j - 1].isGrayColor())
                                    grains[i][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[i][0].isGrayColor())
                                    grains[i][0].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[i + 1][j - 1].isGrayColor())
                                    grains[i + 1][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[i + 1][0].isGrayColor())
                                    grains[i + 1][0].changeGrainColor(grains[i][j].getGrainColor());
                            } else {
                                if (prevGrains[i + 1][j].isGrayColor())
                                    grains[i + 1][j].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[i][j - 1].isGrayColor())
                                    grains[i][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[i][j + 1].isGrayColor())
                                    grains[i][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[i + 1][j - 1].isGrayColor())
                                    grains[i + 1][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[i + 1][j + 1].isGrayColor())
                                    grains[i + 1][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                            }

                        } else if (i == x - 1) {
                            if (j == 0) {
                                if (prevGrains[0][j].isGrayColor())
                                    grains[0][j].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[i][y - 1].isGrayColor())
                                    grains[i][y - 1].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[i][j + 1].isGrayColor())
                                    grains[i][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[0][y - 1].isGrayColor())
                                    grains[0][y - 1].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[0][j + 1].isGrayColor())
                                    grains[0][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                            } else if (j == y - 1) {
                                if (prevGrains[0][j].isGrayColor())
                                    grains[0][j].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[i][j - 1].isGrayColor())
                                    grains[i][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[i][0].isGrayColor())
                                    grains[i][0].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[0][j - 1].isGrayColor())
                                    grains[0][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[0][0].isGrayColor())
                                    grains[0][0].changeGrainColor(grains[i][j].getGrainColor());
                            } else {
                                if (prevGrains[0][j].isGrayColor())
                                    grains[0][j].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[i][j - 1].isGrayColor())
                                    grains[i][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[i][j + 1].isGrayColor())
                                    grains[i][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[0][j - 1].isGrayColor())
                                    grains[0][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[0][j + 1].isGrayColor())
                                    grains[0][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                            }

                        } else if (j == 0) {
                            if (prevGrains[i + 1][j].isGrayColor())
                                grains[i + 1][j].changeGrainColor(grains[i][j].getGrainColor());
                            if (prevGrains[i][y - 1].isGrayColor())
                                grains[i][y - 1].changeGrainColor(grains[i][j].getGrainColor());
                            if (prevGrains[i][j + 1].isGrayColor())
                                grains[i][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                            if (prevGrains[i + 1][y - 1].isGrayColor())
                                grains[i + 1][y - 1].changeGrainColor(grains[i][j].getGrainColor());
                            if (prevGrains[i + 1][j + 1].isGrayColor())
                                grains[i + 1][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                        } else if (j == y - 1) {
                            if (prevGrains[i + 1][j].isGrayColor())
                                grains[i + 1][j].changeGrainColor(grains[i][j].getGrainColor());
                            if (prevGrains[i][j - 1].isGrayColor())
                                grains[i][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                            if (prevGrains[i][0].isGrayColor())
                                grains[i][0].changeGrainColor(grains[i][j].getGrainColor());
                            if (prevGrains[i + 1][j - 1].isGrayColor())
                                grains[i + 1][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                            if (prevGrains[i + 1][0].isGrayColor())
                                grains[i + 1][0].changeGrainColor(grains[i][j].getGrainColor());
                        }


                    } else {//nonperiodic
                        if (i == 0) {
                            if (j == 0) {
                                if (prevGrains[i + 1][j].isGrayColor())
                                    grains[i + 1][j].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[i][j + 1].isGrayColor())
                                    grains[i][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[i + 1][j + 1].isGrayColor())
                                    grains[i + 1][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                            } else if (j == y - 1) {
                                if (prevGrains[i + 1][j].isGrayColor())
                                    grains[i + 1][j].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[i][j - 1].isGrayColor())
                                    grains[i][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[i + 1][j - 1].isGrayColor())
                                    grains[i + 1][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                            } else {
                                if (prevGrains[i + 1][j].isGrayColor())
                                    grains[i + 1][j].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[i][j - 1].isGrayColor())
                                    grains[i][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[i][j + 1].isGrayColor())
                                    grains[i][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[i + 1][j - 1].isGrayColor())
                                    grains[i + 1][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[i + 1][j + 1].isGrayColor())
                                    grains[i + 1][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                            }

                        } else if (i == x - 1) {
                            if (j == 0) {

                                if (prevGrains[i][j + 1].isGrayColor())
                                    grains[i][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                            } else if (j == y - 1) {

                                if (prevGrains[i][j - 1].isGrayColor())
                                    grains[i][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                            } else {
                                if (prevGrains[i][j - 1].isGrayColor())
                                    grains[i][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[i][j + 1].isGrayColor())
                                    grains[i][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                            }


                        }

                    }
                } else {
                    if (prevGrains[i + 1][j].isGrayColor())
                        grains[i + 1][j].changeGrainColor(grains[i][j].getGrainColor());
                    if (prevGrains[i][j - 1].isGrayColor())
                        grains[i][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                    if (prevGrains[i][j + 1].isGrayColor())
                        grains[i][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                    if (prevGrains[i + 1][j - 1].isGrayColor())
                        grains[i + 1][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                    if (prevGrains[i + 1][j + 1].isGrayColor())
                        grains[i + 1][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                }

                break;
            case 3:
                if (i == 0 || i == x - 1 || j == 0 || j == y - 1) {
                    if (controller.conditions) {
                        if (i == 0) {
                            if (j == 0) {
                                if (prevGrains[x - 1][j].isGrayColor())
                                    grains[x - 1][j].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[i][y - 1].isGrayColor())
                                    grains[i][y - 1].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[i][j + 1].isGrayColor())
                                    grains[i][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[x - 1][j + 1].isGrayColor())
                                    grains[x - 1][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[x - 1][y - 1].isGrayColor())
                                    grains[x - 1][y - 1].changeGrainColor(grains[i][j].getGrainColor());
                            } else if (j == y - 1) {
                                if (prevGrains[x - 1][j].isGrayColor())
                                    grains[x - 1][j].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[i][j - 1].isGrayColor())
                                    grains[i][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[i][0].isGrayColor())
                                    grains[i][0].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[x - 1][0].isGrayColor())
                                    grains[x - 1][0].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[x - 1][j - 1].isGrayColor())
                                    grains[x - 1][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                            } else {
                                if (prevGrains[x - 1][j].isGrayColor())
                                    grains[x - 1][j].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[i][j - 1].isGrayColor())
                                    grains[i][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[i][j + 1].isGrayColor())
                                    grains[i][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[x - 1][j + 1].isGrayColor())
                                    grains[x - 1][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[x - 1][j - 1].isGrayColor())
                                    grains[x - 1][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                            }

                        } else if (i == x - 1) {
                            if (j == 0) {
                                if (prevGrains[i - 1][j].isGrayColor())
                                    grains[i - 1][j].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[i][y - 1].isGrayColor())
                                    grains[i][y - 1].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[i][j + 1].isGrayColor())
                                    grains[i][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[i - 1][j + 1].isGrayColor())
                                    grains[i - 1][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[i - 1][y - 1].isGrayColor())
                                    grains[i - 1][y - 1].changeGrainColor(grains[i][j].getGrainColor());
                            } else if (j == y - 1) {
                                if (prevGrains[i - 1][j].isGrayColor())
                                    grains[i - 1][j].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[i][j - 1].isGrayColor())
                                    grains[i][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[i][0].isGrayColor())
                                    grains[i][0].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[i - 1][0].isGrayColor())
                                    grains[i - 1][0].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[i - 1][j - 1].isGrayColor())
                                    grains[i - 1][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                            } else {
                                if (prevGrains[i - 1][j].isGrayColor())
                                    grains[i - 1][j].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[i][j - 1].isGrayColor())
                                    grains[i][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[i][j + 1].isGrayColor())
                                    grains[i][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[i - 1][j + 1].isGrayColor())
                                    grains[i - 1][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[i - 1][j - 1].isGrayColor())
                                    grains[i - 1][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                            }

                        } else if (j == 0) {
                            if (prevGrains[i - 1][j].isGrayColor())
                                grains[i - 1][j].changeGrainColor(grains[i][j].getGrainColor());
                            if (prevGrains[i][y - 1].isGrayColor())
                                grains[i][y - 1].changeGrainColor(grains[i][j].getGrainColor());
                            if (prevGrains[i][j + 1].isGrayColor())
                                grains[i][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                            if (prevGrains[i - 1][j + 1].isGrayColor())
                                grains[i - 1][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                            if (prevGrains[i - 1][y - 1].isGrayColor())
                                grains[i - 1][y - 1].changeGrainColor(grains[i][j].getGrainColor());
                        } else if (j == y - 1) {
                            if (prevGrains[i - 1][j].isGrayColor())
                                grains[i - 1][j].changeGrainColor(grains[i][j].getGrainColor());
                            if (prevGrains[i][j - 1].isGrayColor())
                                grains[i][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                            if (prevGrains[i][0].isGrayColor())
                                grains[i][0].changeGrainColor(grains[i][j].getGrainColor());
                            if (prevGrains[i - 1][0].isGrayColor())
                                grains[i - 1][0].changeGrainColor(grains[i][j].getGrainColor());
                            if (prevGrains[i - 1][j - 1].isGrayColor())
                                grains[i - 1][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                        }


                    } else {//notperiodic
                        if (i == 0) {
                            if (j == 0) {
                                if (prevGrains[i][j + 1].isGrayColor())
                                    grains[i][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                            } else if (j == y - 1) {
                                if (prevGrains[i][j - 1].isGrayColor())
                                    grains[i][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                            } else {
                                if (prevGrains[i][j - 1].isGrayColor())
                                    grains[i][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[i][j + 1].isGrayColor())
                                    grains[i][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                            }

                        } else if (i == x - 1) {
                            if (j == 0) {

                                if (prevGrains[i - 1][j].isGrayColor())
                                    grains[i - 1][j].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[i][j + 1].isGrayColor())
                                    grains[i][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                            } else if (j == y - 1) {

                                if (prevGrains[i - 1][j].isGrayColor())
                                    grains[i - 1][j].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[i][j - 1].isGrayColor())
                                    grains[i][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[i - 1][j - 1].isGrayColor())
                                    grains[i - 1][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                            } else {
                                if (prevGrains[i - 1][j].isGrayColor())
                                    grains[i - 1][j].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[i][j - 1].isGrayColor())
                                    grains[i][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[i][j + 1].isGrayColor())
                                    grains[i][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[i - 1][j + 1].isGrayColor())
                                    grains[i - 1][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                                if (prevGrains[i - 1][j - 1].isGrayColor())
                                    grains[i - 1][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                            }


                        }

                    }
                } else {
                    if (prevGrains[i - 1][j].isGrayColor())
                        grains[i - 1][j].changeGrainColor(grains[i][j].getGrainColor());
                    if (prevGrains[i][j - 1].isGrayColor())
                        grains[i][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                    if (prevGrains[i][j + 1].isGrayColor())
                        grains[i][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                    if (prevGrains[i - 1][j + 1].isGrayColor())
                        grains[i - 1][j + 1].changeGrainColor(grains[i][j].getGrainColor());
                    if (prevGrains[i - 1][j - 1].isGrayColor())
                        grains[i - 1][j - 1].changeGrainColor(grains[i][j].getGrainColor());
                }


                break;
        }
    }


}
