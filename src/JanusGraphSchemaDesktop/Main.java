package JanusGraphSchemaDesktop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        System.out.println(String.class);
        System.out.println(Integer.class);
        System.out.println(Double.class);
        System.out.println(Float.class);

        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        primaryStage.setTitle("JanusGraph Schema建模工具");
        primaryStage.setScene(new Scene(root, 1200, 800));
        primaryStage.setMaximized(true);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
