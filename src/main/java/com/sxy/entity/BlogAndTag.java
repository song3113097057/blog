package com.sxy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * 博客和标签
 * 把博客和标签关系存到数据库中使用的类
 *
 * @author sxy
 * @date 2022/07/23
 */


@Repository
@Data
@AllArgsConstructor
public class BlogAndTag {

    private Long tagId;

    private Long blogId;


}
