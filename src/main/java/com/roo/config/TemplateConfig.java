package com.roo.config;

import com.blade.Blade;
import com.blade.event.BeanProcessor;
import com.blade.ioc.annotation.Bean;
import com.blade.ioc.annotation.Order;
import com.blade.mvc.view.template.JetbrickTemplateEngine;
import com.roo.ext.TplFunctions;
import com.roo.model.Auth;
import jetbrick.template.JetGlobalContext;
import jetbrick.template.resolver.GlobalResolver;

/**
 * 模板引擎、数据库配置加载
 *
 * @author biezhi
 * @date 2017/7/31
 */
@Order(1)
@Bean
public class TemplateConfig implements BeanProcessor {

    @Override
    public void processor(Blade blade) {
        JetbrickTemplateEngine templateEngine = new JetbrickTemplateEngine();
        GlobalResolver         resolver       = templateEngine.getGlobalResolver();
        resolver.registerFunctions(TplFunctions.class);
        resolver.registerMethods(Auth.class);

        JetGlobalContext context = templateEngine.getGlobalContext();
        context.set("version", blade.environment().get("app.version", "v0.0.1"));

        blade.templateEngine(templateEngine);
    }

}
