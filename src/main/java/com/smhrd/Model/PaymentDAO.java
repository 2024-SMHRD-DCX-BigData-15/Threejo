package com.smhrd.Model;

public class PaymentDAO {
    private final IamportAPI iamportAPI = new IamportAPI();

    // 인증 토큰 가져오기
    public String getAuthToken() {
        return iamportAPI.getAuthToken();
    }

    // 결제 준비 요청
    public String preparePayment(String authToken, String merchantUid, int amount) {
        return iamportAPI.preparePayment(authToken, merchantUid, amount);
    }

    // 결제 상태 확인 (추가)
    public String getPaymentStatus(String authToken, String merchantUid) {
        // IamportAPI에 결제 상태 조회 메서드를 추가해 호출
        return iamportAPI.getPaymentStatus(authToken, merchantUid);
    }
}
