package prj5;

import java.util.Comparator;

// -------------------------------------------------------------------------
/**
 * Helps sort Influencer lists for quarterly data
 * 
 * @author jdevi
 * @version Nov 16, 2023
 */
public class CompareByQuarterly
    implements Comparator<Influencer>
{
    // ~ Fields ................................................................
    /**
     * true if data is sorted by engagement rate, false if sorted by channel
     * name.
     */
    private boolean isSortedByRate;
    /**
     * true if data is display by traditional rate, false if displayed by reach
     * rate.
     */
    private boolean engagementRateIsTraditional;

    // ----------------------------------------------------------
    /**
     * Create a new CompareByQuarterly object.
     * 
     * @param sortedByRate
     *            init isSortedByRate field
     * @param isTraditional
     *            init engagementRateIsTraditional
     */
    // ~ Constructors ..........................................................
    public CompareByQuarterly(boolean sortedByRate, boolean isTraditional)
    {
        isSortedByRate = sortedByRate;
        engagementRateIsTraditional = isTraditional;
    }
    // ~Public Methods ........................................................


    // ----------------------------------------------------------
    /**
     * compares two Influencers by quarterly data. Either calls the compare
     * method in CompareByEngagement or CompareByName based on sort method
     * 
     * @param inf1
     *            Influencer object compared with inf2
     * @param inf2
     *            Influencer object compared with inf1
     * @return positive integer if the second influencer ranks higher than first
     *             influencer, negative integer if vice versa. 0 if they rank
     *             the same.
     */
    @Override
    public int compare(Influencer inf1, Influencer inf2)
    {
        if (!isSortedByRate)
        {
            CompareByName names = new CompareByName();
            return names.compare(inf1, inf2);
        }
        CompareByEngagement comparator =
            new CompareByEngagement(engagementRateIsTraditional, null);
        return comparator.compare(inf1, inf2);
    }
}
