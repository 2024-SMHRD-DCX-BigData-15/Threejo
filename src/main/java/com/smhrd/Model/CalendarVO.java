package com.smhrd.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CalendarVO {
    private String sche_title;  // 일정 제목
    private String sche_st_dt;  // 시작 일자
    private String sche_ed_dt;  // 종료 일자
    private String user_id;     // 사용자 ID
}
