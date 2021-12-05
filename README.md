# CS433 Project Encryption
## Requirements
To manually compile and execute the program, use a JDK 9 or higher and do the following (outside the'src' folder):
```bash
    $> "path/to/jdk≥9/bin/javac.exe" -d out -cp src\ src\Main.java src\utils\*.java src\classes\*.java src\menus\*.java
    $> "path/to/jdk≥9/bin/java.exe" -cp out\ Main
```
If you don't want to go through all of that, simply download the most recent executable file from [release](https://github.com/200-0K/CS433-Project-Encryption/releases)

## Main tasks:
1. Implement a symmetric cryptographic system, using Java, that encrypts/decrypts all text files in a given folder or single text file. Your system should allow the user to choose between **DES** or **AES**.
2. Implement a one-way hash function using Java, that generates a message digest of a given file. Your system should allow the user to choose between **SHA-256** or **SHA-512**.

###  For the encryption\decryption part:
1. Get the key from the user. DES algorithm requires a 64-bit key while AES operates on a default key size of 192 bits.
2. Create an instance of the class Cipher for the chosen algorithm transformation. Refer to the document of the [Cipher](https://docs.oracle.com/javase/8/docs/api/javax/crypto/package-frame.html) class for more information regarding supported algorithms and transformations.
3. Initialize the Cipher with an appropriate mode (encrypt or decrypt) and the given Key.
4. Invoke the doFinal(input_bytes) method of the Cipher class to perform encryption or decryption on the input_bytes, which returns an encrypted or decrypted byte array.
5. Read an input file to a byte array and write the encrypted/decrypted byte array to an output file accordingly.

### For the hashing part:
1. Create an instance of the class MessageDigest for the chosen algorithm. Refer to the document of the [MessageDigest](https://docs.oracle.com/javase/8/docs/api/java/security/MessageDigest.html) class for more information regarding supported algorithms.
2. Invoke digest(input_bytes) method of the MessageDigest class to perform hashing on the input_bytes, which returns the generated message digest.
3. Read an input file to a byte array and write the message digest to an output file accordingly.

#### A code snippets from the main() method is provided below. Your code may look different though!
```java
    //encryption / decryption part 
    String File inputFile = new File(filename);
    // to encrypt 
    File encryptedFile = new File(filename + ".encrypted");
    // to decrypt 
    File decryptedFile = new File(filename + ".decrypted");
    try {
        Crypto.encrypt(key, inputFile, encryptedFile);
        Crypto.decrypt(key, encryptedFile, decryptedFile);
    } catch (CryptoException ex) {
        System.out.println(ex.getMessage());
        ex.printStackTrace();
    } 
    //hashing part 
    MessageDigest SHADigest = MessageDigest.getInstance(Chosen algorithm);
    byte[] digest = SHADigest.digest(inputFile);
```
#### Sample Run

    MAIN MENU 
    ==========================================================================
    What do you need to implement?
    1. Encryption 
    2. Hashing 
    3. Exit
    
    Enter your choice: 1 
    1. Encrypt
    2. Decrypt
    3. Back to main menu 
    ---------------------- 
    Enter your choice: 1 
    (1) File (2) Folder 
    Enter your choice: 1 
    Type your file name: text.txt
    Choose the algorithm (AES, DES): AES 
    Enter the secret key : aaaaaaaaaaaaaaaaaaaaaaaa 
    ----------------------
    Done! File text.txt is encrypted using AES-192
    Output file is text.encrypted 
    --------------------------------------------------------------------------
    
    MAIN MENU 
    ==========================================================================
    What do you need to implement?
    1. Encryption 
    2. Hashing 
    3. Exit
    
    Enter your choice: 3
