package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.RespStat;
import com.example.entity.Account;
import com.example.mapper.AccountExample;
import com.example.mapper.AccountMapper;

@Service
public class AccountService {
	
	@Autowired
	AccountMapper accountMapper;

	public List<Account> findAll() {
		AccountExample example = new AccountExample();
		example.createCriteria().andIdIsNotNull();
		return accountMapper.selectByExample(example );
		
	}
	
	
	public long count() {
		AccountExample example = new AccountExample();
		example.createCriteria().andIdIsNotNull();
		return accountMapper.countByExample(example);
	}


	public RespStat add(Account account) {
		int ret = accountMapper.insertSelective(account);
		if(ret > 0) {
			return RespStat.buildRespStat(200, "regist success");
		}else {
			return RespStat.buildRespStat(400, "regist error");
		}
	}


	public RespStat modifyPassword(Account account) {
		int ret = accountMapper.updateByPrimaryKeySelective(account);
		if(ret > 0) {
			return RespStat.buildRespStat(200, "modify success");
		}else {
			return RespStat.buildRespStat(400, "modify error");
		}
	}


	public RespStat deleteById(Integer id) {
		int ret = accountMapper.deleteByPrimaryKey(id);
		if(ret > 0) {
			return RespStat.buildRespStat(200, "delete success");
		}else {
			return RespStat.buildRespStat(400, "delete error");
		}
	}
}
