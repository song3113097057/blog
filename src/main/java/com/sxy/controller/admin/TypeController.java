package com.sxy.controller.admin;

import com.github.pagehelper.PageInfo;
import com.sxy.entity.Type;
import com.sxy.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.naming.Binding;
import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class TypeController {
    @Autowired
    private TypeService typeService;
    @GetMapping("/types")
    public String getTypeList(@RequestParam(value = "pageNum",required = false,defaultValue = "1")Integer pageNum,
                              @RequestParam(value = "pageSize",required = false,defaultValue = "5")Integer pageSize, Model model){
        PageInfo<Type> types = typeService.getTypeList(pageNum,pageSize);
        model.addAttribute("TypePageInfo",types);
        return "admin/types";
    }

    @GetMapping("/types/input")
    public String input(Model model){
        model.addAttribute("type",new Type());
        return "admin/types-input";
    }

    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        model.addAttribute("type",typeService.getType(id));
        return "admin/types-input";
    }
    @PostMapping("/types")
    public String post(@Valid Type type, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        Type type1 = typeService.getTypeByName(type.getName());
        if (type1 != null) {
          bindingResult.rejectValue("name","nameError","不能重复添加分类");
        }
        if(bindingResult.hasErrors()){
            return "admin/types-input";
        }

        int t = typeService.saveType(type);
        if(t == 0){
            redirectAttributes.addFlashAttribute("message","新增失败");
        }else{
            redirectAttributes.addFlashAttribute("message","新增成功");
        }
        return "redirect:/admin/types";
    }

    @PostMapping("/types/{id}")
    public String editPost(@Valid Type type,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes){
        Type type1 = typeService.getTypeByName(type.getName());
        if (type1 != null) {
            bindingResult.rejectValue("name","nameError","不能修改为已存在的类");
        }
        if(bindingResult.hasErrors()){
            return "admin/types-input";
        }

       int t = typeService.updateType(type);
        if(t == 0){
            redirectAttributes.addFlashAttribute("message","更新失败");
        }else{
            redirectAttributes.addFlashAttribute("message","更新成功");
        }
        return "redirect:/admin/types";
    }


    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable("id") Long id,RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("message","删除成功");
        typeService.deleteType(id);
        return "redirect:/admin/types";
    }



}
