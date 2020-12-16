import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.util.ArrayList;
import java.util.HashMap;


public class MovieDatabase
{
    private static HashMap<String, Movie> movieHashMap;
    

    public static void initialize(String moviefile)
    {
        if (movieHashMap == null)
        {
            movieHashMap = new HashMap<String, Movie>();
            loadMovies("data/" + moviefile);
        }
    }

    private static void initialize()
    {
        if (movieHashMap == null)
        {
            movieHashMap = new HashMap<String, Movie>();
            loadMovies("data/ratedmoviesfull.csv");
        }
    }

    public static int getYear(String id)
    {
        initialize();
        return movieHashMap.get(id).getYear();
    }

    public static String getTitle(String id)
    {
        initialize();
        return movieHashMap.get(id).getTitle();
    }

    public static String getPoster(String id)
    {
        initialize();
        return movieHashMap.get(id).getPoster();
    }

    private static void loadMovies(String filename)
    {
        ArrayList<Movie> movieArrayList = new ArrayList<>();
        FileResource fileResource = new FileResource(filename);
        CSVParser csvParser = fileResource.getCSVParser();

        for (CSVRecord csvRecord : csvParser)
        {
            String id = csvRecord.get("id");
            String title = csvRecord.get("title");
            String year = csvRecord.get("year");
            String country = csvRecord.get("country");
            String genre = csvRecord.get("genre");
            String director = csvRecord.get("director");
            int minutes = Integer.parseInt(csvRecord.get("minutes"));
            String poster = csvRecord.get("poster");

            Movie newMovie = new Movie(id, title, year, genre, director, country, poster, minutes);
            movieArrayList.add(newMovie);
        }

        for (Movie movie : movieArrayList)
        {
            movieHashMap.put(movie.getID(), movie);
        }
    }
    public static ArrayList<String> filterBy(Filter f)
    {
        initialize();
        ArrayList<String> list = new ArrayList<String>();
        for (String id : movieHashMap.keySet())
        {
            if (f.satisfies(id))
            {
                list.add(id);
            }
        }
        return list;
    }
}
