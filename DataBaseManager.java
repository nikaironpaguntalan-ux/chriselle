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
        //this is the constructor that will be called when we create an instance of DataBaseManager. It will automatically call the connect method to establish a connection to the database.
        public DataBaseManager(){
            connect();
        }
        //this method is responsible for establishing a connection to the MySQL database using the provided URL, username, and password. If the connection is successful, it will be stored in the connection variable. If there is an error during the connection process, it will print an error message and stack trace.
        private void connect(){

            try {
                connection = DriverManager.getConnection(url, username, password);
            } catch (SQLException e) {
                System.out.println("Failed to connect to the database.");
                e.printStackTrace();
            }
        }
        //this method takes a username and password as parameters and checks if they exist in the credentials table of the database. It uses a prepared statement to prevent SQL injection attacks. If a matching record is found, it returns true, indicating that the user is valid. If there is an error during the validation process, it will print an error message and stack trace, and return false.
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
        //this method takes a username and password as parameters and attempts to add a new user credential to the credentials table in the database. It uses a prepared statement to prevent SQL injection attacks. If the insertion is successful, it returns true. If there is an error during the addition process, it will print an error message and stack trace, and return false.
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
        //this method is responsible for closing the database connection when it is no longer needed. It checks if the connection is not null and is still open before attempting to close it. If there is an error during the closing process, it will print an error message and stack trace.
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
