package com.sxy.mapper;

import com.github.pagehelper.Page;
import com.sxy.entity.Blog;
import com.sxy.entity.BlogAndTag;
import com.sxy.entity.Type;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BlogMapper {

    //根据id查询
    Blog getBlog(Long id);

    //分页查询
    Page<Blog> getBlogList(Blog blog);

    //分页展示所有
    Page<Blog> getAllBlog();

    //搜索博客
    Page<Blog> getSearchBlog(String query);

    //根据id查询博客详情
    Blog getDetailedBlogById(Long id);


    //查询推荐
    List<Blog> getAllRecommendBlog();  //推荐博客展示

    //新增保存
    int saveBlog(Blog blog);

    //保存在t_blog_tags表中
    int saveBlogAndTag(BlogAndTag blogAndTag);

    //更新
    int updateBlog(Blog blog);

    //删除
    void deleteBlog(Long id);
}
