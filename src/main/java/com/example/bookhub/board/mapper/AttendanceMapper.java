package com.example.bookhub.board.mapper;

import com.example.bookhub.board.vo.Attendance;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AttendanceMapper {

    /**
     * 출석체크 정보 조회
     * @param userNo
     * @return
     */
    Attendance userAttendanceCheckByNo(@Param("no") long userNo);

    /**
     * 출석체크 이벤트 등록
     * @param userNo
     */
    void insertAttendance(@Param("no") long userNo);

    /**
     * 출석체크 일수 1증가
     * @param userNo
     */
    void updateAttendance(@Param("no") long userNo);

    /**
     * 출석일 리셋
     * @param userNo
     */
    void resetAttendanceCount(@Param("No") long userNo);
}
