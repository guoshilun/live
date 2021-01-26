package com.jk.jkproject.net;

import android.text.TextUtils;

import com.jk.jkproject.utils.GsonUtils;


/**
 * Created by zhaotun on 15/11/3.
 */
public class ResponseFactory {


    private ResponseFactory() {

    }


    public static Object handleResponse(String json, Class<?> cls) {
        if (TextUtils.isEmpty(json)) {
            return null;
        }

        if (cls == null) {
            return json;
        }

        if (String.class.equals(cls)) {
            return json;
        }

        return GsonUtils.get().fromJson(json, cls);
    }

}
