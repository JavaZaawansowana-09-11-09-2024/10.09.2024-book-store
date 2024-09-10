package pl.comarch.szkolenia.book.store.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Book {
    private int id;
    private String title;
    private String author;
    private double price;
    private int quantity;
}
