package InputCommand;

import constants.errors;
import minidb.xmlParser.DatabaseFile;

public class addArg implements ArgStrategy, UseSetCurrentDb {
    private DatabaseFile CurrentDb;

    @Override
    public void setCurrentDb(DatabaseFile currentDb){
        this.CurrentDb = currentDb;
    }

    @Override
    public boolean matchArg(String arg) {
        return arg.equals("add");
    }

    @Override
    public void execCmd(String arg) {
        if (CurrentDb != null) {
            CurrentDb.addData(arg);
        } else {
            System.out.println(errors.NO_DATABASE_SELECTED);
        }
    }
}
