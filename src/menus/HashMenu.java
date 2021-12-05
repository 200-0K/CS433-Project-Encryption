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
        getAlgorithm();
        processFiles();
    }

    public void getAlgorithm() {
        while (true) {
            ALGORITHM[] algorithms = ALGORITHM.values();

            System.out.println("Choose an Algorithms:");
            for (int i = 0; i < algorithms.length; i++)
                System.out.println((i + 1) + ". " + algorithms[i].getAlgorithm());
            System.out.println((algorithms.length + 1) + ". Back to Main Menu");
            System.out.print("Enter your choice: ");

            int algorithmNum = UserInput.getNumberFromUser();
            try {
                hash.setAlgorithm(algorithms[algorithmNum - 1]);
            } catch (IndexOutOfBoundsException e) {
                if (algorithmNum == algorithms.length + 1)
                    return;
                System.out.println("Please choose a number from the list");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

            processFiles();
        }
    }

    public void processFiles() {
        while (true) {
            this.fileSystem = UserInput.getFileSystemFromUser();
            FileSystem[] files = getFiles();

            for (FileSystem file : files) {
                try {
                    file.read((fileBytes, isFinal) -> {
                        hash.update(fileBytes);

                        if (isFinal) {
                            System.out.println(
                                    file.getFile().getName() + ": " + new BigInteger(1, hash.digest()).toString(16));
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
    }
}
