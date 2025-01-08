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
	private static final String API_SECRET = "AIZ7eYFZBpLL4zcZP3B4ogeNEmciJFJLFL66Iqp1BDmStvC3A8gDaNpzzIRMx9YP0md6ycgsC00FKhEb"; // 아임포트
																																	// API
																																	// Secret

	public String getAuthToken() {
		String token = null;
		try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
			HttpPost httpPost = new HttpPost(API_URL + "/users/getToken");
			httpPost.setHeader("Content-Type", "application/json");

			Map<String, String> params = new HashMap<>();
			params.put("imp_key", API_KEY);
			params.put("imp_secret", API_SECRET);

			ObjectMapper objectMapper = new ObjectMapper();
			String jsonParams = objectMapper.writeValueAsString(params);

			httpPost.setEntity(new StringEntity(jsonParams));
			String response = EntityUtils.toString(httpClient.execute(httpPost).getEntity(), "UTF-8");

			Map<String, Object> responseMap = objectMapper.readValue(response, Map.class);
			Map<String, Object> responseData = (Map<String, Object>) responseMap.get("response");
			token = (String) responseData.get("access_token");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return token;
	}

	public String preparePayment(String authToken, String merchantUid, int amount) {
		String result = null;
		try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
			HttpPost httpPost = new HttpPost(API_URL + "/payments/prepare");
			httpPost.setHeader("Content-Type", "application/json");
			httpPost.setHeader("Authorization", "Bearer " + authToken);

			Map<String, Object> params = new HashMap<>();
			params.put("merchant_uid", merchantUid);
			params.put("amount", amount);

			ObjectMapper objectMapper = new ObjectMapper();
			String jsonParams = objectMapper.writeValueAsString(params);

			httpPost.setEntity(new StringEntity(jsonParams));
			result = EntityUtils.toString(httpClient.execute(httpPost).getEntity(), "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public String getPaymentStatus(String authToken, String merchantUid) {
		String result = null;
		try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
			HttpPost httpPost = new HttpPost(API_URL + "/payments/find/" + merchantUid);
			httpPost.setHeader("Authorization", "Bearer " + authToken);

			String response = EntityUtils.toString(httpClient.execute(httpPost).getEntity(), "UTF-8");
			result = response;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
