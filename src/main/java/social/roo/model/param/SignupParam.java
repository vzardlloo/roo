package social.roo.model.param;

import com.blade.validator.annotation.Email;
import com.blade.validator.annotation.NotEmpty;
import lombok.Data;

/**
 * 注册参数
 *
 * @author biezhi
 * @date 2017/8/4
 */
@Data
public class SignupParam {

    @NotEmpty(message = "请输入用户名")
    private String username;

    @NotEmpty(message = "请输入邮箱")
    @Email(message = "输入正确的邮箱格式")
    private String email;

    @NotEmpty(message = "请输入密码")
    private String password;

    @NotEmpty(message = "请输入确认密码")
    private String repassword;

//    @NotEmpty(message = "请输入验证码")
    private String vcode;

    private String link;

}
