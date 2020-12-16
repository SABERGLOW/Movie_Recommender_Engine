import java.util.ArrayList;
import java.util.Random;

public class RecommendationRunner implements Recommender
{
    private int maxRecNum = 10;

    public ArrayList<String> getItemsToRate()
    {
        ArrayList<String> movieToBeRate = new ArrayList<>();
        ArrayList<String> movieID = MovieDatabase.filterBy(new TrueFilter());
        for (int i = 0; movieToBeRate.size() < 10; i++)
        {
            Random ran = new Random();
            int random = ran.nextInt(movieID.size());
            if (!movieToBeRate.contains(movieID.get(random)))
            {
                movieToBeRate.add(movieID.get(random));
            }
        }
        return movieToBeRate;
    }

    public ArrayList<Rating> getRecommendationsFor(String webRaterID)
    {

        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        OptimizedRatings fr = new OptimizedRatings();
        ArrayList<Rating> ratingList = fr.getSimilarRatings(webRaterID, 10, 3);
        System.out.println("Found ratings for movies : " + ratingList.size());
        return ratingList;

    }



    
    public static void main(String[] args)
    {
        //RecommendationRunner a = new RecommendationRunner();
        //a.getItemsToRate();
        //a.printRecommendationsFor("65");

    }
}
