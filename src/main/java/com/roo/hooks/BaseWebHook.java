package com.roo.hooks;

import com.blade.ioc.annotation.Bean;
import com.blade.mvc.hook.Signature;
import com.blade.mvc.hook.WebHook;
import com.blade.mvc.http.Request;
import com.blade.mvc.http.Response;
import com.roo.config.RooConst;
import lombok.extern.slf4j.Slf4j;

import java.util.Locale;
import java.util.Optional;

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
        Response response = signature.response();
        log.info("Request url: {}", request.uri());

        Optional<String> localeOptional = request.cookie(RooConst.COOKIE_KEY_LOCALE);
        String locale = localeOptional.orElse(Locale.getDefault().toString());
        response.cookie(RooConst.COOKIE_KEY_LOCALE, locale);
        request.attribute(RooConst.USER_KEY_LOCALE, locale);
        return true;
    }

}
