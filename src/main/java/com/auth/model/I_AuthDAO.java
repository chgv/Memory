package com.auth.model;

import java.util.List;

public interface I_AuthDAO {

	public void insert(AuthVO authVO); //新增
 	public void update(AuthVO authVO); //修改
	public void cancel(AuthVO authVO); //權限取消
	public AuthVO getOne(Integer rm_type_no); //查詢一筆
	public List<AuthVO> getAll(); //查詢全部
	
	
}