package social.roo.model.dto;

import lombok.Data;
import social.roo.model.entity.Node;

import java.util.List;

/**
 * @author biezhi
 * @date 2017/8/6
 */
@Data
public class NodeDto {

    private String title;
    private String slug;
    private List<Node> children;

}
