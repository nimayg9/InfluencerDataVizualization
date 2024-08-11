package prj5;

import java.util.Comparator;

// -------------------------------------------------------------------------
/**
 * Using engagement rate and time frame, compares data between influencers to
 * sort influencers in descending order.
 * 
 * @author jdevi
 * @version Nov 16, 2023
 */
public class CompareByEngagement
    implements Comparator<Influencer>
{
    // ~ Fields ................................................................
    /**
     * true if traditional rate is selected, false if reach rate is selected
     */
    private boolean engagementRateIsTraditional;
    /**
     * specifies a month if month filter is selected for said month. If null,
     * quarterly data is collected.
     */
    private MonthEnum month;

    // ----------------------------------------------------------
    /**
     * Create a new CompareByEngagement object.
     * 
     * @param isTraditional
     *            init engagementRateIsTraditional field
     * @param m
     *            init month field; can be null
     */
    // ~ Constructors ..........................................................
    public CompareByEngagement(boolean isTraditional, MonthEnum m)
    {
        engagementRateIsTraditional = isTraditional;
        month = m;
    }


    // ~Public Methods ........................................................
    /**
     * determines the timeframe and engagement rate, and compares the two
     * Influencers according to the selected filters.
     * 
     * @param inf1
     *            Influencer object compared with inf2
     * @param inf2
     *            Influencer object compared with inf1
     * @return positive integer if the second influencer's numbers are bigger,
     *             negative integer if they are smaller, 0 if the numbers are
     *             equal.
     */
    public int compare(Influencer inf1, Influencer inf2)
    {
        if (month != null)
        {
            return compareMonthly(inf1, inf2);
        }

        return compareQuarterly(inf1, inf2);

    }


    // ----------------------------------------------------------
    /**
     * Compares a month's data of two Influencers based on the engagement rate
     * selected.
     * 
     * @param inf1
     *            Influencer object compared with inf2
     * @param inf2
     *            Influencer object compared with inf1
     * @return positive integer if the second influencer's numbers are bigger,
     *             negative integer if they are smaller, 0 if the numbers are
     *             equal.
     */
    private int compareMonthly(Influencer inf1, Influencer inf2)
    {
        // order in metric arrays: Views, Followers, Posts, Likes, Comments
        int[] metrics1 = inf1.getMetricsForMonth(month).toArray();
        int[] metrics2 = inf2.getMetricsForMonth(month).toArray();

        return compareEngagementRate(metrics1, metrics2);
    }


    // ----------------------------------------------------------
    /**
     * Compares quarterly data of two Influencers based on the engagement rate
     * selected
     * 
     * @param inf1
     *            Influencer object compared with inf2
     * @param inf2
     *            Influencer object compared with inf1
     * @return positive integer if the second influencer's numbers are bigger,
     *             negative integer if they are smaller, 0 if the numbers are
     *             equal.
     */
    private int compareQuarterly(Influencer inf1, Influencer inf2)
    {
        // we need the combined metrics over the course of January thru March.
        int[] metrics1 = new int[5];
        int[] metrics2 = new int[5];

        // init metric arrays as well
        for (int i = 0; i < metrics1.length; i++)
        {
            metrics1[i] = 0;
            metrics2[i] = 0;
        }
        // loop thru each relevant month (
        for (int mIndex = 0; mIndex < 3; mIndex++)
        {
            int[] monthlyMetrics1 = inf1.getMetricsForMonth(
                InfluencerCalculator.REF_MONTH_ARRAY[mIndex]).toArray();
            int[] monthlyMetrics2 = inf2.getMetricsForMonth(
                InfluencerCalculator.REF_MONTH_ARRAY[mIndex]).toArray();

            // loop thru monthly metric arrays to add to the quarterly arrays
            for (int i = 0; i < metrics1.length; i++)
            {
                metrics1[i] += monthlyMetrics1[i];
                metrics2[i] += monthlyMetrics2[i];
            }
        }
        metrics1[1] = inf1.getMetricsForMonth(MonthEnum.MARCH).toArray()[2];
        metrics2[1] = inf2.getMetricsForMonth(MonthEnum.MARCH).toArray()[2];

        // we can now call compareEngagementRate with our newly compiled metrics
        // arrays.
        return compareEngagementRate(metrics1, metrics2);

    }


    // ----------------------------------------------------------
    /**
     * Given two arrays of metrics and the selected engagement rate, determines
     * the greater of the rate from each array.
     * 
     * @param metrics1
     *            the metrics from the first Influencer
     * @param metrics2
     *            the metrics from the second Influencer
     * @return positive integer if the second array's rate is bigger, negative
     *             integer if it is smaller, 0 if the numbers are equal.
     */
    private int compareEngagementRate(int[] metrics1, int[] metrics2)
    {
        // determine rate type
        if (engagementRateIsTraditional)
        {
            // traditional rate: ((comments + likes)/followers) x 100
            // LPFCV
            // 01234
            double trad1 = InfluencerCalculator
                .getTraditionalRate(metrics1[0], metrics1[3], metrics1[2]);
            if (metrics1[1] == 0)
            {
                trad1 = 0;
            }
            double trad2 = InfluencerCalculator
                .getTraditionalRate(metrics2[0], metrics2[3], metrics2[2]);
            if (metrics2[1] == 0)
            {
                trad2 = 0;
            }

            // descending order - larger appears closer to the front of the list
            if (trad1 > trad2)
            {
                return -1;
            }
            if (trad1 < trad2)
            {
                return 1;
            }
            return 0;
        }

        // if runtime reaches this point, engagement rate is reach-based
        // reach rate = ( (comments + likes) / views) x 100
        double reach1 = InfluencerCalculator
            .getReachRate(metrics1[3], metrics1[0], metrics1[4]);
        if (metrics1[4] == 0)
        {
            reach1 = 0;
        }
        double reach2 = InfluencerCalculator
            .getReachRate(metrics2[3], metrics2[0], metrics2[4]);
        if (metrics2[4] == 0)
        {
            reach2 = 0;
        }
        // same method applies as previous if-statement
        if (reach1 > reach2)
        {
            return -1;
        }
        if (reach1 < reach2)
        {
            return 1;
        }
        return 0;
    }
}
