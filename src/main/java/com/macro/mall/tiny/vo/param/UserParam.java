package com.macro.mall.tiny.vo.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@ApiModel("admin user-参数")
@Data
public class UserParam {
    @ApiModelProperty("name")
    @NotBlank(message = "请指定admin name")
    private String name;
    @ApiModelProperty("age")
    private int age;
}
