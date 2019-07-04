package com.example.mapper;

import com.example.entity.Account;
import org.springframework.stereotype.Repository;

/**
 * AccountMapper继承基类
 */
@Repository(value="accountMapper")
public interface AccountMapper extends MyBatisBaseDao<Account, Integer, AccountExample> {
	
}