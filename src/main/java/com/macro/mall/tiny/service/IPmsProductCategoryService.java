package com.macro.mall.tiny.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.macro.mall.tiny.entity.PmsProductCategory;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 产品分类 服务类
 * </p>
 *
 * @author jobob
 * @since 2020-08-24
 */
public interface IPmsProductCategoryService extends IService<PmsProductCategory> {
    Page getProductList(int start, int pageSize);
}
