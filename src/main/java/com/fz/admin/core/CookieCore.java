package com.fz.admin.core;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class CookieCore {
    /**
     * 添加cookie
     *
     * @param response
     * @param name Key
     * @param value Value
     * @param maxAge 有效时间
     */
    public static void set(HttpServletResponse response, String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        //cookie.setDomain(".jxwmanage.com"); // cookie作用域
        response.addCookie(cookie);
    }

    /**
     * 检索所有Cookie封装到Map集合
     *
     * @param request
     * @return
     */
    public static Map<String, String> getList(HttpServletRequest request) {
        Map<String, String> cookieMap = new HashMap<String, String>();
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(), cookie.getValue());
            }
        }
        return cookieMap;
    }

    /**
     * 通过Key获取Value
     *
     * @param request
     * @param name Key
     * @return Value
     */
    public static String get(HttpServletRequest request, String name) {
        Map<String, String> cookieMap = getList(request);
        if (cookieMap.containsKey(name)) {
            String cookieValue = (String) cookieMap.get(name);
            return cookieValue;
        } else {
            return null;
        }
    }
    /**
     * 初始化Cookie
     * @param request
     * @param response
     * @return
     */
    public static String initLoginCookie(HttpServletRequest request, HttpServletResponse response)
    {
        String tmpSessionId=get(request, SystemEnum.CookieName.getValue());
        if(tmpSessionId==null||tmpSessionId.isEmpty())
        {
            tmpSessionId=request.getSession().getId();
            //如果未获取到cookie的值 则重新赋值 2个小时过期
            set(response, SystemEnum.CookieName.getValue(), tmpSessionId,60*60*2);
        }
        return tmpSessionId;
    }
}
