package prj5;

import java.text.DecimalFormat;
import java.util.*;

// -------------------------------------------------------------------------
/**
 * Handles most calculations and decisions in this project.
 * 
 * @author jdevi
 * @version Nov 15, 2023
 */
public class InfluencerCalculator {
    // ~ Fields ................................................................
    /**
     * holds the influencer objects read from files
     */
    private DLList<Influencer> influencers;
    /**
     * static constant denoting the number of months in a year
     */
    public static final int NUM_MONTHS = 12;
    /**
     * gives stable conversion between Month and index
     */
    public static final MonthEnum[] REF_MONTH_ARRAY = { MonthEnum.JANUARY,
        MonthEnum.FEBRUARY, MonthEnum.MARCH, MonthEnum.APRIL, MonthEnum.MAY,
        MonthEnum.JUNE, MonthEnum.JULY, MonthEnum.AUGUST, MonthEnum.SEPTEMBER,
        MonthEnum.OCTOBER, MonthEnum.NOVEMBER, MonthEnum.DECEMBER };

    // ----------------------------------------------------------
    /**
     * Create a new InfluencerCalculator object.
     * 
     * @param influencers
     *            init influencers field
     */
    // ~ Constructors ..........................................................
    public InfluencerCalculator(DLList<Influencer> influencers) {
        this.influencers = influencers;
    }


    // ~Public Methods ........................................................
    // ----------------------------------------------------------
    /**
     * return a list of metrics from a given month from each influencer.
     * 
     * @param monthName
     *            selected month
     * @return a list of metrics objects with one from each influencer
     */
    public List<Metrics> getDataForMonth(MonthEnum monthName) {
        List<Metrics> dataForMonth = new ArrayList<>();
        for (int i = 0; i < influencers.size(); i++) {
            dataForMonth.add(influencers.get(i).getMetricsForMonth(monthName));
        }
        return dataForMonth;
    }


    // ----------------------------------------------------------
    /**
     * primary sorting method for this project/class. uses comparator objects
     * for ordering.
     * 
     * @param c
     *            selected comparator to sort DLList with.
     */
    public void insertionSort(Comparator<Influencer> c) {
        // DLList<Influencer> copy = makeShallowCopy();
        influencers.insertionSort(c);
    }


    // ----------------------------------------------------------
    /**
     * prints outputs for the intermediate submission.
     */
    public void printOutput() {
        CompareByQuarterly c = new CompareByQuarterly(false, true);
        insertionSort(c);
        String pattern = "#.#";
        DecimalFormat df = new DecimalFormat(pattern);
        // print traditionals
        for (int i = 0; i < influencers.size(); i++) {
            // LPFCV
            // 01234
            Influencer inf = influencers.get(i);
            System.out.println(inf.getChannelName());
            int[] qData = getQuarterlyData(inf);
            if (qData[2] != 0) {
                System.out.println("traditional: " + df.format(
                    getTraditionalRate(qData[0], qData[3], qData[2]))
                    + "\n==========");
            }
            else {
                System.out.println("traditional: na\n==========");
            }
        }
        System.out.println("**********\n**********");
        c = new CompareByQuarterly(true, false);
        insertionSort(c);
        String nl = "\n";
        // print reaches
        for (int i = 0; i < influencers.size(); i++) {
            Influencer inf = influencers.get(i);
            System.out.println(inf.getChannelName());
            int[] qData = getQuarterlyData(inf);
            if (qData[4] != 0) {
                System.out.println("reach: " + df.format(getReachRate(qData[0],
                    qData[3], qData[4])));
            }
            else {
                System.out.println("reach: na");
            }
            if (i < influencers.size() - 1) {
                System.out.println("==========");
            }
        }
        System.out.println("==========");
    }


    // ----------------------------------------------------------
    /**
     * returns an integer array containing 3 month's worth of data.
     * 
     * @param inf
     *            is the influencer which data is being got
     * @return int array of metric data
     */
    public int[] getQuarterlyData(Influencer inf) {
        int[] result = new int[5];
        for (int i = 0; i < result.length; i++) {
            result[i] = 0;
        }
        for (int mIndex = 0; mIndex < 3; mIndex++) {
            int[] monthlyMetrics = inf.getMetricsForMonth(
                InfluencerCalculator.REF_MONTH_ARRAY[mIndex]).toArray();
            for (int i = 0; i < result.length; i++) {
                result[i] += monthlyMetrics[i];
            }
        }
        result[2] = inf.getMetricsForMonth(MonthEnum.MARCH).toArray()[2];
        return result;

    }


    // ----------------------------------------------------------
    /**
     * gets the index-equivalent of a month in the year, indexed at zero. (i.e.
     * January->0, February->1, ... , December->11)
     * 
     * @param month
     *            specified month of enum type to find the index of
     * @return index of the month
     */
    public static int getIndexForMonth(MonthEnum month) {
        int index = -1;
        for (int i = 0; i < NUM_MONTHS; i++) {
            if (month.equals(REF_MONTH_ARRAY[i])) {
                index = i;
            }
        }
        return index;
    }


    // ----------------------------------------------------------
    /**
     * gets the index-equivalent of a month in the year, indexed at zero. (i.e.
     * January->0, February->1, ... , December->11) **this method is useful for
     * InputFileReader due to the string parameter. As such, this method
     * converts the string to a month enum type.
     * 
     * @param monthStr
     *            specified month of enum type to find the index of. String type
     * @return index of the month
     */
    public static int getIndexForMonth(String monthStr) {
        try {
            MonthEnum month = Enum.valueOf(MonthEnum.class, monthStr
                .toUpperCase());
            return getIndexForMonth(month);
        }
        catch (Exception e) {
            return -1;
        }

    }


    // ----------------------------------------------------------
    /**
     * static helper method for acquiring the traditional rate of some time
     * frame
     * 
     * @param likes
     *            given likes
     * @param comments
     *            given comments
     * @param followers
     *            given followers
     * @return the final traditional rate calculated
     */
    public static double getTraditionalRate(
        int likes,
        int comments,
        int followers) {
        return ((likes + comments) * 1.0 / followers * 1.0) * 100.0;
    }


    // ----------------------------------------------------------
    /**
     * static helper for reach rate.
     * 
     * @param likes
     *            given likes
     * @param comments
     *            given comments
     * @param views
     *            given views
     * @return final calculation of reach rate
     */
    public static double getReachRate(int likes, int comments, int views) {
        return ((likes + comments) * 1.0 / views * 1.0) * 100.0;
    }
}
