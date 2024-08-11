package prj5;

// -------------------------------------------------------------------------
/**
 * metrics class to help store data for an influencer
 * 
 * @author jdevi
 * @version Nov 16, 2023
 */
public class Metrics
{
    /**
     * month from which these metrics were gathered
     */
// private Month month;
    /**
     * likes gained this month
     */
    private int likes;
    /**
     * posts made this month
     */
    private int posts;
    /**
     * followers gained this month
     */
    private int followers;
    /**
     * comments posted this month
     */
    private int comments;
    /**
     * views gained this month
     */
    private int views;

    // ----------------------------------------------------------
    /**
     * Create a new Metrics object.
     * 
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
    public Metrics(int likes, int posts, int followers, int comments, int views)
    {
        this.likes = likes;
        this.posts = posts;
        this.followers = followers;
        this.comments = comments;
        this.views = views;
    }


    /**
     * getter for views field
     * 
     * @return views field
     */
    public int getViews()
    {
        return views;
    }


    /**
     * getter for followers field
     * 
     * @return followers field
     */
    public int getFollowers()
    {
        return followers;
    }


    /**
     * getter for posts field
     * 
     * @return posts field
     */
    public int getPosts()
    {
        return posts;
    }


    /**
     * getter for likes field
     * 
     * @return likes field
     */
    public int getLikes()
    {
        return likes;
    }


    /**
     * getter for comments field
     * 
     * @return comments field
     */
    public int getComments()
    {
        return comments;
    }


    /**
     * contents go in the order of: likes, posts, followers, comments, views
     * 
     * @return the finalized array
     */
    public int[] toArray()
    {
        int[] result = { likes, posts, followers, comments, views };
        return result;
    }
}
