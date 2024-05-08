package com.example.bookhub.main.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchCriteria {
    private String opt;
    private String keyword;
    private String extraKeyword;
    private String extraTitleOpt;
    private String extraAuthorOpt;
    private String extraPublisherOpt;
    private int sort;
    private int cate;
    private int cateKeyword;
    private int pubDate;
    private int period;

    private int page;               // 현재 페이지 번호
    private int recordSize;         // 페이지당 출력할 데이터 개수
    private int pageSize;           // 화면에 출력할 페이지 사이즈
    private int totalRows;          // 전체 데이터갯수
    private int totalPages;         // 전체 페이지 갯수
    private int totalBlocks;        // 전체 블록 갯수
    private int currentBlock;       // 현재 블록
    private int beginPage;          // 블록의 시작 페이지
    private int endPage;            // 블록의 끝 페이지
    private boolean isFirst;        // 첫페이지인지 여부
    private boolean isLast;         // 끝페이지인지 여부
    public SearchCriteria() {
        this.page = 1;
        this.recordSize = 10;
        this.pageSize = 10;
        this.sort= 1;
        this.cateKeyword =1;

    }

    public int getOffset() {
        return (page - 1) * recordSize;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;

        this.totalPages = (int) Math.ceil((double) totalRows/recordSize);
        this.totalBlocks = (int) Math.ceil((double) totalPages/pageSize);
        this.currentBlock = (int) Math.ceil((double) page/pageSize);
        this.beginPage = (currentBlock -1)*pageSize + 1;
        this.endPage = currentBlock*10;
        if (currentBlock == totalBlocks) {
            this.endPage = this.totalPages;
        }
        this.isFirst = page == 1;
        this.isLast = page == totalPages;
    }

    public int getPage() {
        return page;
    }
}
