package com.portal.question.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "answers", unique = true, nullable=false)
public class Answers 
{
	@Id
	@Column(name = "aid")
	private String answerId;
	@Column(name = "answer")
	private String answer;
	@Column(name = "user_id")
	private String userId;
	@Column(name = "qid")
	private String questionId;
	
	public Answers() 
	{
		
	}

	public Answers(String answerId, String answer, String userId, String questionId) {
		this.answerId = answerId;
		this.answer = answer;
		this.userId = userId;
		this.questionId = questionId;
	}

	@Override
	public String toString() {
		return "Answers [answerId=" + answerId + ", answer=" + answer + ", userId=" + userId + ", questionId="
				+ questionId + "]";
	}

	public String getAnswerId() {
		return answerId;
	}

	public void setAnswerId(String answerId) {
		this.answerId = answerId;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	
	
	
}
