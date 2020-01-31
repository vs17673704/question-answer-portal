package com.portal.question.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "answer_comment")
public class AnswerComments 
{
	@Id
	@Column(name = "comment_id")
	private String commentId;
	@Column(name = "comment")
	private String comment;
	@Column(name = "user_id")
	private String userId;
	@Column(name = "aid")
	private String answerId;
	@Column(name = "date")
	private Date date;
	
	public AnswerComments() 
	{
		
	}

	public AnswerComments(String commentId, String comment, String userId, String answerId, Date date) {
		this.commentId = commentId;
		this.comment = comment;
		this.userId = userId;
		this.answerId = answerId;
		this.date = date;
	}


	
	public String getCommentId() {
		return commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "AnswerComments [commentId=" + commentId + ", comment=" + comment + ", userId=" + userId + ", answerId="
				+ answerId + ", date=" + date + "]";
	}
	
	
	
}
