package com.roo.service;

import com.blade.ioc.annotation.Bean;
import com.blade.ioc.annotation.Inject;
import com.blade.jdbc.page.Page;
import com.blade.kit.StringKit;
import com.roo.model.dto.CommentDto;
import com.roo.model.dto.TopicDetailDto;
import com.roo.model.dto.TopicDto;
import com.roo.model.param.SearchParam;
import com.roo.utils.RooUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author biezhi
 * @date 2017/8/1
 */
@Bean
public class TopicService {

    @Inject
    private CommentService commentService;

    @Inject
    private RelationService relationService;

    public Page<TopicDto> getTopics(SearchParam searchParam) {

        List<Object> args  = new ArrayList<>();
        String       where = " where 1=1 ";
        if (StringKit.isNotBlank(searchParam.getQ())) {
            where += "and a.title like ? ";
            args.add("%" + searchParam.getQ() + "%");
        }

        if (StringKit.isNotBlank(searchParam.getSlug())) {
            where += "and a.node_slug = ? ";
            args.add(searchParam.getSlug());
        }

        String sql = "select a.tid, a.title, a.username, b.avatar," +
                "a.node_slug as nodeSlug, a.node_title as nodeTitle," +
                "a.comments, a.created, a.replyed, a.reply_id as replyId, a.reply_user as replyUser" +
                " from roo_topic a" +
                " left join roo_user b on a.username = b.username" + where +
                " order by a.created desc";

        Page<TopicDto> topics = new TopicDto().page(searchParam.getPageRow(), sql, args.toArray());
        return topics;
    }

    public TopicDetailDto getTopicDetail(String tid) {

        String sql = "select a.tid, a.title, a.content, a.username, b.avatar," +
                "a.node_slug as nodeSlug, a.node_title as nodeTitle," +
                "a.comments, a.created, b.avatar" +
                " from roo_topic a" +
                " left join roo_user b on a.username = b.username" +
                " where a.tid = ?";

        TopicDetailDto topicDetail = new TopicDetailDto().query(sql, tid);
        if (topicDetail.getComments() > 0) {
            // 加载评论
            List<CommentDto> commentDtos = commentService.getComments(tid);
            topicDetail.setCommentList(commentDtos);
        }
        topicDetail.setContent(RooUtils.mdToHtml(topicDetail.getContent()));
        topicDetail.setViews(relationService.viewTopic(tid).intValue());
        topicDetail.setLikes(relationService.getTopicLikes(tid));
        topicDetail.setFavorites(relationService.getTopicFavorites(tid));

        return topicDetail;
    }

}
