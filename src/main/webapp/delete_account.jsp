<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>회원탈퇴</title>
  <link rel="stylesheet" href="delete_account.css">
</head>
<body>
  <!-- 상단 헤더 -->
  <header class="header">
    <h1>
      <a href="loginmain.jsp">재능을IT다</a>
    </h1>
    <div class="auth-buttons">
      <a href="logout.jsp">로그아웃</a>
    </div>
  </header>

  <!-- 컨테이너 -->
  <div class="container">
    <!-- 사이드바 -->
    <aside class="sidebar">
      <h2>마이페이지</h2>
      <ul>
        <li><a href="mypage.jsp">내 프로필</a></li>
        <li><a href="ProposalBoxController">제안서보관함</a></li>
        <li><a href="OrderManageController">의뢰관리</a></li>
        <li><a href="delete_account.jsp" class="active">회원탈퇴</a></li>
      </ul>
    </aside>

    <!-- 메인 콘텐츠 -->
    <main class="main-content">
      <h1>회원탈퇴</h1>


      <!-- 회원탈퇴 폼 -->
      <form id="deleteForm" action="DeleteController" method="POST">
        
                <div class="form-item">
          <label>탈퇴사유</label>
          <div>
            <input type="radio" id="reason1" name="reason" value="이용하고 싶은 서비스가 없어요" required>
            <label for="reason1">이용하고 싶은 서비스가 없어요</label>
          </div>
          <div>
            <input type="radio" id="reason2" name="reason" value="서비스 불만족">
            <label for="reason2">서비스 품질이 낮아요</label>
          </div>
          <div>
            <input type="radio" id="reason3" name="reason" value="비매너 회원">
            <label for="reason3">비매너 회원을 만났어요</label>
          </div>
          <div>
            <input type="radio" id="reason4" name="reason" value="잦은 오류 발생">
            <label for="reason4">잦은 오류가 발생해요</label>
          </div>
          <div>
            <input type="radio" id="reason5" name="reason" value="대체 서비스">
            <label for="reason5">대체할 만한 서비스를 찾았어요</label>
          </div>
          <div>
            <input type="radio" id="reason6" name="reason" value="혜택 부족">
            <label for="reason6">쿠폰 · 적립금 등 혜택이 적어요</label>
          </div>
          <div>
            <input type="radio" id="reason7" name="reason" value="기타">
            <label for="reason7">기타</label>
          </div>
        </div>
        
        <div class="form-item">
          <label for="password">아이디 확인</label>
          <input type="text" id="user_id" name="user_id" placeholder="아이디를 입력하세요" required>
          <label for="password">비밀번호 확인</label>
          <input type="password" id="user_pw" name="user_pw" placeholder="비밀번호를 입력하세요" required>
        </div>

        <p class="warning-text">
          • 현재 사용중인 계정 정보는 회원 탈퇴 후 복구가 불가합니다.<br>
          • 진행 중인 거래건이 있거나 페널티 조치 중인 경우 탈퇴 신청이 불가합니다.<br>
          • 탈퇴 후 회원님의 정보는 전자상거래 소비자보호법에 의거한 개인정보처리방침에 따라 관리됩니다.<br>
          • 현재 보유 중인 쿠폰 및 무상지급된 캐시는 모두 자동 소멸됩니다.<br>
          • 탈퇴 후에는 계정 정보가 삭제되어 본인 확인이 불가하므로, 탈퇴 신청 전에 게시글 삭제를 요청해 주시기 바랍니다.<br>
        </p>

        <div class="form-item form-item-checkbox">
          <div>
            <input type="checkbox" id="confirmation" name="confirmation" required>
            <label for="confirmation">주의사항을 모두 확인하였습니다.</label>
          </div>
          <button type="submit" class="delete-button">회원 탈퇴</button>
        </div>

      </form>
    </main>
  </div>
</body>
</html>
