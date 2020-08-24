package com.macro.mall.tiny.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.macro.mall.tiny.common.api.CommonResult;
import com.macro.mall.tiny.dto.PmsProductCategoryParam;
import com.macro.mall.tiny.dto.UmsAdminLoginParam;
import com.macro.mall.tiny.entity.PmsProductCategory;
import com.macro.mall.tiny.service.IPmsProductCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 产品分类 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2020-08-24
 */
@RestController
@Api(tags = "PmsProductCategoryController", description = "产品分页")
@RequestMapping("/product")
public class PmsProductCategoryController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PmsProductCategoryController.class);
    @Autowired
    private IPmsProductCategoryService iPmsProductCategoryService;


    @ApiOperation(value = "测试分页")
//    @PostMapping(value = "/testPagination")
    @RequestMapping(value = "/testPagination", method = RequestMethod.POST)
    public CommonResult<Page> getProductCategory(@RequestBody PmsProductCategoryParam pmsProductCategoryParam, BindingResult result) {
        if (result.hasErrors()) {
            return CommonResult.validateFailed(result.getFieldError().getDefaultMessage());
        }
        Page resultList = iPmsProductCategoryService.getProductList(pmsProductCategoryParam.getPageNum(), pmsProductCategoryParam.getPageSize());
        return CommonResult.success(resultList);
    }


}

