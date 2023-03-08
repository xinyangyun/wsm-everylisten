package com.example.wsmeverylisten.entity;

import cn.hutool.json.JSONObject;

public class VideoInfo{  // 真实项目中不推荐直接使用`public`哦😯
    public String videoName;
    public JSONObject videoInfo;
    public String videoBaseUrl;
    public String audioBaseUrl;
    public String videoBaseRange;
    public String audioBaseRange;
    public String videoSize;
    public String audioSize;
}