package pl.comarch.szkolenia.book.store.database.impl.hibernate;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import pl.comarch.szkolenia.book.store.database.IUserRepository;
import pl.comarch.szkolenia.book.store.model.User;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserDAO implements IUserRepository {

    private final SessionFactory sessionFactory;

    @Override
    public Optional<User> getByLogin(String login) {
        Session session = this.sessionFactory.openSession();
        Query<User> query = session.createQuery(
                "FROM pl.comarch.szkolenia.book.store.model.User WHERE login = :login",
                User.class);
        query.setParameter("login", login);

        try {
            return Optional.of(query.getSingleResult());
        } catch (Exception e) {
            return Optional.empty();
        } finally {
            session.close();
        }
    }
}
