package miniDB;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class createXmlFile {
    private File xmlFile;

    public createXmlFile(String path) {
        try {
            xmlFile = new File(path);
            if (xmlFile.createNewFile()) {
                System.out.println("File created: " + xmlFile.getName());
                this.createEmptyDbEntry();
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

    private void createEmptyDbEntry() {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();

            Element rootElem = doc.createElement("root");
            Element emptyDb = this.createDbEntry(doc, "empty", "true");

            rootElem.appendChild(emptyDb);
            doc.appendChild(rootElem);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(this.xmlFile);
            transformer.transform(source, result);
            // System.out.println("File saved!");

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }

    public Element createDbEntry(Document doc, String name, String disabled) throws ParserConfigurationException {
        Element databaseElem = doc.createElement("database");
        Element nameElem = doc.createElement("name");
        Element pathElem = doc.createElement("path");
        Element createTime = doc.createElement("createdTime");
        Element updateTime = doc.createElement("lastUpdateTime");

        nameElem.appendChild(doc.createTextNode(name));
        pathElem.appendChild(doc.createTextNode("null"));
        createTime.appendChild(doc.createTextNode("null"));
        updateTime.appendChild(doc.createTextNode("null"));

        databaseElem.appendChild(nameElem);
        databaseElem.appendChild(pathElem);
        databaseElem.appendChild(createTime);
        databaseElem.appendChild(updateTime);
        databaseElem.setAttribute("disabled", disabled);

        return databaseElem;
    }

    public void createNewDatabase(String name) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document db = docBuilder.parse(xmlFile);

    }
}
