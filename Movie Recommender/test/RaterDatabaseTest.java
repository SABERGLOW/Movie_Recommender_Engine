import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class RaterDatabaseTest
{
    @Test
    public void testgetRater()
    {
        RaterDatabase.initialize("ratings_short.csv");
        Assert.assertEquals("1", RaterDatabase.getRater("1").getID());
    }

    @Test
    public void testsize()
    {
        RaterDatabase.initialize("ratings_short.csv");
        Assert.assertEquals(1048, RaterDatabase.size());
    }
}