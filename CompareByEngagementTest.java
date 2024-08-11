package prj5;

import student.TestCase;

// -------------------------------------------------------------------------
/**
 * test class for comparebyengagement.
 * 
 * @author jdevi
 * @version Nov 17, 2023
 */
public class CompareByEngagementTest
    extends TestCase
{
    // ~ Fields ................................................................
    /**
     * test object cbe
     */
    private CompareByEngagement cbe;
    /**
     * influencer 1
     */
    private Influencer inf1;
    /**
     * influencer 2
     */
    private Influencer inf2;

    // ~ Constructors ..........................................................
    // ----------------------------------------------------------
    /**
     * set up test class
     */
    public void setUp()
    {
        cbe = new CompareByEngagement(true, MonthEnum.JANUARY);
        inf1 = new Influencer("user1", "channel", "country", "topic");
        inf1.addOtherMetric(0, 1, 1, 3, 1, 1);
        inf2 = new Influencer(
            "user222222asdfdgerfs",
            "channel",
            "country",
            "topic");
        inf2.addOtherMetric(0, 2, 2, 2, 2, 20);
    }


    // ----------------------------------------------------------
    /**
     * test compare() method and private helper methods.
     */
    // ~Public Methods ........................................................
    public void testCompare()
    {
        assertEquals(1, cbe.compare(inf1, inf2));
        assertEquals(-1, cbe.compare(inf2, inf1));
        assertEquals(0, cbe.compare(inf1, inf1));
        cbe = new CompareByEngagement(false, MonthEnum.JANUARY);
        assertEquals(-1, cbe.compare(inf1, inf2));
        assertEquals(1, cbe.compare(inf2, inf1));
        assertEquals(0, cbe.compare(inf1, inf1));
    }
}
