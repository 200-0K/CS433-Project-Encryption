import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

interface FileReadCallback {
    void callback(byte[] fileBytes, boolean isFinal);
}

public class FileSystem {
    public static final int BUFFER_SIZE_BYTES = 10000000; // 10MB
    private File file;

    public FileSystem(String path) throws FileNotFoundException {this(new File(path));}
    public FileSystem(File file) throws FileNotFoundException {
        if (!file.exists()) throw new FileNotFoundException();
        this.file = file;
    }

    public FileSystem[] getFiles(String suffix) {
        if (!file.isDirectory()) return null;
        File[] filteredFiles = file.listFiles((File file) -> file.getAbsolutePath().endsWith(suffix));

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

    public FileSystem createSiblingFile(String fileName) throws IOException {
        return createFile(file.getParentFile().toPath().resolve(fileName).toFile());
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

    public static FileSystem createFile(String path) throws IOException {return createFile(new File(path));}
    public static FileSystem createFile(File file) throws IOException {
        file.createNewFile();
        return new FileSystem(file);
    }
}