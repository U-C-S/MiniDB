package InputCommand;

import constants.errors;
import minidb.xmlParser.DatabaseFile;

public class readArg implements ArgStrategy, UseSetCurrentDb {
    private DatabaseFile CurrentDb;

    @Override
    public void setCurrentDb(DatabaseFile currentDb){
        this.CurrentDb = currentDb;
    }

    @Override
    public boolean matchArg(String arg) {
        return arg.equals("read");
    }

    @Override
    public void execCmd(String arg) {
        String[] cmdArgs = arg.split(" ");
        if (CurrentDb != null) {
            if (cmdArgs.length == 1) {
                CurrentDb.readData();
            } else {
                CurrentDb.readData(cmdArgs[1]);
            }
        } else {
            System.out.println(errors.NO_DATABASE_SELECTED);
        }
    }
}
