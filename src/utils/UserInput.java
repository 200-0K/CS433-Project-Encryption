package utils;
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
                System.out.println("Error "+e.getMessage()+", try again");
                e.printStackTrace();
                sc.nextLine(); // important to clear the buffer
            }
            System.out.print(": ");
        }
        return num;
    }

    public static FileSystem getFileSystemFromUser() {
        Scanner sc = new Scanner(System.in);
        FileSystem file;
        System.out.print("Type File/Directory Path: ");
        while(true) {
            String path = "";
            try {
                path = sc.nextLine();
                file = new FileSystem(path);
                break;
            } catch (FileNotFoundException e) {
                System.out.println("'"+path+"'"+" is not a path for a file or directory, try again");
            }
            System.out.print(": ");
        }
        return file;
    }
}
