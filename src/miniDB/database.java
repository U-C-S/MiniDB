package miniDB;

import org.w3c.dom.*;
import org.xml.sax.SAXException;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.*;

public class database {
    private File xmlFile;

    public database(String path) {
        this.xmlFile = new File(path);
    }

    private void initalizeFile() {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }

    }

    public void readXML() {
        
    }

    public database(File path) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {
            Document doc = dbf.newDocumentBuilder().parse(path);
            doc.getDocumentElement().normalize();
            System.out.println("Root Element: " + doc.getDocumentElement().getNodeName());

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}
