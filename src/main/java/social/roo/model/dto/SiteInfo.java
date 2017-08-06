package social.roo.model.dto;

import lombok.Data;

/**
 * 站点信息
 *
 * @author biezhi
 * @date 2017/8/1
 */
@Data
public class SiteInfo {

    private String siteUrl;
    private String siteTitle;
    private String siteKeyWords;
    private String siteDescription;
    private String adminUser;
    private String adminPass;
    private String adminEmail;

}
