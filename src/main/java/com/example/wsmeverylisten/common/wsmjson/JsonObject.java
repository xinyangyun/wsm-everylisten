package com.example.wsmeverylisten.common.wsmjson;

public interface JsonObject {

    String transString(Object...obj);

    Object transObj(String json, Class<?> clazz);

}
