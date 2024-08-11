package prj5;

import student.TestCase;

// -------------------------------------------------------------------------
/**
 * This test class tests the metrics class
 * 
 * @author dajan
 * @version Nov 17, 2023
 */
public class MetricsTest
    extends TestCase
{
    // ~ Fields ................................................................
    /**
     * a metric object for january
     */
    private Metrics january1;
    /**
     * a metric object for february
     */
    private Metrics february;
    /**
     * another metric object for january :OOOO
     */
    private Metrics january2;
    // ~ Constructors ..........................................................

    // ~Public Methods ........................................................
    /**
     * Set up for all test methods. Runs before every test.
     */
    public void setUp()
    {
        january1 = new Metrics(20, 2, 34, 4, 29);
        february = new Metrics(30, 3, 38, 6, 35);
        january2 = new Metrics(59, 4, 60, 28, 45);
    }


    /**
     * This method tests the getViews method
     */
    public void testGetViews()
    {
        assertEquals(29, january1.getViews());
        assertEquals(35, february.getViews());
        assertEquals(45, january2.getViews());
    }


    /**
     * This method tests the getFollowers method
     */
    public void testGetFollowers()
    {
        assertEquals(34, january1.getFollowers());
        assertEquals(38, february.getFollowers());
        assertEquals(60, january2.getFollowers());
    }


    /**
     * This method tests the getPosts method
     */
    public void testGetPosts()
    {
        assertEquals(2, january1.getPosts());
        assertEquals(3, february.getPosts());
        assertEquals(4, january2.getPosts());
    }


    /**
     * This method tests the getPosts method
     */
    public void testGetLikes()
    {
        assertEquals(20, january1.getLikes());
        assertEquals(30, february.getLikes());
        assertEquals(59, january2.getLikes());
    }


    /**
     * This method tests the getComments method
     */
    public void testGetComments()
    {
        assertEquals(4, january1.getComments());
        assertEquals(6, february.getComments());
        assertEquals(28, january2.getComments());
    }


    /**
     * This method tests the toArray method
     */
    public void testToArray()
    {
        int[] copyArray = { 20, 2, 34, 4, 29 };
        int[] toArrayJan1 = january1.toArray();
        for (int i = 0; i < toArrayJan1.length; i++)
        {
            assertEquals(toArrayJan1[i], copyArray[i]);
        }
    }

}
