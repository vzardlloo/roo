package social.roo.model.param;

import com.blade.jdbc.page.PageRow;
import lombok.Builder;
import lombok.Data;

/**
 * @author biezhi
 * @date 2017/8/1
 */
@Data
public class PageParam {

    @Builder.Default
    private int page  = 1;
    @Builder.Default
    private int limit = 10;

    public PageRow getPageRow() {
        return new PageRow(page, limit);
    }

}
