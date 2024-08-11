package prj5;

import student.TestCase;

// -------------------------------------------------------------------------
/**
 * influencer calculator test class
 * 
 * @author jdevi
 * @version Nov 19, 2023
 */
public class InfluencerCalculatorTest
    extends TestCase
{

    /**
     * This constructor initializes the influencer calculator if needed
     */
    public void setUp()
    {
        // method stub
    }


    /**
     * This tests the getDataForMonth class
     */
    public void testGetDataForMonth()
    {
        DLList<Influencer> list = new DLList<Influencer>();
        Influencer i1 = new Influencer("b", "A", "USA", "sports");
        i1.addOtherMetric(0, 1, 1, 1, 1, 1);
        i1.addOtherMetric(1, 2, 2, 2, 2, 2);
        i1.addOtherMetric(2, 3, 3, 3, 3, 3);
        Influencer i2 = new Influencer("a", "B", "Canada", "food");
        i2.addOtherMetric(0, 1, 1, 1, 1, 1);
        i2.addOtherMetric(1, 2, 2, 2, 2, 2);
        i2.addOtherMetric(2, 3, 3, 3, 3, 3);
        Influencer i3 = new Influencer("c", "C", "France", "cars");
        i3.addOtherMetric(0, 1, 1, 1, 1, 1);
        i3.addOtherMetric(1, 2, 2, 2, 2, 2);
        i3.addOtherMetric(2, 3, 3, 3, 3, 3);
        Influencer i4 = new Influencer("d", "D", "Germany", "comedy");
        i4.addOtherMetric(0, 1, 1, 1, 1, 1);
        i4.addOtherMetric(1, 2, 2, 2, 2, 2);
        i4.addOtherMetric(2, 3, 3, 3, 3, 3);
        list.add(i1);
        list.add(i2);
        list.add(i3);
        list.add(i4);
        InfluencerCalculator ic = new InfluencerCalculator(list);
        Metrics i1Metrics = new Metrics(1, 2, 3, 4, 5);
        int[] arrayMetrics = i1Metrics.toArray();
        // Object[] icJanuaryData = ic.getDataForMonth(Month.JANUARY).toArray();
        // int[] intArray = (int[])icJanuaryData;
        assertEquals(arrayMetrics[0], 1);
        assertEquals(
            ic.getDataForMonth(MonthEnum.JANUARY).get(0).getComments(),
            1);
        assertEquals(
            ic.getDataForMonth(MonthEnum.FEBRUARY).get(0).getComments(),
            2);
        // assertEquals("", ic.getDataForMonth(Month.JANUARY).toArray()
        // .toString());
    }

// /**
// * This tests the getQuarterlyDataForMonth class
// */
// public void testGetQuarterlyData() {
// assertEquals(ic.getQuarterlyData(Month.JANUARY, Month.FEBRUARY,
// Month.MARCH).get(0).get(0).getComments(), 1);
// assertEquals(ic.getQuarterlyData(Month.JANUARY, Month.FEBRUARY,
// Month.MARCH).get(1).get(0).getComments(), 2);
// }


    /**
     * This tests both getIndexForMonth methods
     */
    public static void testGetIndexForMonth()
    {
        assertEquals(
            0,
            InfluencerCalculator.getIndexForMonth(MonthEnum.JANUARY));
        assertEquals(0, InfluencerCalculator.getIndexForMonth("January"));
        assertEquals(1, InfluencerCalculator.getIndexForMonth("february"));
        assertEquals(-1, InfluencerCalculator.getIndexForMonth("1"));
    }


    /**
     * This tests the getRate methods
     */
    public static void testRateMethods()
    {
        assertEquals(
            InfluencerCalculator.getTraditionalRate(1, 1, 1),
            200.00,
            0.01);
        assertEquals(InfluencerCalculator.getReachRate(1, 1, 2), 100.00, 0.01);

    }


    /**
     * test the insertionSort() method :(
     */
    public void testInsertionSortAndLastRateMethod()
    {
        CompareByMonthly cbm =
            new CompareByMonthly(true, true, MonthEnum.JANUARY);

        InputFileReader i = new InputFileReader("SampleInput1_2023.csv");
// for (int i = 0; i < list.size(); i++) {
// System.out.println(list.get(i));
        assertEquals(-1, InfluencerCalculator.getIndexForMonth("1"));

// }

    }

}
