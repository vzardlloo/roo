package com.roo.controller.auth;

import com.blade.mvc.annotation.Path;

/**
 * 认证控制器
 *
 * @author biezhi
 * @date 2017/7/31
 */
@Path("auth")
public class AuthController {

    public String login(){
        return "login";
    }

}
