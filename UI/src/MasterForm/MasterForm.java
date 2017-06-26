pacakge MasterForm;

public class MasterForm extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FMXLLoader.load(getClass().getResource("MasterForm.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("HelloSwingNode Sample");
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}