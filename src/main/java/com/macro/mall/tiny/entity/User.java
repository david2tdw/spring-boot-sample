package com.macro.mall.tiny.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@TableName("User")
@Accessors(chain = true)
public class User {
    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type=IdType.ASSIGN_ID)
    private Long id;
    @TableField("name")
    private String name;
    private Integer age;
    private String email;
    @TableField(exist = false)
    private String ignoreColumn = "ignoreColumn";
    @TableField(exist = false)
    private Integer count;
}
