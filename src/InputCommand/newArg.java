package InputCommand;

import minidb.xmlParser.RegistryFile;

public class newArg implements ArgStrategy{
    @Override
    public boolean matchArg(String arg) {
        return arg.equals("new");
    }

    @Override
    public void execCmd(String arg) {
        RegistryFile registry = RegistryFile.getInstance();
        registry.createNewDatabase(arg);
    }
}
