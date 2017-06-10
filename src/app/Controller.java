package app;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Controller {

    @FXML
    TextField textField;

    @FXML
    ListView<String> list;

    ObservableList<String> items;


    @FXML
    private void initialize(){
        items = FXCollections.observableArrayList ();
        list.setItems(items);
    }

    @FXML
    void onBtnCheckClick(){
        String number = textField.getText();
        if(cardValidator(number)) items.add(number);
    }

    @FXML
    void onBtnFileCheckClick(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Otwórz plik tekstowy");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Plik Tekstowy", "*.txt")
        );
        File file = fileChooser.showOpenDialog(textField.getScene().getWindow());
        if (file!=null){
            try {
                Scanner scanner = new Scanner(file);
                String number = "";
                number = scanner.nextLine();
                System.out.print(number);
                if(cardValidator(number)) items.add(number);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean cardValidator(String number){
        number = number.replaceAll("\\s+","");
        if (!number.matches("\\d+"))return false;
        if (number.length()!=16) return false;
        int odd = 0, even = 0;
        for (int i=15;i>=0;i--){
            int temp = Character.digit(number.charAt(i),10);
            if (i%2==0){
                temp*=2;
                if (temp>9)temp-=9;
                even += temp;
            }else odd += temp;
        }
        if ((odd+even)%10!=0) return false;
        return true;
    }



}
