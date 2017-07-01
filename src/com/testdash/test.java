package com.testdash;

import impl.org.controlsfx.skin.CheckComboBoxSkin;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Skin;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

import org.controlsfx.ControlsFXSample;
import org.controlsfx.control.CheckComboBox;
import org.controlsfx.control.IndexedCheckModel;
import org.controlsfx.samples.Utils;

/**
 * Created by wuping on 6/26/2017.
 */
public class test extends Application{

    static RedisHandling redis;
    static OptionFilteringService optionFilteringService;
    static boolean initialIndicator =true;

    @Override
    public void start(Stage primaryStage) throws Exception {

        int comboWidth = 150;





        //define the content;
        Label factoryLabel = new Label("Factory");
        //ComboBox factoryComboBox = new ComboBox();
        final CheckComboBox<String> factoryComboBox = new CheckComboBox<String>();
        //factoryComboBox.setPrefWidth (comboWidth);


        Label customerLabel = new Label("Customer");
        final CheckComboBox<String> customerComboBox = new CheckComboBox<String>();
        customerComboBox.setPrefWidth (comboWidth);

        Label productLabel = new Label("Product");
        ComboBox productComboBox = new ComboBox();
        productComboBox.setPrefWidth (comboWidth);

        Label packageLabel = new Label("Package");
        ComboBox packageComboBox = new ComboBox();
        packageComboBox.setPrefWidth (comboWidth);

        Label deviceLabel = new Label("Device");
        ComboBox deviceComboBox = new ComboBox();
        deviceComboBox.setPrefWidth (comboWidth);

        Label partnoLabel = new Label("Partno");
        ComboBox partnoComboBox = new ComboBox();
        partnoComboBox.setPrefWidth (comboWidth);

        Label stageLabel = new Label("Stage");
        ComboBox stageComboBox = new ComboBox();
        stageComboBox.setPrefWidth (comboWidth);

        Label operationLabel = new Label("Operation");
        ComboBox operationComboBox = new ComboBox();
        operationComboBox.setPrefWidth (comboWidth);

        Label testCodeLabel = new Label("Testcode");
        ComboBox testCodeComboBox = new ComboBox();
        testCodeComboBox.setPrefWidth (comboWidth);

        Label testProgramLabel = new Label("TestProgram");
        ComboBox testProgramComboBox = new ComboBox();
        testProgramComboBox.setPrefWidth (comboWidth);

        Label programRevisionLabel = new Label("ProgramRevision");
        ComboBox programRevisionComboBox = new ComboBox();
        programRevisionComboBox.setPrefWidth (comboWidth);

        Label camTypeLabel = new Label("CamType");
        ComboBox camTypeComboBox = new ComboBox();
        camTypeComboBox.setPrefWidth (comboWidth);

        Label platformLabel = new Label("Platform");
        ComboBox platformComboBox = new ComboBox();
        platformComboBox.setPrefWidth (comboWidth);

        Button btn=new Button("Submit");



        //add the contents into the grid pan
        GridPane grid = new GridPane();
        grid.setAlignment (Pos.CENTER);

        grid.setHgap (40);
        grid.setVgap (5);
        grid.setPadding (new Insets (30,0,0,0));


        grid.add(factoryLabel,0,0);
        grid.add(factoryComboBox,1,0);
        //grid.add(factoryComboBox,1,0,20,10);

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
        btn.setAlignment (Pos.BOTTOM_RIGHT);

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Bang");
            }
        });


        factoryComboBox.setOnMouseEntered (new EventHandler<MouseEvent> () {
            @Override
            public void handle(MouseEvent event) {
                ArrayList result;
                result = optionFilteringService.getMenu ("Factory", initialIndicator);
                for (int i = 0; i < result.size (); i++) {
                    factoryComboBox.getItems ().add (result.get (i).toString ());
                }
                initialIndicator = false;
                //System.out.println(factoryComboBox.getSelectionModel().getSelectedItem());
            }
        });
        
        factoryComboBox.getCheckModel().getCheckedItems().addListener (new ListChangeListener<String>() {
            public void onChanged(ListChangeListener.Change<? extends String> c) {
                System.out.println(factoryComboBox.getCheckModel().getCheckedItems());
            }
        });





       /*factoryComboBox.setOnShowing((e) -> {
           factoryComboBox.getItems().removeAll();
           factoryComboBox.getItems().clear();

           ArrayList result;
           if(initialIndicator) {
               result = optionFilteringService.getMenu ("Factory", initialIndicator);
               for (int i = 0; i < result.size (); i++) {
                   factoryComboBox.getItems ().add (result.get (i));
               }
               initialIndicator = false;
           }

           else {
               result = optionFilteringService.getMenu("Factory",initialIndicator);
               for (int i = 0; i < result.size(); i++) {
                   factoryComboBox.getItems().add(result.get(i));
               }
           }
           //System.out.println(factoryComboBox.getSelectionModel().getSelectedItem());
       });

*/




        //put the grid pan into a Vbox
        VBox root = new VBox();




        root.getChildren().add(grid);
        Scene scene = new Scene(root, 400, 500);
        primaryStage.setTitle("Option Filtering");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args){
        //String redisServer = "redis://TDnI123!@aurora:6379";
        String redisServer = "redis://localhost:6379";
        redis= new RedisHandling(redisServer);
        redis.redisConnect();
        optionFilteringService = new OptionFilteringService();
        optionFilteringService.userCreation();
        launch(args);
        redis.redisClose();
    }
}