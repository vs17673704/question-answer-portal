package com.portal.question.dao.masterdata;

import java.util.List;

import com.portal.question.model.Topics;

public interface TopicsCrudDAO {
	
	public List<Topics> findAll();

	public List<String> save(Topics topics);

	public Topics findTopic(String topic);
	
}
