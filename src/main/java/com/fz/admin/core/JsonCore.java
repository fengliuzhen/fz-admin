package com.fz.admin.core;

import com.google.gson.Gson;

public class JsonCore {

    private static Gson gson=new Gson();

    public static String toJson(Object object)
    {
        return  gson.toJson(object);
    }
    public static <T> T fromJson(String strJson, Class<T> tClass)
    {
        //Type objectType = new TypeToken<Class<T>>(){}.getType();
        return gson.fromJson(strJson,tClass);
    }
}
