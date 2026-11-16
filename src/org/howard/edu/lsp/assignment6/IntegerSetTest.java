package org.howard.edu.lsp.assignment6;
 
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
 
/**
 * Assignment 6: JUnit 5 tests for IntegerSet.
 *
 * Tests normal, boundary, exceptional, and independent-result behavior.
 * Each test creates its own data and runs independently.
 */
public class IntegerSetTest {
 
    // ===== clear() =====
 
    /**
     * Create a populated set; call clear(); assert that it is empty and its length is zero.
     * Assignment 5 equivalent: 2 point(s).
     */
    @Test
    public void testClearClearsPopulatedSet() {
        IntegerSet set = setOf(-3, 0, 8);
        set.clear();
        assertTrue(set.isEmpty());
        assertEquals(0, set.length());
        assertFalse(set.contains(-3));
        assertFalse(set.contains(0));
        assertFalse(set.contains(8));
        assertEquals("[]", set.toString());
    }
 
    /**
     * Clear an already empty set; verify it remains empty.
     * Assignment 5 equivalent: 2 point(s).
     */
    @Test
    public void testClearClearsEmptySet() {
        IntegerSet set = new IntegerSet();
        set.clear();
        assertTrue(set.isEmpty());
        assertEquals(0, set.length());
        assertEquals("[]", set.toString());
    }
 
    // ===== length() =====
 
    /**
     * Add repeated and unique integers; assert the number of distinct elements.
     * Assignment 5 equivalent: 2 point(s).
     */
    @Test
    public void testLengthCountsUniqueElements() {
        IntegerSet set = setOf(3, 3, -2, 0, -2, 3);
        assertEquals(3, set.length());
    }
 
    /**
     * Verify a new empty set has length zero.
     * Assignment 5 equivalent: 2 point(s).
     */
    @Test
    public void testLengthEmptySetLength() {
        IntegerSet set = new IntegerSet();
        assertEquals(0, set.length());
    }
 
    // ===== equals() =====
 
    /**
     * Construct sets with identical elements in different insertion orders; assert equality.
     * Assignment 5 equivalent: 2 point(s).
     */
    @Test
    public void testEqualsIgnoresInsertionOrder() {
        IntegerSet a = setOf(-3, 0, 8);
        IntegerSet b = setOf(8, -3, 0);
        assertTrue(a.equals(b));
        assertTrue(b.equals(a));
    }
 
    /**
     * Construct sets of equal length with different elements; assert inequality.
     * Assignment 5 equivalent: 2 point(s).
     */
    @Test
    public void testEqualsDetectsDifferentContents() {
        IntegerSet a = setOf(1, 2, 3);
        IntegerSet b = setOf(1, 2, 4);
        assertFalse(a.equals(b));
        assertFalse(b.equals(a));
    }
 
    /**
     * Verify two separately constructed empty sets compare equal.
     * Assignment 5 equivalent: 2 point(s).
     */
    @Test
    public void testEqualsEmptySetsEqual() {
        IntegerSet a = new IntegerSet();
        IntegerSet b = new IntegerSet();
        assertNotSame(a, b);
        assertTrue(a.equals(b));
        assertTrue(b.equals(a));
    }
 
    /**
     * Verify a set equals itself and differs from a set of another size.
     * Assignment 5 equivalent: 2 point(s).
     */
    @Test
    public void testEqualsSameObjectAndDifferentSizes() {
        IntegerSet a = setOf(-1, 0);
        IntegerSet b = setOf(-1);
        assertTrue(a.equals(a));
        assertFalse(a.equals(b));
        assertFalse(b.equals(a));
    }
 
    // ===== contains() =====
 
    /**
     * Verify contains() is true for a present element and false for an absent one.
     * Assignment 5 equivalent: 2 point(s).
     */
    @Test
    public void testContainsPresentAndAbsent() {
        IntegerSet set = setOf(-3, 0, 9);
        assertTrue(set.contains(-3));
        assertTrue(set.contains(0));
        assertTrue(set.contains(9));
        assertFalse(set.contains(8));
    }
 
    /**
     * Verify an empty set does not contain an arbitrary integer.
     * Assignment 5 equivalent: 2 point(s).
     */
    @Test
    public void testContainsEmptySetContainsNothing() {
        IntegerSet set = new IntegerSet();
        assertFalse(set.contains(0));
        assertFalse(set.contains(-1));
        assertFalse(set.contains(Integer.MAX_VALUE));
    }
 
