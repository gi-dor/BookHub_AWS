package com.example.bookhub.user.dto;

import com.example.bookhub.product.vo.Author;
import com.example.bookhub.user.vo.UserPagination;
import java.util.List;
import java.util.StringJoiner;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class WishListDTO {

    private Long wishNo;
    private String name;
    private Long bookNo;
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

