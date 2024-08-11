package prj5;

import student.TestCase;

// -------------------------------------------------------------------------
/**
 * test class for compare by quarterly
 * 
 * @author jdevi
 * @version Nov 17, 2023
 */
public class CompareByQuarterlyTest
    extends TestCase
{
    // ~ Fields ................................................................
    /**
     * test object for cbq
     */
    private CompareByQuarterly cbq;
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
     * set up this test class
     */
    public void setUp()
    {
        cbq = new CompareByQuarterly(true, true);
        inf1 = new Influencer("user1", "channel1", "country", "topic");
        inf1.addOtherMetric(0, 1, 1, 3, 1, 1);
        inf1.addOtherMetric(1, 1, 1, 3, 1, 1);
        inf1.addOtherMetric(2, 1, 1, 3, 1, 1);
        inf2 = new Influencer(
            "user222222asdfdgerfs",
            "channel2",
            "country",
            "topic");
        inf2.addOtherMetric(0, 2, 2, 2, 2, 2);
        inf2.addOtherMetric(1, 2, 2, 2, 2, 2);
        inf2.addOtherMetric(2, 2, 2, 2, 2, 2);
    }


    // ----------------------------------------------------------
    /**
     * test compare method
     */
    // ~Public Methods ........................................................
    public void testCompare()
    {
        assertEquals(1, cbq.compare(inf1, inf2));
        cbq = new CompareByQuarterly(false, true);
        assertEquals(-1, cbq.compare(inf1, inf2));
    }
}
