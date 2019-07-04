package com.example.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.entity.Permission;
import com.example.entity.Role;
import com.example.service.PermissionService;

@Controller
@RequestMapping("/permission")
public class PermissionController {
	
	@Autowired
	PermissionService permissionService;
	
	@RequestMapping("/list")
	@ResponseBody
	public List<Permission> getAll() {
		List<Permission> list  = permissionService.list();
		return list;
	}
	

}
