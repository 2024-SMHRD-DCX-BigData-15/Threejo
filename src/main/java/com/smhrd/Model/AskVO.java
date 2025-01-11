package com.smhrd.Model;
//1
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter // lombok : 어노테이션 기반으로 코드를 자동완성 해주는 라이브러리
@Setter // getter, setter 생성
@AllArgsConstructor // 모든 매개변수를 가진 생성자(모든 필드를 포함)
@NoArgsConstructor // 기본생성자
//@Data getter, setter, toString 기본생성자를 포함하여 한번에 생성
public class AskVO {
		
		private String ask_idx;   // 제목
		private String ask_title;   // 제목
	    private String ask_content;  // 작성자
	    private String ask_file; // 내용
	    private String ask_id; // 내용
	    
	    public AskVO(String ask_title, String ask_content, String ask_id) {
	        this.ask_title = ask_title;
	        this.ask_content = ask_content;
	        this.ask_id = ask_id;
	    }

}

