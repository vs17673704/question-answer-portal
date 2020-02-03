package com.portal.question.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "question_tag")
public class QuestionTag 
{
	@Id
	@Column(name = "qid", nullable=false)
	private String questionId;
	@Id
	@Column(name = "tag", nullable=false)
	private String tag;
	
	public QuestionTag() 
	{
		
	}

	public QuestionTag(String questionId, String tag) {
		this.questionId = questionId;
		this.tag = tag;
	}

	@Override
	public String toString() {
		return "QuestionTag [questionId=" + questionId + ", tag=" + tag + "]";
	}

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
	
}
