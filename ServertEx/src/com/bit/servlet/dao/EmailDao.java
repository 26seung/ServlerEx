package com.bit.servlet.dao;

import java.util.List;

// 추상 메서드의 선언
public interface EmailDao {
	
	public List<EmailVo>getList();	// SELECT
	public int insert(EmailVo vo);	// INSERT
	public int delete(Long no);		// DELETE by PK
	
}
