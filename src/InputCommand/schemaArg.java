package InputCommand;

import constants.errors;
import minidb.xmlParser.DatabaseFile;

public class schemaArg implements ArgStrategy, UseSetCurrentDb {
    private DatabaseFile CurrentDb;

    @Override
    public void setCurrentDb(DatabaseFile currentDb){
        this.CurrentDb = currentDb;
    }

    @Override
    public boolean matchArg(String arg) {
        return arg.equals("schema");
    }

    @Override
    public void execCmd(String arg) {
        if (CurrentDb != null) {
            String xy = arg;

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
