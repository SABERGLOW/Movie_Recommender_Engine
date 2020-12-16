/**Movie Class is a Plain Old Java Object (POJO) class for storing the data about one movie.**/

public class Movie
{
    private String id;           //...String variable representing the IMDB ID of the movie
    private String title;       //...String variable for the movie’s title
    private int year;          //...Integer representing the year
    private String genres;    //...String of one or more genres separated by commas
    private String director; //...String of one or more directors of the movie separated by commas
    private String country; //...String of one or more countries the film was made in, separated by commas
    private String poster; //...String that is a link to an image of the movie poster if one exists, or “N/A” if no poster exists
    private int minutes;  //...Integer for the length of the movie

    public Movie(String anID, String aTitle, String aYear, String theGenres)
    {
        // just in case data file contains extra whitespace
        id = anID.trim();
        title = aTitle.trim();
        year = Integer.parseInt(aYear.trim());
        genres = theGenres;
    }

    public Movie(String anID, String aTitle, String aYear, String theGenres, String aDirector,
                 String aCountry, String aPoster, int theMinutes)
    {
        // just in case data file contains extra whitespace
        id = anID.trim();
        title = aTitle.trim();
        year = Integer.parseInt(aYear.trim());
        genres = theGenres;
        director = aDirector;
        country = aCountry;
        poster = aPoster;
        minutes = theMinutes;
    }

    // Returns ID associated with this item
    public String getID()
    {
        return id;
    }

    // Returns title of this item
    public String getTitle()
    {
        return title;
    }

    // Returns year in which this item was published
    public int getYear()
    {
        return year;
    }

    // Returns genres associated with this item
    public String getGenres()
    {
        return genres;
    }

    // Returns countries the movie was made in
    public String getCountry()
    {
        return country;
    }

    // Returns directors of the movie
    public String getDirector()
    {
        return director;
    }

    // Returns poster string for the movie
    public String getPoster()
    {
        return poster;
    }

    // Returns length of the movie
    public int getMinutes()
    {
        return minutes;
    }

    // Returns a string of the item's information to print easily
    public String toString()
    {
        return "Movie [id=" + id + ", title=" + title + ", year=" + year + ", genres= " + genres + "]";
    }
}
