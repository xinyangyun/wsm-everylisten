package com.example.wsmeverylisten.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class BaiduMap {
        public static void main(String[] args) {
                String address = "北京市海淀区西二旗";
                String city = "北京";
                String ak = "VrKin86v8BfntsYA6yNoWdlIVxpUcavu";

                try {
                        String url = "http://api.map.baidu.com/geocoding/v3/?address=" + address + "&city=" + city + "&output=json&ak=" + ak;
                        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
                        conn.setRequestMethod("GET");
                        conn.connect();

                        if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                                StringBuilder response = new StringBuilder();
                                String line;
                                while ((line = reader.readLine()) != null) {
                                        response.append(line);
                                }
                                reader.close();

                                System.out.println(response);
                                // 解析返回的 JSON 数据
                                // 这里根据实际的 JSON 结构解析经纬度信息
                                // 例如：{"status":0,"result":{"location":{"lng":116.31192,"lat":40.06030},"precise":1,"confidence":80,"level":"地产小区"}}
                        } else {
                                System.out.println("HTTP request failed with status: " + conn.getResponseCode());
                        }

                        conn.disconnect();
                } catch (Exception e) {
                        e.printStackTrace();
                }
        }
}

