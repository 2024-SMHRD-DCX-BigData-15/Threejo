package com.smhrd.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter // lombok : 어노테이션 기반으로 코드를 자동완성 해주는 라이브러리
@Setter // getter, setter 생성
@AllArgsConstructor // 모든 매개변수를 가진 생성자(모든 필드를 포함)
@NoArgsConstructor // 기본생성자
//@Data getter, setter, toString 기본생성자를 포함하여 한번에 생성
public class MyBoard {
	int not_idx;
	String not_title;
	String not_content;
	String not_file;
	String not_create_at;
	}
	
	
