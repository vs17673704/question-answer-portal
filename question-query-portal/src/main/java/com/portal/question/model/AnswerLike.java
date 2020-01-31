package com.portal.question.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "answer_like")
public class AnswerLike 
{
	@Id
	@Column(name = "aid")
	private String answerId;
	@Column(name = "user_id")
	private String userId;
	
	public AnswerLike() 
	{
		
	}

	public AnswerLike(String answerId, String userId) {
		this.answerId = answerId;
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "AnswerLike [answerId=" + answerId + ", userId=" + userId + "]";
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAnswerId() {
		return answerId;
	}

	public void setAnswerId(String answerId) {
		this.answerId = answerId;
	}

	
	
}
