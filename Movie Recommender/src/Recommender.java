import java.util.ArrayList;

public interface Recommender
{
    ArrayList<String> getItemsToRate();

    ArrayList<Rating> getRecommendationsFor(String webRaterID);
}
