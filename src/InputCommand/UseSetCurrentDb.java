package InputCommand;

import minidb.xmlParser.CurrentDBObserver;
import minidb.xmlParser.DatabaseFile;

public interface UseSetCurrentDb {
    public void setCurrentDb(DatabaseFile currentDb);
}
