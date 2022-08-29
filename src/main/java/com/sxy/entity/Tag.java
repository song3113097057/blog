package com.sxy.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

/**
 * 标签
 *
 * @author sxy
 * @date 2022/07/26
 */
@Data
@Repository
public class Tag {
    /**
     * 标签id
     */
    private Long id;
    /**
     * 标签名字
     */
    @NotBlank(message = "分类名称不能为空")
    private String name;
    private List<Blog> blogs = new ArrayList<>();
}
