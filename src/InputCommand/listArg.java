package InputCommand;

import minidb.xmlParser.RegistryFile;

public class listArg implements ArgStrategy{
    @Override
    public boolean matchArg(String arg) {
        String[] cmdArgs = arg.split(" ");
        return cmdArgs[0].equals("list");
    }

    @Override
    public void execCmd(String arg) {
        RegistryFile registry = RegistryFile.getInstance();
        registry.listAllDatabases();
    }
}
