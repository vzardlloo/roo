package com.roo.controller.topic;

import com.blade.ioc.annotation.Inject;
import com.blade.mvc.annotation.GetRoute;
import com.blade.mvc.annotation.Path;
import com.blade.mvc.annotation.PathParam;
import com.blade.mvc.http.Request;
import com.roo.model.dto.TopicDetailDto;
import com.roo.service.TopicService;

/**
 * 帖子控制器
 *
 * @author biezhi
 * @date 2017/8/2
 */
@Path("topic")
public class TopicController {

    @Inject
    private TopicService topicService;

    @GetRoute("/:tid")
    public String detail(@PathParam String tid, Request request) {
        TopicDetailDto topicDetail = topicService.getTopicDetail(tid);
        request.attribute("topic", topicDetail);
        return "topic/detail";
    }

}