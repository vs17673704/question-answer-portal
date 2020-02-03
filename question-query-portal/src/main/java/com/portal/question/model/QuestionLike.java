package com.portal.question.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "question_like")
public class QuestionLike 
{
	@Id
	@Column(name = "qid", nullable=false)
	private String questionId;
	@Id
	@Column(name = "user_id", nullable=false)
	private String userId;
	
	public QuestionLike() 
	{
		
	}

	public QuestionLike(String questionId, String userId) {
		this.questionId = questionId;
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "QuestionLike [questionId=" + questionId + ", userId=" + userId + "]";
	}

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
