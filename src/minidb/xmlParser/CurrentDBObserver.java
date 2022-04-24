package minidb.xmlParser;

import InputCommand.UseSetCurrentDb;

import java.util.ArrayList;

public class CurrentDBObserver {
    private final ArrayList<UseSetCurrentDb> strategyArrayList;

    public CurrentDBObserver(ArrayList<UseSetCurrentDb> strategyArrayList) {
        this.strategyArrayList = strategyArrayList;
    }

    public void attach(UseSetCurrentDb observer){
        strategyArrayList.add(observer);
    }

    public void updateAll(String path) {
        for (UseSetCurrentDb argStart: strategyArrayList) {
            argStart.setCurrentDb(path);
        }
    }
}
