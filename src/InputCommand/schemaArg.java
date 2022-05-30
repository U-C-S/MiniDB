package InputCommand;

import constants.errors;
import minidb.xmlParser.DatabaseFile;

public class schemaArg implements ArgStrategy, UseSetCurrentDb {
    private DatabaseFile CurrentDb;

    @Override
    public void setCurrentDb(String path){
        this.CurrentDb = new DatabaseFile(path);
    }

    @Override
    public boolean matchArg(String arg) {
        String[] cmdArgs = arg.split(" ");
        return cmdArgs[0].equals("schema");
    }

    @Override
    public void execCmd(String arg) {
        String[] cmdArgs = arg.split(" ");
        if (CurrentDb != null) {
            String xy = cmdArgs[1];

            if (xy.equals("show")) {
                System.out.println(CurrentDb.getSchema());
            } else {
                String[] schemaVals = xy.split(",");
                if (schemaVals.length > 1) {
                    CurrentDb.createSchema(xy);
                } else {
                    System.out.println("There should be at least 2 columns of data");
                }
            }

        } else {
            System.out.println(errors.NO_DATABASE_SELECTED);
        }
    }
}
