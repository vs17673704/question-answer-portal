package com.portal.question.dao.masterdata;

import java.util.List;

import com.portal.question.model.SubTopic;

public interface SubTopicCrudDAO {
	
	public List<SubTopic> findAll();
	
	public SubTopic save(SubTopic subTopic);
	
	public SubTopic findById(String subTopicId);
	
	
}
