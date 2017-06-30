package com.testdash;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.text.html.Option;
import java.awt.event.KeyEvent;
import java.util.ArrayList;


/**
 * Created by wuping on 6/26/2017.
 */
public class test extends Application{

    static RedisHandling redis;
    static OptionFilteringService optionFilteringService;

    @Override
    public void start(Stage primaryStage) throws Exception {


        //define the content;
        Label factoryLabel = new Label("Factory");
        ComboBox factoryComboBox = new ComboBox();

        Label customerLabel = new Label("Customer");
        ComboBox customerComboBox = new ComboBox();

        Label productLabel = new Label("Product");
        ComboBox productComboBox = new ComboBox();

        Label packageLabel = new Label("Package");
        ComboBox packageComboBox = new ComboBox();

        Label deviceLabel = new Label("Device");
        ComboBox deviceComboBox = new ComboBox();

        Label partnoLabel = new Label("Partno");
        ComboBox partnoComboBox = new ComboBox();

        Label stageLabel = new Label("Stage");
        ComboBox stageComboBox = new ComboBox();

        Label operationLabel = new Label("Operation");
        ComboBox operationComboBox = new ComboBox();

        Label testCodeLabel = new Label("Testcode");
        ComboBox testCodeComboBox = new ComboBox();

        Label testProgramLabel = new Label("TestProgram");
        ComboBox testProgramComboBox = new ComboBox();

        Label programRevisionLabel = new Label("ProgramRevision");
        ComboBox programRevisionComboBox = new ComboBox();

        Label camTypeLabel = new Label("CamType");
        ComboBox camTypeComboBox = new ComboBox();

        Label platformLabel = new Label("Platform");
        ComboBox platformComboBox = new ComboBox();

        Button btn=new Button("Submit");


        //add the contents into the grid pan
        GridPane grid = new GridPane();

        grid.add(factoryLabel,0,0);
        grid.add(factoryComboBox,1,0);

        grid.add(customerLabel,0,1);
        grid.add(customerComboBox,1,1);

        grid.add(productLabel,0,2);
        grid.add(productComboBox,1,2);

        grid.add(packageLabel,0,3);
        grid.add(packageComboBox,1,3);

        grid.add(deviceLabel,0,4);
        grid.add(deviceComboBox,1,4);

        grid.add(partnoLabel,0,5);
        grid.add(partnoComboBox,1,5);

        grid.add(stageLabel,0,6);
        grid.add(stageComboBox,1,6);

        grid.add(operationLabel,0,7);
        grid.add(operationComboBox,1,7);

        grid.add(testCodeLabel,0,8);
        grid.add(testCodeComboBox,1,8);

        grid.add(testProgramLabel,0,9);
        grid.add(testProgramComboBox,1,9);

        grid.add(programRevisionLabel,0,10);
        grid.add(programRevisionComboBox,1,10);

        grid.add(camTypeLabel,0,11);
        grid.add(camTypeComboBox,1,11);

        grid.add(platformLabel,0,12);
        grid.add(platformComboBox,1,12);

        grid.add(btn, 1,13);


        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Bang");
            }
        });


       factoryComboBox.setOnShowing((e) -> {
           factoryComboBox.getItems().removeAll();
           factoryComboBox.getItems().clear();
           ArrayList result = optionFilteringService.getMenu("Factory");

           for (int i = 0; i < result.size(); i++) {
               factoryComboBox.getItems().add(result.get(i));

           }
           //System.out.println(factoryComboBox.getSelectionModel().getSelectedItem());
       });


       factoryComboBox.setOnAction((e) -> {

            System.out.println(factoryComboBox.getSelectionModel().getSelectedItem());
        });

        //put the grid pan into a Vbox
        VBox root = new VBox();
        root.getChildren().add(grid);
        Scene scene = new Scene(root, 400, 600);
        primaryStage.setTitle("Option Filtering");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args){
        String redisServer = "redis://TDnI123!@aurora:6379";
        redis= new RedisHandling(redisServer);
        redis.redisConnect();
        optionFilteringService = new OptionFilteringService();
        optionFilteringService.userCreation();
        launch(args);
        redis.redisClose();
    }
}
