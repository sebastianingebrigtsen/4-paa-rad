package connect4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;

// Startklassen for JavaFX-appen
public class Connect4App extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Laster inn App.fxml (brukergrensesnittet)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/connect4/App.fxml"));
            Pane root = loader.load();

            // Lager en ny Scene med det som ble lastet inn
            Scene scene = new Scene(root);

            // Legger til tilhørende CSS-stilark
            scene.getStylesheets().add(getClass().getResource("/connect4/style.css").toExternalForm());

            // Konfigurerer vinduet
            primaryStage.setTitle("4 på rad");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false); // Låser størrelsen på vinduet
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace(); // Skriver ut feil om noe går galt ved lasting
        }
    }

    // Metoden som starter JavaFX-programmet
    public static void main(String[] args) {
        launch(args);
    }
}
