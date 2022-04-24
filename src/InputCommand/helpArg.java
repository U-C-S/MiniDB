package InputCommand;

import constants.constants;

public class helpArg implements ArgStrategy{
    @Override
    public boolean matchArg(String arg) {
        String[] cmdArgs = arg.split(" ");
        return cmdArgs[0].equals("help;");
    }

    @Override
    public void execCmd(String arg) {
        System.out.println(constants.HELP_COMMANDS);
    }
}
