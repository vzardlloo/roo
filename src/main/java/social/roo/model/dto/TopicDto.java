package social.roo.model.dto;

import com.blade.jdbc.core.ActiveRecord;
import lombok.Data;

import java.util.Date;

/**
 * @author biezhi
 * @date 2017/8/1
 */
@Data
public class TopicDto extends ActiveRecord {

    private String  tid;
    private String  title;
    private String  username;
    private String  avatar;
    private String  nodeSlug;
    private String  nodeTitle;
    private String  replyUser;
    private Integer comments;
    private Date    created;
    private Date    replyed;
    private Long    replyId;

}
