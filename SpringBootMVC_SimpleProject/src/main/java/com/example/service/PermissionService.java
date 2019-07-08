package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.RespStat;
import com.example.entity.Permission;
import com.example.mapper.PermissionExample;
import com.example.mapper.PermissionMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class PermissionService {

	@Autowired
	PermissionMapper perMapper;
	
	public PageInfo<Permission> getPageInfo(int pageNum, int pageSize){
		PageHelper.startPage(pageNum, pageSize);
		PermissionExample example = new PermissionExample();
		List<Permission> list = perMapper.selectByExample(example );
		return new PageInfo<>(list, 5);
	}

	public Permission getById(Integer id) {
		Permission permission  = perMapper.selectByPrimaryKey(id);
		return permission;
	}

	public RespStat update(Permission permission) {
		int ret = perMapper.updateByPrimaryKeySelective(permission);
		if(ret>0) {
			return RespStat.buildRespStat(200, "success");
		}else {
			return RespStat.buildRespStat(400, "failed");
		}
	}
	
}
