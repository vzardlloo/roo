package com.roo.model.entity;

import com.blade.jdbc.annotation.Table;
import com.blade.jdbc.core.ActiveRecord;
import lombok.Data;

import java.util.Date;

/**
 * 通知
 *
 * @author biezhi
 * @date 2017/8/1
 */
@Table(value = "roo_notice")
@Data
public class Notice extends ActiveRecord {
    private Long    id;
    private String  title;
    private String  toUser;
    private String  fromUser;
    private String  event;
    private Integer state;
    private Date    created;
    private Date    updated;

}
