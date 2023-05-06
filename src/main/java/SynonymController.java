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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

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
    @FXML
    void findSynonym(){
        String word = findTf.getText();
        String language = fromLangLbl.getText();
        if (language.equals("English")){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Look, an Information Dialog");
            alert.setContentText("I have a great message for you!");
            alert.showAndWait();
        }
        else
        {
            List<String> lines = new ArrayList<>();
            List<String> lines3 = new ArrayList<>();
            choicePart();
            try {
                File file1 = new File("Dictionaries\\" + lan1 + "-eng.dict");
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
            if (lan1.equals("swe") ) {
                for (int i = 0; i < lines.size(); i++) {
                    String line = lines.get(i);
                    if ((line.matches(word + " /.*"))) {
                        try {
                            File file1 = new File("Dictionaries\\" + "eng-"+ lan1 + ".dict");
                            String path1 = file1.getAbsolutePath();
                            BufferedReader reader = new BufferedReader(new FileReader(path1));
                            String line2;
                            while ((line2 = reader.readLine()) != null) {
                                lines3.add(line2);
                            }
                            reader.close();

                        }
                        catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        for (int j = 0; j < lines3.size(); j++) {
                            String line3 = lines3.get(j);
                            if (line3.matches(lines.get(i + 1) + " /.*")) {
                                synonymsList.getItems().add(lines3.get(j+1));

                            }
                        }
                    }
                }
            }
            else if (lan1.equals("deu"))
            {
                for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                if ((line.matches(word + " /.*"))) {
                    try {
                        File file1 = new File("Dictionaries\\" + "eng-"+ lan1 + ".dict");
                        String path1 = file1.getAbsolutePath();
                        BufferedReader reader = new BufferedReader(new FileReader(path1));
                        String line2;
                        while ((line2 = reader.readLine()) != null) {
                            lines3.add(line2);
                        }
                        reader.close();

                    }
                    catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    for (int j = 0; j < lines3.size(); j++) {
                        String line3 = lines3.get(j);
                        if (line3.matches(lines.get(i + 1) + " /.*")) {
                            synonymsList.getItems().add(lines3.get(j+1));
                            for(int k = j + 2; k < lines3.size(); k++) {
                                String nextSynonym = lines3.get(k);
                                if (nextSynonym.matches("\\w+ /.") || nextSynonym.matches(".\\s/.*")) {
                                    break;
                                }
                                synonymsList.getItems().add(nextSynonym);
                            }

                        }
                    }
                }
            }}
            else
            {
                for (int i = 0; i < lines.size(); i++) {
                    String line = lines.get(i);
                    if (line.matches(word+ " /.*")) {
                        try {
                            File file1 = new File("Dictionaries\\" + "eng-"+ lan1 + ".dict");
                            String path1 = file1.getAbsolutePath();
                            BufferedReader reader = new BufferedReader(new FileReader(path1));
                            String line2;
                            while ((line2 = reader.readLine()) != null) {
                                lines3.add(line2);
                            }
                            reader.close();

                        }
                        catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        for (int j = 0; j < lines3.size(); j++) {
                            String line3 = lines3.get(j);
                            if (line3.matches(lines.get(i + 1) + " /.*")) {
                                synonymsList.getItems().add(lines3.get(j+1));
                                for(int k = j + 2; k < lines3.size(); k++) {
                                    String nextSynonym = lines3.get(k);
                                    if (nextSynonym.matches("\\w+ /.") || nextSynonym.matches(".\\s/.*")) {
                                        break;
                                    }
                                    synonymsList.getItems().add(nextSynonym);
                                }
                            }
                        }
                    }
                }
            }
            synonymsList.getItems().add("");
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
}

