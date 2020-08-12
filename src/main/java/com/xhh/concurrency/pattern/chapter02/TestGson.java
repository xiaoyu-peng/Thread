package com.xhh.concurrency.pattern.chapter02;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 处理 Gson格式化字符串，整形数字出现科学计数法
 */
public class TestGson {

    private static final String R_EXP = "(?<=id\":).*?(?=(,|$))";

    public static void main(String[] args) {

        // 获取客户自定义信息
        String extraJson = "{\"customer\":{\"id\":2469899890,\"province\":\"北京\",\"city\":\"北京\",\"platform\":\"web\"}}";


        // 正则表达式格式化
        Matcher matcher = Pattern.compile(R_EXP).matcher(extraJson);
        while(matcher.find()){
            extraJson = matcher.replaceAll("\"" + matcher.group() + "\"");
        }

        // 获取客户自定义信息
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String,Object>>(){}.getType();
        Map<String,Object> extraInfoMap = gson.fromJson(extraJson, type);
        Object customerObj = extraInfoMap.get("customer");
        if(null==customerObj || "".equals(customerObj)){
            return;
        }
        Map<String,Object> customer = (Map<String, Object>) extraInfoMap.get("customer");
        System.out.println(customer);

    }
}
