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
        FXMLLoader addLoader4 = new FXMLLoader(getClass().getResource("help.fxml"));

        Parent root1 = addLoader1.load();
        Scene scene1 = new Scene(root1);

        Parent root2 = addLoader2.load();
        Scene scene2 = new Scene(root2);

        Parent root3 = addLoader3.load();
        Scene scene3 = new Scene(root3);

        Parent root4 = addLoader4.load();
        Scene scene4 = new Scene(root4);


        AddController controller2 = new AddController();
        EditController editController = new EditController();
        SynonymController synonymController = new SynonymController();
        HelpController helpController = new HelpController();

        controller2.setController(controller);
        controller2.setScene(scene1);

        editController.setController(controller);
        editController.setScene(scene2);

        synonymController.setController(controller);
        synonymController.setScene(scene3);

        helpController.setController(controller);
        helpController.setScene(scene4);



        controller.setController2(controller2);

        controller.setEditController(editController);

        controller.setSynonymController(synonymController);

        controller.setHelpController(helpController);

        primaryStage.setTitle("The Offline Dictionary App");
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
