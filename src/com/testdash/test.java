package com.testdash;


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.controlsfx.control.CheckComboBox;

public class test extends Application {

    private static RedisHandling redis;
    private static OptionFilteringService optionFilteringService;
    boolean abc=false;

    @Override
    public void start(Stage primaryStage) throws Exception {

        int comboWidth = 150;

        //define the content;
        Label factoryLabel = new Label ("Factory");
        //ComboBox factoryComboBox = new ComboBox();
        final CheckComboBox<String> factoryComboBox = new CheckComboBox<> ();
        //factoryComboBox.setPrefWidth (comboWidth);

        Label customerLabel = new Label ("Customer");
        final CheckComboBox<String> customerComboBox = new CheckComboBox<> ();
        customerComboBox.setPrefWidth (comboWidth);

        Label productLabel = new Label ("Product");
        //ComboBox productComboBox = new ComboBox ();
        final CheckComboBox<String> productComboBox = new CheckComboBox<> ();
        productComboBox.setPrefWidth (comboWidth);

        Label packageLabel = new Label ("Package");
        //ComboBox packageComboBox = new ComboBox ();
        final CheckComboBox<String> packageComboBox = new CheckComboBox<> ();
        packageComboBox.setPrefWidth (comboWidth);

        Label deviceLabel = new Label ("Device");
        //ComboBox deviceComboBox = new ComboBox ();
        final CheckComboBox<String> deviceComboBox = new CheckComboBox<> ();
        deviceComboBox.setPrefWidth (comboWidth);

/*        Label partnoLabel = new Label ("Partno");
        //ComboBox partnoComboBox = new ComboBox ();
        final CheckComboBox<String> partnoComboBox = new CheckComboBox<> ();
        partnoComboBox.setPrefWidth (comboWidth);*/

        Label stageLabel = new Label ("Stage");
        //ComboBox stageComboBox = new ComboBox ();
        final CheckComboBox<String> stageComboBox = new CheckComboBox<> ();
        stageComboBox.setPrefWidth (comboWidth);

        Label operationLabel = new Label ("Operation");
        //ComboBox operationComboBox = new ComboBox ();
        final CheckComboBox<String> operationComboBox = new CheckComboBox<> ();
        operationComboBox.setPrefWidth (comboWidth);

        Label testCodeLabel = new Label ("Testcode");
        //ComboBox testCodeComboBox = new ComboBox ();
        final CheckComboBox<String> testCodeComboBox = new CheckComboBox<> ();
        testCodeComboBox.setPrefWidth (comboWidth);

        Label testProgramLabel = new Label ("TestProgram");
        //ComboBox testProgramComboBox = new ComboBox ();
        final CheckComboBox<String> testProgramComboBox = new CheckComboBox<> ();
        testProgramComboBox.setPrefWidth (comboWidth);

        Label programRevisionLabel = new Label ("ProgramRevision");
        //ComboBox programRevisionComboBox = new ComboBox ();
        final CheckComboBox<String> programRevisionComboBox = new CheckComboBox<> ();
        programRevisionComboBox.setPrefWidth (comboWidth);

        Label camTypeLabel = new Label ("CamType");
        //ComboBox camTypeComboBox = new ComboBox ();
        final CheckComboBox<String> camTypeComboBox = new CheckComboBox<> ();
        camTypeComboBox.setPrefWidth (comboWidth);

/*        Label platformLabel = new Label ("Platform");
        //ComboBox platformComboBox = new ComboBox ();
        final CheckComboBox<String> platformComboBox = new CheckComboBox<> ();
        platformComboBox.setPrefWidth (comboWidth);*/

        Button btn = new Button ("Submit");
        Button factoryBtn = new Button ("set");
        Button customerBtn = new Button("set");


        //add the contents into the grid pan
        GridPane grid = new GridPane ();
        grid.setAlignment (Pos.CENTER);

        grid.setHgap (40);
        grid.setVgap (5);
        grid.setPadding (new Insets (30, 0, 0, 0));

        grid.add (factoryLabel, 0, 0);
        grid.add (factoryComboBox, 1, 0);
        grid.add(factoryBtn,2,0);


        grid.add (customerLabel, 0, 1);
        grid.add (customerComboBox, 1, 1);
        grid.add(customerBtn,2,0);

        grid.add (productLabel, 0, 2);
        grid.add (productComboBox, 1, 2);

        grid.add (packageLabel, 0, 3);
        grid.add (packageComboBox, 1, 3);

        grid.add (deviceLabel, 0, 4);
        grid.add (deviceComboBox, 1, 4);

/*        grid.add (partnoLabel, 0, 5);
        grid.add (partnoComboBox, 1, 5);*/

        grid.add (stageLabel, 0, 6);
        grid.add (stageComboBox, 1, 6);

        grid.add (operationLabel, 0, 7);
        grid.add (operationComboBox, 1, 7);

        grid.add (testCodeLabel, 0, 8);
        grid.add (testCodeComboBox, 1, 8);

        grid.add (testProgramLabel, 0, 9);
        grid.add (testProgramComboBox, 1, 9);

        grid.add (programRevisionLabel, 0, 10);
        grid.add (programRevisionComboBox, 1, 10);

        grid.add (camTypeLabel, 0, 11);
        grid.add (camTypeComboBox, 1, 11);

/*        grid.add (platformLabel, 0, 12);
        grid.add (platformComboBox, 1, 12);*/


        grid.add (btn, 1, 13);
        btn.setAlignment (Pos.BOTTOM_RIGHT);
        btn.setOnAction (event -> System.out.println ("Bang"));



        factoryComboBox.setOnMouseEntered (event -> {
            ArrayList<String> result;
            result = optionFilteringService.getMenu (2);
            factoryComboBox.getItems ().clear();
            factoryComboBox.getCheckModel().clearChecks();
            for(String x:result){
                if(x.isEmpty()){}
                else {
                    factoryComboBox.getItems().add(x);
                    System.out.println("the available options are: " + x);
                }
            }
            if(optionFilteringService.getListOn(0)){
                for(int i=0;i<result.size();i++)
                factoryComboBox.getCheckModel().check(i);}

        });

        factoryComboBox.getCheckModel ().getCheckedItems ().addListener ((ListChangeListener<String>) c -> {
            ObservableList<String> oListInput;
            oListInput = factoryComboBox.getCheckModel ().getCheckedItems ();
            for (String s:oListInput){System.out.println(s);}
        });

        factoryBtn.setOnAction(e -> {
            ObservableList<String> oListInput;
            oListInput = factoryComboBox.getCheckModel ().getCheckedItems ();
            for (String s:oListInput){System.out.println(s);}
            optionFilteringService.selectionHandle (0, oListInput);
            optionFilteringService.resultInter (0);
        });

//        customerComboBox.setOnMouseEntered (event -> {
//            ArrayList<String> result;
//            result = optionFilteringService.getMenu (1);
//            customerComboBox.getItems ().clear();
//            customerComboBox.getCheckModel().clearChecks();
//
//
//            //factoryComboBox.getItems ().add("Select None");
//
//            for(String x:result){
//                if(x.isEmpty()){}
//                else {
//                    customerComboBox.getItems().add(x);
//                    //System.out.println("the available options are: " + x);
//                }
//            }
////            if(optionFilteringService.getListOn(1)){
////                for(int i=0;i<result.size()+1;i++)
////                    customerComboBox.getCheckModel().check(i);
////            }
//
//        });

//        customerComboBox.getCheckModel ().getCheckedItems ().addListener ((ListChangeListener<String>) c -> {
//            ObservableList<String> oListInput;
//            oListInput = customerComboBox.getCheckModel ().getCheckedItems ();
//            for (String s:oListInput){System.out.println(s);}
//        });
//
//        customerBtn.setOnAction(e -> {
//            ObservableList<String> oListInput;
//            oListInput = factoryComboBox.getCheckModel ().getCheckedItems ();
//            for (String s:oListInput){System.out.println(s);}
//            optionFilteringService.selectionHandle (1, oListInput);
//            optionFilteringService.resultInter (1);
//        });


/*

        customerComboBox.setOnMouseEntered (event -> {
            ArrayList<String> result;
            customerComboBox.getItems ().remove (0,customerComboBox.getItems ().size ());
            customerComboBox.getItems ().add("Select None");
            result = optionFilteringService.getMenu (1);
            for(String x:result){
                customerComboBox.getItems ().add(x);
            }
        });

        customerComboBox.getCheckModel ().getCheckedItems ().addListener ((ListChangeListener<String>) c -> {
            String selectResult = customerComboBox.getCheckModel ().getCheckedItems ().toString ();
            System.out.println(customerComboBox.getCheckModel ().getCheckedIndices ());
            optionFilteringService.selecionHandle (1, selectResult);
            optionFilteringService.resultInter (1);
        });

        productComboBox.setOnMouseEntered (event -> {
            ArrayList<String> result;
            productComboBox.getItems ().remove (0,productComboBox.getItems ().size ());
            productComboBox.getItems ().add("Select None");
            result = optionFilteringService.getMenu (2);
            for(String x:result){
                productComboBox.getItems ().add(x);
            }
        });

        productComboBox.getCheckModel ().getCheckedItems ().addListener ((ListChangeListener<String>) c -> {
            String selectResult = productComboBox.getCheckModel ().getCheckedItems ().toString ();
            System.out.println(productComboBox.getCheckModel ().getCheckedIndices ());
            optionFilteringService.selecionHandle (2, selectResult);
            optionFilteringService.resultInter (2);
        });

        packageComboBox.setOnMouseEntered (event -> {
            ArrayList<String> result;
            packageComboBox.getItems ().remove (0,packageComboBox.getItems ().size ());
            packageComboBox.getItems ().add("Select None");
            result = optionFilteringService.getMenu (3);
            for(String x:result){
                packageComboBox.getItems ().add(x);
            }
        });

        packageComboBox.getCheckModel ().getCheckedItems ().addListener ((ListChangeListener<String>) c -> {
            String selectResult = packageComboBox.getCheckModel ().getCheckedItems ().toString ();
            System.out.println(packageComboBox.getCheckModel ().getCheckedIndices ());
            optionFilteringService.selecionHandle (3, selectResult);
            optionFilteringService.resultInter (3);
        });


        deviceComboBox.setOnMouseEntered (event -> {
            ArrayList<String> result;
            deviceComboBox.getItems ().remove (0,deviceComboBox.getItems ().size ());
            deviceComboBox.getItems ().add("Select None");
            result = optionFilteringService.getMenu (4);
            for(String x:result){
                deviceComboBox.getItems ().add(x);
            }
        });

        deviceComboBox.getCheckModel ().getCheckedItems ().addListener ((ListChangeListener<String>) c -> {
            String selectResult = deviceComboBox.getCheckModel ().getCheckedItems ().toString ();
            System.out.println(deviceComboBox.getCheckModel ().getCheckedIndices ());
            optionFilteringService.selecionHandle (4, selectResult);
            optionFilteringService.resultInter (4);
        });

        */
/*partnoComboBox.setOnMouseEntered (event -> {
            ArrayList<String> result;
            partnoComboBox.getItems ().remove (0,partnoComboBox.getItems ().size ());
            partnoComboBox.getItems ().add("Select None");
            result = optionFilteringService.getMenu (5);
            for(String x:result){
                partnoComboBox.getItems ().add(x);
            }
        });

        partnoComboBox.getCheckModel ().getCheckedItems ().addListener ((ListChangeListener<String>) c -> {
            String selectResult = partnoComboBox.getCheckModel ().getCheckedItems ().toString ();
            System.out.println(partnoComboBox.getCheckModel ().getCheckedIndices ());
            optionFilteringService.selecionHandle (5, selectResult);
            optionFilteringService.resultInter (5);
        });
*//*

        stageComboBox.setOnMouseEntered (event -> {
            ArrayList<String> result;
            stageComboBox.getItems ().remove (0,stageComboBox.getItems ().size ());
            stageComboBox.getItems ().add("Select None");
            result = optionFilteringService.getMenu (6);
            for(String x:result){
                stageComboBox.getItems ().add(x);
            }
        });

        stageComboBox.getCheckModel ().getCheckedItems ().addListener ((ListChangeListener<String>) c -> {
            String selectResult = stageComboBox.getCheckModel ().getCheckedItems ().toString ();
            System.out.println(stageComboBox.getCheckModel ().getCheckedIndices ());
            optionFilteringService.selecionHandle (6, selectResult);
            optionFilteringService.resultInter (6);
        });

        operationComboBox.setOnMouseEntered (event -> {
            ArrayList<String> result;
            operationComboBox.getItems ().remove (0,operationComboBox.getItems ().size ());
            operationComboBox.getItems ().add("Select None");
            result = optionFilteringService.getMenu (7);
            for(String x:result){
                operationComboBox.getItems ().add(x);
            }
        });

        operationComboBox.getCheckModel ().getCheckedItems ().addListener ((ListChangeListener<String>) c -> {
            String selectResult = operationComboBox.getCheckModel ().getCheckedItems ().toString ();
            System.out.println(operationComboBox.getCheckModel ().getCheckedIndices ());
            optionFilteringService.selecionHandle (7, selectResult);
            optionFilteringService.resultInter (7);
        });

        testCodeComboBox.setOnMouseEntered (event -> {
            ArrayList<String> result;
            testCodeComboBox.getItems ().remove (0,testCodeComboBox.getItems ().size ());
            testCodeComboBox.getItems ().add("Select None");
            result = optionFilteringService.getMenu (8);
            for(String x:result){
                testCodeComboBox.getItems ().add(x);
            }
        });

        testCodeComboBox.getCheckModel ().getCheckedItems ().addListener ((ListChangeListener<String>) c -> {
            String selectResult = testCodeComboBox.getCheckModel ().getCheckedItems ().toString ();
            System.out.println(testCodeComboBox.getCheckModel ().getCheckedIndices ());
            optionFilteringService.selecionHandle (8, selectResult);
            optionFilteringService.resultInter (8);
        });

        testProgramComboBox.setOnMouseEntered (event -> {
            ArrayList<String> result;
            testProgramComboBox.getItems ().remove (0,testProgramComboBox.getItems ().size ());
            testProgramComboBox.getItems ().add("Select None");
            result = optionFilteringService.getMenu (9);
            for(String x:result){
                testProgramComboBox.getItems ().add(x);
            }
        });

        testProgramComboBox.getCheckModel ().getCheckedItems ().addListener ((ListChangeListener<String>) c -> {
            String selectResult = testProgramComboBox.getCheckModel ().getCheckedItems ().toString ();
            System.out.println(testProgramComboBox.getCheckModel ().getCheckedIndices ());
            optionFilteringService.selecionHandle (9, selectResult);
            optionFilteringService.resultInter (9);
        });

        programRevisionComboBox.setOnMouseEntered (event -> {
            ArrayList<String> result;
            programRevisionComboBox.getItems ().remove (0,programRevisionComboBox.getItems ().size ());
            programRevisionComboBox.getItems ().add("Select None");
            result = optionFilteringService.getMenu (10);
            for(String x:result){
                programRevisionComboBox.getItems ().add(x);
            }
        });

        programRevisionComboBox.getCheckModel ().getCheckedItems ().addListener ((ListChangeListener<String>) c -> {
            String selectResult = programRevisionComboBox.getCheckModel ().getCheckedItems ().toString ();
            System.out.println(programRevisionComboBox.getCheckModel ().getCheckedIndices ());
            optionFilteringService.selecionHandle (10, selectResult);
            optionFilteringService.resultInter (10);
        });

        camTypeComboBox.setOnMouseEntered (event -> {
            ArrayList<String> result;
            camTypeComboBox.getItems ().remove (0,camTypeComboBox.getItems ().size ());
            camTypeComboBox.getItems ().add("Select None");
            result = optionFilteringService.getMenu (11);
            for(String x:result){
                camTypeComboBox.getItems ().add(x);
            }
        });

        camTypeComboBox.getCheckModel ().getCheckedItems ().addListener ((ListChangeListener<String>) c -> {
            String selectResult = camTypeComboBox.getCheckModel ().getCheckedItems ().toString ();
            System.out.println(camTypeComboBox.getCheckModel ().getCheckedIndices ());
            optionFilteringService.selecionHandle (11, selectResult);
            optionFilteringService.resultInter (11);
        });
*/

/*        platformComboBox.setOnMouseEntered (event -> {
            ArrayList<String> result;
            platformComboBox.getItems ().remove (0,platformComboBox.getItems ().size ());
            platformComboBox.getItems ().add("Select None");
            result = optionFilteringService.getMenu (12);
            for(String x:result){
                platformComboBox.getItems ().add(x);
            }
        });

        platformComboBox.getCheckModel ().getCheckedItems ().addListener ((ListChangeListener<String>) c -> {
            String selectResult = platformComboBox.getCheckModel ().getCheckedItems ().toString ();
            System.out.println(platformComboBox.getCheckModel ().getCheckedIndices ());
            optionFilteringService.selecionHandle (12, selectResult);
            optionFilteringService.resultInter (12);
        });*/


        //put the grid pan into a Vbox
        VBox root = new VBox ();

        root.getChildren ().add (grid);
        Scene scene = new Scene (root, 400, 500);
        primaryStage.setTitle ("Option Filtering");
        primaryStage.setScene (scene);
        primaryStage.show ();
    }

    public static void main(String[] args) {
        String redisServer = "redis://TDnI123!@aurora:6379";
        //String redisServer = "redis://localhost:6379";
        redis = new RedisHandling (redisServer);
        redis.redisConnect ();
        optionFilteringService = new OptionFilteringService ();
        optionFilteringService.userCreation ();
        launch (args);
        optionFilteringService.userDel ();
        redis.redisClose ();
    }
}