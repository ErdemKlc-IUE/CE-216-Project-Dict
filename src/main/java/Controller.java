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
    private HelpController helpController;

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

    public HelpController getHelpController() {
        return helpController;
    }

    public void setHelpController(HelpController helpController) {
        this.helpController = helpController;
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

    int foundWordCounter = 0;


    @FXML
    void search() {

        //Clearing the list before every search
        list.getItems().clear();

        //Getting the abbreviation of the selected language
        choicePart();

        //Showing an alert when word is not entered
        if (searchedWord.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Please select the languages and enter the word you want to translate");
            alert.showAndWait();
        }

        else {

            //Calling necessary translate methods
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

        //Showing an alert when there is no translation for the entered word
        if(foundWordCounter == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("Word has no translation in any language.");
            alert.showAndWait();

        }

        //Resetting the word counter
        if(foundWordCounter != 0){
            foundWordCounter= 0;
        }

    }

    //Translating between two languages with a direct dictionary between them
    void translate(String lang2) {

        List<String> lines = new ArrayList<>();

        //Getting the abbreviation of the selected language
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

        //Differentiating German and Swedish because of their different file structures
        if (lan1.equals("swe") || lan1.equals("deu")) {
            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                //Searching for the entered word
                if (line.matches(searchedWord.getText() + " /.*")) {
                    //Adding translation to the list
                    list.getItems().add(lines.get(i + 1));
                    foundWordCounter++;
                }
            }
        } else{
            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                //Searching for the entered word
                if ((line.matches(searchedWord.getText() + " /.*"))) {
                    for (int j = i + 1; j < lines.size(); j++) {
                        String nextLine = lines.get(j);
                        //Finding all the translations
                        if (nextLine.matches("\\w+ /.*") || nextLine.matches(".*\\s/.*")) {
                            break;
                        }
                        //Adding translation to the list
                        list.getItems().add(nextLine);
                    }
                    foundWordCounter++;
                }
            }
        }
        //Leaving gaps between translations in different languages
        list.getItems().add("");
    }

    //Translating between two languages that don't have a direct dictionary between them
    void doubleTranslate(String lang2) {

        List<String> lines = new ArrayList<>();
        List<String> lines2 = new ArrayList<>();
        List<String> lines3 = new ArrayList<>();

        //Getting the abbreviation of the selected language
        choicePart();

        try {
            File file1 = new File("Dictionaries\\" + lan1 + "-" + lang2 + ".txt");
            String path1 = file1.getAbsolutePath();
            BufferedReader reader = new BufferedReader(new FileReader(path1));
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            reader.close();

        } catch (Exception t) {

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


            for (int i = 0; i < lines2.size(); i++) {
                String line = lines2.get(i);
                //Showing an alert when word is not entered
                if (line.matches(searchedWord.getText() + " /.*")) {

                    for (int k = 0; k < lines3.size(); k++) {
                        String line2 = lines3.get(k);
                        if (line2.matches(lines2.get(i + 1) + " /.*")) {
                            for (int l = k + 1; l < lines3.size(); l++) {
                                String nextLine = lines3.get(l);
                                //Finding all the translations
                                if (nextLine.matches("\\w+ /.*") || nextLine.matches(".*\\s/.*")) {
                                    break;
                                }
                                //Adding translation to the list
                                list.getItems().add(nextLine);
                            }
                            foundWordCounter++;
                        }
                    }
                }
            }
            //Leaving gaps between translations in different languages
            list.getItems().add("");

        }

        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            if ((line.matches(searchedWord.getText() + " /.*"))) {
                for (int j = i + 1; j < lines.size(); j++) {
                    String nextLine = lines.get(j);
                    if (nextLine.matches("\\w+ /.*") || nextLine.matches(".*\\s/.*")) {
                        break;
                    }
                    list.getItems().add(nextLine);

                }
                list.getItems().add("");
                foundWordCounter++;
            }
        }
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

    //Switching to add window
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
            addStage.setTitle("Add");
            Stage stage = (Stage) list.getScene().getWindow();
            stage.hide();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Switching to edit window
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

    //Switching to synonym window
    @FXML
    void switchToSynonym () throws Exception{

        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("synonym.fxml")));
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Synonym");
        stage.setScene(new Scene(parent, 600, 400));
        stage.setMinWidth(605);
        stage.setMinHeight(405);
        stage.setResizable(true);
        // Hide the current window
        Stage stage1 = (Stage) list.getScene().getWindow();
        stage1.hide();
        stage.show();
    }

    //Switching to help window
    @FXML
    void help () throws Exception{

        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Help.fxml")));
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Help");
        stage.setScene(new Scene(parent, 670, 500));
        stage.setMinWidth(700);
        stage.setMinHeight(550);
        stage.setResizable(true);
        // Hide the current window
        Stage stage1 = (Stage) list.getScene().getWindow();
        stage1.hide();

        stage.show();
    }

    //Exiting from the app
    @FXML
    void exit () {
        System.exit(0);
    }



}