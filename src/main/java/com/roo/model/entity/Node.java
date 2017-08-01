package com.roo.model.entity;

import com.blade.jdbc.annotation.Table;
import com.blade.jdbc.core.ActiveRecord;
import lombok.Data;

/**
 * 节点
 *
 * @author biezhi
 * @date 2017/8/1
 */
@Table(value = "roo_node")
@Data
public class Node extends ActiveRecord {

    private Integer id;
    private String  slug;
    private String  title;
    private String  description;
}
