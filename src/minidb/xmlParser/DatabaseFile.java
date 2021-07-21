package minidb.xmlParser;

import java.io.IOException;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class DatabaseFile extends XMLFiles {
    private Element schemaElem;
    private Element metaElem;
    private Element dataElem;

    public DatabaseFile(String path) {
        super(path);
    }

    @Override
    protected void createFile() {
        // prefix `X` to avoid name space conflict
        Element rootElem = doc.createElement("Xroot");
        Element meta = doc.createElement("Xmeta");
        Element schema = doc.createElement("Xschema");
        Element data = doc.createElement("Xdata");

        rootElem.appendChild(meta);
        rootElem.appendChild(schema);
        rootElem.appendChild(data);
        doc.appendChild(rootElem);

        this.updateFile();
    }

    public void EditMode() {
        metaElem = (Element) doc.getElementsByTagName("Xmeta").item(0);
        schemaElem = (Element) doc.getElementsByTagName("Xschema").item(0);
        dataElem = (Element) doc.getElementsByTagName("Xdata").item(0);
        System.out.println("Edit Mode On; " + schemaElem.getAttribute("val"));
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

    public String showSchema() {
        return schemaElem.getAttribute("val");
    }

    public void createSchema(String value) {
        schemaElem.setAttribute("val", value);
        this.updateFile();
    }

    public void addData(String value) {
        String[] vals = value.split(",");
        String[] schemaArray = schemaElem.getAttribute("val").split(",");

        if (vals.length == schemaArray.length) {
            Element store = doc.createElement("store");
            store.setAttribute(schemaArray[0], vals[0]);

            for (int i = 1; i < schemaArray.length; i++) {
                String v = vals[i];
                String s = schemaArray[i];
                Element x = doc.createElement(s);
                x.appendChild(doc.createTextNode(v));
                store.appendChild(x);
            }
            dataElem.appendChild(store);

            this.updateFile();
        } else {
            print("The data does not follow the declared schema: " + this.showSchema());
        }

    }

    public void readData() {
        String ss = "";
        String[] schemaArray = schemaElem.getAttribute("val").split(",");
        for (String string : schemaArray) {
            ss += string + " ";
        }
        print(ss);

        NodeList dataList = doc.getElementsByTagName("store");
        for (int i = 0; i < dataList.getLength(); i++) {
            String s = "";
            Node x = dataList.item(i);
            NodeList y = x.getChildNodes();
            for (int j = 0; j < y.getLength(); j++) {
                Node z = y.item(j);
                s += z.getTextContent().trim() + " ";
            }
            print(s);
        }

    }
}
