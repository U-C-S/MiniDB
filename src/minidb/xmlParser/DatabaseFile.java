package minidb.xmlParser;

import org.w3c.dom.*;
import java.util.StringJoiner;

/**
 * Used for performing CRUD operations and XML parsing on the database file.
 */
public class DatabaseFile extends XMLFiles {
    private static String TAG_STORAGE = "Xstorage";
    private static String TAG_META = "Xmeta";
    private static String TAG_DATA = "Xdata";
    // private Element schemaElem;
    private Element metaElem;
    private Element storageElem;

    public DatabaseFile(String path) {
        super(path);
    }

    @Override
    protected void createFile() {
        // prefix `X` to avoid name space conflict
        Element rootElem = doc.createElement("Xroot");
        Element meta = doc.createElement(TAG_META);
        Element data = doc.createElement(TAG_STORAGE);

        rootElem.appendChild(meta);
        rootElem.appendChild(data);
        doc.appendChild(rootElem);

        this.updateFile();
    }

    public void EditMode() {
        metaElem = (Element) doc.getElementsByTagName(TAG_META).item(0);
        storageElem = (Element) doc.getElementsByTagName(TAG_STORAGE).item(0);
        // System.out.println("Edit Mode On; " + schemaElem.getAttribute("val"));
    }

    public String getSchema() {
        return storageElem.getAttribute("schema");
    }

    public void createSchema(String value) {
        storageElem.setAttribute("schema", value);
        this.updateFile();
    }

    public void addData(String value) {
        String[] vals = value.split(",");
        String[] schemaArray = this.getSchema().split(",");

        if (vals.length == schemaArray.length) {
            Element newDataElem = doc.createElement(TAG_DATA);
            newDataElem.setAttribute("id", vals[0]);

            for (int i = 1; i < schemaArray.length; i++) {
                String v = vals[i];
                String s = schemaArray[i];
                Element x = doc.createElement(s);
                x.appendChild(doc.createTextNode(v));
                newDataElem.appendChild(x);
            }
            storageElem.appendChild(newDataElem);

            this.updateFile();
        } else {
            print("The data does not follow the declared schema: " + this.getSchema());
        }

    }

    public void readData() {
        String[] schemaArray = this.getSchema().split(",");
        String headers = String.join("    ", schemaArray);
        print(headers);

        NodeList dataList = doc.getElementsByTagName(TAG_DATA);
        for (int i = 0; i < dataList.getLength(); i++) {
            Node singleItem = dataList.item(i);
            NodeList itemsChildren = singleItem.getChildNodes();

            String dataString = singleItem.getAttributes().getNamedItem("id").getNodeValue() + "  ";

            for (int j = 0; j < itemsChildren.getLength(); j++) {
                Node z = itemsChildren.item(j);
                dataString += z.getTextContent().trim() + "  ";
            }

            print(dataString.trim());
        }

    }
}
