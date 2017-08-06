package social.roo.model.dto;

import com.blade.jdbc.core.ActiveRecord;
import lombok.Data;

import java.util.Date;

/**
 * 评论DTO
 *
 * @author biezhi
 * @date 2017/8/2
 */
@Data
public class CommentDto extends ActiveRecord {

    private Long   coid;
    private String tid;
    private String author;
    private String avatar;
    private String content;
    private Date   created;

}
