package com.sxy.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sxy.entity.Blog;
import com.sxy.entity.BlogAndTag;
import com.sxy.entity.Tag;
import com.sxy.mapper.BlogMapper;
import com.sxy.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    BlogMapper blogMapper;

    @Override
    public Blog getBlog(Long id) {
        return blogMapper.getBlog(id);
    }

    @Override
    public PageInfo<Blog> getBlogList(Integer pageNum, Integer pageSize,Blog blog) {
        PageHelper.startPage(pageNum,pageSize);
        List<Blog> blogs = blogMapper.getBlogList(blog);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);
        return pageInfo;
    }

    @Override
    public PageInfo<Blog> getSearchBlog(Integer pageNum, Integer pageSize, String query) {
        PageHelper.startPage(pageNum,pageSize);
        List<Blog> blogs = blogMapper.getSearchBlog(query);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);
        return pageInfo;
    }

    @Override
    public PageInfo<Blog> getAllBlog(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Blog> showBlogs = blogMapper.getAllBlog();
        PageInfo<Blog> pageInfo = new PageInfo<>(showBlogs);
        return pageInfo;
    }

    @Override
    public List<Blog> getAllRecommendBlog() {
        return blogMapper.getAllRecommendBlog();
    }

    @Override
    public Blog getDetailedBlogById(Long id) {
        return blogMapper.getDetailedBlogById(id);
    }

   /* @Override
    public PageInfo<Blog> getAllBlog(Integer pageNum, Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Blog> showBlogs = blogMapper.getAllBlogList();
        PageInfo<Blog> pageInfo = new PageInfo<>(showBlogs);
        return pageInfo;
    };*/


    @Transactional
    @Override
    public int saveBlog(Blog blog) {
            blog.setCreateTime(new Date());
            blog.setUpdateTime(new Date());
            blog.setViews(0);
            //保存博客
            blogMapper.saveBlog(blog);
           //保存博客后才能获取自增的id
            Long id = blog.getId();
           //将标签的数据存到t_blogs_tag表中
           List<Tag> tags = blog.getTags();
           BlogAndTag blogAndTag = null;
           for (Tag tag : tags) {
            //新增时无法获取自增的id,在mybatis里修改
            blogAndTag = new BlogAndTag(tag.getId(), id);
            blogMapper.saveBlogAndTag(blogAndTag);
          }
          return 1 ;

    }

    @Transactional
    @Override
    public int updateBlog(Blog blog) {

        blog.setUpdateTime(new Date());
        //将标签的数据存到t_blogs_tag表中
        List<Tag> tags = blog.getTags();
        BlogAndTag blogAndTag = null;
        for (Tag tag : tags) {
            blogAndTag = new BlogAndTag(tag.getId(), blog.getId());
            blogMapper.saveBlogAndTag(blogAndTag);
        }

        return blogMapper.updateBlog(blog);
    }

    @Transactional
    @Override
    public void deleteBlog(Long id) {
        blogMapper.deleteBlog(id);
    }
}
