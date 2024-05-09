package com.example.bookhub.board.service;

import com.example.bookhub.board.mapper.AttendanceMapper;
import com.example.bookhub.board.mapper.CouponMapper;
import com.example.bookhub.board.vo.Attendance;
import com.example.bookhub.product.mapper.BuyMapper;
import com.example.bookhub.product.vo.Coupon;
import com.example.bookhub.product.vo.CouponProduced;
import com.example.bookhub.user.mapper.UserMapper;
import com.example.bookhub.user.vo.User;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendanceService {

    private final CouponMapper couponMapper;
    private final AttendanceMapper attendanceMapper;
    private final UserMapper userMapper;

    /**
     * 출석하기
     * @param userNo
     */
    public void insertAttendance(long userNo) {

        attendanceMapper.insertAttendance(userNo);
    }

    /**
     * 출석 정보 조회
     * @param userNo
     * @return
     */
    public List<Attendance> userAttendanceCheck(long userNo) {

        return attendanceMapper.userAttendanceCheckByNo(userNo);
    }

//    public void deleteAttendance() {
//        attendanceMapper.deleteAttendance();
//    }

    public int attendanceCheckCount(long userNo) {

        return attendanceMapper.attendanceCheckCount(userNo);
    }

    public void insertAttendanceCoupon(long userNo) {

        User user = userMapper.selectUserByNo(userNo);

        CouponProduced couponProduced = new CouponProduced();
        couponProduced.setCoupon(couponMapper.getCoupon(1));
        couponProduced.setUser(user);

        couponMapper.insertAttendanceCoupon(couponProduced);
    }

}
