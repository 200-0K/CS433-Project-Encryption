rmdir /s /q out
cd src
"C:\Program Files\Java\jdk-11.0.1\bin\javac.exe" -d ..\out -cp .\ *.java
cd ..\out
mkdir jar
"C:\Program Files\Java\jdk-11.0.1\bin\jar.exe" cvfm jar\Encryption.jar ..\src\MANIFEST.MF .
cls
"C:\Program Files\Java\jdk-11.0.1\bin\java.exe" -jar jar\Encryption.jar
pause