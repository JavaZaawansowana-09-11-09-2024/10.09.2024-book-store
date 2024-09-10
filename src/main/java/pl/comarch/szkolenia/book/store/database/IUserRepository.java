package pl.comarch.szkolenia.book.store.database;

import pl.comarch.szkolenia.book.store.model.User;

import java.util.Optional;

public interface IUserRepository {
    Optional<User> getByLogin(String login);
}
