package com.macro.mall.tiny.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.github.pagehelper.PageHelper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.macro.mall.tiny.entity.PmsProductCategory;
import com.macro.mall.tiny.mapper.PmsProductCategoryMapper;
import com.macro.mall.tiny.service.IPmsProductCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 产品分类 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-08-24
 */
@Service
public class PmsProductCategoryServiceImpl extends ServiceImpl<PmsProductCategoryMapper, PmsProductCategory> implements IPmsProductCategoryService {

    @Autowired
    private PmsProductCategoryMapper pmsProductCategoryMapper;
    
    @Override
    public Page getProductList(int pageNum, int pageSize) {
        QueryWrapper<PmsProductCategory> queryWrapper = new QueryWrapper();
        queryWrapper.ge("level", 0);
        
        IPage<PmsProductCategory> page = new Page<>(pageNum, pageSize);
        pmsProductCategoryMapper.selectPage(page, queryWrapper);

        System.out.println("总页数:"+page.getPages());
        System.out.println("总记录数:"+page.getTotal());
        List<PmsProductCategory> res =page.getRecords();
        System.out.println(res.toString());
        return (Page) page;
    }
}
