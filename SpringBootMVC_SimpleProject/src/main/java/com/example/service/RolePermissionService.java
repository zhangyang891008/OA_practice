package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Permission;
import com.example.entity.Role;
import com.example.entity.RolePermission;
import com.example.mapper.PermissionExample;
import com.example.mapper.PermissionMapper;
import com.example.mapper.RolePermissionExample;
import com.example.mapper.RolePermissionMapper;

@Service
public class RolePermissionService {
	
	@Autowired
	RolePermissionMapper rpMapper;
	
	@Autowired
	PermissionMapper permissionMapper;

	public List<Permission> getPermissionsByRole(List<Role> roles) {
		List<Integer> roleIds = new ArrayList<Integer>();
		for(Role role:roles) {
			roleIds.add(role.getId());
		}
		//1.根据角色主键查询结果
		RolePermissionExample rpExample = new RolePermissionExample();
		rpExample.createCriteria().andRoleIdIn(roleIds);
		List<RolePermission> rolePermissions = rpMapper.selectByExample(rpExample);
		
		if(rolePermissions == null || rolePermissions.size()==0) {
			return new ArrayList<Permission>();
		}
		List<Integer> permissionIds = new ArrayList<Integer>();
		for(RolePermission rolePermission: rolePermissions) {
			permissionIds.add(rolePermission.getPermissionId());
		}
		//2.根据权限主键查询权限
		PermissionExample permissionExample = new PermissionExample();
		permissionExample.createCriteria().andIdIn(permissionIds);
		return permissionMapper.selectByExample(permissionExample);
		
	}

}
