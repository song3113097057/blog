package com.sxy.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 博客
 *
 * @author sxy
 * @date 2022/07/21
 */
@Data
@Repository
public class Blog {


    /**
     * 博客id
     */
    private Long id;
    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String content;
    /**
     * 首页图片
     */
    private String firstPicture;
    /**
     * flag
     */
    private String flag;
    /**
     * 访问量
     */
    private Integer views;
    /**
     * 赞赏
     */
    private boolean appreciation;
    /**
     * 评论数
     */
    private Integer commentCount;
    /**
     * 分享状态
     */
    private boolean shareStatement;

    /**
     * 开启评论
     */
    private boolean commentabled;
    /**
     * 是否已发表
     */
    private boolean published;
    /**
     * 推荐
     */
    private boolean recommend;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 标签id
     */
    private String tagIds;
    /**
     * 博客描述
     */
    private String description;

    /**
     * 博客类型
     */
    private Type type;
    /**
     * 类型id
     */
    private Long typeId;
    /**
     * 用户
     */
    private User user;
    /**
     * 用户id
     */
    private Long userId;
    private List<Comment> comments = new ArrayList<>();
    private List<Tag> tags = new ArrayList<>();

    public void init(){

        this.tagIds = tagsToIds(this.getTags());
    }

    //将tags集合转换为tagIds字符串形式：“1,2,3”,用于编辑博客时显示博客的tag
    private String tagsToIds(List<Tag> tags){
        if(!tags.isEmpty()){
            StringBuffer ids = new StringBuffer();
            boolean flag = false;
            for(Tag tag: tags){
                if(flag){
                    ids.append(",");
                }else {
                    flag = true;
                }
                ids.append(tag.getId());
            }
            return ids.toString();
        }else {
            return tagIds;
        }
    }

}
