package com.smhrd.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter // lombok : 어노테이션 기반으로 코드를 자동완성 해주는 라이브러리
@Setter // getter, setter 생성
@AllArgsConstructor // 모든 매개변수를 가진 생성자(모든 필드를 포함)
@NoArgsConstructor // 기본생성자
//@Data getter, setter, toString 기본생성자를 포함하여 한번에 생성
public class Member {

	// 1명의 유저정보를 저장할 수 있는 우리만의 자료형
	// VO(Value Object)
	// id, pw, tel, address
	private String id;
	private String pw;
	private String email;
	private String tell;

	// 생성자
	// 1) 기본 생성자
	// 2) (전달인자)매개변수가 있는 생성자
}