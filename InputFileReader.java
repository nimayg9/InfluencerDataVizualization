package prj5;

import student.IOHelper;
import java.util.Scanner;
import java.text.DecimalFormat;

// -------------------------------------------------------------------------
/**
 * This class reads and parses data in files to store the data in Influencer
 * objects.
 * 
 * @author jdevi
 * @version Nov 15, 2023
 */
public class InputFileReader
{
    // ~ Fields ................................................................
    /**
     * influencer DLList read from file parsing
     */
    private DLList<Influencer> influencers;
    /**
     * max amt of influencers in one file
     */
    public static final int INFLUENCER_TOKENS = 4;

    // ~ Constructors ..........................................................
    // ----------------------------------------------------------
    /**
     * Create a new InputFileReader object.
     * 
     * @param fileName
     *            name of file being read in string form
     */
    public InputFileReader(String fileName)
    {
        influencers = new DLList<Influencer>();
        readInfluencerFile(fileName);
        @SuppressWarnings("unused")
        InfluencerCalculator influencerCalc =
            new InfluencerCalculator(influencers);
        influencerCalc.printOutput();

    }


    // ~Public Methods ........................................................
    // ----------------------------------------------------------
    /**
     * main method for this class. parses file given in constructor.
     * 
     * @param fileName
     *            name of file passed from constructor
     */
    private void readInfluencerFile(String fileName)
    {
        @SuppressWarnings("resource")
        Scanner inStream = IOHelper.createScanner(fileName);
        inStream.nextLine();// skip header

        int monthIndex = 0;
        while (inStream.hasNextLine())
        {
            String line = inStream.nextLine().replaceAll(" ", "");
            String[] values = line.split(",");
            String month = values[0];
            String username = values[1];
            String channel = values[2];
            String country = values[3];
            String mainTopic = values[4];
            int likes = toInt(values[5]);
            int posts = toInt(values[6]);
            int followers = toInt(values[7]);
            int comments = toInt(values[8]);
            int views = toInt(values[9]);

            Influencer nextInfluencer =
                new Influencer(username, channel, country, mainTopic);
            if (influencers.contains(nextInfluencer))
            {
                // getting the reference to the original influencer
                nextInfluencer = retrieveInfluencerFromList(nextInfluencer);
                monthIndex = InfluencerCalculator.getIndexForMonth(month);
                nextInfluencer.addOtherMetric(
                    monthIndex,
                    likes,
                    posts,
                    followers,
                    comments,
                    views);
            }
            else // here we keep the reference on the newly created influencer
            {
                monthIndex = InfluencerCalculator.getIndexForMonth(month);
                nextInfluencer.addOtherMetric(
                    monthIndex,
                    likes,
                    posts,
                    followers,
                    comments,
                    views);
                influencers.add(nextInfluencer);
            }
        }

        inStream.close();
    }


    // ----------------------------------------------------------
    /**
     * helper method to add metric info to a pre-existing influencer in the data
     * structure.
     * 
     * @param influencerInList
     *            the influencer in question
     * @return the influencer in the list
     */
    private Influencer retrieveInfluencerFromList(Influencer influencerInList)
    {
        int i = 0;
        while (i < influencers.size()) // might have to make it less than or
                                       // equal to
        {
            Influencer thisInfluencer = influencers.get(i);
            if (thisInfluencer.equals(influencerInList))
            {
                return thisInfluencer;
            }
            i++;
        }
        return null;
    }


    // ----------------------------------------------------------
    /**
     * sends a string to an int if possible. throws exception when necessary.
     * 
     * @param strInteger
     *            string form of the integer
     * @return the integer originally in string form, now able to truly express
     *             itself. returns 0 if bad parse
     */
    private int toInt(String strInteger)
    {
        try
        {
            return Integer.parseInt(strInteger);
        }
        catch (Exception e)
        {
            return 0;
        }
    }
}
