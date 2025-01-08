<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="https://cdn.iamport.kr/v1/iamport.js"></script>

<script type="text/javascript">
IMP.init("imp68125723");
IMP.request_pay(
		  {
		    channelKey: "channel-key-ec9984ca-656c-459d-813b-7283f52c4454",
		    pay_method: "card",
		    merchant_uid: `payment-${crypto.randomUUID()}`, // 주문 고유 번호, 변경 시 다른 결제 가능
		    name: "노르웨이 회전 의자",
		    amount: 10,
		    buyer_email: "gildong@gmail.com",
		    buyer_name: "홍길동",
		    buyer_tel: "010-4242-4242",
		    buyer_addr: "서울특별시 강남구 신사동",
		    buyer_postcode: "01182",
		  },
		  function (rsp) {
		    if (rsp.success) {
		    	console.log(rsp);
		    }else {
		    	console.log(rsp);
		    }
		  },
		);
</script>

</head>
<body>
<button onclick='requestPay()'>결제하기</button>
</body>
</html>