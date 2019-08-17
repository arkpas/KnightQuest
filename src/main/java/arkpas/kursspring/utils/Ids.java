package arkpas.kursspring.utils;

import java.util.Set;

public class Ids {

    public static int getNextId(Set<Integer> idSet) {
        if (idSet.isEmpty())
            return 0;
        int highestID = idSet.stream().max(Integer::compareTo).get();
        return highestID + 1;
    }
}
