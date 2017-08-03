package com.roo.controller.topic;

import com.blade.ioc.annotation.Inject;
import com.blade.mvc.annotation.GetRoute;
import com.blade.mvc.annotation.Path;
import com.blade.mvc.annotation.PathParam;
import com.blade.mvc.annotation.PostRoute;
import com.blade.mvc.http.Request;
import com.blade.mvc.ui.RestResponse;
import com.roo.model.dto.Auth;
import com.roo.model.dto.TopicDetailDto;
import com.roo.service.RelationService;
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

    @Inject
    private RelationService relationService;

    @GetRoute("/:tid")
    public String detail(@PathParam String tid, Request request) {
        TopicDetailDto topicDetail = topicService.getTopicDetail(tid);
        request.attribute("topic", topicDetail);
        return "topic/detail";
    }

    @PostRoute("like/:tid")
    public RestResponse<Boolean> like(@PathParam String tid) {
        Long uid = Auth.loginUser().getUid();
        topicService.likeTopic(uid, tid, true);
        return RestResponse.ok();
    }

    @PostRoute("unlike/:tid")
    public RestResponse<Boolean> unlike(@PathParam String tid) {
        Long uid = Auth.loginUser().getUid();
        topicService.likeTopic(uid, tid, false);
        return RestResponse.ok();
    }

    @PostRoute("favorite/:tid")
    public RestResponse<Boolean> favorite(@PathParam String tid) {
        Long uid = Auth.loginUser().getUid();
        topicService.favoriteTopic(uid, tid, true);
        return RestResponse.ok();
    }

    @PostRoute("unfavorite/:tid")
    public RestResponse<Boolean> unfavorite(@PathParam String tid) {
        Long uid = Auth.loginUser().getUid();
        topicService.favoriteTopic(uid, tid, true);
        return RestResponse.ok();
    }

    @PostRoute("gain/:tid")
    public RestResponse<Boolean> gain(@PathParam String tid, int num) {
        Long uid = Auth.loginUser().getUid();
        if (relationService.isGain(uid, tid)) {
            return RestResponse.fail("请勿重复操作");
        }
        topicService.gain(uid, tid, num > 0);
        return RestResponse.ok();
    }

}