package com.example.bookhub.main.controller;


import com.example.bookhub.main.service.SearchService;
import com.example.bookhub.main.vo.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;



@RequiredArgsConstructor
@Controller
public class SearchController {
    private final SearchService searchService;

    //  출시일 순 검색 했을때
    @GetMapping("/search")
    public String searchBooks(@RequestParam(name = "keyword", required = false) String keyword, Model model) {
        List<Book> search = searchService.getPubDate(keyword);
        model.addAttribute("Search", search); // 모델에 책 정보를 담아서 HTML로 전달.
        return "main/searchList"; // searchList.html로 반환
    }

}



/*api에서 데이터 가져와서 html에서 바로 보이게 하는 코드
    @GetMapping("/search")
    public String searchBooksAndRenderHtml(@RequestParam("query") String query, Model model) {
        List<Book> books = searchService.searchBooks(query);
        model.addAttribute("books", books); // searchService 인스턴스를 통해 메서드 호출
        return "main/searchList"; // searchlist.html로 반환
    }
*/

