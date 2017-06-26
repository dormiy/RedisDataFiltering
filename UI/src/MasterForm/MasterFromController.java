package MasterFrom;

public class MasterFormController{

    private Button button;

    void initialization(){

        assert button!=null :"fx:id=\"button\" was not injected: check your FXML file 'MasterForm.fxml'.";
        @Override
        public void handler(ActionEvent event) {

            System.out.println("hello");
        }


        button

    }



}