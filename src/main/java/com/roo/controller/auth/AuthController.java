package com.roo.controller.auth;

import com.blade.mvc.annotation.GetRoute;
import com.blade.mvc.annotation.Path;
import com.blade.mvc.annotation.PostRoute;
import com.blade.mvc.ui.RestResponse;

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

    @PostRoute("signin")
    public RestResponse<Boolean> doSignin(){
        return RestResponse.ok();
    }

    @PostRoute("signup")
    public RestResponse<Boolean> doSignup(){
        return RestResponse.ok();
    }

}
