package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.RespStat;
import com.example.entity.Permission;
import com.example.service.PermissionService;
import com.example.service.RoleService;

@RestController
@RequestMapping("/api/v1/manager")
public class ManagerRestController {

	@Autowired
	RoleService roleService;
	
	@Autowired
	PermissionService permissionService;
	
	@RequestMapping("role/delete")
	@ResponseBody
	public RespStat deleteRoleById(Integer id){
		RespStat ret = roleService.deleteRoleById(id);
		
		return ret;
	}
 
	
	@RequestMapping(value="permission/update",method = RequestMethod.POST)
	public RespStat permissionUpdate(@RequestBody Permission permission) {
		RespStat ret = permissionService.update(permission);
		return ret;
	}
}
