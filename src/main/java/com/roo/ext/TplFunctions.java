package com.roo.ext;

import com.blade.mvc.WebContext;
import com.roo.config.RooConst;

import java.util.Locale;
import java.util.Optional;

/**
 * 模板函数
 *
 * @author biezhi
 * @date 2017/7/31
 */
public class TplFunctions {

    public static String lang(String key) {
        Optional<String> locale = WebContext.request().cookie(RooConst.COOKIE_KEY_LOCALE);
        return lang(locale.orElse(Locale.getDefault().toString()));
    }

    public static String lang(String locale, String key) {
        return RooConst.langs.get(locale).getOrDefault(key, "默认值");
    }

    public static String route(String sub) {
        return route(sub, "");
    }

    public static String route(String sub, String append) {
        return "/" + sub + "/" + append;
    }

    public static String url(String sub) {
        return "/" + sub;
    }

    public static String setActive(String url) {
        return setActive(url, "");
    }

    public static String setActive(String url, String appendClass) {
        String uri = WebContext.request().uri();
        if (uri.equals(url)) {
            if (null != appendClass) {
                return "class=\"active " + appendClass + "\"";
            }
            return "class=\"active \"";
        }
        return "";
    }

}