    // ===== largest() =====
 
    /**
     * Verify largest() returns the maximum across negative, zero, and positive values.
     * Assignment 5 equivalent: 2 point(s).
     */
    @Test
    public void testLargestNegativeAndPositiveValues() {
        IntegerSet set = setOf(-10, 0, 8, -3, 2);
        assertEquals(8, set.largest());
        assertEquals(-2, setOf(-9, -2, -5).largest());
        assertEquals(Integer.MAX_VALUE, setOf(Integer.MIN_VALUE, Integer.MAX_VALUE).largest());
    }
 
    /**
     * Use assertThrows() to verify IllegalStateException for an empty set.
     * Assignment 5 equivalent: 2 point(s).
     */
    @Test
    public void testLargestEmptySetThrowsException() {
        IntegerSet set = new IntegerSet();
        assertThrows(IllegalStateException.class, () -> set.largest());
    }
 
    // ===== smallest() =====
 
    /**
     * Verify smallest() returns the minimum across negative, zero, and positive values.
     * Assignment 5 equivalent: 2 point(s).
     */
    @Test
    public void testSmallestNegativeAndPositiveValues() {
        IntegerSet set = setOf(8, -10, 0, -3, 2);
        assertEquals(-10, set.smallest());
        assertEquals(2, setOf(9, 2, 5).smallest());
        assertEquals(Integer.MIN_VALUE, setOf(Integer.MAX_VALUE, Integer.MIN_VALUE).smallest());
    }
 
    /**
     * Use assertThrows() to verify IllegalStateException for an empty set.
     * Assignment 5 equivalent: 2 point(s).
     */
    @Test
    public void testSmallestEmptySetThrowsException() {
        IntegerSet set = new IntegerSet();
        assertThrows(IllegalStateException.class, () -> set.smallest());
    }
 
    // ===== add() =====
 
    /**
     * Add the same integer repeatedly; verify only one copy exists.
     * Assignment 5 equivalent: 2 point(s).
     */
    @Test
    public void testAddIgnoresDuplicates() {
        IntegerSet set = new IntegerSet();
        set.add(7);
        set.add(7);
        set.add(7);
        assertEquals(1, set.length());
        assertTrue(set.contains(7));
        assertEquals("[7]", set.toString());
    }
 
    /**
     * Add Integer.MIN_VALUE, Integer.MAX_VALUE, and zero; verify all are represented.
     * Assignment 5 equivalent: 2 point(s).
     */
    @Test
    public void testAddHandlesIntegerBoundaries() {
        IntegerSet set = new IntegerSet();
        set.add(Integer.MAX_VALUE);
        set.add(Integer.MIN_VALUE);
        set.add(0);
        assertContents(set, Integer.MIN_VALUE, 0, Integer.MAX_VALUE);
    }
 
    // ===== remove() =====
 
    /**
     * Remove a present element; verify it is absent and others remain.
     * Assignment 5 equivalent: 2 point(s).
     */
    @Test
    public void testRemoveRemovesExistingElement() {
        IntegerSet set = setOf(1, 2, 3);
        set.remove(2);
        assertFalse(set.contains(2));
        assertContents(set, 1, 3);
        set.remove(1);
        set.remove(3);
        assertTrue(set.isEmpty());
        assertEquals(0, set.length());
    }
 
    /**
     * Remove an absent element; verify the set is unchanged.
     * Assignment 5 equivalent: 2 point(s).
     */
    @Test
    public void testRemoveIgnoresAbsentElement() {
        IntegerSet set = setOf(1, 2, 3);
        set.remove(99);
        assertContents(set, 1, 2, 3);
        IntegerSet empty = new IntegerSet();
        empty.remove(99);
        assertContents(empty);
    }
 
    // ===== union() =====
 
    /**
     * Verify union() contains all distinct elements from two overlapping sets.
     * Assignment 5 equivalent: 2 point(s).
     */
    @Test
    public void testUnionOverlappingSets() {
        IntegerSet a = setOf(-3, 0, 8, 15);
        IntegerSet b = setOf(-3, 8, 11, 22);
        IntegerSet result = a.union(b);
        assertContents(result, -3, 0, 8, 11, 15, 22);
        assertNotSame(a, result);
        assertNotSame(b, result);
        assertContents(a, -3, 0, 8, 15);
        assertContents(b, -3, 8, 11, 22);
    }
 
