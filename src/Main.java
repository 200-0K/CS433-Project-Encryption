import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("MAIN MENU");
        System.out.println("==========================================================================");
        System.out.println("What do you need to implement?");
        System.out.println("1. Encryption");
        System.out.println("2. Hashing");
        System.out.println("3. Exit");

        System.out.print("Enter your choice: ");
        int choiceOne = sc.nextInt();

        System.out.println("1. Encrypt");
        System.out.println("2. Decryp");
        System.out.println("3. Back to main menu");

        System.out.println("----------------------");
        System.out.print("Enter your choice: ");
        int choiceTwo = sc.nextInt();

        System.out.println("(1) File (2) Folder ");
        System.out.print("Enter your choice: ");
        int choiceThree = sc.nextInt();

        System.out.print("Type your file name: ");
        String fileName = sc.nextLine();

        System.out.print("Choose the algorithm (AES, DES):");
        String algorithm = sc.nextLine();

        System.out.print("Enter the secret key:");
        String secretKey = sc.nextLine();

        System.out.println("----------------------");
        System.out.println("Done! File text.txt is encrypted using AES-192");
        System.out.println("Output file is ");
        System.out.println("--------------------------------------------------------------------------");


    }
}