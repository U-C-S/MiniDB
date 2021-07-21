import java.util.Objects;
import java.util.Scanner;

import org.w3c.dom.Document;

import constants.constants;
import minidb.xmlParser.DatabaseFile;
import minidb.xmlParser.RegistryFile;

/*
TODO
- Comment the code
*/

public class cli {
    static RegistryFile registry;
    static Document CurrentDb;

    public static void main(String[] args) {
        System.out.println(constants.HEADING);
        registry = new RegistryFile(constants.DATA_XML_PATH);

        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.print(constants.CMD_PREFIX);

            String currentCmd = input.nextLine();

            // break if user wants to exit
            if (Objects.equals(currentCmd, "exit;")) {
                break;
            }

            cliInputs(currentCmd);

        }

        input.close();
    }

    private static void cliInputs(String input) {
        String[] inputCmds = input.split(" ");

        switch (inputCmds[0]) {
            case "new": {
                registry.createNewDatabase(inputCmds[1]);
                System.out.println("Successfully created Database named: " + inputCmds[1]);
                break;
            }
            case "use": {
                String path = registry.getDatabasePath(inputCmds[1], false);
                if (path != null) {
                    DatabaseFile db = new DatabaseFile(path);
                    CurrentDb = db.getDocument();
                    System.out.println("Successfully loaded Database named: " + inputCmds[1]);
                } else {
                    System.out.println("Database not found");
                }
                break;
            }

            case "list": {
                registry.listAllDatabases();
                break;
                // in future, use
                // - list databases - to list all the databases
                // - list elements - to list all the data entered into the database
            }

            case "info": {

                break;
            }

            case "schema/update/delete/read/add": {

                break;
            }

            default: {
                System.out.println("UNKNOWN COMMAND: " + inputCmds[0] + "\nType `info commands` for commands list");
                break;
            }
        }
    }

}

// update where id=2 name=cow
