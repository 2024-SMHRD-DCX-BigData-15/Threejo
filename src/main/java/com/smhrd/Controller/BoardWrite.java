package com.smhrd.controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.smhrd.Model.*;

@WebServlet("/write")
public class BoardWrite extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// MultipartRequest => COS 라이브러리 추가
		
		//(request, 파일저장경로, 최대파일크기, 인코딩타입, 파일이름생성규칙)
		ServletContext context = request.getServletContext();
		String uploadPath = context.getRealPath("upload");
		int maxSize = 500*1024*1024; //5MB
		
		MultipartRequest multi = new MultipartRequest(request, uploadPath, maxSize, "UTF-8", new DefaultFileRenamePolicy());
		//
		String title = multi.getParameter("title");
		String content = multi.getParameter("content");
		String writer = multi.getParameter("writer");
		String img = multi.getFilesystemName("img");
		
		MyBoard uploadBoard = new MyBoard(title, content, writer, img);
		
		BoardDAO dao = new BoardDAO();
		int res = dao.writeBoard(uploadBoard);
		
		if(res>0) {
			response.sendRedirect("boardlist.jsp");
		} else {
			response.sendRedirect("boardform.jsp");
		}
	}

}
