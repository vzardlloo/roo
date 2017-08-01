package com.roo.model;

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

    private String host;
    private String port;
    private String database;
    private String url;
    private String driver;
    private String username;
    private String password;

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