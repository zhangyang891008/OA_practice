package com.example.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.RespStat;
import com.example.entity.Account;
import com.example.entity.Role;
import com.example.service.AccountService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/account")
public class AccountController {
	
	@Autowired
	AccountService accService;
 
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
	
	//个人信息跳转
	@RequestMapping("/profile")
	public String profile() {
		
		return "account/profile";
	}
	
	@RequestMapping("/fileUpload")
	public String fileUpload(MultipartFile filename,String password, HttpServletRequest request) {
		System.out.println(filename);
		System.out.println(filename.getOriginalFilename());
			
		try {
			File path = new File(ResourceUtils.getURL("classpath:").getPath());
	        File upload = new File(path.getAbsolutePath()+File.separator+ "static/uploads");
	        
	        System.out.println("upload:" + upload);
	        
	        filename.transferTo(new File(upload+File.separator+filename.getOriginalFilename()));
	        Account account = (Account) request.getSession().getAttribute("account");
	        account.setImage(filename.getOriginalFilename());
	        accService.saveImage(account);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "account/profile";
	}
	
}
