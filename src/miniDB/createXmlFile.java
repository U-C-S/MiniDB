package miniDB;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;

public class createXmlFile {
    private File xmlFile;

    public createXmlFile(String path) {
        xmlFile = new File(path);
    }

    public void newInstance() {
        try {
            if (xmlFile.createNewFile()) {
                System.out.println("File created: " + xmlFile.getName());
                this.createEmptyDbEntry();
            } else {
                System.out.println("A existing `minidb.xml` Found!!");
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

    private Element createDbEntry(Document doc, String name, String disabled) throws ParserConfigurationException {
        Element databaseElem = doc.createElement("database");
        Element nameElem = doc.createElement("name");
        Element pathElem = doc.createElement("path");
        Element createTime = doc.createElement("createdTime");
        Element updateTime = doc.createElement("lastUpdateTime");

        DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_DATE_TIME; // Superior ISO8601 Format
        LocalDateTime now = LocalDateTime.now();
        String timeNow = dtf.format(now);

        String dbPath = constants.DB_DIR_PATH + name + ".xml";

        nameElem.appendChild(doc.createTextNode(name));
        pathElem.appendChild(doc.createTextNode(dbPath));
        createTime.appendChild(doc.createTextNode(timeNow));
        updateTime.appendChild(doc.createTextNode(timeNow));

        databaseElem.appendChild(nameElem);
        databaseElem.appendChild(pathElem);
        databaseElem.appendChild(createTime);
        databaseElem.appendChild(updateTime);
        databaseElem.setAttribute("disabled", disabled);

        return databaseElem;
    }

    /**
     * Method for creating a new database. Which means both creating a entry in the
     * minidb.xml and A new database-xml file.
     * 
     * @param name - The name of the database
     */
    public void createNewDatabase(String name) {
        try {
            DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document db = docBuilder.parse(xmlFile);

            db.getDocumentElement().appendChild(createDbEntry(db, name, "false"));

            Transformer tf = TransformerFactory.newInstance().newTransformer();
            tf.setOutputProperty(OutputKeys.INDENT, "yes");
            tf.setOutputProperty(OutputKeys.METHOD, "xml");

            DOMSource domSource = new DOMSource(db);
            StreamResult sr = new StreamResult(xmlFile);
            tf.transform(domSource, sr);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
