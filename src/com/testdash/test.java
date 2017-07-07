package com.testdash;


import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

import org.controlsfx.control.CheckComboBox;

public class test extends Application {

    private static RedisHandling redis;
    private static OptionFilteringService optionFilteringService;


    @Override
    public void start(Stage primaryStage) throws Exception {


        //define the content;
        final Label factoryLabel = new Label("Factory");
        final Label customerLabel = new Label("Customer");
        final Label productLabel = new Label("Product");
        final Label packageLabel = new Label("Package");
        final Label deviceLabel = new Label("Device");
        final Label operationLabel = new Label("Operation");
        final Label stageLabel = new Label("Stage");
        final Label testProgramLabel = new Label("Test Program");
        final Label programRevisionLabel = new Label("Program Revision");
        final Label testCodeLabel = new Label("Test Code");
        final Label camTypeLabel = new Label("CamType");


        final CheckComboBox<String> factoryComboBox = new CheckComboBox<>();
        final CheckComboBox<String> customerComboBox = new CheckComboBox<>();
        final CheckComboBox<String> productComboBox = new CheckComboBox<>();
        final CheckComboBox<String> packageComboBox = new CheckComboBox<>();
        final CheckComboBox<String> deviceComboBox = new CheckComboBox<>();
        final CheckComboBox<String> operationComboBox = new CheckComboBox<>();
        final CheckComboBox<String> stageComboBox = new CheckComboBox<>();
        final CheckComboBox<String> testProgramComboBox = new CheckComboBox<>();
        final CheckComboBox<String> programRevisionComboBox = new CheckComboBox<>();
        final CheckComboBox<String> testCodeComboBox = new CheckComboBox<>();
        final CheckComboBox<String> camTypeComboBox = new CheckComboBox<>();

        final Button factoryBtn = new Button("set");
        final Button customerBtn = new Button("set");
        final Button productBtn = new Button("set");
        final Button packageBtn = new Button("set");
        final Button deviceBtn = new Button("set");
        final Button operationBtn = new Button("set");
        final Button stageBtn = new Button("set");
        final Button testProgramBtn = new Button("set");
        final Button programRevisionBtn = new Button("set");
        final Button testCodeBtn = new Button("set");
        final Button camTypeBtn = new Button("set");

        final Button btn = new Button("Submit");


        final TableView table = new TableView ();



        //add the contents into the grid pan
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);

        grid.setHgap(40);
        grid.setVgap(5);
        grid.setPadding(new Insets(30, 0, 0, 0));

        grid.add(factoryLabel, 0, 0);
        grid.add(customerLabel, 0, 1);
        grid.add(productLabel, 0, 2);
        grid.add(packageLabel, 0, 3);
        grid.add(deviceLabel, 0, 4);
        grid.add(operationLabel, 0, 5);
        grid.add(stageLabel, 0, 6);
        grid.add(testProgramLabel, 0, 7);
        grid.add(programRevisionLabel, 0, 8);
        grid.add(testCodeLabel, 0, 9);
        grid.add(camTypeLabel, 0, 10);

        grid.add(factoryComboBox, 1, 0);
        grid.add(customerComboBox, 1, 1);
        grid.add(productComboBox, 1, 2);
        grid.add(packageComboBox, 1, 3);
        grid.add(deviceComboBox, 1, 4);
        grid.add(operationComboBox, 1, 5);
        grid.add(stageComboBox, 1, 6);
        grid.add(testProgramComboBox, 1, 7);
        grid.add(programRevisionComboBox, 1, 8);
        grid.add(testCodeComboBox, 1, 9);
        grid.add(camTypeComboBox, 1, 10);

        grid.add(factoryBtn, 2, 0);
        grid.add(customerBtn, 2, 1);
        grid.add(productBtn, 2, 2);
        grid.add(packageBtn, 2, 3);
        grid.add(deviceBtn, 2, 4);
        grid.add(operationBtn, 2, 5);
        grid.add(stageBtn, 2, 6);
        grid.add(testProgramBtn, 2, 7);
        grid.add(programRevisionBtn, 2, 8);
        grid.add(testCodeBtn, 2, 9);
        grid.add(camTypeBtn, 2, 10);


