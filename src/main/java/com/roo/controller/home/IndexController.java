package com.roo.controller.home;

import com.blade.mvc.annotation.GetRoute;
import com.blade.mvc.annotation.Path;

/**
 * @author biezhi
 * @date 2017/8/1
 */
@Path
public class IndexController {

    @GetRoute
    public String index(){
        return "index";
    }

}
