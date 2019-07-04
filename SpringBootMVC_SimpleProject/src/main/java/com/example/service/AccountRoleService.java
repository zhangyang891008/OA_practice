package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.AccountRole;
import com.example.entity.Role;
import com.example.mapper.AccountRoleExample;
import com.example.mapper.AccountRoleMapper;
import com.example.mapper.RoleExample;
import com.example.mapper.RoleMapper;

@Service
public class AccountRoleService {

	@Autowired
	AccountRoleMapper accountRoleMapper;
	@Autowired
	RoleMapper roleMapper;
	
	//通过账户主键获得角色
	public List<Role> getRolesByAccountId(Integer id) {
		
		//1.查询accountId对应的roleId
		AccountRoleExample example = new AccountRoleExample();
		example.createCriteria().andAccountIdEqualTo(id);
		List<AccountRole> aRoles = accountRoleMapper.selectByExample(example);
		if(aRoles==null || aRoles.size()==0) {
			return new ArrayList<Role>();
		}
		  
		//2.根据roleId去表中获取对应的Role对象
		List<Integer> roleIds =  accountRoleMapper.seletctRoleIdByAccountId(id);
		RoleExample roleExample = new RoleExample();
		roleExample.createCriteria().andIdIn(roleIds);    
		List<Role> roles = roleMapper.selectByExample(roleExample);
		return roles;
		
	}

}
