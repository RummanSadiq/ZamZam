package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("newSample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setFullScreen(true);
        primaryStage.setScene(new Scene(root, 1337, 768));
//        primaryStage.setMinWidth(1337);
//        primaryStage.setMinHeight(768);
//        primaryStage.setMaxHeight(Double.MAX_VALUE);
//        primaryStage.setMaxWidth(Double.MAX_VALUE);
//        primaryStage.setResizable(false);
        primaryStage.setMaximized(true);
        primaryStage.show();
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Controller controller = new Controller();
                controller.closeFile();
            }
        });
    }


    public static void main(String[] args) {
        launch(args);

    }
}
