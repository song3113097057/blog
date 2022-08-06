package com.sxy.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.sxy.entity.Blog;

import java.util.List;

public interface BlogService {
    //根据id查询
    Blog getBlog(Long id);

    //查询分页
    PageInfo<Blog> getBlogList(Integer pageNum, Integer pageSize,Blog blog);


    //分页展示所有
    PageInfo<Blog> getAllBlog(Integer pageNum, Integer pageSize);

    // 根据搜索模糊查询
    PageInfo<Blog> getSearchBlog(Integer pageNum,Integer pageSize,String query);


    //查询推荐
    List<Blog> getAllRecommendBlog();  //

    //根据id查询博客详情
    Blog getDetailedBlogById(Long id);

    //新增保存对象
    int saveBlog(Blog blog);

    //更新
    int updateBlog(Blog blog);

    //删除
    void deleteBlog(Long id);
}
