import java.sql.Connection;
import java.sql.DriverManager;

/*
 * This class is responsible for connecting
 * the JavaFX application with MySQL database
 */
public class DBConnection {

    public static Connection getConnection() {
        try {
            // Change username and password if needed
            return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/quizdb",
                "root",
                "password"
            );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
