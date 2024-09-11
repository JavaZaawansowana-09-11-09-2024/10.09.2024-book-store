package pl.comarch.szkolenia.book.store.controllers;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.comarch.szkolenia.book.store.database.impl.hibernate.ClientDAO;
import pl.comarch.szkolenia.book.store.model.Address;
import pl.comarch.szkolenia.book.store.model.Client;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class TestController {

    private final ClientDAO clientDAO;

    @RequestMapping(path = "/test", method = RequestMethod.GET)
    public String test() {
        Client client = new Client();
        client.setName("Janusz");
        client.setSurname("Kowalski");

        Address address1 = new Address();
        address1.setCity("Krakow");
        address1.setStreet("Jakas");
        address1.setNo("23A");

        Address address2 = new Address();
        address2.setCity("Krakow");
        address2.setStreet("Jakas");
        address2.setNo("23A");

        Address address3 = new Address();
        address3.setCity("Krakow");
        address3.setStreet("Jakas");
        address3.setNo("23A");

        client.getAddressSet().add(address1);
        client.getAddressSet().add(address2);
        client.getAddressSet().add(address3);

        this.clientDAO.persist(client);

        return "redirect:/main";
    }

    @GetMapping(path = "/test2")
    public String test2() {
        Optional<Client> clientBox = this.clientDAO.getById(1);

        if(clientBox.isPresent()) {
            Client c = clientBox.get();
            System.out.println(c.getId());
            System.out.println(c.getName());
            System.out.println(c.getSurname());
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!");
            System.out.println(c.getAddressSet());
        }


        return "redirect:/main";
    }

}
