package utils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileSystem {
    public static final int BUFFER_SIZE_BYTES = 500000000; // 500MB
    private File file;

    public FileSystem(String path) throws FileNotFoundException {this(new File(path));}
    public FileSystem(File file) throws FileNotFoundException {
        if (!file.exists()) throw new FileNotFoundException();
        this.file = file;
    }

    /**
     * Get all files within current <code>FileSystem</code>
     * @return <code>FileSystem[]</code> contains all files matching the filters | 
     * <code>null</code> if the current <code>FileSystem</code> is not a directory
     */
    public FileSystem[] getFiles() {return getFiles(null, false);}
    /**
     * Get all files within current <code>FileSystem</code>
     * 
     * @param suffix filter files by file name sffuix | pass <code>null</code> to skip suffix filtering
     * @param excludeDirectories only get files not folder/directories
     * @return <code>FileSystem[]</code> contains all files matching the filters | 
     * <code>null</code> if the current <code>FileSystem</code> is not a directory
     */
    public FileSystem[] getFiles(String suffix, boolean excludeDirectories) {
        if (!file.isDirectory()) return null;
        
        File[] filteredFiles;
        filteredFiles = file.listFiles((File file) -> 
            (suffix != null ? file.getAbsolutePath().endsWith(suffix) : true) &&
            (excludeDirectories ? file.isFile() : true)
        );

        FileSystem[] files = new FileSystem[filteredFiles.length];
        for (int i = 0; i < filteredFiles.length; i++)
            try {
                files[i] = new FileSystem(filteredFiles[i]);
            } catch (FileNotFoundException e) {e.printStackTrace();}

        return files;
    }

    public File getFile() {
        return this.file;
    }
    
    /**
     * Create a new file sibiling to the current file if 
     * it is a file and not a directory, or a new file within a 
     * directory if the current file is a directory
     * 
     * @param fileName in the form [name].[ext]
     * @return new <code>FileSystem</code> object with respect to the current <code>FileSystem</code> file location
     * @throws IOException if an I/O error occurs
     */
    public FileSystem createFile(String fileName) throws IOException {
        File newFile = file;
        if (file.isFile()) newFile = file.getParentFile();
        
        newFile = newFile.toPath().resolve(fileName).toFile();
        newFile.createNewFile();
        return new FileSystem(newFile);
    }

    /**
     * <p>Create and return a directory <code>FileSystem</code> if not exists</p>
     * <p>If current <code>FileSystem</code> is a file, then create a directory at the same level i.e. its parent</p>
     * 
     * @param dirName directory name that will be created
     * @return directory <code>FileSystem</code> is created or exists or <code>null</code> otherwise
     */
    public FileSystem createDirectory(String dirName) throws FileNotFoundException {
        File newDir = file;
        if (file.isFile()) newDir = file.getParentFile();
        newDir = newDir.toPath().resolve(dirName).toFile();

        newDir.mkdir();
        return new FileSystem(newDir);
    }

    /**
     * "Note that this method is intended for simple cases where it is
     * convenient to read all bytes into a byte array. It is not intended for
     * reading input streams with large amounts of data"
     * 
     * @return All bytes of this <code>FileSystem</code> file
     * @throws IOException if an I/O error occurs
     */
    public byte[] readAllBytes() throws IOException {
        FileInputStream fInputStream = new FileInputStream(file);
        byte[] fileBytes = fInputStream.readAllBytes();
        fInputStream.close();
        return fileBytes;
    }

    public void read(FileReadCallback callback) throws IOException {
        FileInputStream fInputStream = new FileInputStream(file);
        byte[] fileBytes;
        int available;
        while ((available = fInputStream.available()) != 0) {
            boolean isFinal = (BUFFER_SIZE_BYTES >= available);
            int readBytes = (isFinal) ? available : BUFFER_SIZE_BYTES;
            fileBytes = fInputStream.readNBytes(readBytes);
            callback.callback(fileBytes, isFinal);
        }
        fInputStream.close();
    }

    public void append(byte[] content) throws IOException {
        FileOutputStream fInputStream = new FileOutputStream(file, true);
        fInputStream.write(content);
        fInputStream.close();
    }
}