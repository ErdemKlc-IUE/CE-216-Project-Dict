import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.List;


public class Controller implements Initializable {


    @FXML
    ListView<String> list;

    @FXML
    MenuButton fromLang;

    @FXML
    MenuItem eng1,fra1,deu1,ita1,ell1,swe1,tur1;

    @FXML
    TextField searchedWord;

    @FXML
    Label fromLangLbl;

    String lan1;
    private AddController controller2;
    private EditController editController;
    private SynonymController synonymController;

    public SynonymController getSynonymController() {
        return synonymController;
    }

    public void setSynonymController(SynonymController synonymController) {
        this.synonymController = synonymController;
    }

    private final Stage addStage;
    private final Stage editStage;

    private ArrayList<Scene> sceneList;

    public AddController getController2() {
        return controller2;
    }

    public void setController2(AddController controller2) {
        this.controller2 = controller2;
    }

    public EditController getEditController() {
        return editController;
    }

    public void setEditController(EditController editController) {
        this.editController = editController;
    }

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {

        eng1.setOnAction(event -> fromLangLbl.setText(eng1.getText()));
        fra1.setOnAction(event -> fromLangLbl.setText(fra1.getText()));
        deu1.setOnAction(event -> fromLangLbl.setText(deu1.getText()));
        ita1.setOnAction(event -> fromLangLbl.setText(ita1.getText()));
        ell1.setOnAction(event -> fromLangLbl.setText(ell1.getText()));
        swe1.setOnAction(event -> fromLangLbl.setText(swe1.getText()));
        tur1.setOnAction(event -> fromLangLbl.setText(tur1.getText()));

        controller2.init(this);
        editController.init(this);
        synonymController.init(this);


    }
    public Controller() {
        this.controller2 = new AddController();
        this.editController = new EditController();
        this.synonymController = new SynonymController();
        addStage = new Stage();
        editStage = new Stage();
        sceneList = new ArrayList<>();
    }
    @FXML
    void search() {

        list.getItems().clear();

        choicePart();

        if (searchedWord.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Please select the languages and enter the word you want to translate");
            alert.showAndWait();
        }

        else {

            switch (lan1) {
                case "eng" -> {
                    list.getItems().add("ENGLISH -> GERMAN"); translate("deu");
                    list.getItems().add("ENGLISH -> ITALIAN"); translate("ita");
                    list.getItems().add("ENGLISH -> FRENCH"); translate("fra");
                    list.getItems().add("ENGLISH -> SWEDISH"); translate("swe");
                    list.getItems().add("ENGLISH -> MODERN GREEK"); translate("ell");
                    list.getItems().add("ENGLISH -> TURKISH"); translate("tur");
                }
                case "deu" -> {
                    list.getItems().add("GERMAN -> ENGLISH"); translate("eng");
                    list.getItems().add("GERMAN -> ITALIAN"); translate("ita");
                    list.getItems().add("GERMAN -> FRENCH"); translate("fra");
                    list.getItems().add("GERMAN -> SWEDISH"); translate("swe");
                    list.getItems().add("GERMAN -> MODERN GREEK"); translate("ell");
                    list.getItems().add("GERMAN -> TURKISH"); translate("tur");
                }
                case "ell" -> {
                    list.getItems().add("MODERN GREEK -> ENGLISH"); translate("eng");
                    list.getItems().add("MODERN GREEK -> GERMAN");doubleTranslate("deu");
                    list.getItems().add("MODERN GREEK -> ITALIAN"); translate("ita");
                    list.getItems().add("MODERN GREEK -> FRENCH"); translate("fra");
                    list.getItems().add("MODERN GREEK -> SWEDISH"); translate("swe");
                    list.getItems().add("MODERN GREEK -> TURKISH"); doubleTranslate("tur");
                }
                case "fra" -> {
                    list.getItems().add("FRENCH -> ENGLISH"); translate("eng");
                    list.getItems().add("FRENCH -> GERMAN"); translate("deu");
                    list.getItems().add("FRENCH -> ITALIAN"); translate("ita");
                    list.getItems().add("FRENCH -> SWEDISH"); translate("swe");
                    list.getItems().add("FRENCH -> MODERN GREEK"); translate("ell");
                    list.getItems().add("FRENCH -> TURKISH"); translate("tur");
                }
                case "ita" -> {
                    list.getItems().add("ITALIAN -> ENGLISH"); translate("eng");
                    list.getItems().add("ITALIAN -> GERMAN"); translate("deu");
                    list.getItems().add("ITALIAN -> FRENCH"); doubleTranslate("fra");
                    list.getItems().add("ITALIAN -> SWEDISH"); translate("swe");
                    list.getItems().add("ITALIAN -> MODERN GREEK"); translate("ell");
                    list.getItems().add("ITALIAN -> TURKISH"); translate("tur");
                }
                case "swe" -> {
                    list.getItems().add("SWEDISH -> ENGLISH"); translate("eng");
                    list.getItems().add("SWEDISH -> GERMAN"); translate("deu");
                    list.getItems().add("SWEDISH -> ITALIAN"); translate("ita");
                    list.getItems().add("SWEDISH -> FRENCH"); translate("fra");
                    list.getItems().add("SWEDISH -> MODERN GREEK"); translate("ell");
                    list.getItems().add("SWEDISH -> TURKISH"); translate("tur");
                }
                case "tur" -> {
                    list.getItems().add("TURKISH -> ENGLISH"); translate("eng");
                    list.getItems().add("TURKISH -> GERMAN"); translate("deu");
                    list.getItems().add("TURKISH -> ITALIAN"); doubleTranslate("ita");
                    list.getItems().add("TURKISH -> FRENCH"); doubleTranslate("fra");
                    list.getItems().add("TURKISH -> SWEDISH"); doubleTranslate("swe");
                    list.getItems().add("TURKISH -> MODERN GREEK"); doubleTranslate("ell");
                }
                default -> {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Error");
                    alert.setContentText("Please select a language");
                    alert.showAndWait();
                }
            }

        }

        if(list.getItems().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("Word is not found in any language.");
            alert.showAndWait();
        }

    }

