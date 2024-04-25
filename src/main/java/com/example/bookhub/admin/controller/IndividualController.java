package com.example.bookhub.admin.controller;

import com.example.bookhub.admin.dto.IndividualDto;
import com.example.bookhub.admin.service.IndividualService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class IndividualController {

    private final IndividualService individualService;

    @GetMapping("/admin/individual/noanswer")
    public String noAnswerList(Model model, IndividualDto dto){
        List<IndividualDto> saveDto = individualService.getNoAnswerList();
        model.addAttribute("noanswer", saveDto);
        return "admin/individual/noanswerlist";
    }

}
