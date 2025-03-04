import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class ConnectFourApp extends Application {
    private ConnectFourGame game;
    private GridPane gridPane;
    private Label statusLabel;

    // Definerer brettdimensjoner og celle-størrelse.
    private final int rows = 6;
    private final int cols = 7;
    private final int cellSize = 80;

    @Override
    public void start(Stage primaryStage) {
        game = new ConnectFourGame();
        BorderPane root = new BorderPane();

        // Toppen: meny og status
        VBox topContainer = new VBox();
        MenuBar menuBar = createMenuBar();
        statusLabel = new Label("Tur: " + game.getCurrentPlayer().getName());
        topContainer.getChildren().addAll(menuBar, statusLabel);
        root.setTop(topContainer);

        // Midten: spillbrettet
        gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        updateGrid();
        root.setCenter(gridPane);

        Scene scene = new Scene(root, cols * cellSize, rows * cellSize + 50);
        primaryStage.setScene(scene);
        primaryStage.setTitle("4 på rad");
        primaryStage.show();
    }

    // Lager en meny med muligheter for lagring og lasting.
    private MenuBar createMenuBar() {
        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("File");

        MenuItem saveItem = new MenuItem("Save Game");
        saveItem.setOnAction(e -> {
            try {
                ConnectFourFileHandler.saveGame(game, "game_save.txt");
                showAlert("Game Saved", "Spilltilstanden er lagret.");
            } catch (Exception ex) {
                showAlert("Error", "Feil ved lagring: " + ex.getMessage());
            }
        });

        MenuItem loadItem = new MenuItem("Load Game");
        loadItem.setOnAction(e -> {
            try {
                ConnectFourFileHandler.loadGame(game, "game_save.txt");
                updateGrid();
                statusLabel.setText("Tur: " + game.getCurrentPlayer().getName());
                showAlert("Game Loaded", "Spilltilstanden er lastet.");
            } catch (Exception ex) {
                showAlert("Error", "Feil ved lasting: " + ex.getMessage());
            }
        });

        fileMenu.getItems().addAll(saveItem, loadItem);
        menuBar.getMenus().add(fileMenu);
        return menuBar;
    }

    // Oppdaterer UI-brettet ut fra modellens tilstand.
    private void updateGrid() {
        gridPane.getChildren().clear();
        int[][] gridData = game.getBoard().getGrid();

        // Tegn celler med brikker (om noen)
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                StackPane cell = createCell(row, col, gridData[row][col]);
                gridPane.add(cell, col, row);
            }
        }
        // Legger til transparente knapper over hver kolonne for å registrere klikk.
        for (int col = 0; col < cols; col++) {
            int currentCol = col;
            Button colButton = new Button();
            colButton.setPrefWidth(cellSize);
            colButton.setPrefHeight(rows * cellSize);
            colButton.setOpacity(0); // gjør knappen usynlig
            colButton.setOnAction(e -> handleColumnClick(currentCol));
            gridPane.add(colButton, col, 0, 1, rows);
        }
    }

    // Lager én celle i brettet.
    private StackPane createCell(int row, int col, int value) {
        StackPane stack = new StackPane();
        stack.setPrefSize(cellSize, cellSize);
        stack.setStyle("-fx-border-color: black; -fx-background-color: lightblue;");

        // Tegn en sirkel hvis cellen ikke er tom.
        if (value != 0) {
            Circle token = new Circle(cellSize / 2 - 10);
            if (value == 1) {
                token.setFill(Color.RED);
            } else if (value == 2) {
                token.setFill(Color.YELLOW);
            }
            stack.getChildren().add(token);
        }
        return stack;
    }

    // Håndterer klikk i en gitt kolonne.
    private void handleColumnClick(int col) {
        try {
            game.makeMove(col);
            updateGrid();
            if (game.isGameOver()) {
                String msg;
                if (game.getWinner() != null) {
                    msg = game.getWinner().getName() + " vinner!";
                } else {
                    msg = "Uavgjort!";
                }
                showAlert("Spill Slutt", msg);
                statusLabel.setText("Spill over");
            } else {
                statusLabel.setText("Tur: " + game.getCurrentPlayer().getName());
            }
        } catch (IllegalArgumentException ex) {
            showAlert("Ugyldig trekk", ex.getMessage());
        } catch (IllegalStateException ex) {
            showAlert("Spill over", ex.getMessage());
        }
    }

    // Viser en dialogboks med en melding.
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