    /**
     * Verify union with an empty set and with the same object.
     * Assignment 5 equivalent: 2 point(s).
     */
    @Test
    public void testUnionEmptyAndSameOperand() {
        IntegerSet a = setOf(1, 2);
        IntegerSet empty = new IntegerSet();
        IntegerSet rightEmpty = a.union(empty);
        IntegerSet leftEmpty = empty.union(a);
        IntegerSet self = a.union(a);
        assertContents(rightEmpty, 1, 2);
        assertContents(leftEmpty, 1, 2);
        assertContents(self, 1, 2);
        assertNotSame(a, rightEmpty);
        assertNotSame(a, leftEmpty);
        assertNotSame(a, self);
        rightEmpty.clear();
        leftEmpty.add(9);
        self.remove(1);
        assertContents(a, 1, 2);
        assertContents(empty);
        assertContents(empty.union(empty));
    }
 
    /**
     * Verify both inputs remain unchanged; modify the result and verify the inputs are still unchanged.
     * Assignment 5 equivalent: 3 point(s).
     */
    @Test
    public void testUnionDoesNotMutateInputs() {
        IntegerSet a = setOf(1, 2);
        IntegerSet b = setOf(2, 3);
        IntegerSet result = a.union(b);
        assertContents(result, 1, 2, 3);
        assertNotSame(a, result);
        assertNotSame(b, result);
        assertContents(a, 1, 2);
        assertContents(b, 2, 3);
        result.remove(1);
        result.add(99);
        assertContents(a, 1, 2);
        assertContents(b, 2, 3);
        result.clear();
        assertContents(result);
        assertContents(a, 1, 2);
        assertContents(b, 2, 3);
        a.add(40);
        b.add(50);
        assertContents(result);
    }
 
    // ===== intersect() =====
 
    /**
     * Verify intersection contains exactly the shared elements.
     * Assignment 5 equivalent: 2 point(s).
     */
    @Test
    public void testIntersectOverlappingSets() {
        IntegerSet a = setOf(-3, 0, 8, 15);
        IntegerSet b = setOf(-3, 8, 11, 22);
        IntegerSet result = a.intersect(b);
        assertContents(result, -3, 8);
        assertNotSame(a, result);
        assertNotSame(b, result);
        assertContents(a, -3, 0, 8, 15);
        assertContents(b, -3, 8, 11, 22);
    }
 
    /**
     * Verify disjoint intersection is empty and self-intersection is unchanged.
     * Assignment 5 equivalent: 2 point(s).
     */
    @Test
    public void testIntersectDisjointAndSameOperand() {
        IntegerSet a = setOf(1, 2);
        IntegerSet b = setOf(3, 4);
        IntegerSet disjoint = a.intersect(b);
        IntegerSet self = a.intersect(a);
        assertContents(disjoint);
        assertContents(self, 1, 2);
        assertNotSame(a, self);
        assertNotSame(a, disjoint);
        assertNotSame(b, disjoint);
        disjoint.add(9);
        self.clear();
        assertContents(a, 1, 2);
        assertContents(b, 3, 4);
        assertContents(a.intersect(new IntegerSet()));
        assertContents(new IntegerSet().intersect(a));
    }
 
    /**
     * Modify the intersection result and verify both input sets remain unchanged.
     * Assignment 5 equivalent: 3 point(s).
     */
    @Test
    public void testIntersectIndependentResult() {
        IntegerSet a = setOf(1, 2);
        IntegerSet b = setOf(2, 3);
        IntegerSet result = a.intersect(b);
        assertContents(result, 2);
        assertNotSame(a, result);
        assertNotSame(b, result);
        assertContents(a, 1, 2);
        assertContents(b, 2, 3);
        result.remove(2);
        result.add(99);
        assertContents(a, 1, 2);
        assertContents(b, 2, 3);
        result.clear();
        assertContents(result);
        assertContents(a, 1, 2);
        assertContents(b, 2, 3);
        a.add(40);
        b.add(50);
        assertContents(result);
    }
 
    // ===== diff() =====
 
    /**
     * Verify A.diff(B) returns elements in A but not B, and distinguish direction.
     * Assignment 5 equivalent: 2 point(s).
     */
    @Test
    public void testDiffDirectionMatters() {
        IntegerSet a = setOf(-3, 0, 8, 15);
        IntegerSet b = setOf(-3, 8, 11, 22);
        IntegerSet forward = a.diff(b);
        IntegerSet backward = b.diff(a);
        assertContents(forward, 0, 15);
        assertContents(backward, 11, 22);
        assertNotSame(a, forward);
        assertNotSame(b, forward);
        assertContents(a, -3, 0, 8, 15);
        assertContents(b, -3, 8, 11, 22);
    }
 
