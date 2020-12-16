import org.junit.Assert;
import org.junit.Test;

public class OptimizedRatingsTest
{

    @Test
    public void testgetSimilarRatings()
    {
        OptimizedRatings OR = new OptimizedRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        Assert.assertEquals(59, OR.getSimilarRatings("3" , 2, 1).size());
    }
}