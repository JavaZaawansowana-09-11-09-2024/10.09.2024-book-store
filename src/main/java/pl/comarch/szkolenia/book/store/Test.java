package pl.comarch.szkolenia.book.store;

import pl.comarch.szkolenia.book.store.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Test {
    public static void main(String[] args) {
        Optional<Book> bookBox = find(7);
        if(bookBox.isPresent()) {
            System.out.println(bookBox.get().getTitle());
        } else {
            System.out.println("Ni ma takiej !!");
        }

        bookBox.ifPresentOrElse(
                b -> System.out.println(b.getTitle()),
                () -> System.out.println("Ni ma !!"));
    }

    public static Optional<Book> find(int id) {
        List<Book> books = new ArrayList<>();
        books.add(new Book(1, "Title 1", "Author 1", 30.00, 10));
        books.add(new Book(2, "Title 2", "Author 2", 55.99, 10));
        books.add(new Book(3, "Title 3", "Author 3", 109.44, 10));
        books.add(new Book(4, "Title 4", "Author 4", 14.45, 10));
        books.add(new Book(5, "Title 5", "Author 5", 50.00, 10));

        for(Book book : books) {
            if(book.getId() == id) {
                return Optional.of(book);
            }
        }

        return Optional.empty();
    }
}
