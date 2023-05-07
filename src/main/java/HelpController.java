import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelpController {

    private Controller controller;

    private Parent root;
    private Stage stage;
    private Scene scene;

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
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

    //Returning back to main window
    @FXML
    public void backToMain(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        stage=(Stage)((Node)e.getSource()).getScene().getWindow();
        stage.setTitle("The Offline Dictionary App");
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
