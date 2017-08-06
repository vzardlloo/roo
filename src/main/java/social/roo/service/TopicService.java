package social.roo.service;

import com.blade.ioc.annotation.Bean;
import com.blade.ioc.annotation.Inject;
import com.blade.jdbc.page.Page;
import com.blade.kit.StringKit;
import social.roo.model.dto.CommentDto;
import social.roo.model.dto.TopicDetailDto;
import social.roo.model.dto.TopicDto;
import social.roo.model.entity.Topic;
import social.roo.model.param.SearchParam;
import social.roo.utils.RooUtils;

import java.util.ArrayList;
import java.util.Date;
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
                " order by " + searchParam.getOrderBy();

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

    /**
     * 发布帖子
     *
     * @param topic
     */
    public void createTopic(Topic topic) {
        Date date = new Date();
        topic.setCreated(date);
        topic.setUpdated(date);
        long   created = date.getTime() / 1000;
        double weight  = RooUtils.calcWeight(0, 0, 0, 0, created);
        topic.setWeight(weight);

        topic.save();
    }

    /**
     * 点赞、取消点赞
     *
     * @param uid
     * @param tid
     * @param isLike
     */
    public void likeTopic(Long uid, String tid, boolean isLike) {
        Topic topic = new Topic().find(tid);
        if (isLike) {
            relationService.likeTopic(uid, tid);
        } else {
            relationService.unlikeTopic(uid, tid);
        }
        this.updateWeight(tid, topic.getComments(), topic.getGains(), topic.getCreated().getTime() / 1000);
    }

    /**
     * 收藏、取消收藏
     *
     * @param uid
     * @param tid
     * @param isFavorite
     */
    public void favoriteTopic(Long uid, String tid, boolean isFavorite) {
        Topic topic = new Topic().find(tid);
        if (isFavorite) {
            relationService.favoriteTopic(uid, tid);
        } else {
            relationService.unfavoriteTopic(uid, tid);
        }
        this.updateWeight(tid, topic.getComments(), topic.getGains(), topic.getCreated().getTime() / 1000);
    }

    /**
     * 增益
     *
     * @param tid
     * @param isIncrement
     */
    public void gain(Long uid, String tid, boolean isIncrement) {

        relationService.gainTopic(uid, tid);

        Topic topic = new Topic().find(tid);
        Topic temp  = new Topic();
        if (isIncrement) {
            temp.setGains(topic.getGains() + 1);
        } else {
            temp.setGains(topic.getGains() - 1);
        }
        temp.update(tid);
        this.updateWeight(tid, topic.getComments(), temp.getGains(), topic.getCreated().getTime() / 1000);
    }

    private void updateWeight(String tid, int comments, int gains, long created) {
        int    likes     = relationService.getTopicLikes(tid);
        int    favorites = relationService.getTopicFavorites(tid);
        double weight    = RooUtils.calcWeight(likes, favorites, comments, gains, created);
        Topic  temp      = new Topic();
        temp.setWeight(weight);
        temp.update(tid);
    }

    public void updateTopic(Topic topic) {
        Topic  temp    = topic.find(topic.getTid());
        long   created = temp.getCreated().getTime() / 1000;
        double weight  = RooUtils.calcWeight(0, 0, 0, 0, created);
        topic.setWeight(weight);

        topic.update();
    }

}