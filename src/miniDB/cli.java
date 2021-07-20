package miniDB;

public class cli {
    public cli(String input) {
        createXmlFile mainXml = new createXmlFile("./minidb.xml");
        String[] inputCmds = input.split(" ");

        switch (inputCmds[0]) {
            case "newdb": {
                System.out.println(inputCmds[1]);
                mainXml.createNewDatabase(inputCmds[1]);
                break;
            }

            default: {
                System.out.println("UNKNOWN COMMAND: " + inputCmds[0]);
                break;
            }
        }
    }
}
