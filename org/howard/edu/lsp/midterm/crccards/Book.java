package org.howard.edu.lsp.midterm.crccards;

/**
 * Stores a book's identifying information and controls its availability.
 * @author Ashley Petgrave
 */
public class Book {
    private final String isbn;
    private final String title;
    private final String author;
    private boolean available = true;

    /**
     * Creates an available book.
     * @param isbn the book's identifier
     * @param title the book's title
     * @param author the book's author
     */
    public Book(String isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
    }

    /**
     * Returns the identifier.
     * @return the ISBN
     */
    public String getIsbn() { return isbn; }

    /**
     * Returns the title.
     * @return the title
     */
    public String getTitle() { return title; }

    /**
     * Returns the author.
     * @return the author's name
     */
    public String getAuthor() { return author; }

    /**
     * Reports availability.
     * @return true if the book is available
     */
    public boolean isAvailable() { return available; }

    /**
     * Marks an available book as borrowed.
     * @throws IllegalStateException if the book is already borrowed
     */
    public void borrowBook() {
        if (!available) {
            throw new IllegalStateException("Book is already borrowed.");
        }
        available = false;
    }

    /**
     * Marks a borrowed book as available.
     * @throws IllegalStateException if the book is already available
     */
    public void returnBook() {
        if (available) {
            throw new IllegalStateException("Book is already available.");
        }
        available = true;
    }

    /**
     * Formats the book details and lending status.
     * @return ISBN, title, author, and bracketed availability
     */
    @Override
    public String toString() {
        return isbn + " " + title + " by " + author
                + (available ? " [AVAILABLE]" : " [BORROWED]");
    }
}
