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
    TableView<String> table;
    @FXML
    MenuItem eng1,fra1,deu1,ita1,ell1,swe1,tur1;
    @FXML
    MenuItem eng2,fra2,deu2,ita2,ell2,swe2,tur2;
    @FXML
    TextField addedWord,translation;
    @FXML
    Button addBtn;
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
    void editWord() {

        // Open the file
        choicePart();
        File file = new File("Dictionaries\\" + lan1 + "-" + lan2 + ".dict");
        String path = file.getAbsolutePath();
        //File file = new File("file.txt");

        try {
            // Read the contents of the file
            BufferedReader reader = new BufferedReader(new FileReader(path));
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            reader.close();

            // Prompt the user for the word they want to edit
            BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter the word you want to edit: ");
            String oldWord = inputReader.readLine();

            // Prompt the user for the new version of the word
            System.out.println("Enter the new version of the word: ");
            String newWord = inputReader.readLine();

            // Replace the word in the content
            String newContent = content.toString().replace(oldWord, newWord);

            // Write the modified content back to the file
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(newContent);
            writer.close();

            System.out.println("Word replaced successfully.");

        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
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
}
