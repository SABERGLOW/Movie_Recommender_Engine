import java.util.ArrayList;
import java.util.Collections;

public class OptimizedRatings
{
    private double dotProduct(Rater me, Rater r)
    {
        double dp = 0;
        ArrayList<String> memovieid = me.getItemsRated();
        for (String id : memovieid)
        {
            if (r.getItemsRated().contains(id))
            {
                dp += (me.getRating(id) - 5) * (r.getRating(id) - 5);
            }
        }
        return dp;
    }

    private ArrayList<Rating> getSimilarities(String raterId)
    {
        ArrayList<Rating> simiList = new ArrayList<>();
        ArrayList<Rater> raters = RaterDatabase.getRaters();
        for (Rater r : raters) {
            if (!r.getID().equals(raterId))
            {
                double dotProduct = dotProduct(RaterDatabase.getRater(raterId), r);
                if (dotProduct > 0)
                {
                    simiList.add(new Rating(r.getID(), dotProduct));
                }
            }
        }
        Collections.sort(simiList);
        Collections.reverse(simiList);
        return simiList;
    }


    public ArrayList<Rating> getSimilarRatings(String raterID, int numSimilarRaters, int minimalRaters)
    {
        ArrayList<Rating> ratingList = new ArrayList<>();
        ArrayList<String> movidIDByTopSimilar = new ArrayList<>();
        ArrayList<Rating> simiList1 = getSimilarities(raterID);
        int num = simiList1.size();
        if (numSimilarRaters > num)
        {
            numSimilarRaters = num;
        }
        for (int i = 0; i < numSimilarRaters; i++)
        {
            String raterID1 = simiList1.get(i).getItem();
            ArrayList<String> movieRated1 = RaterDatabase.getRater(raterID1).getItemsRated();
            for (String movieID : movieRated1)
            {
                if (!movidIDByTopSimilar.contains(movieID))
                {
                    movidIDByTopSimilar.add(movieID);
                }
            }
        }

        for (String j : movidIDByTopSimilar)
        {
            double ave = 0;
            ArrayList<Rating> simiList = getSimilarities(raterID);
            int count = 0;
            double total = 0;
            int simiweighttotal = 0;
            for (int i = 0; i < numSimilarRaters; i++)
            {
                double rating = RaterDatabase.getRater(simiList.get(i).getItem()).getRating(j);
                if (rating != -1)
                {
                    count++;
                    total += rating * simiList.get(i).getValue();
                    simiweighttotal += simiList.get(i).getValue();
                }
            }
            if (count >= minimalRaters)
            {
                ave = total / simiweighttotal;
            }
            if (ave > 0)
            {
                ratingList.add(new Rating(j, ave));
            }
        }
        Collections.sort(ratingList);
        Collections.reverse(ratingList);
        return ratingList;
    }
}
