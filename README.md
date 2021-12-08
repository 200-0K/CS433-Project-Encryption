# CS433 Project Encryption
A simple program that performs symmetric encryption/decryption and hashing on any type of file. 

## Compilation
* _**To compile and/or run, JDK 11 or higher is required.**_

To manually compile and run the program, use JDK 11 or higher and run the following commands (outside the `src` folder):
```bash
$> "path/to/jdk≥11/bin/javac.exe" -d out -cp src\ src\Main.java src\utils\*.java src\classes\*.java src\menus\*.java
$> "path/to/jdk≥11/bin/java.exe" -cp out\ Main
```
Or download the latest `jar` file from the [release](https://github.com/200-0K/CS433-Project-Encryption/releases/latest) page, and run the following:
```bash
$> java -jar "path/to/Encryption.jar"
```
* If you don't have JRE version 11 or higher, then simply download the program bundled with the appropriate version of JRE from the [release](https://github.com/200-0K/CS433-Project-Encryption/releases/latest) page, and execute `Run.bat`

## Features
- Can Encrypt/Decrypt/Hash all type of files e.g. (txt, png, pdf, exe, etc...)
- Selecting a folder path when Encrypting/Decrypting/Hashing will process all files inside it

### Available Encryption Algorithms:
1. AES (192-bits)
2. DESede/Triple DES (192-bits)

### Available Hashing Algorithms:
1. SHA-256
2. SHA-512

## Used Libraries:
#### For the encryption\decryption part: [Cipher](https://docs.oracle.com/javase/8/docs/api/javax/crypto/package-frame.html)
#### For the hashing part: [MessageDigest](https://docs.oracle.com/javase/8/docs/api/java/security/MessageDigest.html) 
