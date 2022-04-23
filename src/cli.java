import java.util.Scanner;
import minidb.xmlParser.DatabaseFile;
import minidb.xmlParser.RegistryFile;
import constants.*;

/*
To do
- Comment the code
- Table Layout for the data.
- usage of threads
*/

/**
 * Javadoc comments. All are static methods because we are not creating any
 * object of this class.
 * 
 * @author Chanakya
 */
public class cli {

    /**
     * The Registry File is a XML file which contains the information about the all
     * the databases created. It acts as a pointer to the Database File. So, We
     * instantly load the registry file.
     */

    /**
     * registry can be a singleton
     */
    RegistryFile registry;

    /**
     * This attribute is for storing the DatabaseFile instance. Which is assigned
     * when the user calls the command "use". if the user does not call the command
     * "use" then we show an error message.
     */
    DatabaseFile CurrentDb;

    /*
        function okay
     */
    public void run() {
        print(constants.HEADING);

        registry = new RegistryFile(constants.DATA_XML_PATH);
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.print(constants.CMD_PREFIX);

            String currentCmdCommand = input.nextLine();

            if (currentCmdCommand.equals("exit;")) {
                break;
            }
            long startTime = System.nanoTime();
            executeCliInputs(currentCmdCommand);
            long endTime = System.nanoTime();

            long exeTime = (endTime - startTime) / 1000000;
            print("\nExecution Time: " + exeTime + "ms");
        }

        input.close();
    }

    private void executeCliInputs(String input) {
        String[] cmdArgs = input.split(" ");

        /**
         * Strategy pattern can be implemented to ensure OCP
         */
        switch (cmdArgs[0]) {
            case "new": {
                createNewDatabase(cmdArgs[1]);
                break;
            }

            case "use": {
                useDatabase(cmdArgs[1]);
                break;
            }

            case "list": {
                listAllDatabase();
                break;
            }

            case "help;": {
                showHelp();
                break;
            }

            case "info": {
                // For querying the meta info of the database
                // TODO
            }

            case "schema": {
                schemaCommand(cmdArgs[1]);
                break;
            }

            case "add": {
                addRecordToDB(cmdArgs[1]);
                break;
            }

            case "read": {
                readFromDB(cmdArgs);

                break;
            }

            case "drop": {
                dropTable(cmdArgs[1]);
                break;
            }

            //code smell
            case "update": {
                // TODO
                if (CurrentDb != null) {
                }
                break;
            }

            case "delete": {
                deleteFromDB(cmdArgs[1]);
                break;
            }

            default: {
                unknownCommandMsg(cmdArgs[0]);
                break;
            }
        }
    }

    private void deleteFromDB(String cmdArgs) {
        if (CurrentDb != null) {
            CurrentDb.deleteData(cmdArgs);
        } else {
            print(errors.NO_DATABASE_SELECTED);
        }
    }

    private void dropTable(String cmdArgs) {
        registry.deleteDatabase(cmdArgs);
    }

    private void readFromDB(String[] cmdArgs) {
        if (CurrentDb != null) {
            if (cmdArgs.length == 1) {
                CurrentDb.readData();
            } else {
                CurrentDb.readData(cmdArgs[1]);
            }
        } else {
            print(errors.NO_DATABASE_SELECTED);
        }
    }

    private void addRecordToDB(String cmdArgs) {
        if (CurrentDb != null) {
            CurrentDb.addData(cmdArgs);
        } else {
            print(errors.NO_DATABASE_SELECTED);
        }
    }

    private void schemaCommand(String cmdArgs) {
        if (CurrentDb != null) {
            String xy = cmdArgs;

            if (xy.equals("show")) {
                print(CurrentDb.getSchema());
            } else {
                String[] schemaVals = xy.split(",");
                if (schemaVals.length > 1) {
                    CurrentDb.createSchema(xy);
                } else {
                    print("There should be at least 2 columns of data");
                }
            }

        } else {
            print(errors.NO_DATABASE_SELECTED);
        }
    }

    private void unknownCommandMsg(String cmdArgs) {
        print("UNKNOWN COMMAND: " + cmdArgs + "\nType `help;` for commands list");
    }

    private void showHelp() {
        print(constants.HELP_COMMANDS);
    }

    private void listAllDatabase() {
        registry.listAllDatabases();
    }

    private void useDatabase(String name) {
        String path = registry.getDatabasePath(name, false);

        if (path != null) {
            CurrentDb = new DatabaseFile(path);
            CurrentDb.EditMode();
            print("Successfully loaded Database named: " + name);
        } else {
            print("Database not found");
        }
    }

    private void createNewDatabase(String name) {
        registry.createNewDatabase(name);
    }

    private static void print(String x) {
        System.out.println(x);
    }
}

// Commands that are available
// ✅ read
// ✅ read id=2
// read id=8..99

// ✅ add 04,cow,25

// ✅ delete id=2
// delete id=5..7

// ✅ schema id, name, age
// schema update id, name,age
// ✅ schema show

// The hardest one:
// update name='cow' where id=2

// Future:
// - import/export databases cmds