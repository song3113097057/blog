package com.sxy.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sxy.entity.Tag;
import com.sxy.mapper.TagMapper;
import com.sxy.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagMapper tagMapper;



    @Override
    public  int  saveTag(Tag tag) {
        return tagMapper.saveTag(tag);
    }

    @Override
    public Tag getTag(Long id) {
        return tagMapper.getTag(id);
    }


    @Override
    public Tag getTagByName(String name) {
        return tagMapper.getTagByName(name);
    }


    @Override
    public  PageInfo<Tag> getTagList(Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Tag> tags = tagMapper.getTagList();
        PageInfo<Tag> pageInfo = new PageInfo<>(tags);
        return pageInfo;
    }


    @Override
    public List<Tag> getAllTag() {
        return tagMapper.getAllTag();
    }



    @Override
    public List<Tag> getBlogTag(){
        return tagMapper.getBlogTag();
    }

    @Override
    public List<Tag> getTagByString(String tagIds) {    //从tagIds字符串中获取id，根据id获取tag集合
        List<Tag> tags = new ArrayList<>();
        List<Long> ids  = convertToList(tagIds);
        for (Long id : ids) {
            tags.add(tagMapper.getTag(id));
        }
        return tags;
    }



    private List<Long> convertToList(String ids) {  //把前端的tagIds字符串转换为list集合
        List<Long> list = new ArrayList<>();
        if (!"".equals(ids) && ids != null) {
            String[] idarray = ids.split(",");
            for (int i=0; i < idarray.length;i++) {
                list.add(new Long(idarray[i]));
            }
        }
        return list;
    }

    @Override
    public int updateTag(Tag tag) {
        return tagMapper.updateTag(tag);
    }


    @Override
    public void deleteTag(Long id) {
        tagMapper.deleteTag(id);
    }
}
