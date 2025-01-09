package com.smhrd.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.smhrd.Model.BoardDAO;
import com.smhrd.Model.MyBoard;

@WebServlet("/board")
public class BoardList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		BoardDAO dao = new BoardDAO();
		List<MyBoard> list = dao.select();
		// JavaObject => JSONObject(json 형식의 객체구조) => GSON
		Gson gson = new Gson();
		String json = gson.toJson(list);
		
		response.setCharacterEncoding("UTF-8");
//		response.setContentType("text/html; charset=UTF-8");
		// 텍스트 출력 스트림(out / server -> client) => PrintWriter
		PrintWriter out = response.getWriter();
		out.print(json);
	}

}
