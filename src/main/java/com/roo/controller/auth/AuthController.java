package com.roo.controller.auth;

import com.blade.ioc.annotation.Inject;
import com.blade.mvc.annotation.*;
import com.blade.mvc.http.Request;
import com.blade.mvc.http.Response;
import com.blade.mvc.ui.RestResponse;
import com.blade.patchca.DefaultPatchca;
import com.blade.patchca.Patchca;
import com.blade.validator.annotation.Valid;
import com.roo.model.param.SignupParam;
import com.roo.service.AccountService;
import lombok.extern.slf4j.Slf4j;

/**
 * 认证控制器
 *
 * @author biezhi
 * @date 2017/7/31
 */
@Path
@Slf4j
public class AuthController {

    @Inject
    private AccountService accountService;

    private Patchca patchca = new DefaultPatchca();

    @GetRoute("patchca")
    public void patchca(Request request, Response response) {
        try {
            String code = patchca.token(response);
            request.attribute("vcode", code);
        } catch (Exception e) {
            log.error("Create patchca fail", e);
        }
    }

    @GetRoute("signin")
    public String signin() {
        return "auth/signin";
    }

    @GetRoute("signup")
    public String signup() {
        return "auth/signup";
    }

    @PostRoute("signin")
    @JSON
    public RestResponse<Boolean> doSignin(String username, String password) {
        return accountService.login(username, password);
    }

    @PostRoute("signup")
    public void doSignup(@Valid SignupParam signupParam, Request request, Response response) {
        RestResponse restResponse = accountService.register(signupParam);
        if(restResponse.isSuccess()){
            request.attribute("email", signupParam.getEmail());
            response.render("auth/signup_success");
        } else {
            response.json(response);
        }
    }

    /**
     * 激活页面
     *
     * @param code
     * @return
     */
    @GetRoute("active")
    public String active(@Param String code) {
        return "auth/active";
    }

}
