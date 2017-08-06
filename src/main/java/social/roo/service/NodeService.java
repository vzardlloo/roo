package social.roo.service;

import social.roo.model.entity.Node;

/**
 * 节点
 *
 * @author biezhi
 * @date 2017/8/3
 */
public class NodeService {

    public Node getNode(String slug){
        return new Node().where("slug", slug).find();
    }
}