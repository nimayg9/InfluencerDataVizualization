package prj5;

import java.util.NoSuchElementException;
import student.TestCase;

// -------------------------------------------------------------------------
/**
 * dllist test class
 * 
 * @author rashed72
 * @version Nov 17, 2023
 */
public class DLListTest extends TestCase {
    // ~ Fields ................................................................
    /**
     * not empty list
     */
    private DLList<String> list;
    /**
     * it might be an empty list who knows
     */
    private DLList<String> emptyList;
    // ~ Constructors ..........................................................

    // ~Public Methods ........................................................
    /**
     * Set up all the test methods
     */
    public void setUp() {
        list = new DLList<String>();
        list.add("Influencer1");
        list.add("Influencer2");
        emptyList = new DLList<String>();
    }


    // ----------------------------------------------------------
    /**
     * test add method to back of list.
     */
    public void testAdd() {
        assertEquals("{Influencer1, Influencer2}", list.toString());
        assertEquals(2, list.size());
        list.add("Influencer3");
        assertEquals(3, list.size());

        assertTrue(emptyList.isEmpty());
        emptyList.add("Influencer1");
        assertFalse(emptyList.isEmpty());
    }


    // ----------------------------------------------------------
    /**
     * test add other cases.
     */
    public void testAdd2() {
        assertEquals(0, emptyList.size());
        emptyList.add("A");
        assertEquals(1, emptyList.size());
        emptyList.add("B");
        assertEquals(2, emptyList.size());
        assertEquals("B", emptyList.get(1));

    }


    // ----------------------------------------------------------
    /**
     * test add method to specific index.
     */
    public void testAddIndex() {
        emptyList.add("B");
        emptyList.add(0, "A");
        assertEquals("A", emptyList.get(0));
        assertEquals(2, emptyList.size());
        emptyList.add(2, "D");
        assertEquals("D", emptyList.get(2));
        emptyList.add(2, "C");
        assertEquals("C", emptyList.get(2));
        Exception e = null;
        try {
            list.add(null);
        }
        catch (Exception exception) {
            e = exception;
        }
        assertTrue(e instanceof IllegalArgumentException);
        Exception e2 = null;
        try {
            list.add(0, null);
        }
        catch (Exception exception) {
            e2 = exception;
        }
        assertTrue(e2 instanceof IllegalArgumentException);
    }


    // ----------------------------------------------------------
    /**
     * test add method for exceptions
     */
    public void testAddExceptions() {
        emptyList.add("A");
        Exception e = null;
        try {
            emptyList.add(2, "B");
        }
        catch (Exception exception) {
            e = exception;
        }
        assertTrue(e instanceof IndexOutOfBoundsException);
        Exception e2 = null;
        e2 = null;
        try {
            emptyList.add(-1, "B");
        }
        catch (Exception exception) {
            e2 = exception;
        }
        assertTrue(e2 instanceof IndexOutOfBoundsException);
    }


    // ----------------------------------------------------------
    /**
     * test get method.
     */
    public void testGet() {
        list.add("Influencer3");
        assertEquals(3, list.size());
        assertEquals("Influencer1", list.get(0));
        assertEquals("Influencer2", list.get(1));
        Exception e = null;
        try {
            emptyList.get(-1);
        }
        catch (Exception exception) {
            e = exception;
        }
        assertTrue(e instanceof IndexOutOfBoundsException);
        e = null;
        try {
            emptyList.get(1);
        }
        catch (Exception exception) {
            e = exception;
        }
        assertTrue(e instanceof IndexOutOfBoundsException);
    }


    // ----------------------------------------------------------
    /**
     * test toString method.
     */
    public void testToString() {
        assertEquals("{Influencer1, Influencer2}", list.toString());
    }


    // ----------------------------------------------------------
    /**
     * test contains method.
     */
    public void testContains() {
        assertFalse(emptyList.contains("Influencer1"));
        emptyList.add("Influencer1");
        assertTrue(emptyList.contains("Influencer1"));
        assertFalse(emptyList.contains("Influencer2"));
        emptyList.add("Influencer2");
        assertTrue(emptyList.contains("Influencer2"));
    }


    // ----------------------------------------------------------
    /**
     * test last index of method.
     */
    public void testLastIndexOf() {
        assertEquals(-1, emptyList.lastIndexOf("A"));
        emptyList.add("A");
        assertEquals(0, emptyList.lastIndexOf("A"));
        emptyList.add("A");
        assertEquals(1, emptyList.lastIndexOf("A"));
        emptyList.add("B");
        assertEquals(1, emptyList.lastIndexOf("A"));
        assertEquals(2, emptyList.lastIndexOf("B"));
        emptyList.add("A");
        assertEquals(3, emptyList.lastIndexOf("A"));
    }


    // ----------------------------------------------------------
    /**
     * test remove method from an index.
     */
    public void testRemoveIndex() {
        @SuppressWarnings("hiding")
        DLList<String> list5 = new DLList<String>();
        list5.add("A");
        list5.add("B");
        assertTrue(list5.remove(1));
        assertEquals("{A}", list5.toString());
        assertEquals(1, list5.size());
        list5.add("B");
        assertTrue(list5.remove(0));
        assertEquals(1, list5.size());
    }


    // ----------------------------------------------------------
    /**
     * test remove method given entry.
     */
    public void testRemoveObj() {
        @SuppressWarnings("hiding")
        DLList<String> list6 = new DLList<String>();
        assertFalse(list6.remove(null));
        list6.add("A");
        list6.add("B");
        assertEquals(2, list6.size());
        assertTrue(list6.remove("A"));
        assertEquals("B", list6.get(0));
        assertEquals(1, list6.size());
        list6.add("C");
        assertTrue(list6.remove("C"));
        assertEquals("B", list6.get(0));
    }


    /**
     * Tests the private iterator class
     */
    public void testIterator() {
        assertFalse(emptyList.iterator().hasNext());
        Exception exception = null;
        try {
            emptyList.iterator().next();
        }
        catch (NoSuchElementException e) {
            exception = e;
        }
        assertTrue(exception instanceof NoSuchElementException);

        assertTrue(list.iterator().hasNext());
        assertEquals("Influencer1", list.iterator().next());
    }


    // ----------------------------------------------------------
    /**
     * test toarray method.
     */
    public void testToArray() {
        Object[] resultArray = list.toArray();
        assertEquals("Influencer1", resultArray[0]);
        assertEquals("Influencer2", resultArray[1]);
    }

}
