package com.roo.service;

import com.blade.ioc.annotation.Bean;
import com.blade.kit.EncrypKit;
import com.blade.kit.UUID;
import com.blade.mvc.WebContext;
import com.blade.mvc.ui.RestResponse;
import com.roo.config.RooConst;
import com.roo.model.entity.Actived;
import com.roo.model.entity.Profile;
import com.roo.model.entity.User;
import com.roo.model.param.SignupParam;
import com.roo.utils.EmailUtils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * 账户相关Service
 * <p>
 * 登录、注册、激活、修改密码、忘记密码、找回密码
 *
 * @author biezhi
 * @date 2017/8/2
 */
@Bean
public class AccountService {

    public RestResponse<Boolean> register(SignupParam signupParam) {
        String vcode = WebContext.request().session().attribute("vcode");
//        if (!signupParam.getVcode().equals(vcode)) {
//            return RestResponse.fail("验证码输入错误");
//        }
        if (!signupParam.getPassword().equals(signupParam.getRepassword())) {
            return RestResponse.fail("密码输入不一致");
        }
        long count = new User().where("username", signupParam.getUsername())
                .and("state", 1).count();
        if (count > 0) {
            return RestResponse.fail("用户名已存在");
        }
        count = new User().where("email", signupParam.getUsername())
                .and("state", 1).count();
        if (count > 0) {
            return RestResponse.fail("邮箱已被注册");
        }

        String pwd = EncrypKit.md5(signupParam.getUsername() + signupParam.getPassword());

        LocalDateTime now = LocalDateTime.now();

        User user = new User();
        user.setUsername(signupParam.getUsername());
        user.setPassword(pwd);
        user.setEmail(signupParam.getEmail());
        user.setState(0);
        user.setCreated(Date.from(now.atZone(ZoneId.systemDefault()).toInstant()));
        user.setUpdated(user.getCreated());
        user.setRole("member");
        Long uid = user.save();

        String code       = UUID.UU64();
        String activeLink = RooConst.settings.get("site_url") + "/active?code=" + code;

        signupParam.setLink(activeLink);

        Actived actived = new Actived();
        actived.setCode(code);
        actived.setUid(uid);
        actived.setEmail(signupParam.getEmail());
        actived.setCreated(Date.from(now.atZone(ZoneId.systemDefault()).toInstant()));
        // 有效期2小时
        actived.setExpired(Date.from(now.plusHours(2).atZone(ZoneId.systemDefault()).toInstant()));
        actived.setState(0);
        actived.save();

        // 发送注册邮件
        EmailUtils.sendRegister(signupParam);
        return RestResponse.ok();
    }

    public RestResponse<Boolean> login(String username, String password) {
        return RestResponse.ok();
    }

    public void active() {

    }

}
