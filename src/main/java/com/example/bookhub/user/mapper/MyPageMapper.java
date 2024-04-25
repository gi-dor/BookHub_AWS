package com.example.bookhub.user.mapper;

import com.example.bookhub.board.vo.Inquiry;
import com.example.bookhub.product.vo.Buy;
import com.example.bookhub.user.vo.WishList;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MyPageMapper {

    int countCoupon(String id);

    List<Buy> selectOrderListById(String id);

    List<Inquiry> selectInquiryList(String id);

    void deleteUserById(String id);

    void updatePassword(@Param("id") String id, @Param("password") String password);

    List<WishList> selectWishListById(String id);
}
