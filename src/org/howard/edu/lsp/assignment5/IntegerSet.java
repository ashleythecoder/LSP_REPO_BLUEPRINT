package org.howard.edu.lsp.assignment5;

import java.util.Collections;
import java.util.Objects;
import java.util.TreeSet;

/**
 * A mutable mathematical set of integers with no duplicates.
 * Set-producing operations return independent sets and leave both operands
 * unchanged. Values are displayed in ascending numerical order.
 */
public final class IntegerSet {
    private final TreeSet<Integer> elements;

    /** Creates an empty set. */
    public IntegerSet() {
        elements = new TreeSet<>();
    }

    private IntegerSet(IntegerSet source) {
        elements = new TreeSet<>(source.elements);
    }

    /** Removes every element from this set. */
    public void clear() {
        elements.clear();
    }

    /**
     * Counts the distinct elements.
     * @return the number of distinct elements in this set
     */
    public int length() {
        return elements.size();
    }

    /**
     * Compares sets by their elements, regardless of insertion order.
     * @param b the set to compare, which may be null
     * @return true if both sets contain exactly the same elements
     */
    public boolean equals(IntegerSet b) {
        return b != null && elements.equals(b.elements);
    }

    /**
     * Compares this set with another object by its elements.
     * @param other the object to compare
     * @return true if other is an IntegerSet with the same elements
     */
    @Override
    public boolean equals(Object other) {
        return other instanceof IntegerSet && equals((IntegerSet) other);
    }

    /** @return an element-based hash code consistent with equality */
    @Override
    public int hashCode() {
        return elements.hashCode();
    }

    /**
     * Tests membership in this set.
     * @param value the integer to find
     * @return true if value belongs to this set
     */
    public boolean contains(int value) {
        return elements.contains(value);
    }

    /**
     * Returns the greatest element.
     * @return the largest integer in this set
     * @throws IllegalStateException if the set is empty
     */
    public int largest() {
        if (isEmpty()) {
            throw new IllegalStateException("An empty set has no largest element.");
        }
        return elements.last();
    }

    /**
     * Returns the least element.
     * @return the smallest integer in this set
     * @throws IllegalStateException if the set is empty
     */
    public int smallest() {
        if (isEmpty()) {
            throw new IllegalStateException("An empty set has no smallest element.");
        }
        return elements.first();
    }

    /**
     * Adds an integer, ignoring duplicates.
     * @param item the integer to add
     */
    public void add(int item) {
        elements.add(item);
    }

    /**
     * Removes an integer if present; otherwise leaves the set unchanged.
     * @param item the integer to remove
     */
    public void remove(int item) {
        elements.remove(item);
    }

    /**
     * Returns the union without modifying either operand.
     * @param b the other set
     * @return a new independent set containing elements in either set
     * @throws NullPointerException if b is null
     */
    public IntegerSet union(IntegerSet b) {
        Objects.requireNonNull(b, "Other set must not be null.");
        IntegerSet result = new IntegerSet(this);
        result.elements.addAll(b.elements);
        return result;
    }

    /**
     * Returns the intersection without modifying either operand.
     * @param b the other set
     * @return a new independent set of elements shared by both sets
     * @throws NullPointerException if b is null
     */
    public IntegerSet intersect(IntegerSet b) {
        Objects.requireNonNull(b, "Other set must not be null.");
        IntegerSet result = new IntegerSet(this);
        result.elements.retainAll(b.elements);
        return result;
    }

    /**
     * Returns this set minus b without modifying either operand.
     * @param b the set whose elements are excluded
     * @return a new independent set containing elements in this set but not b
     * @throws NullPointerException if b is null
     */
    public IntegerSet diff(IntegerSet b) {
        Objects.requireNonNull(b, "Other set must not be null.");
        IntegerSet result = new IntegerSet(this);
        result.elements.removeAll(b.elements);
        return result;
    }

    /**
     * Uses the assignment's definition of complement: b minus this set.
     * Neither operand is modified.
     * @param b the set from which elements are selected
     * @return a new independent set containing elements in b but not this set
     * @throws NullPointerException if b is null
     */
    public IntegerSet complement(IntegerSet b) {
        Objects.requireNonNull(b, "Other set must not be null.");
        return b.diff(this);
    }

    /**
     * Tests whether every element of this set is in b.
     * @param b the possible superset
     * @return true if this is a subset of b, including when this set is empty
     * @throws NullPointerException if b is null
     */
    public boolean isSubsetOf(IntegerSet b) {
        Objects.requireNonNull(b, "Other set must not be null.");
        return b.elements.containsAll(elements);
    }

    /**
     * Tests whether the sets have no common elements.
     * @param b the other set
     * @return true if no elements are shared, including empty-set cases
     * @throws NullPointerException if b is null
     */
    public boolean isDisjoint(IntegerSet b) {
        Objects.requireNonNull(b, "Other set must not be null.");
        return Collections.disjoint(elements, b.elements);
    }

    /**
     * Returns elements that appear in exactly one operand, modifying neither.
     * @param b the other set
     * @return a new independent set representing the symmetric difference
     * @throws NullPointerException if b is null
     */
    public IntegerSet symmetricDiff(IntegerSet b) {
        Objects.requireNonNull(b, "Other set must not be null.");
        IntegerSet result = union(b);
        result.elements.removeAll(intersect(b).elements);
        return result;
    }

    /**
     * Tests whether this set is empty.
     * @return true if this set contains no elements
     */
    public boolean isEmpty() {
        return elements.isEmpty();
    }

    /**
     * Formats integers in ascending order, separated by comma and one space.
     * @return bracketed elements, for example [-3, 0, 8], or [] when empty
     */
    @Override
    public String toString() {
        return elements.toString();
    }
}
