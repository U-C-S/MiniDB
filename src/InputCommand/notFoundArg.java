package InputCommand;

public class notFoundArg implements ArgStrategy{
    @Override
    public boolean matchArg(String arg) {
        return false;
    }

    @Override
    public void execCmd(String arg) {
        System.out.println("UNKNOWN COMMAND: " + arg + "\nType `help;` for commands list");
    }
}
