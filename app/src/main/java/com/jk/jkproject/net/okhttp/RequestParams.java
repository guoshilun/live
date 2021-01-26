package com.jk.jkproject.net.okhttp;

import android.text.TextUtils;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.RequestBody;

public class RequestParams {
    private HashMap<String, String> params;

    private RequestParams() {
        if (this.params == null)
            this.params = new HashMap<String, String>();
    }

    private RequestParams(RequestParams paramRequestParams) {
        this.params = (HashMap<String, String>) paramRequestParams.getParams().clone();
    }

    public static RequestParams get() {
        return Init.p;
    }

    private String valueToString(String paramString) {
        return (paramString == null) ? "" : paramString;
    }

    public void clear() {
        this.params.clear();
    }

    public RequestParams clone() {
        return new RequestParams(Init.p);
    }

    public String get(String paramString) {
        try {
            return this.params.get(paramString);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return "";
        }
    }

    public HashMap<String, String> getParams() {
        return this.params;
    }

    public void put(String paramString, Object paramObject) {
        this.params.put(paramString, paramObject.toString());
    }

    public void put(String paramString1, String paramString2) {
        this.params.put(paramString1, paramString2);
    }

    public String remove(String paramString) {
        return this.params.remove(paramString);
    }

    public String toQueryString(String paramString) {
        String str1 = toString();
        String str2 = paramString;
        if (!TextUtils.isEmpty(str1)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(paramString);
            if (paramString.contains("?")) {
                paramString = "&";
            } else {
                paramString = "?";
            }
            stringBuilder.append(paramString);
            paramString = stringBuilder.toString();
            stringBuilder = new StringBuilder();
            stringBuilder.append(paramString);
            stringBuilder.append(str1);
            str2 = stringBuilder.toString();
        }
        return str2;
    }

    public RequestBody toRequestBody() {
        FormBody.Builder builder = new FormBody.Builder();
        /* monitor enter TypeReferenceDotClassExpression{ObjectType{com/jk/jkproject/net/okhttp/RequestParams}} */
        try {
            for (Map.Entry<String, String> entry : this.params.entrySet())
                builder.addEncoded((String) entry.getKey(), URLEncoder.encode(valueToString((String) entry.getValue()), "UTF-8"));
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
        }
        /* monitor exit TypeReferenceDotClassExpression{ObjectType{com/jk/jkproject/net/okhttp/RequestParams}} */
        return (RequestBody) builder.build();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, String> entry : this.params.entrySet()) {
            if (stringBuilder.length() > 0)
                stringBuilder.append("&");
            stringBuilder.append((String) entry.getKey());
            stringBuilder.append("=");
            stringBuilder.append((String) entry.getValue());
        }
        return stringBuilder.toString();
    }

    private static class Init {
        private static RequestParams p = new RequestParams();
    }
}
