package com.example.bookhub.user.mapper;

import com.example.bookhub.board.vo.Inquiry;
import com.example.bookhub.product.vo.Buy;
import com.example.bookhub.user.dto.InquiryListDTO;
import com.example.bookhub.user.dto.PageListDTO;
import com.example.bookhub.user.dto.WishListDTO;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

@Mapper
public interface MyPageMapper {

    int countCoupon(String id);


    List<Buy> selectOrderListById(String id);

    int countInquiry(String id);

    List<InquiryListDTO>selectInquiryListPaging(@Param("id")String id , @Param("offset")int offset);
//    PageListDTO<InquiryListDTO> selectInquiryListPaging(@Param("id") String id, @Param("offset") int offset);


    List<Inquiry> selectInquiryList(String id );



    void deleteUserById(String id);

    void updatePassword(@Param("id") String id, @Param("password") String password);

    List<WishListDTO> selectWishListById(@Param("userId") String id ,@Param("offset")int offset );

    int getTotalWishListCount(String id);


}
