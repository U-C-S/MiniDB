import java.util.Objects;
import java.util.Scanner;

import constants.constants;
import minidb.xmlParser.DatabaseFile;
import minidb.xmlParser.RegistryFile;

/*
TODO
- Comment the code
*/

public class cli {
    static RegistryFile DataFile = new RegistryFile(constants.DATA_XML_PATH);

    public static void main(String[] args) {
        System.out.println(constants.HEADING);

        DataFile.load();
        new DatabaseFile(DataFile.getFile());

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
                DataFile.createNewDatabase(inputCmds[1]);
                System.out.println("Successfully created Database named: " + inputCmds[1]);
                break;
            }
            case "use": {

                break;
            }

            case "list": {
                DataFile.listAllDatabases();
                break;
            }

            case "info": {

                break;
            }

            case "schema/update/delete/read": {

                break;
            }

            default: {
                System.out.println("UNKNOWN COMMAND: " + inputCmds[0]);
                break;
            }
        }
    }

}

// update where id=2 name=cow
