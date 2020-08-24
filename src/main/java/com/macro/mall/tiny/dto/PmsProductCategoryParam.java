package com.macro.mall.tiny.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PmsProductCategoryParam {
    @ApiModelProperty(value = "页码", required = true)
    private int pageNum;

    @ApiModelProperty(value ="数量", required = true)
    private int pageSize;
}
