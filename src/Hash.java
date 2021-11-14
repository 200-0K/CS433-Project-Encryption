import java.io.File;
import java.security.MessageDigest;
import java.util.Scanner;

public class Hash {

    public static void main(String[] arge) {

        Scanner input = new Scanner(System.in);

        System.out.print("Enter full path of your file : ");
        String PathFile = input.nextLine();

        System.out.print("Chooce the Algorithm (SHA-256 , SHA-512) : ");
        String TypeAlgorthim = input.nextLine();

        System.out.print(Hashfunction(PathFile, TypeAlgorthim));

    }

    public static String Hashfunction(String FailePath, String algorithm) {

        String hashValue = "";

        try {
            String DataOfHash = ""; // To save text from the faile
            File MyFile = new File(FailePath + ".txt"); 
            Scanner myReader = new Scanner(MyFile);

           // To read file 
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                DataOfHash = data;
            }
            myReader.close();
           
            MessageDigest SHADigest = MessageDigest.getInstance(algorithm);
             SHADigest.update(DataOfHash.getBytes());
              byte[] digest = SHADigest.digest();
               
              StringBuffer Sb = new StringBuffer(); 
                for (byte A : digest) {
                    Sb.append(String.format("%02x", A & 0xff));
                    hashValue = Sb.toString();
            }
        } catch (Exception e) {

        }
        return hashValue;
    }

}

        // For test only
        // for example
        // Hash hash = new Hash();
        // System.out.println(hash.hash("ملف تبي تشوف تشفر بشكل صحيح او لا"));
 