package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.RespStat;
import com.example.entity.Role;
import com.example.mapper.RoleExample;
import com.example.mapper.RoleMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class RoleService {

	@Autowired
	RoleMapper roleMapper;
	
	public PageInfo<Role> getPageInfo(int pageNum, int pageSize){
		PageHelper.startPage(pageNum, pageSize);
		RoleExample example = new RoleExample();
		List<Role> list = roleMapper.selectByExample(example);
		return new PageInfo<>(list, 5);
	}

	public RespStat deleteRoleById(Integer id) {
		try {
			int ret = roleMapper.deleteByPrimaryKey(id);
			if(ret>0) {
				return RespStat.buildRespStat(200,"success");
			}else {
				return RespStat.buildRespStat(400,"failed");
			}
		} catch (Exception e) {
			return RespStat.buildRespStat(400, "exception occurried", e.getMessage());
		}
	}
}
