package InputCommand;

public interface ArgStrategy {
    public boolean matchArg(String arg);
    public void execCmd (String arg);
}
