package classes;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {
    public enum Algorithm {
        SHA256("SHA-256"),
        SHA512("SHA-512");

        private String algorithm;

        private Algorithm(String algorithm) {
            this.algorithm = algorithm;
        }

        public String getAlgorithm() {
            return algorithm;
        }
    }

    MessageDigest mDigest;

    public void setAlgorithm(Algorithm algorithm) throws NoSuchAlgorithmException {
        mDigest = MessageDigest.getInstance(algorithm.getAlgorithm());
    }

    public void update(byte[] bytes) {
        mDigest.update(bytes);
    }

    public byte[] digest() {
        return mDigest.digest();
    }
}