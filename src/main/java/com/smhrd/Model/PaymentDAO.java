package com.smhrd.Model;

public class PaymentDAO {
    private final IamportAPI iamportAPI = new IamportAPI();

    public String getAuthToken() {
        return iamportAPI.getAuthToken();
    }

    // 결제 준비 요청
    public String preparePayment(String authToken, String merchantUid, int amount) {
        // IamportAPI의 preparePayment 호출 (추가 구현 필요)
        return "ready"; // 테스트용 반환값
    }

    // 결제 상태 조회
    public String getPaymentStatus(String authToken, String merchantUid) {
        // IamportAPI의 getPaymentStatus 호출 (추가 구현 필요)
        return "paid"; // 테스트용 반환값
    }
}
