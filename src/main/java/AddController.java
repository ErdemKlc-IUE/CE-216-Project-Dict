import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class AddController implements Initializable {

    private Controller controller;


    @FXML
    MenuButton fromLang,toLang;
    @FXML
    ListView<String> list;
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

        choicePart();

        String fileNameTxt = "Dictionaries\\" + lan1 + "-" + lan2 + ".txt";

        File fileTxt = new File(fileNameTxt);

        if (word.isEmpty() || word2.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Please fill in all the fields");
            alert.showAndWait();
            return;
        }

        if (!fileTxt.exists()) {
            try {
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileNameTxt), StandardCharsets.UTF_8));
                String text1 = word + " / /\n"+ word2 + "\n";
                writer.write(text1);
                writer.close();
                wordsList.getItems().add(word);
                wordsList1.getItems().add(word2);
                addedWord.clear();
                translation.clear();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            List<String> lines = new ArrayList<>();
            File file =fileTxt;

            try {
                String path = file.getAbsolutePath();
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8));
                String line;
                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            boolean wordExists = false;
            for (String line : lines) {
                if (line.startsWith(word + " /")) {
                    wordExists = true;
                    break;
                }
            }

            if (wordExists) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error");
                alert.setContentText("This word already exists in the dictionary");
                alert.showAndWait();
            } else {
                try {
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true), StandardCharsets.UTF_8));
                    String text1 = word + " / /\n" + word2 + "\n";
                    writer.write(text1);
                    writer.close();
                    wordsList.getItems().add(word);
                    wordsList1.getItems().add(word2);
                    addedWord.clear();
                    translation.clear();
                } catch (IOException e) {
                    e.printStackTrace();
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
    @FXML
    void switchToSynonym(ActionEvent e) throws Exception {

        root = FXMLLoader.load(getClass().getResource("synonym.fxml"));
        stage=(Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void switchToEdit(ActionEvent e) throws Exception {

        root = FXMLLoader.load(getClass().getResource("edit.fxml"));
        stage=(Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}