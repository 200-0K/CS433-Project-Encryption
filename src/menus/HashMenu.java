package menus;

public class HashMenu implements IMenu {
    
    public void run() {
        // Loop:
            // ask about type of algorithm or go back to main menu
            // ask about file/dir path
            
            // Loop: process files one by one
                // create new dir for the hashed files
                // create new file inside dir for the hash
                // update with the help of FileSystem.read callback
                // use isFinal value of the callback to call digest
                // append digest to the new file
            //

            // print summary message with the following:
                // Alogirhtm used
                // Number of hashed files
                // Output dir
            //
        //
    }
}
