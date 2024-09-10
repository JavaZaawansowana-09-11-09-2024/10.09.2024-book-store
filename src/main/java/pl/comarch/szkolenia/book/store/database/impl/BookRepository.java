package pl.comarch.szkolenia.book.store.database.impl;

import org.springframework.stereotype.Repository;
import pl.comarch.szkolenia.book.store.database.IBookRepository;
import pl.comarch.szkolenia.book.store.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class BookRepository implements IBookRepository {

    private final List<Book> books = new ArrayList<>();

    public BookRepository() {
        this.books.add(new Book(1, "Title 1", "Author 1", 30.00, 10));
        this.books.add(new Book(2, "Title 2", "Author 2", 55.99, 10));
        this.books.add(new Book(3, "Title 3", "Author 3", 109.44, 10));
        this.books.add(new Book(4, "Title 4", "Author 4", 14.45, 10));
        this.books.add(new Book(5, "Title 5", "Author 5", 50.00, 10));
    }

    @Override
    public List<Book> getAll() {
        return this.books;
    }

    @Override
    public Optional<Book> getById(final int id) {
        return this.books.stream().filter(b -> b.getId() == id).findFirst();
    }

    @Override
    public void persist(Book book) {
        this.books.stream()
                .filter(b -> b.getId() == book.getId())
                .findFirst()
                .ifPresentOrElse(b -> {
                    b.setAuthor(book.getAuthor());
                    b.setTitle(book.getTitle());
                    b.setPrice(book.getPrice());
                    b.setQuantity(book.getQuantity());
                    },
                        () -> this.books.add(book)
                );
    }
}
