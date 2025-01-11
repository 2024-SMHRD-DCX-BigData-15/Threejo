<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>메인페이지</title>
    <link rel="stylesheet" href="main.css">
    <script src="https://kit.fontawesome.com/3baaf0c2a4.js" crossorigin="anonymous"></script>
</head>
<body>
    <header class="header">
        <h1>
            <a href="main.jsp">재능을IT다</a>
        </h1>
        <div class="auth-buttons">
            <a href="join.jsp">회원가입</a>
            <a href="login.jsp">로그인</a>
        </div>
    </header>

    <div class="hero-section">
        <div class="hero-text">
            <h2>WELCOME TO TALENT MARKET</h2>
            <p>내게 필요한 전문가를 찾아보세요</p>
            
            <form class="search-box" action="" method="get">
                <input type="text" placeholder="검색어를 입력하세요.">	
                <button class="search-btn" type="submit">
                    <i class="fa-solid fa-magnifying-glass"></i>
                </button>
            </form>
        </div>
    </div>
    
    <nav class="services">
        <div class="service-item">
            <a href="news.jsp">
                <img src="customer-service.png" alt="고객센터">
            </a>
            <p>고객센터</p>
        </div>
        <div class="service-item">
            <a href="order.jsp">
                <img src="To_request.png" alt="의뢰하기">
            </a>
            <p>의뢰하기</p>
        </div>
        <div class="service-item">
            <a href="OrderListController">
                <img src="requestlist.png" alt="의뢰목록">
            </a>
            <p>의뢰목록</p>
        </div>
        <div class="service-item">
            <a href="calender.jsp">
                <img src="Schedule_Management.png" alt="일정관리">
            </a>
            <p>일정관리</p>
        </div>
    </nav>
</body>
</html>
