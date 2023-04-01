import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

import java.awt.*;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.List;


public class Controller {

    @FXML
    ListView list;

    @FXML
    MenuButton fromLang,toLang;

    @FXML
    TextField searchedWord;



    @FXML
    void search() throws InvocationTargetException {

            // ...

         // UNC path of the file on the network share
        File file = new File("eng-tur.dict");
        String path=file.getAbsolutePath();

         // Create a File object using the UNC path
         //File file = new File(path);

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
             if ((line.matches(searchedWord + " /.*"))) {
                 //System.out.println(lines.get(i + 1));
                 list.getItems().add(lines.get(i + 1));
                 if (lines.get(i + 2).contains("2.")) {
                     //System.out.println(lines.get(i + 2));
                     list.getItems().add(lines.get(i + 2));
                 }
                 if (lines.get(i + 3).contains("3.")) {
                     //System.out.println(lines.get(i + 3));
                     list.getItems().add(lines.get(i + 3));
                 }
             }
         }



         /*
        System.out.println("Kelimeyi giriniz: ");
        Scanner scanner = new Scanner(System.in);
        String kelime = scanner.nextLine();


         */


/*

        Scanner scan=new Scanner("aaaaa");

        list.getItems().add(searchedWord.getText());
        */


    }

    @FXML
    void select() {
        fromLang.setText(fromLang.getAccessibleText());
    }


    @FXML
    void exit() {
        System.exit(0);
    }

}
