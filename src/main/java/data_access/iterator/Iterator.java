package data_access.iterator;

import java.util.NoSuchElementException;

/**
 * An interface for iterating over a collection of elements.
 *
 * @param <T> the type of elements returned by this iterator
 *
 * @author Jessica
 */
public interface Iterator<T> {
    /**
     * Returns {@code true} if the iteration has more elements.
     * (In other words, returns {@code true} if {@link #next} would
     * return an element rather than throwing an exception.)
     *
     * @return {@code true} if the iteration has more elements
     */
    boolean hasNext();

    /**
     * Returns the next element in the iteration.
     *
     * @return the next element in the iteration
     * @throws NoSuchElementException if the iteration has no more elements
     */
    T next();
}
