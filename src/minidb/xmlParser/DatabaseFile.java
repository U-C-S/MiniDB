package minidb.xmlParser;

import org.w3c.dom.*;
import org.xml.sax.SAXException;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class DatabaseFile {
    private File xmlFile;
    private Document doc;

    // public DatabaseFile(String path) {
    // this.xmlFile = new File(path);
    // }

    public DatabaseFile(String path) {
        try {
            xmlFile = new File(path);
            if (xmlFile.createNewFile()) {
                initalize();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initalize() {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            doc = docBuilder.newDocument();

            Element rootElem = doc.createElement("root");
            doc.appendChild(rootElem);

            this.UpdateFile();

        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    private void UpdateFile() {
        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "no");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");

            DOMSource source = new DOMSource(this.doc);
            StreamResult result = new StreamResult(this.xmlFile);
            transformer.transform(source, result);

        } catch (TransformerException err) {
            err.printStackTrace();
        }
    }

    public void blah() {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {
            Document doc = dbf.newDocumentBuilder().parse(xmlFile);
            doc.getDocumentElement().normalize();
            System.out.println("Root Element: " + doc.getDocumentElement().getNodeName());

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    public Document getDocument() {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            doc = docBuilder.parse(xmlFile);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return doc;
    }
}
