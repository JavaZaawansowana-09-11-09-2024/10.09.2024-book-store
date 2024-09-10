package pl.comarch.szkolenia.book.store.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User {
    private int id;
    private String login;
    private String password;
}
