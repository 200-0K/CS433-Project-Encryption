import java.io.File;

public class FileSystem {
    private File file;

    public FileSystem(String path) {this(new File(path));}
    public FileSystem(File file) {
        this.file = file;
    }

    public File[] getFiles(String suffix) {
        if (!file.isDirectory()) return null;
        File[] filteredFiles = file.listFiles((File file) -> file.getAbsolutePath().endsWith(suffix));
        return filteredFiles;
    }
}