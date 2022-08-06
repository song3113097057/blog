package com.sxy.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString
@NoArgsConstructor
@Repository
public class Tag {
    private Long id;
    @NotBlank(message = "分类名称不能为空")
    private String name;
    private List<Blog> blogs = new ArrayList<>();
}
