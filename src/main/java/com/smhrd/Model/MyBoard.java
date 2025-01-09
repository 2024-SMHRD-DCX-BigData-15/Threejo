package com.smhrd.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyBoard {
   // idx, title, content, writer, img, indate를 한번에 
   // 묶을 수 있는 나만의 자료형 MyBoard
   private int svc_idx;
   private String svc_title;
   private String svc_content;
   private String user_id;
   private String svc_file;
   private String created_at;
   public MyBoard(String title, String content, String writer, String img) {
      super();
      this.svc_title = svc_title;
      this.svc_content = svc_content;
      this.user_id = user_id;
      this.svc_file = svc_file;
      
   }
   
   
   
   
   
}
