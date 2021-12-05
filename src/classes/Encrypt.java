package classes;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
/**
 * <p> To Use: Set Tranformation ➔ Set Key ➔ Set Mode </p>
 * <p> Loop: Set Text ➔ Update </p>
 */
public class Encrypt {
    public enum Transformation {
        AES("AES", 24), 
        TDES("DESede", 24);

        private String transformation;
        private String algorithm;
        private int keySizeInBytes;
        private Transformation(String transformation, int keySizeInBytes) {
            this.transformation = transformation;
            this.keySizeInBytes = keySizeInBytes;
            algorithm = transformation.split("/")[0];
        }

        public String getAlgorithm() {return algorithm;}
        public String getTransformation() {return transformation;}
        public int getKeySizeInBytes() {return keySizeInBytes;}
    }

    public static final String CHARSET = "UTF-8";
    private Transformation transformation;
    private byte[] text;
    private Key key;
    private Cipher cipher;

    private Encrypt(){}

    public void setAlgorithm(Encrypt.Transformation transformation) throws NoSuchAlgorithmException, NoSuchPaddingException  {
        this.transformation = transformation;
        cipher = Cipher.getInstance(transformation.getTransformation());
    }

    public void setText(String text) {this.setText(text.getBytes(Charset.forName(CHARSET)));}
    public void setText(byte[] text) {
        this.text = text;
    }

    public void setKey(String key) {this.setKey(key.getBytes(Charset.forName(CHARSET)));}
    public void setKey(byte[] key) {
        this.key = new SecretKeySpec(key, this.transformation.getAlgorithm()); 
    }

    public void encryptMode() throws InvalidKeyException {
        cipher.init(Cipher.ENCRYPT_MODE, key);
    }

    public void decryptMode() throws InvalidKeyException {
        cipher.init(Cipher.DECRYPT_MODE, key);
    }

    public byte[] update(boolean isFinal) throws IllegalBlockSizeException, BadPaddingException {
        return (isFinal) ? cipher.doFinal(text) : cipher.update(text);
    }

    public Transformation getTransformation() {
        return transformation;
    }

    public static class Builder {
        private Transformation tr;
        private String key;
        private boolean isEncryptMode;

        public Builder setAlgorithm(Transformation tr) {
            this.tr = tr;
            return this;
        }

        public Builder setKey(String key) {
            this.key = key;
            return this;
        }

        public Builder setEncryptionMode(boolean isEncryptMode) {
            this.isEncryptMode = isEncryptMode;
            return this;
        }

        public Encrypt build() {
            Encrypt encrypt = null;
            try {
                encrypt = new Encrypt();
                encrypt.setAlgorithm(tr);
                encrypt.setKey(key);
                if (isEncryptMode) encrypt.encryptMode();
                else encrypt.decryptMode();
            } catch (Exception e) {e.printStackTrace();}
            return encrypt;
        }
    }
}