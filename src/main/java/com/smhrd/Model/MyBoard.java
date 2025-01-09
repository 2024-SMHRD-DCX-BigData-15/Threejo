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
   private int idx;
   private String title;
   private String content;
   private String writer;
   private String img;
   private String indate;
   public MyBoard(String title, String content, String writer, String img) {
      super();
      this.title = title;
      this.content = content;
      this.writer = writer;
      this.img = img;
      
   }
   
   
   
   
   
}
