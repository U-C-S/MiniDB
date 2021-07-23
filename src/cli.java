import java.util.Scanner;

import constants.*;
import minidb.xmlParser.DatabaseFile;
import minidb.xmlParser.RegistryFile;

/*
To do
- Comment the code
- Table Layout for the data.
- use threads
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
            if (currentCmd.equals("exit;")) {
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
            }

            case "help;": {
                System.out.println(constants.HELP_COMMANDS);
                break;
            }

            case "info": {
                // For querying the meta info of the database
            }

            case "schema": {
                if (CurrentDb != null) {
                    String xy = inputCmds[1];

                    if (xy.equals("show")) {
                        System.out.println(CurrentDb.getSchema());
                    } else {
                        String[] schemaVals = xy.split(",");
                        if (schemaVals.length > 1) {
                            CurrentDb.createSchema(xy);
                        } else {
                            System.out.println("There should be atleast 2 columns of data");
                        }
                    }
                } else {
                    System.out.println(errors.NO_DATABASE_SELECTED);
                }
                break;
            }

            case "add": {
                if (CurrentDb != null) {
                    CurrentDb.addData(inputCmds[1]);
                } else {
                    System.out.println(errors.NO_DATABASE_SELECTED);
                }

                break;
            }

            case "read": {
                if (CurrentDb != null) {
                    if (inputCmds.length == 1) {
                        CurrentDb.readData();
                    } else {
                        CurrentDb.readData(inputCmds[1]);
                    }
                } else {
                    System.out.println(errors.NO_DATABASE_SELECTED);
                }

                break;
            }

            case "drop": {
                registry.deleteDatabase(inputCmds[1]);
                break;
            }

            case "update": {
                if (CurrentDb != null) {
                }
                break;
            }

            case "delete": {
                if (CurrentDb != null) {
                    CurrentDb.deleteData(inputCmds[1]);
                } else {
                    System.out.println(errors.NO_DATABASE_SELECTED);
                }
                break;
            }

            default: {
                System.out.println("UNKNOWN COMMAND: " + inputCmds[0] + "\nType `help;` for commands list");
                break;
            }
        }
    }

}

// Commands that are available
// ✅ read
// ✅ read id=2
// read id=8..99

// update name='cow' where id=2

// ✅ add 04,cow,25

// ✅ delete id=2
// delete id=5..7

// ✅ schema id, name, age
// schema update id, name,age
// ✅ schema show

// Future
// - import/export databases cmds