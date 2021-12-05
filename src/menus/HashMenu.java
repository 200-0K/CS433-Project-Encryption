package menus;

import java.io.FileNotFoundException;
import java.io.IOException;

import classes.Hash;
import classes.Hash.Algorithm;
import utils.Bytes;
import utils.FileSystem;
import utils.UserInput;

public class HashMenu implements IMenu {

    private Hash hash = new Hash();
    private FileSystem fileSystem;

    public void run() {
        while (true) {
            System.out.println("----------------------");
            if (!getAlgorithm()) return;
            processFiles();
        }
    }

    public boolean getAlgorithm() {
        Algorithm[] algorithms = Algorithm.values();
        
        System.out.println("Choose an Algorithm:");
        for (int i = 0; i < algorithms.length; i++)
            System.out.println((i + 1) + ". " + algorithms[i].getAlgorithm());
        
        int backMenuNum = algorithms.length + 1;
        System.out.println(backMenuNum + ". Back to Main Menu");
        System.out.print("Enter your choice: ");

        int algorithmNum = UserInput.getNumberFromUser(1, backMenuNum);
        if (algorithmNum == backMenuNum) return false;
        
        try {
            hash.setAlgorithm(algorithms[algorithmNum - 1]);
            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return false;
    }

    public void processFiles() {
        System.out.println();
        this.fileSystem = UserInput.getFileSystemFromUser();
        FileSystem[] files = getFiles();

        for (FileSystem file : files) {
            try {
                file.read((fileBytes, isFinal) -> {
                    hash.update(fileBytes);
                    if (isFinal) {
                        System.out.println(
                            "* " + file.getFile().getName() + ": " + Bytes.encode(hash.digest())
                        );
                    }
                });
            } catch (FileNotFoundException e) {
                System.out.println(file.getFile().getAbsolutePath() + " is Not Found");
            }catch (IOException e) { System.out.println("I/O Error: " + e.getMessage()); }
        }
    }

    private FileSystem[] getFiles() {
        FileSystem[] files = fileSystem.getFiles(null, true);
        if (files == null) files = new FileSystem[] { fileSystem };
        return files;
    }
}
