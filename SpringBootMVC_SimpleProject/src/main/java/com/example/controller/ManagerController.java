package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.entity.Account;
import com.example.entity.Permission;
import com.example.entity.Role;
import com.example.service.AccountService;
import com.example.service.PermissionService;
import com.example.service.RoleService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/manager")
public class ManagerController {
 
	@Autowired
	AccountService accountService;
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	PermissionService perService;
	
	@RequestMapping("/accountList")
	public String accountList(@RequestParam(defaultValue = "1")int pageNum, @RequestParam(defaultValue = "5")int pageSize, Model map) {
		PageInfo<Account> accounts = accountService.findByPageDetails(pageNum, pageSize);
		map.addAttribute("page", accounts);
		return "manager/list";
	}
	
	//获取角色和权限
	@RequestMapping("/permissionList")
	public String listPermissions(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "5") int pageSize ,Model model) {
		PageInfo<Permission> permissions =  perService.getPageInfo(pageNum, pageSize);
		model.addAttribute("page", permissions);
		return "manager/permissionList";
	}
	
	@RequestMapping("/roleList")
	public String listRoles(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "5") int pageSize ,Model model) {
		PageInfo<Role> roles= roleService.getPageInfo(pageNum, pageSize);
		model.addAttribute("page", roles);
		return "manager/roleList";
	}
	
	@RequestMapping("/permissionModify")
	public String permissionModify(Integer id, Model model) {
		Permission permission = perService.getById(id);
		model.addAttribute("p", permission);
		return "manager/permissionModify";
	}
}
