package com.example.bookhub.user.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserPagination {

    // 화면에 보여줄 글, 행의 갯수
    private int rows = 10;

    // 페이징 네비게이션의 보여줄 페이지 갯수    1, 2 , 3 , 4 ,5 >  >>
    private int pages = 5;

    // 전체 글의 갯수
    private int totalRows;

    // 전체 페이지 갯수  totalPages = 5 --> 글 갯수 26~30개
    private int totalPages;

    // 전체 페이지네이션 바에 표시할 전체 블럭 갯수
    private int totalBlocks;

    // 현재 페이지 번호
    private int currentPage;

    // 현재 블럭 번호
    private int currentBlock;

    private int begin;
    private int end;
    private int beginPage;
    private int endPage;

    // 현재  첫번째 페이지 ,마지막 페이지 구분
    private boolean isFirst;
    private boolean isLast;

    private void init() {

        // 글이 존재하는지
        if (totalRows > 0) {
            // 전체 페이지 갯수 계산하기
            this.totalPages = (int) Math.ceil((double) totalRows / rows);

            // 전체 페이지 블럭 갯수 계산
            this.totalBlocks = (int) Math.ceil((double) totalPages / pages);

            // 현재 블럭 계싼
            this.currentBlock = (int) Math.ceil((double) currentPage / pages);

            // 게시글 조회범위
            this.begin = (currentPage - 1) * rows + 1;
            this.end = currentPage * rows;

            // 페이지 내비게이션 출력범위 계산
            this.beginPage = (currentBlock - 1) * pages + 1;
            this.endPage = currentBlock * pages;

            if (currentBlock == totalBlocks) {
                this.endPage = this.totalPages;
            }

            if (currentPage == 1) {
                this.isFirst = true;
            }

            if (currentPage == totalPages) {
                this.isLast = true;
            }
        }
    }


    public UserPagination(int currentPage, int totalRows) {
        this.totalRows = totalRows;
        this.currentPage = currentPage;
        init();
    }


    public UserPagination(int currentPage, int totalRows, int rows) {
        this.totalRows = totalRows;
        this.currentPage = currentPage;
        this.rows = rows;
        init();
    }


    public UserPagination(int currentPage, int totalRows, int rows, int pages) {
        this.totalRows = totalRows;
        this.currentPage = currentPage;
        this.rows = rows;
        this.pages = pages;
        init();
    }
}
