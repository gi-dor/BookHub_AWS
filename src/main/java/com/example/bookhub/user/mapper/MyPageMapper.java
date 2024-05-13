package com.example.bookhub.user.mapper;

import com.example.bookhub.board.vo.Inquiry;
import com.example.bookhub.product.vo.Buy;
import com.example.bookhub.user.dto.InquiryListDTO;
import com.example.bookhub.user.dto.OrderDetailDTO;
import com.example.bookhub.user.dto.OrderListDTO;
import com.example.bookhub.user.dto.PageListDTO;
import com.example.bookhub.user.dto.WishListDTO;
import java.time.LocalDateTime;
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

    // 캐싱 1:1 문의

    List<InquiryListDTO>cacheInquiries( @Param("offset")int offset);

    // 캐싱 X 1:1 문의
    List<InquiryListDTO>NocacheInquiries( @Param("offset")int offset);


    // 문의사항 전체 조회
    int countInquiriesAll();


    List<Inquiry> selectInquiryList(String id );



    void deleteUserById(String id);

    void updatePassword(@Param("id") String id, @Param("password") String password);

    List<WishListDTO> selectWishListById(@Param("userId") String id ,@Param("offset")int offset );

    int getTotalWishListCount(String id);


    List<Inquiry> findInquiryByDate(@Param("startDate") LocalDateTime startDate,
                                    @Param("endDate") LocalDateTime endDate,
                                    @Param("id") String id);

    List<OrderListDTO> selectOrderListByIdPaging(@Param("id") String id, @Param("offset") int offset);

    int countOrder(String id);

    List<OrderDetailDTO> selectOrderDetailById(@Param("id") String id ,
                                               @Param("no") Long no);

    List<OrderDetailDTO> deliveryDetail(@Param("id") String id ,
                                        @Param("no") Long no);
}
