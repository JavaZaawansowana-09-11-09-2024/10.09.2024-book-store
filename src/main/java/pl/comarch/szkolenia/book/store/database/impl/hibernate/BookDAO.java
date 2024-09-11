package pl.comarch.szkolenia.book.store.database.impl.hibernate;

import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import pl.comarch.szkolenia.book.store.database.IBookRepository;
import pl.comarch.szkolenia.book.store.model.Book;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BookDAO implements IBookRepository {

    private final SessionFactory sessionFactory;

    @Override
    public List<Book> getAll() {
        Session session = this.sessionFactory.openSession();
        Query<Book> query = session.createQuery(
                "FROM pl.comarch.szkolenia.book.store.model.Book",
                Book.class);
        List<Book> result = query.getResultList();
        session.close();
        return result;
    }

    @Override
    public Optional<Book> getById(int id) {
        Session session = this.sessionFactory.openSession();
        Query<Book> query = session.createQuery("FROM pl.comarch.szkolenia.book.store.model.Book WHERE id = :id", Book.class);
        query.setParameter("id", id);

        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        } finally {
            session.close();
        }
    }

    @Override
    public void persist(Book book) {
        Session session = this.sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.persist(book);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }
}
