package com.portal.question.dao.masterdata;

import java.util.List;

import com.portal.question.model.Tags;

public interface TagsCrudDAO {
	
	public List<Tags> findAll();
	
	public String delete(String tag);

	public List<Tags> save(List<String> tags);
	
}
