package minidb.xmlParser;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import constants.constants;

/**
 * Contains the methods for performing CRUD operations on the registry file
 * 'minidb.xml'
 */
public class RegistryFile {
    private File xmlFile;
    private Document doc;

    public File getFile() {
        return xmlFile;
    }

    public RegistryFile(String path) {
        xmlFile = new File(path);
    }

    public void load() {
        try {
            boolean FileNotExists = xmlFile.createNewFile();
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            if (FileNotExists) {
                this.doc = docBuilder.newDocument();
                System.out.println("Intialized: " + xmlFile.getPath());
                Element rootElem = doc.createElement("root");
                Element emptyDb = this.addDbEntry("empty", "true");

                rootElem.appendChild(emptyDb);
                doc.appendChild(rootElem);

                this.UpdateFile();

            } else {
                this.doc = docBuilder.parse(xmlFile);
            }

        } catch (ParserConfigurationException | SAXException | IOException err) {
            err.printStackTrace();
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

        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }

    /**
     * 
     * @param name     - Name of the database
     * @param disabled - Always false for user created databases
     * @return The XML Element which can be appended into the doc
     * @throws ParserConfigurationException
     */
    private Element addDbEntry(String name, String disabled) throws ParserConfigurationException {
        Document doc = this.doc;

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
            Element dbEntry = addDbEntry(name, "false");
            this.doc.getDocumentElement().appendChild(dbEntry);
            this.UpdateFile();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void printt(String x) {
        System.out.println(x);
    }

    // TODO
    public void listAllDatabases() {
        try {
            NodeList list = this.doc.getElementsByTagName("database");
            for (int i = 0; i < list.getLength(); i++) {
                Node dbx = list.item(i);
                // System.out.println(dbx.getNodeName());
                String name = dbx.getFirstChild().getNodeValue();
                System.out.println(name);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deleteDatabase(String name) {

    }
}
