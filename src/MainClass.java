public class MainClass {
    public static void main(String[] args) {
        System.out.println("my smell");
        CliBuilder cb = new CliBuilder();
        cli db_cli = cb.buildCli();
        db_cli.run();
    }
}
