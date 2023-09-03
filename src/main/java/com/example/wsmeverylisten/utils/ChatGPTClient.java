package com.example.wsmeverylisten.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ChatGPTClient {
    public static void main(String[] args) {
        try {
            // 设置 API 地址
            String apiUrl = "https://api.openai.com/v1/chat/completions";

            // 设置访问令牌
            String apiKey = "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";

            // 构建请求参数
            String input = "中国第一个皇帝是谁";
            String model = "gpt-3.5-turbo";
            int maxTokens = 50;

            String jsonInput = "{\"model\": \"" + model + "\", \"messages\": [{\"role\": \"system\", \"content\": \"You are a helpful assistant.\"}, {\"role\": \"user\", \"content\": \"" + input + "\"}], \"max_tokens\": " + maxTokens + "}";

            // 创建连接
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Bearer " + apiKey);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            // 发送请求
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(jsonInput.getBytes());
            outputStream.flush();
            outputStream.close();

            // 处理响应
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                // 解析响应
                String jsonResponse = response.toString();
                System.out.println(jsonResponse);
                // 在这里处理 ChatGPT 的响应数据
            } else {
                System.out.println("Request failed with response code: " + responseCode);
            }

            // 断开连接
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
