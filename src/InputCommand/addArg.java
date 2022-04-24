package InputCommand;

import constants.errors;

public class addArg implements ArgStrategy{
    @Override
    public boolean matchArg(String arg) {
        return arg.equals("add");
    }

    @Override
    public void execCmd(String arg) {
//        if (CurrentDb != null) {
//            CurrentDb.addData(cmdArgs);
//        } else {
//            System.out.println(errors.NO_DATABASE_SELECTED);
//        }
    }
}
