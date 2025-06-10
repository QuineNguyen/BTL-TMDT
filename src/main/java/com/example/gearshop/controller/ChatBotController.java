package com.example.gearshop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/chatbot")
public class ChatBotController {

    private static final String API_KEY = "AIzaSyAHRP3IV0poPc5YJ0dqqeOoyIOXdID5NAM";
    private static final String API_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key=" + API_KEY;

    @PostMapping("/sendMessage")
    public ResponseEntity<?> sendMessage(@RequestBody Map<String, String> input) {
        if (!input.containsKey("message") || input.get("message").trim().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Invalid input"));
        }

        String userMessage = input.get("message").trim();

        Map<String, Object> requestData = new HashMap<>();
        requestData.put("contents", List.of(
            Map.of("parts", List.of(Map.of("text", userMessage)))
        ));

        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(API_URL, requestData, Map.class);
            if (response.getStatusCode().value() != 200) {
                return ResponseEntity.status(response.getStatusCode().value()).body(Map.of("error", "Google Gemini API error"));
            }

            Map<String, Object> responseBody = response.getBody();
            if (responseBody == null || !responseBody.containsKey("candidates")) {
                return ResponseEntity.badRequest().body(Map.of("error", "Unexpected API response format"));
            }

            // Sử dụng List thay vì ép kiểu thành mảng
            List<Map<String, Object>> candidates = (List<Map<String, Object>>) responseBody.get("candidates");
            Map<String, Object> candidate = candidates.get(0);
            Map<String, Object> content = (Map<String, Object>) candidate.get("content");
            List<Map<String, Object>> parts = (List<Map<String, Object>>) content.get("parts");
            String aiResponse = parts.get(0).get("text").toString().trim();

            return ResponseEntity.ok(Map.of("response", aiResponse));
        } catch (Exception e) {
            e.printStackTrace(); // In lỗi chi tiết ra console
            return ResponseEntity.status(500).body(Map.of("error", "Failed to fetch response from API"));
        }
    }
}
