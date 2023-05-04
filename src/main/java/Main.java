import javafx.application.Application;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader root = new FXMLLoader(getClass().getResource("sample.fxml"));
        Scene mainScene = new Scene(root.load());

        Controller controller = new Controller();

        FXMLLoader addLoader1 = new FXMLLoader(getClass().getResource("AddScene.fxml"));
        FXMLLoader addLoader2 = new FXMLLoader(getClass().getResource("edit.fxml"));
        FXMLLoader addLoader3 = new FXMLLoader(getClass().getResource("synonym.fxml"));

        Parent root1 = addLoader1.load();
        Scene scene1 = new Scene(root1);

        Parent root2 = addLoader2.load();
        Scene scene2 = new Scene(root2);

        Parent root3 = addLoader3.load();
        Scene scene3 = new Scene(root3);

        AddController controller2 = new AddController();
        EditController editController = new EditController();
        SynonymController synonymController = new SynonymController();

        controller2.setController(controller);
        controller2.setScene(scene1);

        editController.setController(controller);
        editController.setScene(scene2);

        synonymController.setController(controller);
        synonymController.setScene(scene3);


        controller.setController2(controller2);

        controller.setEditController(editController);

        controller.setSynonymController(synonymController);

        primaryStage.setTitle("The Offline Dictionary App");
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
