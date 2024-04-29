package com.example.bookhub.user.dto;

import com.example.bookhub.user.vo.UserPagination;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageWishListDTO {

    private List<WishListDTO> wishListDTO;
    private UserPagination userPagination;

}
