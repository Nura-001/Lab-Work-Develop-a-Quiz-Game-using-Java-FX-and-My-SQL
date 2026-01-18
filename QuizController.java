import java.sql.*;
import java.util.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.*;
import javafx.util.Duration;

/*
 * This class controls quiz logic,
 * random question loading, timer, and score
 */
public class QuizController {

    List<Question> questions = new ArrayList<>();
    int currentIndex = 0;
    int score = 0;
    int timeLeft = 15;

    Label timerLabel = new Label("Time: 15");
    Timeline timer;

    // Load 5 random questions from database
    public void loadQuestions() throws Exception {
        Connection con = DBConnection.getConnection();
        Statement st = con.createStatement();

        ResultSet rs = st.executeQuery(
            "SELECT * FROM questions ORDER BY RAND() LIMIT 5"
        );

        while (rs.next()) {
            questions.add(new Question(
                rs.getString("question"),
                rs.getString("optionA"),
                rs.getString("optionB"),
                rs.getString("optionC"),
                rs.getString("optionD"),
                rs.getString("correct").charAt(0)
            ));
        }
    }

    // Start countdown timer
    public void startTimer() {
        timer = new Timeline(
            new KeyFrame(Duration.seconds(1), e -> {
                timeLeft--;
                timerLabel.setText("Time: " + timeLeft);

                if (timeLeft == 0) {
                    nextQuestion('X');
                }
            })
        );
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();
    }

    // Move to next question
    public void nextQuestion(char answer) {
        timer.stop();

        if (answer == questions.get(currentIndex).correctAnswer) {
            score++;
        }

        currentIndex++;
        timeLeft = 15;

        if (currentIndex < questions.size()) {
            startTimer();
        } else {
            saveScore();
        }
    }

    // Save player score to database
    public void saveScore() {
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO scores (player_name, score) VALUES (?, ?)"
            );
            ps.setString(1, Main.playerName);
            ps.setInt(2, score);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
