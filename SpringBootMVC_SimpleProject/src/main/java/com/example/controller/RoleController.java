package com.example.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.entity.Permission;
import com.example.entity.Role;
import com.example.service.RolePermissionService;
import com.example.service.RoleService;

@Controller
@RequestMapping("/role")
public class RoleController {
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	RolePermissionService rpService;
	
	@RequestMapping("/list")
	@ResponseBody
	public List<Role> list() {
		return roleService.getAll();
	}
	
	@RequestMapping("/getPermissions")
	@ResponseBody
	public List<Permission> getPermissions(HttpServletRequest request){
		List<Role> roles = (List<Role>) request.getSession().getAttribute("role");
		List<Permission> permissions = rpService.getPermissionsByRole(roles);
		request.getSession().setAttribute("permission", permissions);
		return permissions;
	}

}
