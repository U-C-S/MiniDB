package InputCommand;

import minidb.xmlParser.CurrentDBObserver;
import minidb.xmlParser.DatabaseFile;
import minidb.xmlParser.RegistryFile;

public class useArg implements ArgStrategy, UseSetCurrentDb{
    private DatabaseFile CurrentDb;
    private CurrentDBObserver currentDBO;

    public void setCurrentDBO(CurrentDBObserver currentDBO) {
        this.currentDBO = currentDBO;
    }

    @Override
    public void setCurrentDb(DatabaseFile currentDb) {
        this.CurrentDb = currentDb;
    }

    @Override
    public boolean matchArg(String arg) {
        return arg.equals("use");
    }

    @Override
    public void execCmd(String arg) {
        RegistryFile registry = RegistryFile.getInstance();
        String path = registry.getDatabasePath(arg, false);

        if (path != null) {
//            CurrentDb = new DatabaseFile(path);
//            CurrentDb.EditMode();
            currentDBO.updateAll(path);
            CurrentDb.EditMode();
            System.out.println("Successfully loaded Database named: " + arg);
        } else {
            System.out.println("Database not found");
        }
    }
}
