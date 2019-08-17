package utils;

import arkpas.kursspring.utils.Ids;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

public class IdsTest {

    @Test
    public void testEmptySet () {
        int id = Ids.getNextId(new HashSet<Integer>());
        assertEquals(0, id);

    }

    @Test
    public void testFilledSet () {
        Set<Integer> set = new HashSet<>();
        set.add(13);
        set.add(6);
        set.add(21);
        set.add(2);
        int id = Ids.getNextId(set);
        assertEquals(22, id);

    }
}
