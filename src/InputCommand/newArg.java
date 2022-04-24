package InputCommand;

import minidb.xmlParser.RegistryFile;

public class newArg implements ArgStrategy{
    @Override
    public boolean matchArg(String arg) {
        String[] cmdArgs = arg.split(" ");
        return cmdArgs[0].equals("new");
    }

    @Override
    public void execCmd(String arg) {
        String[] cmdArgs = arg.split(" ");
        RegistryFile registry = RegistryFile.getInstance();
        registry.createNewDatabase(cmdArgs[1]);
    }
}
