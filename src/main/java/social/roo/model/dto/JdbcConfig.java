package social.roo.model.dto;

import com.blade.validator.annotation.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Jdbc配置
 *
 * @author biezhi
 * @date 2017/8/1
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JdbcConfig {

    @NotEmpty(message = "数据库主机不能为空")
    private String host;

    @NotEmpty(message = "数据库端口不能为空")
    private String port;

    @NotEmpty(message = "数据库名不能为空")
    private String database;

    @NotEmpty(message = "数据库用户不能为空")
    private String username;

    @NotEmpty(message = "数据库密码不能为空")
    private String password;

    private String url;
    private String driver;

    @Builder.Default
    private int initialSize                   = 2;
    @Builder.Default
    private int minIdle                       = 2;
    @Builder.Default
    private int maxActive                     = 30;
    @Builder.Default
    private int maxWait                       = 5000;
    @Builder.Default
    private int minEvictableIdleTimeMillis    = 30_000;
    @Builder.Default
    private int timeBetweenEvictionRunsMillis = 60_000;

    public String getUrl() {
        return "jdbc:mysql://" + host + ":" + port + "/" + database;
    }

}