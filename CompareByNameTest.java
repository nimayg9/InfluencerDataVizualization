package prj5;
// -------------------------------------------------------------------------
/**
 *  Tests that the compareByName Comparator works properly
 * 
 *  @author markelm22
 *  @version Nov 19, 2023
 */
public class CompareByNameTest extends student.TestCase
{
    //~ Fields ................................................................
    private CompareByName cbn;
    private Influencer infl1;
    private Influencer infl2;
    private Influencer infl3;
    private Influencer infl4;
    //~ Constructors ..........................................................
    /**
     * Sets up the following test class
     */
    public void setUp()
    {
        cbn = new CompareByName();
        infl1 = new Influencer("TommyPlays", "channelTwo", "pop", "random");
        infl2 = new Influencer("AndrewPlays", "channelOne", "pop", "random");
        infl3 = new Influencer("Tommy", "channel", "pop", "random");
        infl4 = new Influencer("TommyWoah", "channelOne", "rock", "random");
    }
    //~Public  Methods ........................................................
    // ----------------------------------------------------------
    /**
     * Compares different influencer objects by channel name
     */
    public void testCompareNames()
    {
        assertEquals(-1, cbn.compare(infl2, infl1));
        assertEquals(1, cbn.compare(infl2, infl3));
        assertEquals(0, cbn.compare(infl2, infl4));
        
    }
}
