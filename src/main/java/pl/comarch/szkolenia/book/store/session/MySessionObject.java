package pl.comarch.szkolenia.book.store.session;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import pl.comarch.szkolenia.book.store.model.User;

@Component
@SessionScope
public class MySessionObject {
    private String cos;
    private User user;
}
