package prj5;

import student.TestCase;

// -------------------------------------------------------------------------
/**
 * Test class for influencer.
 * 
 * @author dajan
 * @version Nov 17, 2023
 */
public class InfluencerTest
    extends TestCase
{
    // ~ Fields ................................................................
    /**
     * first influencer test object
     */
    private Influencer i1;
    /**
     * second influencer test object
     */
    private Influencer i2;
    // ~ Constructors ..........................................................

    // ~Public Methods ........................................................
    /**
     * Set up for all test methods. Runs before every test.
     */
    public void setUp()
    {
        i1 = new Influencer("a", "A", "USA", "sports");
        i2 = new Influencer("a", "A", "USA", "sports");

    }


    /**
     * Test getters
     */
    public void testGetters()
    {
        assertEquals("a", i1.getName());
        assertEquals("A", i1.getChannelName());
        assertEquals("USA", i1.getCountry());
        assertEquals("sports", i1.getMainTopic());
    }


    /**
     * Test equals
     */
    public void testEquals()
    {
        assertTrue(i1.equals(i1));
        assertTrue(i1.equals(i2));
        assertFalse(i1.equals(null));
        Object obj = new Object();
        assertFalse(i1.equals(obj));
        Influencer i3 = new Influencer("A", "A", "USA", "sports");
        assertFalse(i1.equals(i3));
        Influencer i4 = new Influencer("a", "B", "USA", "sports");
        assertFalse(i1.equals(i4));
        Influencer i5 = new Influencer("a", "A", "Japan", "sports");
        assertFalse(i1.equals(i5));
        Influencer i6 = new Influencer("a", "A", "USA", "reading");
        assertFalse(i1.equals(i6));
    }


    /**
     * Test the metrics
     */
    public void testYearlyMetrics()
    {
        i1.getYearlyMetrics();
        i1.getMetricsForMonth(MonthEnum.JANUARY);
        i1.addOtherMetric(11, 5, 7, 8, 9, 10);
        assertNotNull(i1);
    }

}
