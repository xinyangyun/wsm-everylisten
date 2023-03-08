package com.example.wsmeverylisten.common.wsmjson;

public class JsonUtil {

    public static String ToJson(Province province){
        //"{"+"id"+":1,"+"name"+":"+"666"+","+"jianCheng"+":"+"777"+","+"shengHui"+":"+"888"+"}";

        //Gson gson = new Gson();
        //String jso = gson.toJson(province);
        //System.out.println(jso);

        String id = String.valueOf(province.getId());
        String name = province.getName();
        String jianCheng = province.getJianCheng();
        String shengHui = province.getShengHui();

        String json = "{"+"\"id\":"+id+",\"name\""+":\""+name+"\","+"\"jianCheng\""+":\""+jianCheng+"\","+"\"shengHui\""+":\""+shengHui+"\"}";
        return json;
    }

    public static Province ToProvince(String json) {
        Province province = new Province();




        return province;
    }



    public static void main(String[] args) {
        Province province = new Province("1","陕西","秦","西安");
        System.out.println(ToJson(province));
    }

}
