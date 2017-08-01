package com.roo.config;

import com.roo.model.entity.Setting;
import jetbrick.template.JetGlobalContext;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Roo常量
 *
 * @author biezhi
 * @date 2017/7/31
 */
public class RooConst {

    public static JetGlobalContext context;

    public static Map<String, String> settings;

    public static void refreshSysInfo(List<Setting> settings) {
        Map<String, String> map = settings.stream()
                .collect(Collectors.toMap(x -> x.getSkey(), y -> y.getSvalue()));
        RooConst.settings = map;
        context.set("settings", map);
    }

}
