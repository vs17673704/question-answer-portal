 package com.portal.question.rest;

import java.util.List;

public class QuestionBuffer 
{
	private List<String> taglist;
	private String question;
	private String companyId;
	private String subTopicId;
	private String userId;
	
	public QuestionBuffer() 
	{
		
	}

	public QuestionBuffer(List<String> taglist, String question, String companyId, String subTopicId, String userId) {
		this.taglist = taglist;
		this.question = question;
		this.companyId = companyId;
		this.subTopicId = subTopicId;
		this.userId = userId;
	}

	
	public List<String> getTaglist() {
		return taglist;
	}

	public void setTaglist(List<String> taglist) {
		this.taglist = taglist;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getSubTopicId() {
		return subTopicId;
	}

	public void setSubTopicId(String subTopicId) {
		this.subTopicId = subTopicId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
