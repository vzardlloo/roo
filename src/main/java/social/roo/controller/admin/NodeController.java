package social.roo.controller.admin;

import com.blade.mvc.annotation.GetRoute;
import com.blade.mvc.annotation.Path;
import com.blade.mvc.annotation.PostRoute;
import com.blade.mvc.http.Request;
import com.blade.mvc.ui.RestResponse;
import com.blade.validator.annotation.Valid;
import social.roo.model.entity.Node;

import java.util.List;

/**
 * 节点管理
 *
 * @author biezhi
 * @date 2017/8/3
 */
@Path("admin/node")
public class NodeController {

    @GetRoute
    public String node(Request request) {
        List<Node> nodes = new Node().where("state", 1).findAll();
        request.attribute("nodes", nodes);
        return "admin/advert/index";
    }

    @PostRoute("save")
    public RestResponse<Boolean> saveNode(@Valid Node node) {
        node.save();
        return RestResponse.ok();
    }

    @PostRoute("update")
    public RestResponse<Boolean> updateNode(Node node) {
        node.update();
        return RestResponse.ok();
    }

}