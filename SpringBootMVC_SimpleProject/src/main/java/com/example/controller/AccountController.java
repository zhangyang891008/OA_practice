package com.example.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.RespStat;
import com.example.entity.Account;
import com.example.service.AccountService;

@Controller
@RequestMapping("/account")
public class AccountController {
	
	@Autowired
	AccountService accService;
	
	@RequestMapping("/list")
	//@ResponseBody
	public String list(Model map) {
		List<Account> accountsList = accService.findAll();
		long count = accService.count();
		map.addAttribute("page", accountsList);
		map.addAttribute("accountStat", count);
		return "account/list";
	}
	
	@RequestMapping("/add")
	public String add(HttpServletRequest request,Account account1 ,Model map) {
		if(request.getParameter("loginName")==null) {
			map.addAttribute("stat", RespStat.buildRespStat(400, "register error"));
			return "account/register";
		}else {
			Account account = account1;
			RespStat retStat = accService.add(account);
			map.addAttribute("stat", retStat);
			return "account/register";
		}
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
	public RespStat deleteById(Integer id) {
		RespStat stat = accService.deleteById(id);
		return stat;
	}
	
}
