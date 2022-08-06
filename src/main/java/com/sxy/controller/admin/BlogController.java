package com.sxy.controller.admin;

import com.github.pagehelper.PageInfo;
import com.sxy.entity.Blog;
import com.sxy.entity.Type;
import com.sxy.entity.User;
import com.sxy.service.BlogService;
import com.sxy.service.TagService;
import com.sxy.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    private TagService tagService;

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    //分页展示
    @GetMapping("/blogs")
    public String blogs(@RequestParam(value = "pageNum",required = false,defaultValue = "1")Integer pageNum,
                        @RequestParam(value = "pageSize",required = false,defaultValue = "5")Integer pageSize, Model model,Blog blog){
        PageInfo<Blog> blogs = blogService.getBlogList(pageNum,pageSize,blog);
        model.addAttribute("types",typeService.getAllType());
        model.addAttribute("BlogPageInfo",blogs);
        return "admin/blogs";
    }

    //博客查询
    @PostMapping("/blogs/search")
    public String search(@RequestParam(value = "pageNum",required = false,defaultValue = "1")Integer pageNum,
                        @RequestParam(value = "pageSize",required = false,defaultValue = "5")Integer pageSize, Model model,Blog blog){
        PageInfo<Blog> blogs = blogService.getBlogList(pageNum,pageSize,blog);
        model.addAttribute("BlogPageInfo",blogs);
        //仅返回blogs页面下的blogList页面
        return "admin/blogs :: blogList";
    }

   //跳转到博客新增页面
    @GetMapping("/blogs/input")//返回一个blog对象给前端th:object
    public String input(Model model){
        model.addAttribute("types",typeService.getAllType());
        model.addAttribute("tags",tagService.getAllTag());
        model.addAttribute("blog",new Blog());
        return "admin/blogs-input";
    }

    //跳转到博客编辑页面
    @GetMapping("/blogs/{id}/input")//返回一个blog对象给前端th:object
    public String editInput(@PathVariable Long id, Model model){
        model.addAttribute("types",typeService.getAllType());
        model.addAttribute("tags",tagService.getAllTag());
        Blog blog = blogService.getBlog(id);
        //初始化tagIds：将tags集合转换为tagIds字符串形式
        blog.init();
        model.addAttribute("blog",blog);
        return "admin/blogs-input";
    }

    @PostMapping("/blogs")//博客新增.编辑
    public String post( Blog blog, RedirectAttributes redirectAttributes,HttpSession session){
        //设置blog对象各属性的值
        blog.setUser((User) session.getAttribute("user"));
        blog.setUserId(blog.getUser().getId());
        blog.setType(typeService.getType(blog.getType().getId()));
        blog.setTypeId(blog.getType().getId());
        blog.setTags(tagService.getTagByString(blog.getTagIds()));

        if (blog.getId() == null) {   //id为空，则为新增
            blogService.saveBlog(blog);
        } else {
            blogService.updateBlog(blog);
        }

        redirectAttributes.addFlashAttribute("message","操作成功");

        return "redirect:/admin/blogs";
    }

    @GetMapping("/blogs/{id}/delete")
    public String deleteBlog(@PathVariable Long id, RedirectAttributes attributes){
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/blogs";
    }


}
