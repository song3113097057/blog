package com.sxy.controller.admin;

import com.github.pagehelper.PageInfo;
import com.sxy.entity.Tag;
import com.sxy.entity.Type;
import com.sxy.service.TagService;
import com.sxy.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class TagController {
    @Autowired
    private TagService tagService;
    @GetMapping("/tags")
    public String getTagList(@RequestParam(value = "pageNum",required = false,defaultValue = "1")Integer pageNum,
                              @RequestParam(value = "pageSize",required = false,defaultValue = "5")Integer pageSize, Model model){
        PageInfo<Tag> tags = tagService.getTagList(pageNum,pageSize);
        model.addAttribute("TagPageInfo",tags);
        return "admin/tags";
    }

    @GetMapping("/tags/input")
    public String input(Model model){
        model.addAttribute("tag",new Tag());
        return "admin/tags-input";
    }

    @GetMapping("/tags/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        model.addAttribute("tag",tagService.getTag(id));
        return "admin/tags-input";
    }
    @PostMapping("/tags")
    public String post(@Valid Tag tag, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        Tag tag1 = tagService.getTagByName(tag.getName());
        if (tag1 != null) {
          bindingResult.rejectValue("name","nameError","不能重复添加分类");
        }
        if(bindingResult.hasErrors()){
            return "admin/tags-input";
        }

        int t = tagService.saveTag(tag);
        if(t == 0){
            redirectAttributes.addFlashAttribute("message","新增失败");
        }else{
            redirectAttributes.addFlashAttribute("message","新增成功");
        }
        return "redirect:/admin/tags";
    }

    @PostMapping("/tags/{id}")
    public String editPost(@Valid Tag tag,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes){
        Tag tag1 = tagService.getTagByName(tag.getName());
        if (tag1 != null) {
            bindingResult.rejectValue("name","nameError","不能修改为已存在的类");
        }
        if(bindingResult.hasErrors()){
            return "admin/tags-input";
        }

       int t = tagService.updateTag(tag);
        if(t == 0){
            redirectAttributes.addFlashAttribute("message","更新失败");
        }else{
            redirectAttributes.addFlashAttribute("message","更新成功");
        }
        return "redirect:/admin/tags";
    }


    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable("id") Long id,RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("message","删除成功");
        tagService.deleteTag(id);
        return "redirect:/admin/tags";
    }



}
