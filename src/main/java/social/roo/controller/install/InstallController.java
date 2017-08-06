package social.roo.controller.install;

import com.blade.mvc.annotation.GetRoute;
import com.blade.mvc.annotation.JSON;
import com.blade.mvc.annotation.Path;
import com.blade.mvc.annotation.PostRoute;
import com.blade.mvc.http.Request;
import com.blade.mvc.ui.RestResponse;
import com.blade.validator.annotation.Valid;
import social.roo.config.DbConfig;
import social.roo.model.dto.JdbcConfig;
import social.roo.model.dto.SiteInfo;

import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author biezhi
 * @date 2017/8/1
 */
@Path("install")
public class InstallController {

    public static final String CLASSPATH = InstallController.class.getClassLoader().getResource("").getPath();

    /**
     * 安装页面
     *
     * @return
     */
    @GetRoute
    public String install(Request request) {
        String  lockPath = CLASSPATH + "install.lock";
        boolean exist    = Files.exists(Paths.get(lockPath));
        if (exist) {
            request.attribute("message", "已经安装过！");
        }
        return "install/index";
    }

    @PostRoute
    @JSON
    public RestResponse<Boolean> doInstall(@Valid JdbcConfig jdbcConfig, SiteInfo siteInfo) {

        return RestResponse.ok();
    }

    /**
     * 检查数据库是否可连接
     *
     * @param jdbcConfig
     * @return
     */
    @PostRoute("check")
    @JSON
    public RestResponse<Boolean> checkConn(@Valid JdbcConfig jdbcConfig) {
        return RestResponse.ok(DbConfig.checkConnect(jdbcConfig));
    }

}
