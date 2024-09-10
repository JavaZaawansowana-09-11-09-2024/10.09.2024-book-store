package pl.comarch.szkolenia.book.store.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.comarch.szkolenia.book.store.sevices.IBookService;

@Controller
@RequiredArgsConstructor
public class CommonController {

    private final IBookService bookService;

    @RequestMapping(path = {"/", "/main", "/index"}, method = RequestMethod.GET)
    public String main(Model model) {
        model.addAttribute("books", this.bookService.getAllBooks());
        return "main";
    }

    @RequestMapping(path = "/contact", method = RequestMethod.GET)
    public String contact() {
        return "contact";
    }
}
