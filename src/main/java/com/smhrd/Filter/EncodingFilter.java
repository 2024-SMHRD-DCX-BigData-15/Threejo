package com.smhrd.Filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class EncodingFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 초기화 로직이 필요 없다면 비워둡니다.
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // 모든 요청과 응답을 UTF-8로 설정
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        // 다음 필터 또는 서블릿 실행
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // 종료 시 정리할 리소스가 있다면 구현
    }
}
