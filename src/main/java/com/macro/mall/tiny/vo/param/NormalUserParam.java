package com.macro.mall.tiny.vo.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;


@ApiModel("普通用户")
@Accessors(chain = true)
@Data
public class NormalUserParam {
    @ApiModelProperty("name")
    private String name;
    @ApiModelProperty("age")
    private int age;
    @ApiModelProperty("email")
    private String email;
}