    /**
     * Verify difference with an empty set and difference with itself.
     * Assignment 5 equivalent: 2 point(s).
     */
    @Test
    public void testDiffEmptyAndSameOperand() {
        IntegerSet a = setOf(1, 2);
        IntegerSet empty = new IntegerSet();
        IntegerSet rightEmpty = a.diff(empty);
        IntegerSet leftEmpty = empty.diff(a);
        IntegerSet self = a.diff(a);
        assertContents(rightEmpty, 1, 2);
        assertContents(leftEmpty);
        assertContents(self);
        assertNotSame(a, rightEmpty);
        assertNotSame(empty, leftEmpty);
        assertNotSame(a, self);
        rightEmpty.clear();
        leftEmpty.add(8);
        self.add(9);
        assertContents(a, 1, 2);
        assertContents(empty);
    }
 
    /**
     * Modify the difference result and verify neither input changes.
     * Assignment 5 equivalent: 3 point(s).
     */
    @Test
    public void testDiffIndependentResult() {
        IntegerSet a = setOf(1, 2);
        IntegerSet b = setOf(2, 3);
        IntegerSet result = a.diff(b);
        assertContents(result, 1);
        assertNotSame(a, result);
        assertNotSame(b, result);
        assertContents(a, 1, 2);
        assertContents(b, 2, 3);
        result.remove(1);
        result.add(99);
        assertContents(a, 1, 2);
        assertContents(b, 2, 3);
        result.clear();
        assertContents(result);
        assertContents(a, 1, 2);
        assertContents(b, 2, 3);
        a.add(40);
        b.add(50);
        assertContents(result);
    }
 
    // ===== complement() =====
 
    /**
     * Verify A.complement(B) returns elements in B but not A.
     * Assignment 5 equivalent: 2 point(s).
     */
    @Test
    public void testComplementReverseDifference() {
        IntegerSet a = setOf(-3, 0, 8, 15);
        IntegerSet b = setOf(-3, 8, 11, 22);
        IntegerSet forward = a.complement(b);
        IntegerSet backward = b.complement(a);
        assertContents(forward, 11, 22);
        assertContents(backward, 0, 15);
        assertNotSame(a, forward);
        assertNotSame(b, forward);
        assertContents(a, -3, 0, 8, 15);
        assertContents(b, -3, 8, 11, 22);
    }
 
    /**
     * Verify complement with an empty receiver and with an empty argument.
     * Assignment 5 equivalent: 1 point(s).
     */
    @Test
    public void testComplementEmptyOperands() {
        IntegerSet a = setOf(1, 2);
        IntegerSet empty = new IntegerSet();
        IntegerSet leftEmpty = empty.complement(a);
        IntegerSet rightEmpty = a.complement(empty);
        assertContents(leftEmpty, 1, 2);
        assertContents(rightEmpty);
        assertNotSame(a, leftEmpty);
        assertNotSame(empty, rightEmpty);
        assertContents(a.complement(a));
        assertContents(empty.complement(empty));
        leftEmpty.clear();
        rightEmpty.add(9);
        assertContents(a, 1, 2);
        assertContents(empty);
    }
 
    /**
     * Modify the complement result and verify neither input changes.
     * Assignment 5 equivalent: 2 point(s).
     */
    @Test
    public void testComplementIndependentResult() {
        IntegerSet a = setOf(1, 2);
        IntegerSet b = setOf(2, 3);
        IntegerSet result = a.complement(b);
        assertContents(result, 3);
        assertNotSame(a, result);
        assertNotSame(b, result);
        assertContents(a, 1, 2);
        assertContents(b, 2, 3);
        result.remove(3);
        result.add(99);
        assertContents(a, 1, 2);
        assertContents(b, 2, 3);
        result.clear();
        assertContents(result);
        assertContents(a, 1, 2);
        assertContents(b, 2, 3);
        a.add(40);
        b.add(50);
        assertContents(result);
    }
 
    // ===== isSubsetOf() =====
 
    /**
     * Verify a proper subset is recognized.
     * Assignment 5 equivalent: 2 point(s).
     */
    @Test
    public void testIsSubsetOfProperSubset() {
        IntegerSet a = setOf(1, 3);
        IntegerSet b = setOf(1, 2, 3);
        assertTrue(a.isSubsetOf(b));
        assertFalse(b.isSubsetOf(a));
    }
 
