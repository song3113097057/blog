package com.sxy.controller;


import com.alibaba.druid.proxy.jdbc.JdbcParameter;
import com.github.pagehelper.PageInfo;
import com.sxy.entity.Blog;
import com.sxy.entity.Tag;
import com.sxy.entity.Type;
import com.sxy.service.BlogService;
import com.sxy.service.TagService;
import com.sxy.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @GetMapping("/")
    public String index(@RequestParam(value = "pageNum",required = false,defaultValue = "1")Integer pageNum,
                        @RequestParam(value = "pageSize",required = false,defaultValue = "5")Integer pageSize, Model model){
        PageInfo<Blog> showBlogs = blogService.getAllBlog(pageNum,pageSize);
        List<Type> showTypes = typeService.getBlogType();
        List<Tag>  showTags = tagService.getBlogTag();
        List<Blog> recommendBlogs = blogService.getAllRecommendBlog();
        model.addAttribute("ShowBlogPageInfo",showBlogs);
        model.addAttribute("showTypes",showTypes);
        model.addAttribute("showTags",showTags);
        model.addAttribute("recommendBlogs",recommendBlogs);
        return "index";
    }

    @PostMapping("/search")
    public String search(@RequestParam(value = "pageNum",required = false,defaultValue = "1")Integer pageNum,
                         @RequestParam(value = "pageSize",required = false,defaultValue = "5")Integer pageSize, @RequestParam(value = "query") String query, Model model){
        PageInfo<Blog> searchBlogs = blogService.getSearchBlog(pageNum,pageSize,query);
        model.addAttribute("SearchBlogsPageInfo",searchBlogs);
        model.addAttribute("query",query);

        return "search";
    }

    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id,Model model){
        Blog blog = blogService.getDetailedBlogById(id);
        model.addAttribute("blog",blog);
        return "blog";
    }

}
