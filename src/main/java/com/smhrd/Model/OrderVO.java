package com.smhrd.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter // lombok : 어노테이션 기반으로 코드를 자동완성 해주는 라이브러리
@Setter // getter, setter 생성
@AllArgsConstructor// 모든 매개변수를 가진 생성자(모든 필드를 포함)
@NoArgsConstructor // 기본생성자
//@Data getter, setter, toString 기본생성자를 포함하여 한번에 생성
public class OrderVO {
		private int svc_idx;          // 의뢰 ID
	    private String svc_title;     // 의뢰 제목
	    private String svc_content;   // 의뢰 내용
	    private String svc_file;      // 첨부 파일 경로
	    private int user_id;          // 사용자 ID
	    
	    private String writer;			// 의뢰 작성자의 이름
	    private String createDate;		// 의뢰 작성 날짜
	    private String status;			// 의뢰의 현재 상태 (예: 진행 중, 완료 등)
	    private String description;		// 의뢰에 대한 설명 (요구 사항, 작업 내용 등)
	    
	    
}