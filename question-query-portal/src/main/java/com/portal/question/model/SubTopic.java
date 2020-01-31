package com.portal.question.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "subtopic")
public class SubTopic 
{
	@Id
	@Column(name = "subtopic_id")
	private String subtopicId;
	@Column(name = "subtopic")
	private String subtopic;
	@Column(name = "topic")
	private String topicName;
	
	public SubTopic() 
	{
		
	}

	public SubTopic(String subtopicId, String subtopic, String topicName) {
		this.subtopicId = subtopicId;
		this.subtopic = subtopic;
		this.topicName = topicName;
	}


	
	
	@Override
	public String toString() {
		return "SubTopic [subtopicId=" + subtopicId + ", subtopic=" + subtopic + ", topicName=" + topicName + "]";
	}

	public String getSubtopicId() {
		return subtopicId;
	}

	public void setSubtopicId(String subtopicId) {
		this.subtopicId = subtopicId;
	}

	public String getSubtopic() {
		return subtopic;
	}

	public void setSubtopic(String subtopic) {
		this.subtopic = subtopic;
	}

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topic_name) {
		this.topicName = topic_name;
	}

	
	
}
