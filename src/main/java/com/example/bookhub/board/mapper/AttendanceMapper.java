package com.example.bookhub.board.mapper;

import com.example.bookhub.board.vo.Attendance;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface AttendanceMapper {

    /**
     * 출석체크 정보 조회
     *
     * @param userNo
     * @return
     */
    List<Attendance> userAttendanceCheckByNo(@Param("no") long userNo);

    /**
     * 출석체크하기
     *
     * @param userNo
     */
    void insertAttendance(@Param("no") long userNo);

    void deleteAttendance();

    int attendanceCheckCount(@Param("no") long userNo);
}
