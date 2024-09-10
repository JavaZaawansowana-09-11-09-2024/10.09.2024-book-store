package pl.comarch.szkolenia.book.store.sevices.impl;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.comarch.szkolenia.book.store.database.IUserRepository;
import pl.comarch.szkolenia.book.store.model.User;
import pl.comarch.szkolenia.book.store.sevices.IAuthenticationService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements IAuthenticationService {

    private final IUserRepository userRepository;

    @Autowired
    HttpSession httpSession;

    @Override
    public void authenticate(String login, String password) {
        Optional<User> userBox = this.userRepository.getByLogin(login);
        if(userBox.isPresent() && userBox.get().getPassword().equals(password)) {
            this.httpSession.setAttribute("isLogged", true);
        }
    }

    @Override
    public void logout() {
        this.httpSession.removeAttribute("isLogged");
    }
}
