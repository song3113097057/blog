package com.sxy.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

/**
 * 类型
 *
 * @author sxy
 * @date 2022/07/29
 */
@Data
@Repository
public class Type {
    /**
     * 类型id
     */
    private Long id;

    /**
     * 类型名称
     */
    @NotBlank(message = "分类名称不能为空")
    private String name;
    private List<Blog> blogs = new ArrayList<>();
}
