import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class EditController implements Initializable {

    private Controller controller;


    @FXML
    MenuButton fromLang,toLang;
    @FXML
    Label fromLangLbl,toLangLbl;
    @FXML
    MenuItem eng1,fra1,deu1,ita1,ell1,swe1,tur1;
    @FXML
    MenuItem eng2,fra2,deu2,ita2,ell2,swe2,tur2;
    @FXML
    TextField oldW,newW,oldT,newT;

    String lan1,lan2;
    private Parent root;
    private Stage stage;
    private Scene scene;

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void init(Controller controller) {
        setController(controller);
    }
    @FXML
    void editWord() throws IOException {

        // Open the file
        choicePart();

        String oldWord=oldW.getText();
        String newWord=newW.getText();

        File file = new File("Dictionaries\\" + lan1 + "-" + lan2 + ".dict");
        String path = file.getAbsolutePath();
        //File file = new File("file.txt");

        List<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String line;
        while ((line = reader.readLine()) != null) {
            list.add(line);
        }
        reader.close();

        // Replace old string with new string
        for (int i = 0; i < list.size(); i++) {
            String str = list.get(i);
            if(str.matches(oldWord + " /.*")) {
                str = str.replace(oldWord, newWord);
            }

            list.set(i, str);
        }

        // Write modified content back to the file
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        for (String str : list) {
            writer.write(str + "\n");
        }
        writer.close();
    }



    public void choicePart(){
        switch (fromLangLbl.getText()) {
            case "English" -> lan1 = "eng";
            case "French" -> lan1 = "fra";
            case "German" -> lan1 = "deu";
            case "Italian" -> lan1 = "ita";
            case "Modern Greek" -> lan1 = "ell";
            case "Swedish" -> lan1 = "swe";
            case "Turkish" -> lan1 = "tur";
        }

        switch (toLangLbl.getText()) {
            case "English" -> lan2 = "eng";
            case "French" -> lan2 = "fra";
            case "German" -> lan2 = "deu";
            case "Italian" -> lan2 = "ita";
            case "Modern Greek" -> lan2 = "ell";
            case "Swedish" -> lan2 = "swe";
            case "Turkish" -> lan2 = "tur";
        }
    }

    @FXML
    public void returnBack(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        stage=(Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void goToAdd(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("AddScene.fxml"));
        stage=(Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void goToSynonym(ActionEvent e) throws IOException {

    }

    @FXML
    void editTranslation() throws IOException {
        // Open the file
        choicePart();

        String oldWord=oldW.getText();
        String oldTranslation=oldT.getText();
        String newTranslation=newT.getText();

        File file = new File("Dictionaries\\" + lan1 + "-" + lan2 + ".dict");
        String path = file.getAbsolutePath();
        //File file = new File("file.txt");

        List<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String line;
        while ((line = reader.readLine()) != null) {
            list.add(line);
        }
        reader.close();

        // Replace old string with new string
        for (int i = 0; i < list.size(); i++) {
            String str = list.get(i);
            if(str.matches(oldWord + " /.*")) {
                i++;
                String str2=list.get(i);
                while(!str2.contains("/*/")) {
                    if(str2.contains(oldTranslation)) {
                        str2 = str2.replace(oldTranslation, newTranslation);
                        list.set(i, str2);
                        break;
                    }
                    i++;
                    str2=list.get(i);
                }

            }
        }

        // Write modified content back to the file
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        for (String str : list) {
            writer.write(str + "\n");
        }
        writer.close();
    }

}
