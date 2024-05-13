
package com.example.bookhub.main;

import com.example.bookhub.main.dto.BookListDto;
import com.example.bookhub.main.dto.SearchCriteria;
import com.example.bookhub.main.service.MainPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class MainController {

    private final MainPageService mainPageService;
    @GetMapping("/")
    public String mainPage(Model model) {
        SearchCriteria criteria = new SearchCriteria();
        BookListDto mainBestSeller = mainPageService.mainBestSeller(criteria);
        BookListDto mainNewBook= mainPageService.mainNewBook(criteria);
        BookListDto mainBookViewCount= mainPageService.mainBookViewCount(criteria);
        BookListDto mainBookRecommend= mainPageService.mainBookRecommend(criteria);

        model.addAttribute("mainBestSeller", mainBestSeller);
        model.addAttribute("mainNewBook", mainNewBook);
        model.addAttribute("mainBookViewCount", mainBookViewCount);
        model.addAttribute("mainBookRecommend", mainBookRecommend);

        return "main/main2";
    }



    @GetMapping("/navbar")
    public String Navbar() {
        return "navbar";
    }


    @GetMapping("/list")
    public String BestSellerList() {
        return "catelist";
    }

    @GetMapping("/searchlist")
    public String searchList() {
        return "main/searchList";
    }



}










