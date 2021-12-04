package menus;

public class HashMenu implements IMenu {
    
    public void run() {
        // Loop:
            // ask about type of algorithm or go back to main menu
            // ask about file/dir path
            
            // Loop: process files one by one (Copy getFiles() from EncryptMenu)
                // call update for the message digest object with the help of FileSystem.read callback (See processFiles() method from EncryptMenu)
                // use isFinal value of the callback to call digest method
                // print digest value using BigInteger(1, digest).toString(16), e.g.
                    // test.txt: ee26b0dd4af7e749aa1a8ee3c10ae9923f618980772e473f8819a5d4940e0db27ac185f8a0e1d5f84f88bc887fd67b143732c304cc5fa9ad8e6f57f50028a8ff
            //
        //
    }
}
