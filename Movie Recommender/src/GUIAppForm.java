import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.net.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class GUIAppForm extends RecommendationRunner
{
    private JPanel panel1;
    private JTable table1;
    private JButton submitRatingButton;
    private JButton FINDMYRATINGSSSSSButton;
    private JButton WEBSITEButton;
    private JButton GITHUBButton;
    private int raterID;

    public GUIAppForm()
    {
        ArrayList<String> moviesToBeRated = getItemsToRate();
        MovieDatabase MD = new MovieDatabase();
        ArrayList<String> movieTable = new ArrayList<>();
        for(String ID: moviesToBeRated)
        {
            ID = MovieDatabase.getYear(ID) + " : " + MovieDatabase.getTitle(ID);
            movieTable.add(ID);
        }


        Object [][] rowData =
                {
                        {moviesToBeRated.get(0) , movieTable.get(0)},
                        {moviesToBeRated.get(1) , movieTable.get(1)},
                        {moviesToBeRated.get(2) , movieTable.get(2)},
                        {moviesToBeRated.get(3) , movieTable.get(3)},
                        {moviesToBeRated.get(4) , movieTable.get(4)},
                        {moviesToBeRated.get(5) , movieTable.get(5)},
                        {moviesToBeRated.get(6) , movieTable.get(6)},
                        {moviesToBeRated.get(7) , movieTable.get(7)},
                        {moviesToBeRated.get(8) , movieTable.get(8)},
                        {moviesToBeRated.get(9) , movieTable.get(9)}
                };

        String [] ratings = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        JComboBox ratingsBox = new JComboBox(ratings);

        table1.setModel(new DefaultTableModel(rowData, new String[]{"MovieID", "Movie", "Rating"}));
        table1.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(ratingsBox));
        ArrayList<String> capturedRatings = new ArrayList<>();

        ratingsBox.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {

                String value = (String) ratingsBox.getSelectedItem();
                if(value != null)
                {
                    capturedRatings.add(value);
                }
            }
        });

        //............... now we have the movies and the user's ratings.........................//


        submitRatingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {

                RaterDatabase RD = new RaterDatabase();
                RaterDatabase.initialize("ratings.csv");
                raterID = RaterDatabase.size() +1 ;

                try
                {
                    FileWriter fileWriter = new FileWriter("C:\\Users\\wali6\\Documents\\Programming Projects\\IDEA Projects\\MovieRecommender\\src\\data\\ratings.csv", true);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    PrintWriter pw = new PrintWriter(bufferedWriter);
                    int i = 0;
                    for(String ID: moviesToBeRated)
                    {
                        if(capturedRatings.get(i) != "0")
                        pw.println(raterID + "," + ID + "," + capturedRatings.get(i));
                        i++;
                    }
                    pw.flush();
                    pw.close();
                }
                catch (IOException ioException)
                {
                    ioException.printStackTrace();
                }
////////////////////////////////////////............... RATINGS SAVED IN CSV FILE.............................................//////////////////////////////
            }
        });

        FINDMYRATINGSSSSSButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                RaterDatabase RD = new RaterDatabase();
                RaterDatabase.initialize("ratings.csv");
                raterID = RaterDatabase.size();
                ArrayList<Rating> result = getRecommendationsFor(Integer.toString(raterID));


                String [][] rowData = new String[result.size()][3];
                for(int i = 0; i< result.size(); i++)
                {
                    Rating currRating = result.get(i);
                    String currMovieID = currRating.getItem();
                    rowData[i][0] = currMovieID;
                    rowData[i][1] =  MovieDatabase.getTitle(currMovieID);
                    rowData[i][2] =  Integer.toString(MovieDatabase.getYear(currMovieID));
                }

                table1.setModel(new DefaultTableModel(rowData, new String[]{"MovieID", "Movie", "Year"}));
            }
        });


        WEBSITEButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI("https://www.dukelearntoprogram.com//capstone/recommender.php?id=N4yFi2jPx5Xr7l"));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (URISyntaxException uriSyntaxException) {
                    uriSyntaxException.printStackTrace();
                }
            }
        });
        GITHUBButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI("https://github.com/SABERGLOW"));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (URISyntaxException uriSyntaxException) {
                    uriSyntaxException.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args)
    {
        JFrame frame = new JFrame("GUIAppForm");
        JFrame jFrame = new JFrame("MovieApp");
        jFrame.setContentPane(new GUIAppForm().panel1);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);
    }
}
