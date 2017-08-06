package social.roo.ext;

import com.blade.kit.StringKit;
import social.roo.Roo;

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
        if (StringKit.isBlank(sub)) {
            return Roo.me().getSetting("site_url");
        }
        String url = Roo.me().getSetting("site_url") + "/" + sub;
        return url;
    }
}