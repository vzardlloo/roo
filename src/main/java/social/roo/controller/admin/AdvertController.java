package social.roo.controller.admin;

import com.blade.mvc.annotation.GetRoute;
import com.blade.mvc.annotation.Path;
import com.blade.mvc.annotation.PostRoute;
import com.blade.mvc.http.Request;
import com.blade.mvc.ui.RestResponse;
import com.blade.validator.annotation.Valid;
import social.roo.model.entity.Advert;

import java.util.List;

/**
 * 侧边栏广告管理
 *
 * @author biezhi
 * @date 2017/8/3
 */
@Path("admin/advert")
public class AdvertController {

    @GetRoute
    public String advert(Request request) {
        List<Advert> adverts = new Advert().where("state", 1).findAll();
        request.attribute("adverts", adverts);
        return "admin/advert/index";
    }

    @PostRoute("save")
    public RestResponse<Boolean> saveAdvert(@Valid Advert advert) {
        advert.save();
        return RestResponse.ok();
    }

    @PostRoute("update")
    public RestResponse<Boolean> updateAdvert(Advert advert) {
        advert.update();
        return RestResponse.ok();
    }

}
