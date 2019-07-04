package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Role;
import com.example.mapper.RoleExample;
import com.example.mapper.RoleMapper;

@Service
public class RoleService {

	@Autowired
	RoleMapper roleMapper;
	
	public List<Role> getAll() {
		RoleExample example = new RoleExample();
		example.createCriteria();
		List<Role> roles = roleMapper.selectByExample(example);
		System.out.println(roles);
		return roles;
	}
}
