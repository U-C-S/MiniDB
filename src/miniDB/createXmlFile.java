package miniDB;

import java.io.File;
import java.io.IOException;

public class createXmlFile {
    private File xmlFile;

    public createXmlFile(String path) {
        try {
            xmlFile = new File(path);
            if (xmlFile.createNewFile()) {
                System.out.println("File created: " + xmlFile.getName());
            } else {
                System.out.println("minidb.xml Found!!");
            }
        } catch (IOException e) {
            System.out.println("Congratulations! You created a ERROR;;");
            e.printStackTrace();
        }
    }

    public File getFile() {
        return xmlFile;
    }
}
