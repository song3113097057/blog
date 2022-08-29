package com.sxy.mapper;

import com.github.pagehelper.Page;
import com.sxy.entity.Type;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Mapper
@Repository
public interface TypeMapper {
    //保存新增分类
    int saveType(Type type);

    //根据id查询分类
    Type getType(@Param("id") Long id);

    //根据name查询分类
    Type getTypeByName(@Param("name") String name);

    //分页查询所有分类
    Page<Type> getTypeList();

    //查询所有分类
    List<Type> getAllType();

    //获取topType
    List<Type> getBlogType();

    //修改分类,先查询再修改保存
    int updateType(Type type);

    //删除分类
    void deleteType(@Param("id") Long id);
}
