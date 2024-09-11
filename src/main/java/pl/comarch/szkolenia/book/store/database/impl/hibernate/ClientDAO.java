package pl.comarch.szkolenia.book.store.database.impl.hibernate;

import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import pl.comarch.szkolenia.book.store.model.Client;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ClientDAO {

    private final SessionFactory sessionFactory;

    public void persist(Client client) {
        Session session = this.sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.persist(client);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    public Optional<Client> getById(int id) {
        Session session = this.sessionFactory.openSession();
        Query<Client> query = session.createQuery(
                "FROM pl.comarch.szkolenia.book.store.model.Client where id = :id",
                Client.class);
        query.setParameter("id", id);

        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}
