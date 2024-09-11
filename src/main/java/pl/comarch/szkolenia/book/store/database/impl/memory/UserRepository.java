package pl.comarch.szkolenia.book.store.database.impl.memory;

import org.springframework.stereotype.Repository;
import pl.comarch.szkolenia.book.store.database.IUserRepository;
import pl.comarch.szkolenia.book.store.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository implements IUserRepository {

    private final List<User> users = new ArrayList<>();

    public UserRepository() {
        this.users.add(new User(1, "janusz", "janusz123"));
        this.users.add(new User(2, "wiesiek", "wiesiek123"));
    }

    @Override
    public Optional<User> getByLogin(final String login) {
        return this.users.stream()
                .filter(u -> u.getLogin().equals(login))
                .findFirst();
    }
}
