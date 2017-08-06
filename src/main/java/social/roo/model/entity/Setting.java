package social.roo.model.entity;

import com.blade.jdbc.annotation.Table;
import com.blade.jdbc.core.ActiveRecord;
import lombok.Data;

/**
 * 系统配置
 *
 * @author biezhi
 * @date 2017/8/1
 */
@Table(value = "roo_setting", pk = "skey")
@Data
public class Setting extends ActiveRecord{

    private String  skey;
    private String  svalue;
    private Integer state;

}
