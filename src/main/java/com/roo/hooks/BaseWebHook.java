package com.roo.hooks;

import com.blade.ioc.annotation.Bean;
import com.blade.mvc.hook.Signature;
import com.blade.mvc.hook.WebHook;
import com.blade.mvc.http.Request;
import lombok.extern.slf4j.Slf4j;

/**
 * @author biezhi
 * @date 2017/7/31
 */
@Slf4j
@Bean
public class BaseWebHook implements WebHook {

    @Override
    public boolean before(Signature signature) {
        Request  request  = signature.request();
        log.info("{}\t{}", request.method(), request.uri());
        return true;
    }

}
