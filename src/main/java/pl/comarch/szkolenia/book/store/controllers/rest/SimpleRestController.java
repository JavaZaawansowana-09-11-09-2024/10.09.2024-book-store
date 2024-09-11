package pl.comarch.szkolenia.book.store.controllers.rest;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.comarch.szkolenia.book.store.database.IBookRepository;
import pl.comarch.szkolenia.book.store.model.Book;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/rest/api")
public class SimpleRestController {

    private final IBookRepository bookRepository;

    @GetMapping(path = "/test1")
    public Book getBook() {
        return this.bookRepository.getById(1).get();
    }

    @PostMapping(path = "/test2")
    public Book test2(@RequestBody Book book,
                      @RequestParam("parametr") String parametr,
                      @RequestHeader("mojHeader") String h1,
                      @RequestHeader("mojHeader2") String h2) {
        System.out.println(book);
        System.out.println(parametr);
        System.out.println(h1);
        System.out.println(h2);

        book.setId(100);
        return book;
    }

    @GetMapping(path = "/test3")
    public ResponseEntity<Book> test3() {
        return ResponseEntity.status(201)
                .header("h1", "cos")
                .header("h2", "cosinnego")
                .body(new Book());
    }

}
