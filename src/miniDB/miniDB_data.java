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

/**
 * Contains the methods for performing CRUD operations on file 'minidb.xml'
 */
public class miniDB_data {
    private File xmlFile;

    public File getFile() {
        return xmlFile;
    }

    public miniDB_data(String path) {
        xmlFile = new File(path);
    }

    public void load() {
        try {
            if (xmlFile.createNewFile()) {
                System.out.println("File created: " + xmlFile.getName());
                this.initalizeFile();
            } else {
                System.out.println("A existing `minidb.xml` Found!!");
            }
        } catch (IOException e) {
            System.out.println("Congratulations! You created a ERROR;;");
            e.printStackTrace();
        }
    }

    private void initalizeFile() {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();

            Element rootElem = doc.createElement("root");
            Element emptyDb = this.addDbEntry(doc, "empty", "true");

            rootElem.appendChild(emptyDb);
            doc.appendChild(rootElem);

            this.UpdateFile(doc);

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        }
    }

    private void UpdateFile(Document doc) {
        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "no");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(this.xmlFile);
            transformer.transform(source, result);

        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }

    private Element addDbEntry(Document doc, String name, String disabled) throws ParserConfigurationException {
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
            Document doc = docBuilder.parse(xmlFile);

            doc.getDocumentElement().appendChild(addDbEntry(doc, name, "false"));
            this.UpdateFile(doc);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteDatabase(String name) {

    }
}
