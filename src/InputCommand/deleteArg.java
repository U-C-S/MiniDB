package InputCommand;

import constants.errors;

public class deleteArg implements ArgStrategy{
    @Override
    public boolean matchArg(String arg) {
        return arg.equals("delete");
    }

    @Override
    public void execCmd(String arg) {
//        if (CurrentDb != null) {
//            CurrentDb.deleteData(arg);
//        } else {
//            print(errors.NO_DATABASE_SELECTED);
//        }
    }
}
