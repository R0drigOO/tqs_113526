/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tqs.sets;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import tqs.sets.BoundedSetOfNaturals;

/**
 * @author ico0
 */
class BoundedSetOfNaturalsTest {
    private BoundedSetOfNaturals setA;
    private BoundedSetOfNaturals setB;
    private BoundedSetOfNaturals setC;


    @BeforeEach
    public void setUp() {
        setA = new BoundedSetOfNaturals(1);
        setB = BoundedSetOfNaturals.fromArray(new int[]{10, 20, 30, 40, 50, 60});
        setC = BoundedSetOfNaturals.fromArray(new int[]{50, 60});
    }

    @AfterEach
    public void tearDown() {
        setA = setB = setC = null;
    }

    @Disabled("TODO revise test logic")
    @Test
    public void testAddElement() {

        setA.add(99);
        assertTrue(setA.contains(99), "add: added element not found in set.");
        assertEquals(1, setA.size());

        setB.add(11);
        assertTrue(setB.contains(11), "add: added element not found in set.");
        assertEquals(7, setB.size(), "add: elements count not as expected.");
    }

    @Disabled("TODO revise to test the construction from invalid arrays")
    @Test
    public void testAddFromBadArray() {
        int[] elems = new int[]{10, -20, -30};

        // must fail with exception
        assertThrows(IllegalArgumentException.class, () -> setA.add(elems));
    }

    @Test
    @DisplayName("Adding duplicate elements should throw an exception")
    public void testAddDuplicate() {
        BoundedSetOfNaturals set = new BoundedSetOfNaturals(5);
        set.add(10);
        assertThrows(IllegalArgumentException.class, () -> set.add(10), "Should not allow duplicate elements");
    }

    @Test
    @DisplayName("Adding elements beyond max size should fail")
    public void testAddBeyondMaxSize() {
        BoundedSetOfNaturals set = new BoundedSetOfNaturals(2);
        set.add(1);
        set.add(2);
        assertThrows(IllegalArgumentException.class, () -> set.add(3), "Should not allow more elements than maxSize");
    }

    @Test
    @DisplayName("Adding zero or negative values should fail")
    public void testAddInvalidNumbers() {
        BoundedSetOfNaturals set = new BoundedSetOfNaturals(3);
        assertThrows(IllegalArgumentException.class, () -> set.add(0), "Zero should not be allowed");
        assertThrows(IllegalArgumentException.class, () -> set.add(-5), "Negative numbers should not be allowed");
    }

    @Test
    @DisplayName("Newly created set should be empty")
    public void testEmptySetSize() {
        BoundedSetOfNaturals set = new BoundedSetOfNaturals(5);
        assertEquals(0, set.size(), "New set should have size 0");
    }

    @Test
    @DisplayName("Intersection test between two sets")
    public void testIntersection() {
        BoundedSetOfNaturals setA = BoundedSetOfNaturals.fromArray(new int[]{1, 2, 3, 4});
        BoundedSetOfNaturals setB = BoundedSetOfNaturals.fromArray(new int[]{3, 4, 5, 6});

        assertTrue(setA.intersects(setB), "Sets should intersect");

        BoundedSetOfNaturals setC = BoundedSetOfNaturals.fromArray(new int[]{7, 8, 9});
        assertFalse(setA.intersects(setC), "Sets should NOT intersect");
    }


}
