package com.roo.config;

import com.blade.Blade;
import com.blade.Environment;
import com.blade.event.BeanProcessor;
import com.blade.ioc.annotation.Bean;
import io.github.biezhi.ome.OhMyEmail;

import static io.github.biezhi.ome.OhMyEmail.SMTP_QQ;

/**
 * 邮箱配置
 *
 * @author biezhi
 * @date 2017/8/3
 */
@Bean
public class EmailConfig implements BeanProcessor {

    @Override
    public void processor(Blade blade) {
        Environment environment = blade.environment();
        if (environment.hasKey("email.username")
                && environment.hasKey("email.password")) {

            OhMyEmail.config(SMTP_QQ(false),
                    environment.get("email.username").get(),
                    environment.get("email.password").get());

            // 测试发送
            try {
                OhMyEmail.subject("这是一封测试TEXT邮件")
                        .from("王爵的QQ邮箱")
                        .to("921293209@qq.com")
                        .html("<h1>信件内容</h1>")
                        .send();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}
