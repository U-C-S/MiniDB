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
    public void setCurrentDb(String path) {
        this.CurrentDb = new DatabaseFile(path);
    }

    @Override
    public boolean matchArg(String arg) {
        String[] cmdArgs = arg.split(" ");
        return cmdArgs[0].equals("use");
    }

    @Override
    public void execCmd(String arg) {
        String[] cmdArgs = arg.split(" ");
        RegistryFile registry = RegistryFile.getInstance();
        String path = registry.getDatabasePath(cmdArgs[1], false);

        if (path != null) {
            CurrentDb = new DatabaseFile(path);
            currentDBO.updateAll(path);
            CurrentDb.EditMode();
            System.out.println(path);
            System.out.println("Successfully loaded Database named: " + cmdArgs[0]);
        } else {
            System.out.println("Database not found");
        }
    }
}