    /**
     * Verify a set with an element missing from the other set is not a subset.
     * Assignment 5 equivalent: 2 point(s).
     */
    @Test
    public void testIsSubsetOfNotSubset() {
        IntegerSet a = setOf(1, 4);
        IntegerSet b = setOf(1, 2, 3);
        assertFalse(a.isSubsetOf(b));
        assertFalse(b.isSubsetOf(a));
    }
 
    /**
     * Verify equal sets are subsets of each other.
     * Assignment 5 equivalent: 1 point(s).
     */
    @Test
    public void testIsSubsetOfEqualSets() {
        IntegerSet a = setOf(1, 2);
        IntegerSet b = setOf(2, 1);
        assertTrue(a.isSubsetOf(b));
        assertTrue(b.isSubsetOf(a));
        assertTrue(a.isSubsetOf(a));
    }
 
    /**
     * Verify the empty set is a subset of a nonempty set, but not vice versa.
     * Assignment 5 equivalent: 2 point(s).
     */
    @Test
    public void testIsSubsetOfEmptyCases() {
        IntegerSet empty = new IntegerSet();
        IntegerSet a = setOf(1);
        assertTrue(empty.isSubsetOf(a));
        assertFalse(a.isSubsetOf(empty));
        assertTrue(empty.isSubsetOf(new IntegerSet()));
    }
 
    // ===== isDisjoint() =====
 
    /**
     * Verify disjoint sets return true.
     * Assignment 5 equivalent: 2 point(s).
     */
    @Test
    public void testIsDisjointNoOverlap() {
        IntegerSet a = setOf(-1, 0);
        IntegerSet b = setOf(1, 2);
        assertTrue(a.isDisjoint(b));
        assertTrue(b.isDisjoint(a));
    }
 
    /**
     * Verify overlapping sets return false.
     * Assignment 5 equivalent: 2 point(s).
     */
    @Test
    public void testIsDisjointOverlap() {
        IntegerSet a = setOf(1, 2);
        IntegerSet b = setOf(2, 3);
        assertFalse(a.isDisjoint(b));
        assertFalse(b.isDisjoint(a));
    }
 
    /**
     * Verify disjointness with an empty set and with the same nonempty object.
     * Assignment 5 equivalent: 2 point(s).
     */
    @Test
    public void testIsDisjointEmptyAndSameOperand() {
        IntegerSet a = setOf(1, 2);
        IntegerSet empty = new IntegerSet();
        assertTrue(a.isDisjoint(empty));
        assertTrue(empty.isDisjoint(a));
        assertTrue(empty.isDisjoint(empty));
        assertFalse(a.isDisjoint(a));
    }
 
    // ===== symmetricDiff() =====
 
    /**
     * Verify symmetric difference contains elements appearing in exactly one set.
     * Assignment 5 equivalent: 3 point(s).
     */
    @Test
    public void testSymmetricDiffPartiallyOverlappingSets() {
        IntegerSet a = setOf(-3, 0, 8, 15);
        IntegerSet b = setOf(-3, 8, 11, 22);
        IntegerSet result = a.symmetricDiff(b);
        assertContents(result, 0, 11, 15, 22);
        assertNotSame(a, result);
        assertNotSame(b, result);
        assertContents(a, -3, 0, 8, 15);
        assertContents(b, -3, 8, 11, 22);
    }
 
    /**
     * Verify symmetric difference of identical sets is empty.
     * Assignment 5 equivalent: 2 point(s).
     */
    @Test
    public void testSymmetricDiffIdenticalSets() {
        IntegerSet a = setOf(1, 2);
        IntegerSet b = setOf(2, 1);
        IntegerSet result = a.symmetricDiff(b);
        IntegerSet self = a.symmetricDiff(a);
        assertContents(result);
        assertContents(self);
        assertNotSame(a, result);
        assertNotSame(b, result);
        assertNotSame(a, self);
        result.add(9);
        self.add(8);
        assertContents(a, 1, 2);
        assertContents(b, 1, 2);
    }
 
