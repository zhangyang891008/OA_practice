package com.example.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.RespStat;
import com.example.entity.Account;
import com.example.entity.Role;
import com.example.service.AccountRoleService;
import com.example.service.AccountService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/account")
public class AccountController {
	
	@Autowired
	AccountService accService;
	@Autowired
	AccountRoleService arService;
	
	@RequestMapping("/listold")
	public String list(Model map) {
		List<Account> accountsList = accService.findAll();
		long count = accService.count();
		map.addAttribute("page", accountsList);
		map.addAttribute("accountStat", count);
		return "account/list";
	}
	
	@RequestMapping("/list")
	public String list(@RequestParam(defaultValue = "1")int pageNum, @RequestParam(defaultValue = "5")int pageSize, Model map) {
		PageInfo<Account> accounts = accService.findByPage(pageNum, pageSize);
		map.addAttribute("page", accounts);
		return "account/list";
	}
	
	@RequestMapping("/logOut")
	public String logOut(HttpServletRequest request) {
		request.getSession().removeAttribute("account");
		return "index";
	}
	
	@RequestMapping("/login")
	public String login() {
		return "account/login";
	}
	
	@RequestMapping("/validataAccount")
	@ResponseBody
	public String validataAccount(@RequestParam String loginName, @RequestParam String password,HttpServletRequest request) {
		Account account = accService.findByLoginNameAndPassword(loginName, password);
		if(account ==null) {
			return "failed";
		}
		request.getSession().setAttribute("account", account);
		return "success";
	}
	
	@RequestMapping("/add")
	public String add(HttpServletRequest request,Account account1 ,Model map) {
		if(account1.getId()!=null && account1.getLoginName()!=null) {			
			Account account = account1;
			RespStat retStat = accService.add(account);
			map.addAttribute("stat", retStat);
		}else {
			map.addAttribute("stat", RespStat.buildRespStat(400, "id and loginname cannot be null"));
		}
		return "account/register";
 
	}
	
	@RequestMapping("/register")
	public String add(Model map) {
		return "account/register";
	}
	
	@RequestMapping("/modifyPasswordById")
	@ResponseBody
	public RespStat modifyPassword(Integer id,String password) {
		Account account = new Account();
		account.setId(id);
		account.setPassword(password);
		RespStat retStat = accService.modifyPassword(account);
		return retStat ;
	}
	
	@RequestMapping("/deleteById")
	@ResponseBody
	public RespStat deleteById(Integer id, HttpServletRequest request) {
		Account account = (Account) request.getSession().getAttribute("account");
		if(account != null && account.getRole().equals("admin")) {
			RespStat stat = accService.deleteById(id);
			return stat;			
		}else {
			RespStat stat = RespStat.buildRespStat(400, "当前用户"+account.getLoginName()+"没有删除权限！");
			return stat;
		}
	}
	
	@RequestMapping("/getRoles")
	@ResponseBody
	public List<Role> getRoles(HttpServletRequest request) {
		Account account = (Account) request.getSession().getAttribute("account");
		Integer accountId = account.getId();
		List<Role> roles = arService.getRolesByAccountId(accountId);
		request.getSession().setAttribute("role", roles);
		return roles;
	}
	
	
}
