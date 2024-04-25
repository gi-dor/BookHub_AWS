package com.example.bookhub.admin.mapper;

import com.example.bookhub.admin.dto.IndividualDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IndividualMapper {

    List<IndividualDto> getNoAnswerList();

}
