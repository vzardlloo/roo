package com.roo.service;

import com.blade.ioc.annotation.Bean;
import com.blade.jdbc.page.Page;
import com.roo.model.dto.TopicDto;
import com.roo.model.param.SearchParam;

/**
 * @author biezhi
 * @date 2017/8/1
 */
@Bean
public class TopicService {

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

}
