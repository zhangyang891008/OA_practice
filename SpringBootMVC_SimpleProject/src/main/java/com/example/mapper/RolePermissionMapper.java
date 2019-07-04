package com.example.mapper;

import com.example.entity.RolePermission;
import org.springframework.stereotype.Repository;

/**
 * RolePermissionMapper继承基类
 */
@Repository
public interface RolePermissionMapper extends MyBatisBaseDao<RolePermission, Integer, RolePermissionExample> {
}