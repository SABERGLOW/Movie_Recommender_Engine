import org.junit.Assert;
import org.junit.Test;

public class MovieDatabaseTest
{
    @Test
    public void testgetYear()
    {
        Assert.assertEquals(2014, MovieDatabase.getYear("3112654"));
    }

    @Test
    public void testgetTitle()
    {
        Assert.assertEquals("Mea culpa", MovieDatabase.getTitle("3112654"));
    }

    @Test
    public void testgetPoster()
    {
        Assert.assertEquals("http://ia.media-imdb.com/images/M/MV5BNTQ0ODAwMjI4M15BMl5BanBnXkFtZTgwMzUwMzA4MzE@._V1_SX300.jpg", MovieDatabase.getPoster("3112654"));
    }

    @Test
    public void testFilter()
    {
        Assert.assertEquals(3143, (MovieDatabase.filterBy(new TrueFilter())).size());
    }
}