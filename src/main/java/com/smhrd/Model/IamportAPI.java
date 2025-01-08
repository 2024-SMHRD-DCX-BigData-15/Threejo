package com.smhrd.Model;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public class IamportAPI {
    private static final String API_URL = "https://api.iamport.kr";
    private static final String API_KEY = "3345716251105812"; // 아임포트 API Key
    private static final String API_SECRET = "AIZ7eYFZBpLL4zcZP3B4ogeNEmciJFJLFL66Iqp1BDmStvC3A8gDaNpzzIRMx9YP0md6ycgsC00FKhEb"; // 아임포트 API Secret

    // 인증 토큰 발급
    public String getAuthToken() {
        String token = null;
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(API_URL + "/users/getToken");
            httpPost.setHeader("Content-Type", "application/json");

            Map<String, String> params = new HashMap<>();
            params.put("imp_key", API_KEY);
            params.put("imp_secret", API_SECRET);

            ObjectMapper objectMapper = new ObjectMapper();
            httpPost.setEntity(new StringEntity(objectMapper.writeValueAsString(params)));

            String response = EntityUtils.toString(httpClient.execute(httpPost).getEntity(), "UTF-8");
            Map<String, Object> responseMap = objectMapper.readValue(response, Map.class);

            if (responseMap.containsKey("response")) {
                token = (String) ((Map<String, Object>) responseMap.get("response")).get("access_token");
            } else {
                throw new RuntimeException("Failed to retrieve auth token: " + response);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error during auth token retrieval", e);
        }
        return token;
    }
}
