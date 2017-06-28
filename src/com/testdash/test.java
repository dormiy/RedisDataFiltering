package com.testdash;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.Observable;

/**
 * Created by wuping on 6/26/2017.
 */
public class test extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        Button btn=new Button();

        btn.setText("Say Hello World");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello world");
            }
        });
        StackPane root = new StackPane();
        root.getChildren().add(btn);

        ObservableList<String> options = FXCollections.observableArrayList ("option1", "option2", "option3");
        final ComboBox comboxC = new ComboBox (options);
        comboxC.getItems ().addAll ("option4");
        comboxC.setOnAction ((Event ev) -> {
            System.out.println ("combox");
        });

        root.getChildren ().add(comboxC);

        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("hello world  ");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args){
        launch(args);
    }



}
