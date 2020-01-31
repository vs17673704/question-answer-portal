package com.portal.question.dao.masterdata;

import com.portal.question.model.Users;

public interface UserCrudDAO {
	
	public Users findById(String userId);
	
	public Users save(Users userdetails);
	
}
