package InputCommand;

import constants.errors;
import minidb.xmlParser.DatabaseFile;

public class deleteArg implements ArgStrategy, UseSetCurrentDb {
    private DatabaseFile CurrentDb;

    @Override
    public void setCurrentDb(DatabaseFile currentDb){
        this.CurrentDb = currentDb;
    }

    @Override
    public boolean matchArg(String arg) {
        String[] cmdArgs = arg.split(" ");
        return cmdArgs[0].equals("delete");
    }

    @Override
    public void execCmd(String arg) {
        String[] cmdArgs = arg.split(" ");
        if (CurrentDb != null) {
            CurrentDb.deleteData(cmdArgs[1]);
        } else {
            System.out.println(errors.NO_DATABASE_SELECTED);
        }
    }
}
