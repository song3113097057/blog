package com.sxy.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.sxy.entity.Type;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.awt.print.Pageable;
import java.util.List;

public interface TypeService {

    //保存新增分类
    int saveType(Type type);

    //根据id查询分类
    Type getType(Long id);

    //通过名称查询分类
    Type getTypeByName(String name);

    //分页查询所有分类
    PageInfo<Type> getTypeList(Integer pageNum,Integer pageSize);

    //查询所有分类
    List<Type> getAllType();

    //获取topType
    List<Type> getBlogType();

    //修改分类,先查询再修改保存
    int updateType(Type type);

    //删除分类
    void deleteType(Long id);
}