    /**
     * Verify behavior with an empty set and with disjoint sets.
     * Assignment 5 equivalent: 2 point(s).
     */
    @Test
    public void testSymmetricDiffEmptyAndDisjointSets() {
        IntegerSet a = setOf(1, 2);
        IntegerSet b = setOf(3, 4);
        IntegerSet empty = new IntegerSet();
        IntegerSet rightEmpty = a.symmetricDiff(empty);
        IntegerSet leftEmpty = empty.symmetricDiff(a);
        IntegerSet disjoint = a.symmetricDiff(b);
        assertContents(rightEmpty, 1, 2);
        assertContents(leftEmpty, 1, 2);
        assertContents(disjoint, 1, 2, 3, 4);
        assertNotSame(a, rightEmpty);
        assertNotSame(a, leftEmpty);
        assertNotSame(a, disjoint);
        assertNotSame(b, disjoint);
        rightEmpty.clear();
        leftEmpty.add(9);
        disjoint.remove(3);
        assertContents(a, 1, 2);
        assertContents(b, 3, 4);
        assertContents(empty);
        assertContents(empty.symmetricDiff(empty));
    }
 
    /**
     * Modify the result and verify both input sets remain unchanged.
     * Assignment 5 equivalent: 2 point(s).
     */
    @Test
    public void testSymmetricDiffIndependentResult() {
        IntegerSet a = setOf(1, 2);
        IntegerSet b = setOf(2, 3);
        IntegerSet result = a.symmetricDiff(b);
        assertContents(result, 1, 3);
        assertNotSame(a, result);
        assertNotSame(b, result);
        assertContents(a, 1, 2);
        assertContents(b, 2, 3);
        result.remove(1);
        result.add(99);
        assertContents(a, 1, 2);
        assertContents(b, 2, 3);
        result.clear();
        assertContents(result);
        assertContents(a, 1, 2);
        assertContents(b, 2, 3);
        a.add(40);
        b.add(50);
        assertContents(result);
    }
 
    // ===== isEmpty() =====
 
    /**
     * Verify a newly constructed set is empty.
     * Assignment 5 equivalent: 1 point(s).
     */
    @Test
    public void testIsEmptyNewSet() {
        IntegerSet set = new IntegerSet();
        assertTrue(set.isEmpty());
    }
 
    /**
     * Verify a set with an element is not empty.
     * Assignment 5 equivalent: 1 point(s).
     */
    @Test
    public void testIsEmptyPopulatedSet() {
        IntegerSet set = setOf(0);
        assertFalse(set.isEmpty());
    }
 
    /**
     * Clear a populated set and verify it is empty.
     * Assignment 5 equivalent: 1 point(s).
     */
    @Test
    public void testIsEmptyAfterClear() {
        IntegerSet set = setOf(-1, 0, 1);
        assertFalse(set.isEmpty());
        set.clear();
        assertTrue(set.isEmpty());
        assertEquals(0, set.length());
    }
 
    // ===== toString() =====
 
    /**
     * Verify elements appear in ascending numeric order regardless of insertion order.
     * Assignment 5 equivalent: 2 point(s).
     */
    @Test
    public void testToStringAscendingOrder() {
        IntegerSet set = setOf(15, -3, 8, 0, -20, 2);
        assertEquals("[-20, -3, 0, 2, 8, 15]", set.toString());
    }
 
    /**
     * Verify an empty set formats as [].
     * Assignment 5 equivalent: 1 point(s).
     */
    @Test
    public void testToStringEmptyFormatting() {
        IntegerSet set = new IntegerSet();
        assertEquals("[]", set.toString());
    }
 
    /**
     * Verify comma-space formatting, brackets, and duplicate suppression.
     * Assignment 5 equivalent: 2 point(s).
     */
    @Test
    public void testToStringExactSpacingAndDuplicates() {
        IntegerSet set = setOf(2, 1, 2, 3, 1);
        assertEquals("[1, 2, 3]", set.toString());
        assertEquals("[-7]", setOf(-7, -7).toString());
    }
 

    /** Creates fresh test data without shared mutable state. */
    private static IntegerSet setOf(int... values) {
        IntegerSet result = new IntegerSet();
        for (int value : values) {
            result.add(value);
        }
        return result;
    }

    /** Checks exact membership and size against literal expected values. */
    private static void assertContents(IntegerSet actual, int... expected) {
        assertNotNull(actual);
        assertEquals(expected.length, actual.length(), "Number of elements");
        for (int value : expected) {
            assertTrue(actual.contains(value), "Missing element: " + value);
        }
        int[] sorted = expected.clone();
        java.util.Arrays.sort(sorted);
        assertEquals(java.util.Arrays.toString(sorted), actual.toString());
    }
}
