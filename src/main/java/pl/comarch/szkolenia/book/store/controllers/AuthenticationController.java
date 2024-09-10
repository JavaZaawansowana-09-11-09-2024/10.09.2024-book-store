package pl.comarch.szkolenia.book.store.controllers;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.comarch.szkolenia.book.store.sevices.IAuthenticationService;

@Controller
@RequiredArgsConstructor
public class AuthenticationController {

    private final IAuthenticationService authenticationService;
    @Autowired
    private HttpSession httpSession;

    /*@Resource
    private MySessionObject mySessionObject;*/

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(@RequestParam("login") String login,
                         @RequestParam("password") String password) {

        this.authenticationService.authenticate(login, password);

        if(this.httpSession.getAttribute("isLogged") != null) {
            return "redirect:/main";
        }
        return "redirect:/login";
    }

    @RequestMapping(path = "/logout", method = RequestMethod.GET)
    public String logout() {
        this.authenticationService.logout();
        return "redirect:/main";
    }
}
