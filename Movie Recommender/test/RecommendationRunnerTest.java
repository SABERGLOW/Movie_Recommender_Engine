import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class RecommendationRunnerTest
{

    @Test
    public void testgetItemsToRate()
    {
        RecommendationRunner RR = new RecommendationRunner();
        Assert.assertEquals(10, RR.getItemsToRate().size());
    }

    @Test
    public void testgetRecommendationsFor()
    {
        RecommendationRunner RR = new RecommendationRunner();
        Assert.assertEquals(11, RR.getRecommendationsFor("1").size());
    }
}