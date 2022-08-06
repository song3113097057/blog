package com.sxy.service;

import com.github.pagehelper.PageInfo;
import com.sxy.entity.Tag;

import java.util.List;


public interface TagService {

    //保存新增分类
    int saveTag(Tag tag);

    //根据id查询分类
    Tag getTag(Long id);

    //通过名称查询分类
    Tag getTagByName(String name);

    //分页查询所有分类
    PageInfo<Tag> getTagList(Integer pageNum,Integer pageSize);

    //查询所有分类
    List<Tag> getAllTag();

    //从自负床中获取tag集合
    List<Tag>  getTagByString (String typeIds);

    List<Tag> getBlogTag();  //首页展示博客标签

    //修改分类,先查询再修改保存
    int updateTag(Tag tag);

    //删除分类
    void deleteTag(Long id);
}
