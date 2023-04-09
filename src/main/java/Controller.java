import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.*;
import java.util.*;
import java.util.List;


public class Controller {

    @FXML
    ListView<String> list;

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
                lan1 = "eng";
                break;
            }
            case "French": {
                lan1 = "fra";
                break;
            }
            case "German": {
                lan1 = "deu";
                break;
            }
            case "Italian": {
                lan1 = "ita";
                break;
            }
            case "Modern Greek": {
                lan1 = "ell";
                break;
            }
            case "Swedish": {
                lan1 = "swe";
                break;
            }
            case "Turkish": {
                lan1 = "tur";
                break;
            }
        }

        switch (toLangLbl.getText()) {
            case "English": {
                lan2 = "eng";
                break;
            }
            case "French": {
                lan2 = "fra";
                break;
            }
            case "German": {
                lan2 = "deu";
                break;
            }
            case "Italian": {
                lan2 = "ita";
                break;
            }
            case "Modern Greek": {
                lan2 = "ell";
                break;
            }
            case "Swedish": {
                lan2 = "swe";
                break;
            }
            case "Turkish": {
                lan2 = "tur";
                break;
            }
        }


        List<String> lines = new ArrayList<>();
        List<String> lines2 = new ArrayList<>();
        List<String> lines3 = new ArrayList<>();

        if (lan1 == "Not Selected" || lan2 == "Not Selected" || searchedWord.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Please select the languages and enter the word you want to translate");
            alert.showAndWait();
        } else if (lan1 == lan2) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("You can't translate the same language");
            alert.showAndWait();
        }
        else {

            try {
                File file1 = new File("Dictionaries\\" + lan1 + "-" + lan2 + ".dict");
                String path1 = file1.getAbsolutePath();
                BufferedReader reader = new BufferedReader(new FileReader(path1));
                String line;
                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }
                reader.close();

            } catch (Exception t) {

                File file2 = new File("Dictionaries\\" + lan1 + "-eng.dict");
                File file3 = new File("Dictionaries\\eng-" + lan2 + ".dict");
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
                    if (line.matches(searchedWord.getText() + " /.*")) {

                        for (int k = 0; k < lines3.size(); k++) {
                            String line2 = lines3.get(k);
                            if (line2.matches(lines2.get(i + 1) + " /.*")) {
                                list.getItems().add(lines3.get(k + 1));
                                if (lines3.get(k + 2).contains("2.")) {
                                    list.getItems().add(lines3.get(k + 2));
                                    //System.out.println(lines2.get(k + 2));
                                }
                                if (lines3.get(k + 3).contains("3.")) {
                                    list.getItems().add(lines3.get(k + 3));
                                    //System.out.println(lines2.get(k + 3));
                                }
                                if (lines3.get(k + 4).contains("4.")) {
                                    list.getItems().add(lines3.get(k + 4));
                                    //System.out.println(lines2.get(k + 4));
                                }
                            }
                        }
                        if (lines2.get(i + 2).contains("2.")) {
                            System.out.println(lines2.get(i + 2));
                        }
                        if (lines2.get(i + 3).contains("3.")) {
                            System.out.println(lines2.get(i + 3));
                        }
                        if (lines2.get(i + 4).contains("4.")) {
                            System.out.println(lines2.get(i + 4));
                        }
                    }
                }

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

        }

    }

    @FXML
    void exit() {
        System.exit(0);
    }

}