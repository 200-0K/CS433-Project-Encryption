package utils;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInput {
    public static int getNumberFromUser() {
        Scanner sc = new Scanner(System.in);
        int num;
        while (true) {
            try {
                num = sc.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("You must enter a number, try again");
                sc.nextLine(); // important to clear the buffer
            } catch (Exception e) {
                System.out.println("Error"+e.getMessage()+", try again");
                e.printStackTrace();
                sc.nextLine(); // important to clear the buffer
            }
            System.out.print(": ");
        }
        return num;
    }

    public static File getFileFromUser() {
        Scanner sc = new Scanner(System.in);
        File file;
        while(true) {
            String path = "";
            try {
                path = sc.nextLine();
                file = new File(path);
                if (file.exists()) break;
                throw new FileNotFoundException();
            } catch (FileNotFoundException e) {
                System.out.println("'"+path+"'"+" is not a path for a file or directory, try again");
            }
            System.out.print(": ");
        }
        return file;
    }
}
