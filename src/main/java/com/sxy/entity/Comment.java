package com.sxy.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 评论
 *
 * @author sxy
 * @date 2022/07/24
 */
@Data
@Repository
public class Comment {
    /**
     * id
     */
    private Long id;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 电子邮件
     */
    private String email;
    /**
     * 内容
     */
    private String content;
    /**
     * avator
     */
    private String avator;
    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 博客
     */
    private Blog blog;

    //回复评论
    private List<Comment> replyComments = new ArrayList<>();
    private Comment parentComment;

}
