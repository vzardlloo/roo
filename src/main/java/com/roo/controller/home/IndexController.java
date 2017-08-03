package com.roo.controller.home;

import com.blade.ioc.annotation.Inject;
import com.blade.jdbc.page.Page;
import com.blade.mvc.annotation.GetRoute;
import com.blade.mvc.annotation.Path;
import com.blade.mvc.http.Request;
import com.roo.model.dto.TopicDto;
import com.roo.model.param.SearchParam;
import com.roo.service.TopicService;

/**
 * @author biezhi
 * @date 2017/8/1
 */
@Path
public class IndexController {

    @Inject
    private TopicService topicService;

    @GetRoute
    public String index(SearchParam searchParam, Request request) {
        searchParam = null == searchParam ? new SearchParam() : searchParam;
        Page<TopicDto> topicDtoPage = topicService.getTopics(searchParam);
        request.attribute("topics", topicDtoPage.getRows());
        return "index";
    }

}
