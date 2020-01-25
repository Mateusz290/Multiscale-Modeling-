package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;

import javax.imageio.ImageIO;
import java.io.*;

public class Controller {
    String neighborhood;
    String seeding;
    boolean conditions;
    boolean continous;
    int valueR;
    int steps;
    int size;
    int propabilities;
    double scaleVal;
    int seedCount;

    private Algorithm algorithm;

    //Obsługa planszy
    @FXML
    private Button spawner;
    @FXML
    private Button algorithmGrainCurvationButton;
    @FXML
    private Button deleteGrainsButton;
    @FXML
    private Button algorithmSubstractureButton;
    @FXML
    private Button algorithmSubstractureButtonByOneStep;
    @FXML
    private TextField inputSize;
    @FXML
    private TextField scaleInput;
    @FXML
    private TextField numberInclusion;
    @FXML
    private ListView grainsToDelete = new ListView();

    @FXML
    private Button generate;

    @FXML
    private TextField minRadiusOfInclusions;

    @FXML
    private TextField maxRadiusOfInclusions;

    @FXML
    private VBox layout;

    @FXML
    private TextField stepsVal;

    @FXML
    private TextField probabilityRule4;


    @FXML
    ComboBox<String> comboBox;

    @FXML
    ComboBox<String>  comboBox2;

    @FXML
    ComboBox<String>  comboBox3;

    @FXML
    ComboBox<String> grainCurvatureBox;

    @FXML
    protected AnchorPane output;


    @FXML
    private TextField inputSeedVal;



    ObservableList<String> neighboorhoods = FXCollections.observableArrayList("VonNeumann", "Moore",
            "HexagonalRight" ,"HexagonalLeft", "HexagonalRandom", "PentagonalLeft", "PentagonalRight", "PentagonalRandom"
    );

    ObservableList<String> boundaries = FXCollections.observableArrayList("Periodic", "NonPeriodic"
    );

    ObservableList<String> options = FXCollections.observableArrayList("Yes", "No"
    );

    ObservableList<Paint> grains;


    /**
     * start up
     */
    public Controller(){

    }

    @FXML
    private void initialize(){
        comboBox.setValue("VonNeumann");
        comboBox2.setValue("Periodic");
        grainCurvatureBox.setValue("No");
        comboBox3.setValue("No");
        deleteGrainsButton.setDisable(true);
        algorithmSubstractureButton.setDisable(true);
        algorithmGrainCurvationButton.setDisable(true);
        algorithmSubstractureButtonByOneStep.setDisable(true);

        neighborhood = comboBox.getValue();
        comboBox.setItems(neighboorhoods);
        comboBox2.setItems(boundaries);
        comboBox3.setItems(options);
        grainCurvatureBox.setItems(options);


        seeding="Random";
        conditions=true;
        continous=false;
        valueR=0;
    }


    @FXML
    private void setAlgorithmGrainCurvature() {
        if (comboBox3.getValue().equals("Yes")) {

        }

    }

    @FXML
    private void handleOperation() {

        switch (comboBox.getValue()) {
            case "VonNeumann":
                neighborhood="VonNeumann";
                break;
            case "Moore":
                neighborhood="Moore";
                break;
            case "HexagonalLeft":
                neighborhood="HexagonalLeft";
                break;
            case "HexagonalRight":
                neighborhood="HexagonalRight";
                break;
            case "PentagonalRight":
                neighborhood = "PentagonalRight";
                break;
            case "PentagonalLeft":
                neighborhood = "PentagonalLeft";
            case "PentagonalRandom":
                neighborhood="PentagonalRandom";
                break;

        }

        if (grainCurvatureBox.getValue().equals("Yes") && comboBox.getValue().equals("Moore")) {
            comboBox2.setDisable(true);
        } else {
            comboBox2.setDisable(false);
        }

    }


    @FXML
    private void algorithmCurvature() {

        propabilities = Integer.parseInt(probabilityRule4.getText());
        algorithm.algorithmCurvatureStill(propabilities);
//        output.getChildren().clear();
//        output.getChildren().add(algorithm.generate());

    }


    @FXML
    private void deleteSubstructure() {

        algorithmSubstractureButton.setDisable(false);
        algorithmSubstractureButtonByOneStep.setDisable(false);
        algorithm.subsctructures(grains,comboBox3.getValue());
        output.getChildren().clear();
        output.getChildren().add(algorithm.generate());


    }

    @FXML
    private void generateInclusions() {

        System.out.println("Inclusions");
        int inclusionsNumber,minRadius,maxRadius;

        try {

            inclusionsNumber = Integer.parseInt(numberInclusion.getText());
            minRadius = Integer.parseInt(minRadiusOfInclusions.getText());
            maxRadius = Integer.parseInt(maxRadiusOfInclusions.getText());

            //algorithm.generateInclusions(inclusionsNumber,minRadius,maxRadius);
            algorithm.random(inclusionsNumber, minRadius, maxRadius);

            output.getChildren().clear();
            output.getChildren().add(algorithm.generate());

        } catch(Exception e) {
            System.out.println("Blad format!");
        }



    }

    @FXML
    private void handlePeriodic() {
        switch (comboBox2.getValue()) {
            case "Periodic":
                this.conditions=true;
                break;
            case "NonPeriodic":
                this.conditions=false;
                break;
        }
    }

    @FXML
    private void handleSeedCount(){
        seedCount=Integer.parseInt(inputSeedVal.getText());
    }


