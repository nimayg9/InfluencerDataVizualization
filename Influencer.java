package prj5;

// -------------------------------------------------------------------------
/**
 * Influencer stores data from read files, including metrics and account info.
 * 
 * @author jdevi
 * @version Nov 15, 2023
 */
public class Influencer {

    // ~ Fields ................................................................
    /**
     * username of influencer
     */
    private String username;
    /**
     * channel name of influencer (used for name sorting instead of username)
     */
    private String channelName;
    /**
     * country of influencer
     */
    private String country;
    /**
     * main topic of influencer
     */
    private String mainTopic;
    /*
     * (* array of metrics over the year from influencer
     */
    private Metrics[] yearlyMetrics;

    // ~ Constructors ..........................................................
    // ----------------------------------------------------------
    /**
     * Create a new Influencer object.
     * 
     * @param username
     *            init username field
     * @param channelName
     *            init channel name field
     * @param country
     *            init country field
     * @param mainTopic
     *            init main topic field
     */
    public Influencer(
        String username,
        String channelName,
        String country,
        String mainTopic) {
        this.username = username;
        this.channelName = channelName;
        this.country = country;
        this.mainTopic = mainTopic;
        yearlyMetrics = new Metrics[InfluencerCalculator.NUM_MONTHS];
    }
    // ~Public Methods ........................................................


    // ----------------------------------------------------------
    /**
     * getter for username.
     * 
     * @return username field
     */
    public String getName() {
        return username;
    }


    // ----------------------------------------------------------
    /**
     * getter for channel name.
     * 
     * @return channel name field
     */
    public String getChannelName() {
        return channelName;
    }


    // ----------------------------------------------------------
    /**
     * getter for country.
     * 
     * @return country field
     */
    public String getCountry() {
        return country;
    }


    // ----------------------------------------------------------
    /**
     * getter for main topic.
     * 
     * @return mainTopic field
     */
    public String getMainTopic() {
        return mainTopic;
    }


    // ----------------------------------------------------------
    /**
     * getter for array of metrics object.
     * 
     * @return yearlyMetrics field
     */
    public Metrics[] getYearlyMetrics() {
        return yearlyMetrics;
    }


    // ----------------------------------------------------------
    /**
     * checks for equality between influencers.
     * 
     * @param other
     *            influencer (?)
     * @return true if fields match each other, or self compare. false if no
     *         match, not the same class
     */
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (this.getClass() != other.getClass()) {
            return false;
        }
        Influencer influencer = (Influencer)other;

        return this.getName().equals(influencer.getName()) && this
            .getChannelName().equals(influencer.getChannelName()) && this
                .getCountry().equals(influencer.getCountry()) && this
                    .getMainTopic().equals(influencer.getMainTopic());
    }


    // ----------------------------------------------------------
    /**
     * gets the metric object attributed to a month for an influencer.
     * 
     * @param month
     *            the month specified for
     * @return metrics object
     */
    public Metrics getMetricsForMonth(MonthEnum month)
    {
        return yearlyMetrics[InfluencerCalculator.getIndexForMonth(month)];
    }


    // ----------------------------------------------------------
    /**
     * create new metric object and add to yearlyMetrics. used in
     * InputFileReader
     * 
     * @param monthIndex
     *            index in yearlyMetrics array
     * @param likes
     *            init likes field
     * @param posts
     *            init posts field
     * @param followers
     *            init followers field
     * @param comments
     *            init comments field
     * @param views
     *            init views field
     */
    public void addOtherMetric(
        int monthIndex,
        int likes,
        int posts,
        int followers,
        int comments,
        int views) {
        yearlyMetrics[monthIndex] = new Metrics(likes, posts, followers,
            comments, views);
    }
}
