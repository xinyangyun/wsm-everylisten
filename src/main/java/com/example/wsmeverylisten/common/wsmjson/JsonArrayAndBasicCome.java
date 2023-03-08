package com.example.wsmeverylisten.common.wsmjson;

import java.lang.reflect.Array;

// 处理自定义类
public class JsonArrayAndBasicCome implements JsonArrayAndBasic {

    private final static String RIGHT = "[";
    private final static String LEFT = "]";
    private final String start = "\"";

    private JsonArrayAndBasicCome(){}

    /**
     * 单例模式
     * @return
     */
    public static JsonArrayAndBasicCome getJsonArrayCome(){
        return InitJsonArrayCome.jsonArrayCome;
    }

    private interface InitJsonArrayCome{
        JsonArrayAndBasicCome jsonArrayCome = new JsonArrayAndBasicCome();
    }

    public String before(String val, Object obj){
        Class<?> clazz = obj.getClass();
        String typeName = clazz.getTypeName();
        int index;
        if ((index = typeName.indexOf("[")) != -1){
            typeName.substring(0, index);
        }
        if (!typeName.endsWith("String")){
            return val;
        }
        return start + val + start;
    }


    @Override
    public void basicArrayToString(Object obj) {
        if (obj == null){
            return;
        }
        Class<?> clazz = obj.getClass();
        if (clazz.isArray()){
            int len = Array.getLength(obj);
            StringBuilder json = new StringBuilder()
                    .append(RIGHT);
            for (int i = 0; i < len; i++) {
                basicArrayToString(Array.get(obj,i));
                if (json.length() != 1 && !json.equals("") && i != len - 1){
                    json.append(",");
                }
            }
            json.append(LEFT);
            return;
        }
        if (obj == null || "".equals(obj.toString()) || obj.toString().length() == 0){
            return;
        }
        before(obj.toString(), obj);
    }
}

