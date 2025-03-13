package lab5_2;

import java.time.LocalDateTime;

public class Book {
    private String title, author;
    private LocalDateTime published;

    public Book(String title, String author, LocalDateTime published) {
        this.title = title;
        this.author = author;
        this.published = published;
    }

    public String getAuthor() {
        return author;
    }

    public LocalDateTime getPublished() {
        return published;
    }

    public String getTitle() {
        return title;
    }

    public String toString() {
        return title + " by " + author + " published on " + published;
    }
}
