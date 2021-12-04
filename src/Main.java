import menus.EncryptMenu;
import menus.HashMenu;
import utils.UserInput;

public class Main {
    public static void main(String[] args) {
        mainMenu();
    }

    static void mainMenu() {
        while (true) {
            System.out.println("MAIN MENU");
            System.out.println("==========================================================================");
            System.out.println("What do you need to implement?");
            System.out.println("1. Encryption");
            System.out.println("2. Hashing");
            System.out.println("3. Exit");

            while (true) {
                System.out.print("Enter your choice: ");
                int num = UserInput.getNumberFromUser();

                if (num == 1) new EncryptMenu().run();
                else if (num == 2) new HashMenu().run();
                else if (num == 3) return;
                else continue;

                break;
            }

            System.out.println();
        }
    }
}