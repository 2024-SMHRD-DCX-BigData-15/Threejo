package com.smhrd.Model;

import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Lombok 어노테이션 추가
@Getter
@Setter
@Data // Getter, Setter, toString, equals, hashCode 자동 생성
@NoArgsConstructor // 기본 생성자 자동 생성
@AllArgsConstructor // 모든 필드를 초기화하는 생성자 자동 생성
public class ProposalVO {
    private int prop_idx; // 제안 식별자
    private String prop_title; // 제안 제목
    private String prop_content; // 제안 내용
    private long prop_account; // 제안 금액
    private Timestamp prop_ed_td; // 제안 완료일
    private String prop_tell; // 연락 가능한 전화번호
    private int svc_idx; // 글 식별자 (t_service의 fk)
    private String selected_yn; // 채택 여부
    private String prop_id; // 등록자 아이디 (t_user의 fk)
    
 // 생성자가 정의된 예시 (5개의 매개변수를 받는 생성자)
    public ProposalVO(String prop_title, String prop_content, long prop_account, Timestamp prop_ed_td, String prop_tell, int svc_idx, String prop_id) {
        this.prop_title = prop_title;
        this.prop_content = prop_content;
        this.prop_account = prop_account;
        this.prop_ed_td = prop_ed_td;
        this.prop_tell = prop_tell;
        this.svc_idx = svc_idx;
        this.prop_id = prop_id;
        
        
    }
}

