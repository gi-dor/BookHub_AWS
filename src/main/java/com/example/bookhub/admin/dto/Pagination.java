package com.example.bookhub.admin.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Pagination {

    private int rows;
    private int pages;
    private int totalRows;
    private int totalPages;
    private int totalBlocks;
    private int currentPage;
    private int currentBlock;
    private int begin;
    private int end;
    private int beginPage;
    private int endPage;
    private boolean isFirst;
    private boolean isLast;

    public Pagination(int currentPage, int totalRows) {
        this.totalRows = totalRows;
        this.currentPage = currentPage;
        init();
    }

    public Pagination(int currentPage, int totalRows, int rows) {
        this.totalRows = totalRows;
        this.currentPage = currentPage;
        this.rows = rows;
        init();
    }

    public Pagination(int currentPage, int totalRows, int rows, int pages) {
        this.totalRows = totalRows;
        this.currentPage = currentPage;
        this.rows = rows;
        this.pages = pages;
        init();
    }

    private void init() {

        if (totalRows > 0) {
            this.totalPages = (int) Math.ceil((double) totalRows / rows);
            this.totalBlocks = (int) Math.ceil((double) totalPages / pages);
            this.currentBlock = (int) Math.ceil((double) currentPage / pages);
            this.begin = (currentPage - 1) * rows + 1;
            this.end = currentPage * rows;
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

}