    @FXML
    private void handleGoPauseByStep(){

        steps=Integer.parseInt(stepsVal.getText());
        algorithm.run(steps);

    }

    @FXML
    private void handleGoPause(){

//        steps=Integer.parseInt(stepsVal.getText());
        algorithm.run();

    }

    @FXML
    private void createListViewForGrains(){

        Button button = new Button("Submit");
        grainsToDelete.getItems().clear();

        grainsToDelete.getItems().addAll(algorithm.addGrainsToDelete());
        grainsToDelete.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        button.setOnAction(e -> buttonClicked());

        layout = new VBox(10);
        layout.setPadding(new Insets(20,20,20,20));
        layout.getChildren().addAll(grainsToDelete, button);

        output.getChildren().add(layout);


    }

    public void buttonClicked() {
        System.out.println("buttonSubmit");
        grains =  grainsToDelete.getSelectionModel().getSelectedItems();

        if (grains.size()!=0) {
            deleteGrainsButton.setDisable(false);
        } else {
            deleteGrainsButton.setDisable(true);
        }

        for (Paint paint: grains) {
            System.out.print(paint + "  ");
        }

        output.getChildren().remove(layout);

    }



    @FXML
    private void handleGoPauseSubtractures(){

//        steps=Integer.parseInt(stepsVal.getText());
        algorithm.runSubtractures();

    }

    @FXML
    private void handleGoPauseSubtracturesByOneStep() {
        algorithm.runSubtracturesByOneStep();
    }


    @FXML
    private void pauseRun() {
        algorithm.pauseRun();

    }

    @FXML
    private void curvatureConditions() {
        System.out.println("Hello");

        if (grainCurvatureBox.getValue().equals("Yes")) {
            algorithmGrainCurvationButton.setDisable(false);
        } else {
            algorithmGrainCurvationButton.setDisable(true);
        }

        if (grainCurvatureBox.getValue().equals("Yes") && comboBox.getValue().equals("Moore")) {
            comboBox2.setDisable(true);
        } else {
            comboBox2.setDisable(false);
        }
    }

    @FXML
    private void handleGenerate(){
        size=Integer.parseInt(inputSize.getText());
        algorithm =new Algorithm(size,size, this);
        output.getChildren().add(algorithm.generate());
    }

    @FXML
    private void handleSpawn(){
        seedCount=Integer.parseInt(inputSeedVal.getText());
        algorithm.seed();
        output.getChildren().clear();
        output.getChildren().add(algorithm.generate());
    }
    @FXML
    private void handleSpawnTest(){
        seedCount=Integer.parseInt(inputSeedVal.getText());
        algorithm.seedTest();
        output.getChildren().clear();
        output.getChildren().add(algorithm.generate());
    }

    @FXML
    private void drawGrainsBoundaries(){
        System.out.println("drawGraingBoundaries");


        int length= algorithm.generateWithBoundaries();

        System.out.println("Dlugosc granicy: = " + length);


    }

    @FXML
    private void getGrainsSize(){
        System.out.println("getGrainsSize");
        PrintWriter fileWriter1 = null;

        File grainsSize = new File("GrainsSize.txt");


        try {
            fileWriter1 = new PrintWriter(grainsSize);

            algorithm.saveToGrainSizeToFile(fileWriter1, algorithm.getGrainsSize());

        } catch (IOException e) {
            System.out.println("Blad przy zapisie do pliku!");
        } finally {
            if (fileWriter1 != null) {
                fileWriter1.close();
            }

        }

    }

    @FXML
    private void getGrainsSizeTotal(){
        PrintWriter fileWriter1 = null;
        File grainsMediumSize = new File("GrainsMediumSize.txt");


        try {
            fileWriter1 = new PrintWriter(grainsMediumSize);

            algorithm.saveToMediumGrainSizeToFile(fileWriter1, algorithm.getGrainsMediumSize());

        } catch (IOException e) {
            System.out.println("Blad przy zapisie do pliku!");
        } finally {
            if (fileWriter1 != null) {
                fileWriter1.close();
            }

        }

        System.out.println("getGrainsSizeTotal");

    }

    @FXML
    public void saveAsPng() {
        WritableImage image = output.snapshot(new SnapshotParameters(), null);

        // TOD: probably use a file chooser here
        File file = new File("SeedGenerator_last.png");
        File colorGrains = new File("ImageColorGrains.txt");
        File inclusionsGrains = new File("InclusionsGrains.txt");
        PrintWriter fileWriter1 = null;
        PrintWriter fileWriter2 = null;

        try {
            fileWriter1 = new PrintWriter(colorGrains);
            fileWriter2 = new PrintWriter(inclusionsGrains);

            algorithm.saveToFile(fileWriter1, fileWriter2);

            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);


        } catch (IOException e) {
            System.out.println("Blad przy eksporcie!");
        } finally {
            if (fileWriter1 != null) {
                fileWriter1.close();
            }
            if (fileWriter2 != null) {
                fileWriter2.close();
            }
        }
    }

    @FXML
    public void importImage() throws FileNotFoundException {
        System.out.println("Import pliku");

        algorithm.importFile();
        output.getChildren().clear();
        output.getChildren().add(algorithm.generate());

//        Image image = null;
//
//        image = new Image(new FileInputStream("SeedGenerator_last.png"));
//
//
//        if (image==null) System.out.println("Pusty");
//        else System.out.println("Zajety");
//
//        output.getChildren().add(new ImageView(image));

    }


}
