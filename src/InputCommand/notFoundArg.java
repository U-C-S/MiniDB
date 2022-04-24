package InputCommand;

public class notFoundArg implements ArgStrategy{
    @Override
    public boolean matchArg(String arg) {
        return false;
    }

    @Override
    public void execCmd(String arg) {
        String[] cmdArgs = arg.split(" ");
        System.out.println("UNKNOWN COMMAND: " + cmdArgs[0] + "\nType `help;` for commands list");
    }
}
