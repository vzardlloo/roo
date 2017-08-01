package com.roo.ext;

import com.blade.kit.StringKit;
import com.roo.config.RooConst;

/**
 * 模板函数
 *
 * @author biezhi
 * @date 2017/7/31
 */
public class TplFunctions {

    public static String siteUrl() {
        return siteUrl("");
    }

    public static String siteUrl(String sub) {
        if(StringKit.isBlank(sub)){
            return RooConst.settings.get("site_url");
        }
        String url = RooConst.settings.get("site_url") + "/" + sub;
        url = url.replaceAll("[\\/\\/]", "/");
        return url;
    }
}