package pl.comarch.szkolenia.book.store.database.impl.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.comarch.szkolenia.book.store.database.IBookRepository;
import pl.comarch.szkolenia.book.store.model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class BookDAO implements IBookRepository {

    private final Connection connection;

    @Override
    public List<Book> getAll() {
        List<Book> result = new ArrayList<>();
        String sql = "SELECT * FROM tbook";

        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                result.add(new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getDouble("price"),
                        rs.getInt("quantity")));
            }
        } catch (SQLException e) {
            System.out.println("Getting books from DB error !!");
        }

        return result;
    }

    @Override
    public Optional<Book> getById(int id) {
        try {
            String sql = "SELECT * FROM tbook WHERE id = ?";

            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()) {
                return Optional.of(new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getDouble("price"),
                        rs.getInt("quantity")
                ));
            }
            return Optional.empty();
        } catch (SQLException e) {
            System.out.println("Getting book from DB error !!");
            return Optional.empty();
        }
    }

    @Override
    public void persist(Book book) {
        try {
            String sql = "INSERT INTO tbook (title, author, price, quantity) VALUES (?,?,?,?)";
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setDouble(3, book.getPrice());
            preparedStatement.setInt(4, book.getQuantity());

            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            rs.next();
            book.setId(rs.getInt(1));
        } catch (SQLException e) {
            System.out.println("Saving book to DB error !!");
        }
    }
}
