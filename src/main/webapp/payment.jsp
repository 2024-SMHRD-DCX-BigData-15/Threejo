<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.smhrd.Model.PaymentVO" %>
<%@ page import="com.smhrd.Model.PaymentDAO" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>결제</title>
    <script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="https://cdn.iamport.kr/v1/iamport.js"></script>
</head>
<body>
<%
    // 결제 정보를 DB에서 가져오기
    int pay_idx = Integer.parseInt(request.getParameter("pay_idx")); // 전달받은 pay_idx
    PaymentDAO dao = new PaymentDAO();
    PaymentVO payment = dao.getPaymentById(pay_idx); // pay_idx로 Payment 정보 가져오기
%>
    <script type="text/javascript">
        IMP.init("imp68125723");

        const merchantUid = `payment-<%= payment.getPay_idx() %>`; // merchantUid 생성
        const name = "<%= payment.getProp_title() %>";
        const amount = <%= payment.getProp_account() %>;
        const buyerEmail = "<%= payment.getUser_email() %>";
        const buyerName = "<%= payment.getPay_id() %>";
        const buyerTel = "<%= payment.getPay_tell() %>";

        // 결제 요청
        IMP.request_pay({
        	channelKey: "channel-key-ec9984ca-656c-459d-813b-7283f52c4454",
            pay_method: "card",
            merchant_uid: merchantUid,
            name: name,
            amount: amount,
            buyer_email: buyerEmail,
            buyer_name: buyerName,
            buyer_tel: buyerTel,
            buyer_postcode: "01182",
        }, function (rsp) {
            if (rsp.success) {
                console.log("[DEBUG] 결제 성공:", rsp);
            } else {
                console.log("[DEBUG] 결제 실패:", rsp);
            }
        });
    </script>

    <button onclick="location.reload()">결제하기</button>
</body>
</html>
