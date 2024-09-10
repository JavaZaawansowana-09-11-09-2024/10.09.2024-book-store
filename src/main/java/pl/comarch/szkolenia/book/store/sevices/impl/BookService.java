package pl.comarch.szkolenia.book.store.sevices.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.comarch.szkolenia.book.store.database.IBookRepository;
import pl.comarch.szkolenia.book.store.model.Book;
import pl.comarch.szkolenia.book.store.sevices.IBookService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService implements IBookService {

    private final IBookRepository bookRepository;

    @Override
    public List<Book> getAllBooks() {
        return this.bookRepository.getAll();
    }
}