        grid.add(btn, 1, 13);
        btn.setAlignment(Pos.BOTTOM_RIGHT);
        btn.setOnAction(event -> System.out.println("Bang"));


        factoryComboBox.setOnMouseEntered(event -> listMenu(factoryComboBox, 0));
        customerComboBox.setOnMouseEntered(event -> listMenu(customerComboBox, 1));
        productComboBox.setOnMouseEntered(event -> listMenu(productComboBox, 2));
        packageComboBox.setOnMouseEntered(event -> listMenu(packageComboBox, 3));
        deviceComboBox.setOnMouseEntered(event -> listMenu(deviceComboBox, 4));
        operationComboBox.setOnMouseEntered(event -> listMenu(operationComboBox, 5));
        stageComboBox.setOnMouseEntered(event -> listMenu(stageComboBox, 6));
        testProgramComboBox.setOnMouseEntered(event -> listMenu(testProgramComboBox, 7));
        programRevisionComboBox.setOnMouseEntered(event -> listMenu(programRevisionComboBox, 8));
        testCodeComboBox.setOnMouseEntered(event -> listMenu(testCodeComboBox, 9));
        camTypeComboBox.setOnMouseEntered(event -> listMenu(camTypeComboBox, 10));

        factoryBtn.setOnAction(e -> btnPressed(factoryComboBox, 0));
        customerBtn.setOnAction(e -> btnPressed(customerComboBox, 1));
        productBtn.setOnAction(e -> btnPressed(productComboBox, 2));
        packageBtn.setOnAction(e -> btnPressed(packageComboBox, 3));
        deviceBtn.setOnAction(e -> btnPressed(deviceComboBox, 4));
        operationBtn.setOnAction(e -> btnPressed(operationComboBox, 5));
        stageBtn.setOnAction(e -> btnPressed(stageComboBox, 6));
        testProgramBtn.setOnAction(e -> btnPressed(testProgramComboBox, 7));
        programRevisionBtn.setOnAction(e -> btnPressed(programRevisionComboBox, 8));
        testCodeBtn.setOnAction(e -> btnPressed(testCodeComboBox, 9));
        camTypeBtn.setOnAction(e -> btnPressed(camTypeComboBox, 10));



        TableColumn firstNameCol = new TableColumn("Factory");
        TableColumn lastNameCol = new TableColumn("Product");


        table.getColumns ().addAll (firstNameCol,lastNameCol);
        //firstNameCol.


        //put the grid pan into a BorderPane
        BorderPane border = new BorderPane();
        border.setRight (grid);
        border.setCenter (table);

        Scene scene = new Scene(border, 1600, 1000);
        primaryStage.setTitle("Option Filtering");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        //String redisServer = "redis://TDnI123!@aurora:6379";
        String redisServer = "redis://localhost:6379";
        redis = new RedisHandling(redisServer);
        redis.redisConnect();
        optionFilteringService = new OptionFilteringService();
        optionFilteringService.userCreation();
        launch(args);
        optionFilteringService.userDel();
        redis.redisClose();
    }

    private void listMenu(CheckComboBox<String> comboBox, int index) {

        ArrayList<String> result;
        result = optionFilteringService.getMenu(index);
        comboBox.getItems().clear();
        comboBox.getCheckModel().clearChecks();
        for (String x : result) {
            if (!x.isEmpty()) {
                comboBox.getItems().add(x);
                //System.out.println("the available options are: " + x);

            }
        }
        if (optionFilteringService.getListOn(index)) {
            for (int i = 0; i < result.size(); i++) {
                if (!result.get(i).isEmpty()) {
                    comboBox.getCheckModel().check(i);
//                            System.out.println(result.get(i) + " was checked");

                }
            }

        }
    }

    private void btnPressed(CheckComboBox<String> comboBox, int index) {
        ObservableList<String> oListInput;
        oListInput = comboBox.getCheckModel().getCheckedItems();
            for (String s:oListInput){System.out.println(s);}
        optionFilteringService.selectionHandle(index, oListInput);
        optionFilteringService.resultInter();
    }


}