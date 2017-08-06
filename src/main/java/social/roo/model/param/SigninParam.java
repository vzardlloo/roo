package social.roo.model.param;

import com.blade.validator.annotation.NotEmpty;
import lombok.Data;

/**
 * 登录参数
 *
 * @author biezhi
 * @date 2017/8/4
 */
@Data
public class SigninParam {

    @NotEmpty(message = "请输入用户名")
    private String username;

    @NotEmpty(message = "请输入密码")
    private String password;

    private String remeberMe;

}
