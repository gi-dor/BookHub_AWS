package com.example.bookhub.product.dto;

import lombok.Data;
import java.util.Date;

@Data
public class KakaoReadyResponse {
    private String tid; // 결제 고유 번호
    private String next_redirect_pc_url; // web - 받는 결제 페이지
    private Date created_at;
}
