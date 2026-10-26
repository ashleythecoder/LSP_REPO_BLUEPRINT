package org.howard.edu.lsp.midterm.crccards;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Comparator;
import java.util.Objects;

/**
 * Maintains books by ISBN and coordinates lending with a three-book limit.
 * Books belonging to this library are borrowed and returned through it.
 * @author Ashley Petgrave
 */
public class Library {
    private static final int BORROWING_LIMIT = 3;
    private final Map<String, Book> books = new HashMap<>();

    /** Creates an empty library. */
    public Library() { }

    /**
     * Adds a book while preserving the original entry for any duplicate ISBN.
     * @param book the book to add
     * @throws IllegalArgumentException if the ISBN already exists
     * @throws NullPointerException if book is null
     */
    public void addBook(Book book) {
        Objects.requireNonNull(book, "Book must not be null.");
        if (books.containsKey(book.getIsbn())) {
            throw new IllegalArgumentException("Duplicate ISBN.");
        }
        books.put(book.getIsbn(), book);
    }

    /**
     * Finds the stored book instance.
     * @param isbn the identifier to find
     * @return the book, or null if absent
     */
    public Book findBook(String isbn) { return books.get(isbn); }

    /**
     * Locates a book and delegates borrowing after checking the library limit.
     * @param isbn the identifier to borrow
     * @throws IllegalArgumentException if the ISBN is absent
     * @throws IllegalStateException if already borrowed or the limit is reached
     */
    public void borrowBook(String isbn) {
        Book book = requireBook(isbn);
        if (getBorrowedBookCount() >= BORROWING_LIMIT) {
            throw new IllegalStateException("Three books are already borrowed.");
        }
        book.borrowBook();
    }

    /**
     * Locates a book and delegates returning to it.
     * @param isbn the identifier to return
     * @throws IllegalArgumentException if the ISBN is absent
     * @throws IllegalStateException if the book is already available
     */
    public void returnBook(String isbn) { requireBook(isbn).returnBook(); }

    private Book requireBook(String isbn) {
        Book book = findBook(isbn);
        if (book == null) {
            throw new IllegalArgumentException("ISBN not found.");
        }
        return book;
    }

    /**
     * Collects available books without exposing the internal collection.
     * @return a new list of available book instances, possibly empty
     */
    public List<Book> getAvailableBooks() {
        List<Book> result = new ArrayList<>();
        for (Book book : books.values()) {
            if (book.isAvailable()) { result.add(book); }
        }
        return result;
    }

    /**
     * Finds exact, case-sensitive author matches, including borrowed books.
     * @param author the author to match
     * @return a new list of matching book instances, possibly empty
     */
    public List<Book> getBooksByAuthor(String author) {
        List<Book> result = new ArrayList<>();
        for (Book book : books.values()) {
            if (Objects.equals(book.getAuthor(), author)) { result.add(book); }
        }
        return result;
    }

    /**
     * Retrieves every book in ascending lexicographical ISBN order.
     * @return a new sorted list, empty when the library is empty
     */
    public List<Book> getBooksSortedByIsbn() {
        List<Book> result = new ArrayList<>(books.values());
        result.sort(Comparator.comparing(Book::getIsbn));
        return result;
    }

    /**
     * Counts current borrowed books from their availability states.
     * @return the number of borrowed books
     */
    public int getBorrowedBookCount() {
        int count = 0;
        for (Book book : books.values()) {
            if (!book.isAvailable()) { count++; }
        }
        return count;
    }
}
