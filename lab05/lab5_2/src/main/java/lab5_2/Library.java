package lab5_2;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> store = new ArrayList<Book>();

    public Library(){}

    public List<Book> findBooksByAuthor(String author) {
        List<Book> books = new ArrayList<Book>();
        for (Book book : store) {
            if (book.getAuthor().equals(author)) {
                books.add(book);
            }
        }
        return books;
    }

    public void addBook(Book book) {
        store.add(book);
    }

    public List<Book> findBooks(LocalDateTime from, LocalDateTime to) {
        List<Book> books = new ArrayList<Book>();
        for (Book book : store) {
            if (book.getPublished().isAfter(from) && book.getPublished().isBefore(to)) {
                books.add(book);
            }
        }
        return books;
    }
}