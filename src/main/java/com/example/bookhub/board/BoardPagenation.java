package com.example.bookhub.board;

public class BoardPagenation {

    private int page;
    private int size;

    /**
     * 페이지 요청 객체를 생성하는 생성자
     * @param page  요청할 페이지 번호
     * @param size  페이지당 항목 수
     */
    public BoardPagenation(int page, int size) {
        this.page = page;
        this.size = size;
    }

    /**
     * 현재 요청한 페이지 번호를 반환
     * @return
     */
    public int getPage() {
        return page;
    }

    /**
     * 페이지 번호를 설정하는 메서드
     * @param page 설정하고자 하는 페이지 번호
     */
    public void setPage(int page) {
        this.page = page;
    }

    /**
     * 페이지 항목 수를 반환하는 메서드
     */
    public int getSize() {
        return size;
    }

    /**
     * 페이지당 항목 수를 설정하는 메서드
     * @param size 설정할 페이지 항목 수
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * 페이지의 시작 오프셋을 계산하여 반환하는 메서드
     * @return
     */
    public int getOffset() {
        return (page - 1) * size;
    }


}
