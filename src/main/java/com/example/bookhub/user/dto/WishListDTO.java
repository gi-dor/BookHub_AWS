package com.example.bookhub.user.dto;

import com.example.bookhub.product.vo.Author;
import java.util.List;
import java.util.StringJoiner;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class WishListDTO {

    private Long wishNo;
    private String name;
    private Long price;
    private String bookImages;
    private List<Author> authorList;

    public String getAuthors() {
        StringJoiner joiner = new StringJoiner(" , ");
        for (Author author : authorList) {
            joiner.add(author.getName());
        }
        return joiner.toString();
    }
}

