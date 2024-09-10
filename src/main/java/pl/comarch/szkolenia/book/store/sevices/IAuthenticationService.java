package pl.comarch.szkolenia.book.store.sevices;

public interface IAuthenticationService {
    void authenticate(String login, String password);
    void logout();
}
