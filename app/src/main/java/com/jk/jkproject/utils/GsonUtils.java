package com.jk.jkproject.utils;

import com.anbetter.log.MLog;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import java.lang.reflect.Type;

/**
 * Created by zhaotun on 15/11/3.
 */
public class GsonUtils {

    private volatile static GsonUtils instance;

    private Gson gson;

    private GsonUtils() {
        gson = new Gson();
    }

    public static GsonUtils get() {
        if (instance == null) {
            synchronized (GsonUtils.class) {
                if (instance == null) {
                    instance = new GsonUtils();
                }
            }
        }

        return instance;
    }

    public Gson getGson() {
        if (gson == null) {
            gson = new Gson();
        }

        return gson;
    }

    /**
     * Parse to Class T from Json String
     *
     * @param json     json string
     * @param classOfT the class that need to be parsed
     * @return the class that parsed from json
     */
    public <T> T fromJson(String json, Class<T> classOfT) {
        try {
            if (String.class.equals(classOfT)) {
                return (T) json;
            } else {
                return getGson().fromJson(json, classOfT);
            }
        } catch (Exception e) {
            MLog.e("parse json error:" + e.getMessage());
            MLog.e("parse json error json" + json+ classOfT.getName());
            return null;
        }
    }

    /**
     * Format the Json String to make it more clear
     *
     * @param unformattedJsonStr the unformatted Json String
     * @return the formatted Json String
     * @throws JsonSyntaxException
     */
    public String format(String unformattedJsonStr) throws JsonSyntaxException {
        return new GsonBuilder().setPrettyPrinting().create().toJson(new JsonParser().parse(unformattedJsonStr));
    }


    public String toJson(Object src) {
        try {
            return getGson().toJson(src);
        } catch (Exception e) {
            return null;
        }
    }

    public String toJson(Object src, Type typeOfSrc) {
        try {
            return getGson().toJson(src, typeOfSrc);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 将json转换成bean对象
     *
     * @param jsonStr
     * @return
     */
    public <T> T jsonToBean(String jsonStr, Class<T> cl) {
        T obj = null;
        if (gson != null) {
            obj = gson.fromJson(jsonStr, cl);
        }
        return obj;
    }
}
