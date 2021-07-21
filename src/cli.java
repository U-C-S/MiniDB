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
    static DatabaseFile CurrentDb;

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
            long startTime = System.nanoTime();
            cliInputs(currentCmd);
            long endTime = System.nanoTime();

            long exeTime = (endTime - startTime) / 1000000;
            System.out.println("\nExecution Time: " + exeTime + "ms");

        }

        input.close();
    }

    private static void cliInputs(String input) {
        String[] inputCmds = input.split(" ");

        switch (inputCmds[0]) {
            case "new": {
                registry.createNewDatabase(inputCmds[1]);
                break;
            }
            case "use": {
                String path = registry.getDatabasePath(inputCmds[1], false);
                if (path != null) {
                    CurrentDb = new DatabaseFile(path);
                    CurrentDb.EditMode();
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

            case "schema": {
                String xy = inputCmds[1];
                if (xy.equals("show")) {
                    System.out.println(CurrentDb.showSchema());
                } else if (xy.equals("update")) {

                } else {
                    String[] schemaVals = xy.split(",");
                    if (schemaVals.length > 1) {
                        CurrentDb.createSchema(xy);
                    } else {
                        System.out.println("There should be atleast 2 columns of data");
                    }
                }
                break;
            }

            case "add": {
                if (CurrentDb != null) {
                    CurrentDb.addData(inputCmds[1]);
                } else {
                    System.out.println("Select a database with `use` command");
                }

                break;
            }

            case "read": {
                CurrentDb.readData();
                break;
            }

            case "drop": {

                break;
            }

            case "update/delete": {

                break;
            }

            default: {
                System.out.println("UNKNOWN COMMAND: " + inputCmds[0] + "\nType `info commands` for commands list");
                break;
            }
        }
    }

}

// read
// read id=8..99

// update name='cow' where id=2

// add 04,cow,25

// delete id=5..7

// schema id, name, age
// schema update id, name,age
// schema show
