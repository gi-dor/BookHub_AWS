package com.example.bookhub.board.service;

import com.example.bookhub.board.mapper.AttendanceMapper;
import com.example.bookhub.board.vo.Attendance;
import com.example.bookhub.user.mapper.UserMapper;
import com.example.bookhub.user.vo.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class AttendanceService {

    private final AttendanceMapper attendanceMapper;
    private final UserMapper userMapper;

    /**
     * 출석체크 이벤트 참여
     * @param userNO
     */
    public void insertAttendance(long userNo) {

        attendanceMapper.insertAttendance(userNo);
    }

    /**
     * 출석 정보 조회
     * @param userNo
     * @return
     */
    public Attendance userAttendanceCheck(long userNo) {

        return attendanceMapper.userAttendanceCheckByNo(userNo);
    }

    /**
     * 출석하기
     * @param userNo
     */
    public void updateAttendance(long userNo) {

        attendanceMapper.updateAttendance(userNo);
    }

    /**
     * 출석일 수 초기화
     * @param userNo
     */
    public void resetAttendanceCount(long userNo) {

    }

}
