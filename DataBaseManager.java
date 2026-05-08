import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataBaseManager{

        private static final String url = "jdbc:mysql://localhost:3306/javadb";
        private static final String username = "root";
        private static final String password = "";

        private Connection connection;

        public DataBaseManager(){
            connect();
        }

        private void connect(){
            try {
                connection = DriverManager.getConnection(url, username, password);
            } catch (SQLException e) {
                System.out.println("Failed to connect to the database.");
                e.printStackTrace();
            }
        }
        
        public boolean validateUser (String username, String password){
            String query = "SELECT username, password FROM credentials WHERE username = ? AND password = ?";
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setString(1, username);
                stmt.setString(2, password);
                ResultSet rs = stmt.executeQuery();
                return rs.next();
            } catch (SQLException e) {
                System.out.println("Error validating user.");
                e.printStackTrace();
                return false;
            }
        }

        public boolean addUser(String username, String password){
            String query = "INSERT INTO credentials (username, password) VALUES (?, ?)";
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setString(1, username);
                stmt.setString(2, password);
                int rowsAffected = stmt.executeUpdate();
                return rowsAffected > 0;
            } catch (SQLException e) {
                System.out.println("Error adding user.");
                e.printStackTrace();
                return false;
            }
        }

        public void closeConnection(){
            try {
                if(connection != null && !connection.isClosed()){
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println("Error closing database connection.");
                e.printStackTrace();
            }
        }


}