    void translate(String lang2) {

        List<String> lines = new ArrayList<>();


        choicePart();
        try {
            File file1 = new File("Dictionaries\\" + lan1 + "-" + lang2 + ".dict");
            String path1 = file1.getAbsolutePath();
            BufferedReader reader = new BufferedReader(new FileReader(path1));
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            reader.close();

        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (lan1.equals("swe") || lan1.equals("deu")) {
            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                if ((line.matches(searchedWord.getText() + " /.*"))) {
                    list.getItems().add(lines.get(i + 1));
                }
            }
        } else {
            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                //String word=searchedWord.getText();
                if ((line.matches(searchedWord.getText() + " /.*"))) {
                    list.getItems().add(lines.get(i + 1));
                    if (lines.get(i + 2).contains("2.")) {
                        list.getItems().add(lines.get(i + 2));
                    }
                    if (lines.get(i + 3).contains("3.")) {
                        list.getItems().add(lines.get(i + 3));
                    }
                    if (lines.get(i + 4).contains("4.")) {
                        list.getItems().add(lines.get(i + 4));
                    }
                }
            }
        }
        list.getItems().add("");
    }

    void doubleTranslate(String lang2) {

        List<String> lines2 = new ArrayList<>();
        List<String> lines3 = new ArrayList<>();

        choicePart();

        File file2 = new File("Dictionaries\\" + lan1 + "-eng.dict");
        File file3 = new File("Dictionaries\\eng-" + lang2 + ".dict");
        String path2 = file2.getAbsolutePath();
        String path3 = file3.getAbsolutePath();

        try {
            BufferedReader reader1 = new BufferedReader(new FileReader(path2));
            BufferedReader reader2 = new BufferedReader(new FileReader(path3));
            String line;
            while ((line = reader1.readLine()) != null) {
                lines2.add(line);
            }
            reader1.close();

            while ((line = reader2.readLine()) != null) {
                lines3.add(line);
            }
            reader2.close();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // I don't know how many meanings a word can have, so I used a for loop to check all the meanings.
        // And I have to stop the loop when the other word is found.
        // TODO: 3.05.2023 I have to find a better way to do this.
        for (int i = 0; i < lines2.size(); i++) {
            String line = lines2.get(i);
            if (line.matches(searchedWord.getText() + " /.*")) {

                for (int k = 0; k < lines3.size(); k++) {
                    String line2 = lines3.get(k);
                    if (line2.matches(lines2.get(i + 1) + " /.*")) {
                        list.getItems().add(lines3.get(k + 1));
                        if (lines3.get(k + 2).contains("2.")) {
                            list.getItems().add(lines3.get(k + 2));
                        }
                        if (lines3.get(k + 3).contains("3.")) {
                            list.getItems().add(lines3.get(k + 3));
                        }
                        if (lines3.get(k + 4).contains("4.")) {
                            list.getItems().add(lines3.get(k + 4));
                        }
                    }
                }
            }
        }
         list.getItems().add("");

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
    }
    @FXML
    public void addScene() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddScene.fxml"));

            Parent root1 = fxmlLoader.load();

            AddController controller2 = fxmlLoader.getController();
            controller2.init(this);

            Scene scene1 = new Scene(root1);

            controller2.setScene(scene1);

            this.controller2 = controller2;

            addStage.setScene(scene1);
            addStage.show();
            Stage stage = (Stage) list.getScene().getWindow();
            stage.hide();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    void switchToEdit () throws Exception{

        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("edit.fxml")));
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Edit");
        stage.setScene(new Scene(parent, 600, 400));
        stage.setMinWidth(605);
        stage.setMinHeight(405);
        stage.setResizable(true);
        // Hide the current window
        Stage stage1 = (Stage) list.getScene().getWindow();
        stage1.hide();

        stage.show();
    }
    @FXML
    void switchToSynonym () throws Exception{

        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("synonym.fxml")));
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("The Offline Dictionary App");
        stage.setScene(new Scene(parent, 600, 400));
        stage.setMinWidth(605);
        stage.setMinHeight(405);
        stage.setResizable(true);
        // Hide the current window
        Stage stage1 = (Stage) list.getScene().getWindow();
        stage1.hide();
        stage.show();
    }
    @FXML
    void exit () {
        System.exit(0);
    }
}