package com.macro.mall.tiny.vo.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("费用")
@Data
public class voResult {
    @ApiModelProperty("id")
    private String id;
    @ApiModelProperty(value = "jobNo")
    private String jobNo;
}
