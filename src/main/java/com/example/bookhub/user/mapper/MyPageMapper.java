package com.example.bookhub.user.mapper;

import com.example.bookhub.board.vo.Inquiry;
import com.example.bookhub.product.vo.Buy;
import com.example.bookhub.product.vo.BuyBook;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MyPageMapper {

    int countCoupon(String id);

    List<Buy> selectOrderListById(String id);

    List<Inquiry> selectInquiryList(String id);

}
