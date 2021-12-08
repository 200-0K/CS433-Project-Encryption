# CS433 Project Encryption
## Compilation
* _**To compile or run, JDK 11 or higher is required.**_

To manually compile and run the program, use JDK 11 or higher and run the following commands (outside the `src` folder):
```bash
$> "path/to/jdk≥11/bin/javac.exe" -d out -cp src\ src\Main.java src\utils\*.java src\classes\*.java src\menus\*.java
$> "path/to/jdk≥11/bin/java.exe" -cp out\ Main
```
Or download the latest `jar` file from the [release](https://github.com/200-0K/CS433-Project-Encryption/releases/latest) page, and run the following:
```bash
$> java -jar "path/to/Encryption.jar"
```
* If you don't have JDK version 11 or higher, then simply download the JRE version from the [release](https://github.com/200-0K/CS433-Project-Encryption/releases/latest) page, and execute `Run.bat`

## Features
- Can Encrypt/Decrypt/Hash all type of files e.g. (txt, png, pdf, exe, etc...)
- Selecting a folder path when Encrypting/Decrypting/Hashing will process all files inside it

### Available Encryption Algorithms:
1. AES (192-bits)
2. DESede/Triple DES (192-bits)

### Available Hashing Algorithms:
1. SHA-256
2. SHA-512

___________
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
