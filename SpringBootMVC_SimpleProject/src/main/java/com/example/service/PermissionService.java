package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Permission;
import com.example.mapper.PermissionExample;
import com.example.mapper.PermissionMapper;

@Service
public class PermissionService {

	@Autowired
	PermissionMapper permissionMapper;
	
	public List<Permission> list() {
		PermissionExample example = new PermissionExample();
		example.createCriteria();
		return permissionMapper.selectByExample(example);
	}

}
