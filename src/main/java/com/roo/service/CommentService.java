package com.roo.service;

import com.blade.ioc.annotation.Bean;
import com.roo.model.dto.CommentDto;

import java.util.List;

/**
 * @author biezhi
 * @date 2017/8/2
 */
@Bean
public class CommentService {

    public List<CommentDto> getComments(String tid) {
        String sql = "select a.coid, a.author, b.avatar, a.content, a.created" +
                " from roo_comment a" +
                " left join roo_user b on a.author = b.username" +
                " where a.tid = ?";

        return new CommentDto().queryAll(sql, tid);
    }

}
