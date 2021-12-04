import java.security.MessageDigest;
import java.util.Scanner;
import java.math.BigInteger;

public class Hash {

    private byte[] text;
    private String Algorithm;
    private String hashValue;
    private byte[] digest;

    MessageDigest SHADigest;

    public void setText(String text) {  this.text = text.getBytes(); }
    public void setAlgorithm(String Algorithm) throws Exception { SHADigest = MessageDigest.getInstance(Algorithm); }
    public byte[] digest() { return SHADigest.digest(); }
    public void update() { SHADigest.update(text); } 

}

        // For test only
        // for example
        // Hash hash = new Hash();
        // System.out.println(hash.hash("ملف تبي تشوف تشفر بشكل صحيح او لا"));
 