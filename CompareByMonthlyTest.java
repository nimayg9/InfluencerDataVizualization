package prj5;

import student.TestCase;

// -------------------------------------------------------------------------
/**
 * test class for comparebymonthly.
 * 
 * @author jdevi
 * @version Nov 17, 2023
 */
public class CompareByMonthlyTest
    extends TestCase
{
    // ~ Fields ................................................................
    /**
     * test object cbm
     */
    private CompareByMonthly cbm;
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
        cbm = new CompareByMonthly(true, true, MonthEnum.JANUARY);
        inf1 = new Influencer("user1", "channel1", "country", "topic");
        inf1.addOtherMetric(0, 1, 1, 3, 1, 1);
        inf2 = new Influencer(
            "user222222asdfdgerfs",
            "channel2",
            "country",
            "topic");
        inf2.addOtherMetric(0, 2, 2, 2, 2, 2);
    }


    // ----------------------------------------------------------
    /**
     * test compare method.
     */
    // ~Public Methods ........................................................
    public void testCompare()
    {
        assertEquals(1, cbm.compare(inf1, inf2));
        cbm = new CompareByMonthly(false, true, MonthEnum.JANUARY);
        assertEquals(-1, cbm.compare(inf1, inf2));
    }
}
