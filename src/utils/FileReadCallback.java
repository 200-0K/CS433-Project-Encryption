package utils;

public interface FileReadCallback {
    void callback(byte[] fileBytes, boolean isFinal);
}
