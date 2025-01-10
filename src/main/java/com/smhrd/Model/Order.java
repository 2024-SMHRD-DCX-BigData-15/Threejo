package com.smhrd.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Order 클래스: 데이터 저장 및 전달

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor


// svc_idx svc_title svc_content svc_file user_id 
public class Order {
	  private int svc_idx;          // 의뢰 ID
	    private String svc_title;     // 의뢰 제목
	    private String svc_content;   // 의뢰 내용
	    private String svc_file;      // 첨부 파일 경로
	    private int user_id;          // 사용자 ID
}