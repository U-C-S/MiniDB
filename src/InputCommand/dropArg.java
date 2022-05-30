package InputCommand;

import minidb.xmlParser.RegistryFile;

public class dropArg implements ArgStrategy{
    @Override
    public boolean matchArg(String arg) {
        String[] cmdArgs = arg.split(" ");
        return cmdArgs[0].equals("drop");
    }

    @Override
    public void execCmd(String arg) {
        String[] cmdArgs = arg.split(" ");
        RegistryFile registry = RegistryFile.getInstance();
        registry.deleteDatabase(cmdArgs[1]);
    }
}
