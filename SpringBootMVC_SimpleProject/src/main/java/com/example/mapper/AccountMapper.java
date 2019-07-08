package com.example.mapper;

import com.example.entity.Account;

import java.util.List;

import org.springframework.stereotype.Repository;

/**
 * AccountMapper继承基类
 */
@Repository(value="accountMapper")
public interface AccountMapper extends MyBatisBaseDao<Account, Integer, AccountExample> {
	
	public List<Account> getRolesAndPermissions();
	
}