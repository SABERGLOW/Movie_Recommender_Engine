/**
EfficientRater class contains various methods that are used to
 add and extract ratings from the ratings HashMap.
 **/

import java.util.ArrayList;
import java.util.HashMap;

public class EfficientRater implements Rater
{
    private String myID;
    private HashMap<String, Rating> myRatings;

///////////////////////////////////////...Constructor.../////////////////////////////////////////
    public EfficientRater(String id)
    {
        myID = id;
        myRatings = new HashMap<>();
    }
/////////////////////////////////////////////////////////////////////////////////////////////////


    public void addRating(String item, double rating)
    {
        myRatings.put(item, new Rating(item, rating));
    }

    public boolean hasRating(String item)
    {
        return myRatings.containsKey(item);
    }

    public String getID()
    {
        return myID;
    }

    public double getRating(String item)
    {
        if (myRatings.containsKey(item))
        {
            return myRatings.get(item).getValue();
        }
        return -1;
    }

    public int numRatings()
    {
        return myRatings.size();
    }

    public ArrayList<String> getItemsRated()
    {
        ArrayList<String> list = new ArrayList<>(myRatings.keySet());
        return list;//arrayList of item;
    }
}
