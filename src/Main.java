import menus.Menu;
import utils.UserInput;

public class Main {
    public static void main(String[] args) {
        Menu[] menus = Menu.values();
        while (true) {
            StringBuilder sBuilder = new StringBuilder();
            sBuilder.append("MAIN MENU\n");
            sBuilder.append("==========================================================================\n");
            sBuilder.append("What do you need to implement?\n");
            for (int i = 0; i < menus.length; i++) sBuilder.append(i+1).append(". ").append(menus[i].getMenuName()).append("\n");
            
            int exitNum = menus.length+1;
            sBuilder.append(exitNum).append(". Exit");

            System.out.println(sBuilder.toString());
            System.out.print("Enter your choice: ");
            
            int num = UserInput.getNumberFromUser(1, exitNum);
            if (num == exitNum) return;
            menus[num-1].getMenu().run();

            System.out.println("\033[H\033[2J"); // clear screen
        }
    }
}