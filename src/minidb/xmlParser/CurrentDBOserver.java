package minidb.xmlParser;

import InputCommand.UseSetCurrentDb;

import java.util.ArrayList;

public class CurrentDBOserver {
    private final ArrayList<UseSetCurrentDb> strategyArrayList;

    public CurrentDBOserver(ArrayList<UseSetCurrentDb> strategyArrayList) {
        this.strategyArrayList = strategyArrayList;
    }

    public void updateAll() {

    }
}
