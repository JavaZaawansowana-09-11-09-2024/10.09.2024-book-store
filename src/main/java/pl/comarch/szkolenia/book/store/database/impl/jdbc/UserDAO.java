package pl.comarch.szkolenia.book.store.database.impl.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.comarch.szkolenia.book.store.database.IUserRepository;
import pl.comarch.szkolenia.book.store.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@RequiredArgsConstructor
public class UserDAO implements IUserRepository {

    private final Connection connection;
    @Override
    public Optional<User> getByLogin(String login) {
        try {
            String sql = "SELECT * FROM tuser WHERE login = ?";
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, login);

            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()) {
                return Optional.of(new User(
                        rs.getInt("id"),
                        rs.getString("login"),
                        rs.getString("password")
                ));
            }
            return Optional.empty();
        } catch (SQLException e) {
            System.out.println("Getting user from DB error !!");
            return Optional.empty();
        }
    }
}
