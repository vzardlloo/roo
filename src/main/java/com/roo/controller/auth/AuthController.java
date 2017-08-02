package com.roo.controller.auth;

import com.blade.mvc.annotation.GetRoute;
import com.blade.mvc.annotation.Path;

/**
 * 认证控制器
 *
 * @author biezhi
 * @date 2017/7/31
 */
@Path
public class AuthController {

    @GetRoute("signin")
    public String signin(){
        return "auth/signin";
    }

    @GetRoute("signup")
    public String signup(){
        return "auth/signup";
    }

}
