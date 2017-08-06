package social.roo.model.entity;

import com.blade.jdbc.annotation.Table;
import com.blade.jdbc.core.ActiveRecord;
import lombok.Data;

import java.util.Date;

/**
 * 用户
 *
 * @author biezhi
 * @date 2017/8/1
 */
@Table(value = "roo_user", pk = "uid")
@Data
public class User extends ActiveRecord {

    private Long    uid;
    private String  username;
    private String  password;
    private String  email;
    private String  avatar;
    private String  role;
    private Date    created;
    private Date    updated;
    private Date    logined;
    private Integer state;

}