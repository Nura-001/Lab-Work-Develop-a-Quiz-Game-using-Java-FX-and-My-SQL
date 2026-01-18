import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/*
 * Main JavaFX class
 * Handles UI, exit, and restart
 */
public class Main extends Application {

    static String playerName;

    @Override
    public void start(Stage stage) {

        Label title = new Label("C Programming Quiz");
        TextField nameField = new TextField();
        nameField.setPromptText("Enter your name");

        Button startBtn = new Button("Start Quiz");
        Button exitBtn = new Button("Exit");

        startBtn.setOnAction(e -> {
            playerName = nameField.getText();
            // Quiz screen logic can be extended
        });

        exitBtn.setOnAction(e -> stage.close());

        VBox root = new VBox(10, title, nameField, startBtn, exitBtn);

        stage.setScene(new Scene(root, 300, 200));
        stage.setTitle("Quiz Game");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
