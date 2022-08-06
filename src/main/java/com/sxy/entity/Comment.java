package com.sxy.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@ToString
@NoArgsConstructor
@Repository
public class Comment {
    private Long id;
    private String nickname;
    private String email;
    private String content;
    private String avator;
    private Date createTime;

    private Blog blog;

    //回复评论
    private List<Comment> replyComments = new ArrayList<>();
    private Comment parentComment;

}
