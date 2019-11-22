package ma.sqli.factories;

import ma.sqli.entities.File;

public class FileFactory {
    public File getFileInstance(String name){
        return new File(name);
    }
}
