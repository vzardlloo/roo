package com.roo.service;

import com.blade.ioc.annotation.Bean;
import com.blade.ioc.annotation.Inject;
import com.blade.jdbc.page.Page;
import com.roo.model.dto.CommentDto;
import com.roo.model.dto.TopicDetailDto;
import com.roo.model.dto.TopicDto;
import com.roo.model.param.SearchParam;
import com.roo.utils.RooUtils;

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

        String sql = "select a.tid, a.title, a.username, b.avatar," +
                "a.node_slug as nodeSlug, a.node_title as nodeTitle," +
                "a.comments, a.created, a.replyed, a.reply_id as replyId, a.reply_user as replyUser" +
                " from roo_topic a" +
                " left join roo_user b on a.username = b.username" +
                " order by a.created desc";

        Page<TopicDto> topics = new TopicDto().page(searchParam.getPageRow(), sql);
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
