package com.example.bookhub.admin.service;

import com.example.bookhub.admin.dto.IndividualDto;
import com.example.bookhub.admin.mapper.IndividualMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IndividualService {

    private final IndividualMapper individualMapper;

    public List<IndividualDto> getNoAnswerList(){
        return individualMapper.getNoAnswerList();
    }
}
