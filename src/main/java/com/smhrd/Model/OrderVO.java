package com.smhrd.Model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderVO {
    private int svc_idx;          // 의뢰 ID
    private String svc_title;     // 의뢰 제목
    private String svc_content;   // 의뢰 내용
    private String svc_categori;  // 서비스 카테고리
    private long svc_account;     // 의뢰 예산
    private String svc_ed_td;     // 의뢰 완료일
    private String svc_id;        // 작성자 ID

    @Override
    public String toString() {
        return "OrderVO{" +
                "svc_idx=" + svc_idx +
                ", svc_title='" + svc_title + '\'' +
                ", svc_content='" + svc_content + '\'' +
                ", svc_categori='" + svc_categori + '\'' +
                ", svc_account=" + svc_account +
                ", svc_ed_td='" + svc_ed_td + '\'' +
                ", svc_id='" + svc_id + '\'' +
                '}';
    }
}
