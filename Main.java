import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        DataBaseManager dbManager = new DataBaseManager();
        Scanner scanner = new Scanner(System.in);
        LoginManager loginManager = new LoginManager(dbManager, scanner);

        while (true) {
            System.out.println();
            System.out.println("--- MENU ---");
            System.out.println("1. Add User Credential");
            System.out.println("2. Test Login");
            System.out.println("3. Exit");
            System.out.print("Select an option (1-3): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 


            if (choice == 1) {
                loginManager.addUser();
            } else if (choice == 2) {
                loginManager.login();
            } else if (choice == 3) {
                System.out.println("Exiting program. Goodbye!");
                break;
            } else {
                System.out.println("Invalid option. Please try again.");
            }       
        }
        
        dbManager.closeConnection();
        scanner.close();
    }
}
