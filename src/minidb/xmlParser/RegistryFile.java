package minidb.xmlParser;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
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
                Element rootElem = doc.createElement("root");
                Element emptyDb = this.addDbEntry("empty", "true");

                rootElem.appendChild(emptyDb);
                doc.appendChild(rootElem);

                this.UpdateFile();
                new DatabaseFile(this.getDatabasePath("empty", true));
                System.out.println("Intialized: " + xmlFile.getPath());

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

        } catch (TransformerException err) {
            err.printStackTrace();
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

        nameElem.appendChild(doc.createTextNode(name));
        pathElem.appendChild(doc.createTextNode(this.getDatabasePath(name, true)));
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
            if (!this.isDatabaseExists(name)) {
                Element dbEntry = addDbEntry(name, "false");
                this.doc.getDocumentElement().appendChild(dbEntry);
                this.UpdateFile();

                new DatabaseFile(this.getDatabasePath(name, true));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // private void printt(String x) {
    // System.out.println(x);
    // }

    /**
     * To list all the created databases in the register
     */
    public void listAllDatabases() {
        NodeList list = this.doc.getElementsByTagName("database");

        for (int i = 0; i < list.getLength(); i++) {
            Node dbNode = list.item(i);
            NodeList childList = dbNode.getChildNodes();
            String name = childList.item(0).getTextContent();
            System.out.println(name);
        }
    }

    /**
     * Checks if the database name already exists in the register
     * 
     * @param name - The name of the database
     * @return The index of the database in the register, if not found returns -1
     */
    public int checkDatabase(String name) {
        int x = -1;
        NodeList list = this.doc.getElementsByTagName("name");
        for (int i = 0; i < list.getLength(); i++) {
            Node dbNode = list.item(i);
            String dbName = dbNode.getTextContent();
            // System.out.println(dbName + " " + name);
            if (Objects.equals(dbName, name)) {
                x = i;
            }
        }

        return x;
    }

    private boolean isDatabaseExists(String name) {
        if (checkDatabase(name) != -1) {
            return true;
        } else
            return false;
    }

    /**
     * @param name   - Name of the Database
     * @param create - True only if you are creating a new database
     * @return The path of the database
     */
    public String getDatabasePath(String name, boolean create) {
        if (create) {
            return constants.DB_DIR_PATH + name + ".xml";
        } else {
            if (isDatabaseExists(name)) {
                return constants.DB_DIR_PATH + name + ".xml";
            } else {
                return null;
            }
        }
    }

    public void deleteDatabase(String name) {

    }
}
