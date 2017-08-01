package com.roo.config;

import com.blade.Blade;
import com.blade.Environment;
import com.blade.event.BeanProcessor;
import com.blade.ioc.annotation.Bean;
import com.blade.ioc.annotation.Order;

/**
 * 加载国际化文件
 *
 * @author biezhi
 * @date 2017/7/31
 */
@Order(3)
@Bean
public class LangConfig implements BeanProcessor {

    @Override
    public void processor(Blade blade) {
        Environment zhCn = Environment.of("classpath:/lang/zh-CN.properties");
        RooConst.langs.put("zh-CN", zhCn.toMap());
    }

}
