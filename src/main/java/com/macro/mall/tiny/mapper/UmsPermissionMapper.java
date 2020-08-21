package com.macro.mall.tiny.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.macro.mall.tiny.entity.UmsPermission;

import java.util.List;

public interface UmsPermissionMapper extends BaseMapper<UmsPermission> {
    List<UmsPermission> getPermissionList(Long adminId);
}
