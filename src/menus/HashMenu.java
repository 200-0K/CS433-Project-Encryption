package menus;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.rmi.StubNotFoundException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import javax.sound.sampled.SourceDataLine;

import classes.Hash;
import classes.Hash.ALGORITHM;
import utils.FileSystem;
import utils.UserInput;

public class HashMenu implements IMenu {
    
    private Hash hash = new Hash();
    private FileSystem fileSystem;
    private Scanner sc;

    public void run() {
        sc = new Scanner(System.in);
        while (true) {
            ALGORITHM[] algorithms = ALGORITHM.values();

            System.out.println("Choose an Algorithms:");
            for (int i = 0; i < algorithms.length; i++) System.out.println( (i+1) + ". " + algorithms[i].getAlgorithm() );
            System.out.println( (algorithms.length + 1) + ". Back to Main Menu");
            System.out.print("Enter your choice: ");

            int algorithmNum = UserInput.getNumberFromUser();
            try {
                hash.setAlgorithm(algorithms[algorithmNum-1]);
            } catch (IndexOutOfBoundsException e) {
                if (algorithmNum == algorithms.length + 1) return;
                System.out.println("Please choose a number from the list");
            } catch (Exception e) {
                System.out.println("Error: "+e.getMessage());
            }

            this.fileSystem = UserInput.getFileSystemFromUser();
            FileSystem[] files = getFiles();

            for (FileSystem file : files) {
                try {
                    file.read((fileBytes, isFinal) -> {
                        hash.update(fileBytes);

                        if(isFinal) {
                            System.out.println(file.getFile().getName() + ": " + new BigInteger(1,  hash.digest()).toString(16));
                        }
                        
                    });
                } catch (IOException e) {
                    System.out.println("I/O Error");
                    e.printStackTrace();
                }
            } 
        } 

    }

    private FileSystem[] getFiles() {
        FileSystem[] files = fileSystem.getFiles(null, true);
        if (files == null)
            files = new FileSystem[] { fileSystem };
        return files;

        // Loop:
            // ask about type of algorithm or go back to main menu
            // ask about file/dir path
            
            // Loop: process files one by one (Copy getFiles() from EncryptMenu)
                // call update for the message digest object with the help of FileSystem.read callback (See processFiles() method from EncryptMenu)
                // use isFinal value of the callback to call digest method
                // print digest value using BigInteger(1, digest).toString(16), e.g.
                    // test.txt: ee26b0dd4af7e749aa1a8ee3c10ae9923f618980772e473f8819a5d4940e0db27ac185f8a0e1d5f84f88bc887fd67b143732c304cc5fa9ad8e6f57f50028a8ff
            //
        //
    }
}
