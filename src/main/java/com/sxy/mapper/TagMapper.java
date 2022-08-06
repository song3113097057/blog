package com.sxy.mapper;

import com.github.pagehelper.Page;
import com.sxy.entity.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface TagMapper {
    //保存新增分类
    int saveTag(Tag tag);

    //根据id查询分类
    Tag getTag(Long id);

    //根据name查询分类
    Tag getTagByName(String name);

    //分页查询所有分类
    Page<Tag> getTagList();

    //查询所有分类
    List<Tag> getAllTag();

    List<Tag> getBlogTag();  //首页展示博客标签

    //修改分类,先查询再修改保存
    int updateTag(Tag tag);

    //删除分类
    void deleteTag(Long id);


}
