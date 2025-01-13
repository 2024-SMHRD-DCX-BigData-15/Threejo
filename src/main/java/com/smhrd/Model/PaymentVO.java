package com.smhrd.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentVO {
	    private int pay_idx;          // 결제 식별자
	    private String prop_title;    // 결제 제목
	    private long prop_account;    // 결제 금액
	    private String user_email;    // 결제 이메일
	    private String pay_id;        // 결제 아이디
	    private String pay_tell;      // 결제 전화번호
	    private int prop_idx;         // 제안 식별자
	    
	    // 생성자
		public PaymentVO(String prop_title, long prop_account, String user_email, String pay_id, String pay_tell, int prop_idx) {
		        this.prop_title = prop_title;
		        this.prop_account = prop_account;
		        this.user_email = user_email;
		        this.pay_id = pay_id;
		        this.pay_tell = pay_tell;
		        this.prop_idx = prop_idx;
		}
	}
