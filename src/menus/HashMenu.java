package menus;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import classes.Hash;
import classes.Hash.Algorithm;
import utils.Bytes;
import utils.FileSystem;
import utils.Parallel;
import utils.ParallelParameters;
import utils.UserInput;
import utils.Timer;

public class HashMenu implements IMenu {

    private FileSystem fileSystem;
    private Algorithm algorithm;

    public void run() {
        while (true) {
            System.out.println("----------------------");
            if (!getAlgorithm()) return;

            this.fileSystem = UserInput.getFileSystemFromUser();
            FileSystem[] files = getFiles();

            Timer timer = new Timer();
            timer.start();

            processFiles(files);

            timer.stop();
            System.out.println("Finish in: "+timer.stop());
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
            this.algorithm = algorithms[algorithmNum - 1];
            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return false;
    }

    public void processFiles(FileSystem[] files) {
        System.out.println();
        
        Parallel.runParallel(files.length, (ParallelParameters parallelParameters) -> {
            Hash hash = new Hash();
            try {
                hash.setAlgorithm(this.algorithm);
            } catch (NoSuchAlgorithmException e) {e.printStackTrace(); return;}

            int my_rank = parallelParameters.my_rank;
            int my_start = parallelParameters.my_start;
            int my_last = parallelParameters.my_last;
            for (int i = my_start; i < my_last; i++) {
                FileSystem file = files[i];

                try {
                    file.read((fileBytes, isFinal) -> {
                        hash.update(fileBytes);
                        if (isFinal) {
                            System.out.print(
                                "* Thread " + my_rank + ": " + file.getFile().getName() + ": " + Bytes.encode(hash.digest()) + "\n"
                            );
                        }
                    });
                }
                catch (FileNotFoundException e) { System.out.println(file.getFile().getAbsolutePath() + " is Not Found"); } 
                catch (IOException e) { System.out.println("I/O Error: " + e.getMessage()); }
                catch (Exception e) { System.out.println("Error: " + e.getMessage()); }
            }
        });
    }

    private FileSystem[] getFiles() {
        FileSystem[] files = fileSystem.getFiles(null, true);
        if (files == null) files = new FileSystem[] { fileSystem };
        return files;
    }
}