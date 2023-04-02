import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.*;
import java.util.*;
import java.util.List;


public class Controller {

    @FXML
    ListView list;

    @FXML
    MenuButton fromLang,toLang;

    @FXML
    MenuItem eng1,fra1,deu1,ita1,ell1,swe1,tur1;

    @FXML
    MenuItem eng2,fra2,deu2,ita2,ell2,swe2,tur2;

    @FXML
    TextField searchedWord;

    @FXML
    Label fromLangLbl,toLangLbl;

    String lan1,lan2;

    @FXML
    public void initialize() {

        eng1.setOnAction(event -> fromLangLbl.setText(eng1.getText()));
        fra1.setOnAction(event -> fromLangLbl.setText(fra1.getText()));
        deu1.setOnAction(event -> fromLangLbl.setText(deu1.getText()));
        ita1.setOnAction(event -> fromLangLbl.setText(ita1.getText()));
        ell1.setOnAction(event -> fromLangLbl.setText(ell1.getText()));
        swe1.setOnAction(event -> fromLangLbl.setText(swe1.getText()));
        tur1.setOnAction(event -> fromLangLbl.setText(tur1.getText()));


        eng2.setOnAction(event -> toLangLbl.setText(eng2.getText()));
        fra2.setOnAction(event -> toLangLbl.setText(fra2.getText()));
        deu2.setOnAction(event -> toLangLbl.setText(deu2.getText()));
        ita2.setOnAction(event -> toLangLbl.setText(ita2.getText()));
        ell2.setOnAction(event -> toLangLbl.setText(ell2.getText()));
        swe2.setOnAction(event -> toLangLbl.setText(swe2.getText()));
        tur2.setOnAction(event -> toLangLbl.setText(tur2.getText()));

    }


    @FXML
    void search() {

        // UNC path of the file on the network share
        list.getItems().clear();


        switch (fromLangLbl.getText()) {
            case "English": {
              lan1="eng";
              break;
            }
            case "French": {
                lan1="fra";
                break;
            }
            case "German": {
                lan1="deu";
                break;
            }
            case "Italien": {
                lan1="ita";
                break;
            }
            case "Modern Greek": {
                lan1="ell";
                break;
            }
            case "Swedish": {
                lan1="swe";
                break;
            }
            case "Turkish": {
                lan1="tur";
                break;
            }
        }

        switch (toLangLbl.getText()) {
            case "English": {
                lan2="eng";
                break;
            }
            case "French": {
                lan2="fra";
                break;
            }
            case "German": {
                lan2="deu";
                break;
            }
            case "Italien": {
                lan2="ita";
                break;
            }
            case "Modern Greek": {
                lan2="ell";
                break;
            }
            case "Swedish": {
                lan2="swe";
                break;
            }
            case "Turkish": {
                lan2="tur";
                break;
            }
        }


        // Create a File object using the UNC path
        File file1 =new File(lan1+"-"+lan2+".dict");
        String path=file1.getAbsolutePath();

         List<String> lines = new ArrayList<>();
         try {
             BufferedReader reader = new BufferedReader(new FileReader(path));
             String line;
             while ((line = reader.readLine()) != null) {
                 lines.add(line);
             }
             reader.close();


         } catch (IOException e) {
             throw new RuntimeException(e);
         }


         for (int i = 0; i < lines.size(); i++) {
             String line = lines.get(i);
             if ((line.matches(searchedWord.getText() + " /.*"))) {
                 list.getItems().add(lines.get(i + 1));
                 if (lines.get(i + 2).contains("2.")) {
                     list.getItems().add(lines.get(i + 2));
                 }
                 if (lines.get(i + 3).contains("3.")) {
                     list.getItems().add(lines.get(i + 3));
                 }
             }
         }

    }


    @FXML
    void exit() {
        System.exit(0);
    }

}
