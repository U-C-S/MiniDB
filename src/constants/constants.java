package constants;

public class constants {
    public static String VERSION = "v0.3.0";

    static String ROOT_PATH = ".";
    public static String DATA_XML_PATH = ROOT_PATH + "\\minidb.xml";
    public static String DB_DIR_PATH = ROOT_PATH + "\\db";

    public static String HEADING = "\n\n" + 
    "╔═══════════════════════════════════════════════════╗\n" +
    "║                 Welcome to MiniDB                 ║\n" +
    "║                      " + VERSION + "                       ║\n" + 
    "║              --- Made by Chanakya ---             ║\n" + 
    "║      Source: https://github.com/U-C-S/MiniDB      ║\n" +
    "╚═══════════════════════════════════════════════════╝\n" + 
    "Enter the Commands: (Use 'exit;' to exit the cli)";
    
    public static String CMD_PREFIX = "\n\u001B[31m>\u001B[0m ";

    //Alternative for those CLIs that cannot read UTF-8
    public static String HEADINGx = "\n\n" +
    "---------- Welcome to MiniDB ----------\n" +
    "---------------- " + VERSION + " ---------------\n" +
    "----------- Made by Chanakya ----------\n" +
    "Source: https://github.com/U-C-S/MiniDB\n\n" +
    "Enter the Commands: (Use 'exit;' to exit the cli)";

    public static String CMD_PREFIXx = "\n> ";
}
