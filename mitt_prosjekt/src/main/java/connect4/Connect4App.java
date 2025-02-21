//Startfilen for JavaFX-appen

package connect4;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Connect4App extends Application {
    @Override
    public void start(Stage primaryStage) {
        Label label = new Label("Velkommen til 4 på rad!");
        StackPane root = new StackPane(label);
        Scene scene = new Scene(root, 1000, 500);

        primaryStage.setTitle("4 på rad");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}