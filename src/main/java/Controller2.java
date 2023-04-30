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

public class Controller2 implements Initializable {

    private Controller controller;


    @FXML
    MenuButton fromLang,toLang;
    @FXML
    Label fromLangLbl,toLangLbl;
    @FXML
    ListView<String> wordsList,wordsList1;
    @FXML
    MenuItem eng1,fra1,deu1,ita1,ell1,swe1,tur1;
    @FXML
    MenuItem eng2,fra2,deu2,ita2,ell2,swe2,tur2;
    @FXML
    TextField addedWord,translation;
    @FXML
    Button addBtn;
    String lan1,lan2;
    private Scene scene;
    private Parent root;
    private Stage stage;

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
    void addWord() {
        String word = addedWord.getText();
        String word2 = translation.getText();

        List<String> lines = new ArrayList<>();

        choicePart();
        if (word.isEmpty() || word2.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Please fill in all the fields");
            alert.showAndWait();
        } else{
            try {
                File file = new File("Dictionaries\\" + lan1 + "-" + lan2 + ".dict");
                String path = file.getAbsolutePath();
                BufferedReader reader = new BufferedReader(new FileReader(path));
                String line;
                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            for (String line : lines) {
                if (line.matches(word+"/.*")){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Error");
                    alert.setContentText("This word already exists in the dictionary");
                    alert.showAndWait();
                    return;
                }
                else {
                    try {
                        FileWriter fileWriter = new FileWriter("Dictionaries\\" + lan1 + "-" + lan2 + ".dict", true);
                        String text1 = word + " / /\n" +"1." +word2 + "\n";
                        fileWriter.write(text1);
                        fileWriter.close();
                        wordsList.getItems().add(word);
                        wordsList1.getItems().add(word2);
                        addedWord.clear();
                        translation.clear();
                        break;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
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
}