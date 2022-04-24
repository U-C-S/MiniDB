import InputCommand.ArgStrategy;
import InputCommand.UseSetCurrentDb;
import InputCommand.*;
import minidb.xmlParser.CurrentDBObserver;

import java.util.ArrayList;

public class CliBuilder {
    public cli buildCli () {
        cli c = null;

        ArrayList<ArgStrategy> argslist = new ArrayList<>();
        ArrayList<UseSetCurrentDb> usedInCurrentDBO = new ArrayList<>();

        addArg add = new addArg();
        argslist.add(add);
        usedInCurrentDBO.add(add);

        deleteArg delete = new deleteArg();
        argslist.add(delete);
        usedInCurrentDBO.add(delete);

        readArg read = new readArg();
        argslist.add(read);
        usedInCurrentDBO.add(read);

        schemaArg schema = new schemaArg();
        argslist.add(schema);
        usedInCurrentDBO.add(schema);

        useArg use = new useArg();
        argslist.add(use);
        usedInCurrentDBO.add(use);
        CurrentDBObserver observer = new CurrentDBObserver(usedInCurrentDBO);
        use.setCurrentDBO(observer);

        dropArg drop = new dropArg();
        argslist.add(drop);

        helpArg help = new helpArg();
        argslist.add(help);

        listArg list = new listArg();
        argslist.add(list);

        newArg newA = new newArg();
        argslist.add(newA);



        return c;
    }
}
























