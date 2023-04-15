import javafx.application.Application;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader root = new FXMLLoader(getClass().getResource("sample.fxml"));
        Scene mainScene = new Scene(root.load());

        Controller controller = new Controller();

        FXMLLoader addLoader1 = new FXMLLoader(getClass().getResource("AddScene.fxml"));


        Parent root1 = addLoader1.load();
        Scene scene1 = new Scene(root1);


        Controller2 controller2 = new Controller2();

        controller2.setController(controller);
        controller2.setScene(scene1);


        controller.setController2(controller2);



        primaryStage.setTitle("The Offline Dictionary App");
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
