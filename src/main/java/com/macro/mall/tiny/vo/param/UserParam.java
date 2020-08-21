package com.macro.mall.tiny.vo.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@ApiModel("查询某个订单的费用-参数")
@Data
public class UserParam {
    @ApiModelProperty("name")
    @NotBlank(message = "请指定业务编号")
    private String name;
    @ApiModelProperty("age")
    private int age;
}
