package com.example.mapper;

import com.example.entity.AccountRole;

import java.util.List;

import org.springframework.stereotype.Repository;

/**
 * AccountRoleMapper继承基类
 */
@Repository
public interface AccountRoleMapper extends MyBatisBaseDao<AccountRole, Integer, AccountRoleExample> {
	public List<Integer> seletctRoleIdByAccountId(Integer accountId);
}