package pl.comarch.szkolenia.book.store.sevices;

import pl.comarch.szkolenia.book.store.model.Book;

import java.util.List;

public interface IBookService {
    List<Book> getAllBooks();
}
