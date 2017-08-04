package com.roo.model.entity;

import com.blade.jdbc.annotation.Table;
import com.blade.jdbc.core.ActiveRecord;
import lombok.Data;

/**
 * 个人详细信息
 *
 * @author biezhi
 * @date 2017/8/2
 */
@Data
@Table(value = "roo_profile", pk = "uid")
public class Profile extends ActiveRecord {

    private Long   uid;
    private String location;
    private String website;
    private String github;
    private String weibo;
    private String signature;

}
