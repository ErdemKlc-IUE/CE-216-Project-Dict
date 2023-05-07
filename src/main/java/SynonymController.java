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


public class SynonymController implements Initializable {
    @FXML
    MenuItem eng1,fra1,deu1,ita1,ell1,swe1,tur1;
    @FXML
    MenuButton fromLang;
    @FXML
    Label fromLangLbl;
    @FXML
    Button findSynonym,addScene,backScene,editScene;
    @FXML
    TextField findTf;
    @FXML
    ListView<String> synonymsList;
    String lan1;
    private Parent root;
    private Stage stage;
    private Scene scene;
    private Controller controller;

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public Parent getRoot() {
        return root;
    }

    public void setRoot(Parent root) {
        this.root = root;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    //Returning back to main window
    @FXML
    public void returnBack(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        stage=(Stage)((Node)e.getSource()).getScene().getWindow();
        stage.setTitle("The Offline Dictionary App");
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    //Switching to add window
    @FXML
    public void goToAdd(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("AddScene.fxml"));
        stage=(Stage)((Node)e.getSource()).getScene().getWindow();
        stage.setTitle("Add");
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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
        Stage stage1 = (Stage) synonymsList.getScene().getWindow();
        stage1.hide();
        stage.show();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        eng1.setOnAction(event -> fromLangLbl.setText(eng1.getText()));
        fra1.setOnAction(event -> fromLangLbl.setText(fra1.getText()));
        deu1.setOnAction(event -> fromLangLbl.setText(deu1.getText()));
        ita1.setOnAction(event -> fromLangLbl.setText(ita1.getText()));
        ell1.setOnAction(event -> fromLangLbl.setText(ell1.getText()));
        swe1.setOnAction(event -> fromLangLbl.setText(swe1.getText()));
        tur1.setOnAction(event -> fromLangLbl.setText(tur1.getText()));

    }

    public void init(Controller controller) {
        setController(controller);
    }

    int foundSynCounter = 0;

    @FXML
    void findSynonym() {

        //Clearing the synonym list before every findSynonym
        synonymsList.getItems().clear();

        //Getting the abbreviation of the selected language
        choicePart();

        //Showing an alert when there is no entered word
        if (findTf.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Please enter the word you want to find its synonym");
            alert.showAndWait();
        }

        else {

            //Calling necessary translate methods
            switch (lan1) {
                case "eng" -> {
                    directTranslateSyn("deu"); directTranslateSyn("ita");
                    directTranslateSyn("fra"); directTranslateSyn("swe");
                    directTranslateSyn("ell"); directTranslateSyn("tur");
                }
                case "deu" -> {
                    directTranslateSyn("eng"); directTranslateSyn("ita");
                    directTranslateSyn("fra"); directTranslateSyn("swe");
                    directTranslateSyn("ell"); directTranslateSyn("tur");
                }
                case "ell" -> {
                    directTranslateSyn("eng"); doubleTranslateSyn("deu");
                    directTranslateSyn("ita"); directTranslateSyn("fra");
                    directTranslateSyn("swe"); doubleTranslateSyn("tur");
                }
                case "fra" -> {
                    directTranslateSyn("eng"); directTranslateSyn("deu");
                    directTranslateSyn("ita"); directTranslateSyn("swe");
                    directTranslateSyn("ell"); directTranslateSyn("tur");
                }
                case "ita" -> {
                    directTranslateSyn("eng"); directTranslateSyn("deu");
                    doubleTranslateSyn("fra"); directTranslateSyn("swe");
                    directTranslateSyn("ell"); directTranslateSyn("tur");
                }
                case "swe" -> {
                    directTranslateSyn("eng"); directTranslateSyn("deu");
                    directTranslateSyn("ita"); directTranslateSyn("fra");
                    directTranslateSyn("ell"); directTranslateSyn("tur");
                }
                case "tur" -> {
                    directTranslateSyn("eng");directTranslateSyn("deu");
                    doubleTranslateSyn("ita"); doubleTranslateSyn("fra");
                    doubleTranslateSyn("swe"); doubleTranslateSyn("ell");
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

        //Showing an alert when there is no synonym for the entered word
        if(synonymsList.getItems().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText("Synonym is not found.");
            alert.showAndWait();
        }


        if(foundSynCounter != 0){
            foundSynCounter= 0;
        }

    }

    //Finding synonyms between two languages with a direct dictionary between them
    void directTranslateSyn(String lang2) {

        List<String> lines = new ArrayList<>();
        List<String> lines2 = new ArrayList<>();
        String word = findTf.getText();

        //Getting the abbreviation of the selected language
        choicePart();

        try {
            File file1 = new File("Dictionaries\\" + lan1 + "-"+ lang2 +".dict");
            String path1 = file1.getAbsolutePath();
            BufferedReader reader = new BufferedReader(new FileReader(path1));
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
            //Searching for the entered word
            if (line.matches(word + " /.*")) {
                try {
                    File file1 = new File("Dictionaries\\" + lang2 + "-" + lan1 + ".dict");
                    String path1 = file1.getAbsolutePath();
                    BufferedReader reader = new BufferedReader(new FileReader(path1));
                    String line2;
                    while ((line2 = reader.readLine()) != null) {
                        lines2.add(line2);
                    }
                    reader.close();

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                for (int j = 0; j < lines2.size(); j++) {
                    String line3 = lines2.get(j);
                    //Searching for the found translation
                    if (line3.matches(lines.get(i + 1) + " /.*")) {
                        for (int k = j + 1; k < lines2.size(); k++) {
                            String nextLine = lines2.get(k);
                            //Finding all the synonyms
                            if (nextLine.matches("\\w+ /.*") || nextLine.matches(".*\\s/.*")) {
                                break;
                            }
                            //Skipping same words with the entered word
                            if(nextLine.matches(word)) {
                                continue;
                            }
                            for(int l=0;l<foundSynCounter;l++) {
                                if(nextLine.matches(synonymsList.getItems().get(l))) {
                                    continue;
                                }
                            }
                            //Adding synonym to the list
                            synonymsList.getItems().add(nextLine);
                            foundSynCounter++;
                        }
                    }
                }
            }
        }
    }

    //Finding synonyms between two languages that don't have a direct dictionary between them
    void doubleTranslateSyn(String lang2) {
/*
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

 */

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


}