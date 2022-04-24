package InputCommand;

import minidb.xmlParser.RegistryFile;

public class dropArg implements ArgStrategy{
    @Override
    public boolean matchArg(String arg) {
        return arg.equals("drop");
    }

    @Override
    public void execCmd(String arg) {
        RegistryFile registry = RegistryFile.getInstance();
        registry.deleteDatabase(arg);
    }
}
