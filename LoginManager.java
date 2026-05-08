import java.util.Scanner;

public class LoginManager {
    private int maxAttempts = 3;
    private DataBaseManager dbManager;
    private Scanner scanner;

    public LoginManager(DataBaseManager dbManager, Scanner scanner) {
        this.dbManager = dbManager;
        this.scanner = scanner;
    }

    public void addUser(){
        System.out.print("Enter new username: ");
        String username = scanner.nextLine();

        System.out.print("Enter new password: ");
        String password = scanner.nextLine();

        boolean isAdded = dbManager.addUser(username, password);
        if(isAdded){
            System.out.println("User successfully added.");
        }else{
            System.out.println("Failed to add user.");
    }
}


    public void login() {
    
        int attempts = maxAttempts;

        while (attempts > 0) {
            System.out.print("Enter username: ");
            String username = scanner.nextLine();

            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            boolean isValid = dbManager.validateUser(username, password);

            if(isValid){
                System.out.println("Login successful!");
                return;
            } else{
                attempts--;
                System.out.println("Invalid username or password. Attempts remaining: " + attempts);
            }
             
            if (attempts == 0) {
                System.out.println();
                System.out.println("Terminated. Too many failed attempts.");
                return;
            }else{
                System.out.println();
            }
        }      
    }
}