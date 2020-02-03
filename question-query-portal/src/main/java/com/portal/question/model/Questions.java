package com.portal.question.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "questions")
public class Questions {
		
			@Id
			@Column(name = "qid", unique = true, nullable=false )
			private String questionId;
			@Column(name = "question")
			private String question;
			@Column(name = "date")
			private Date date;
			@Column(name = "subtopic_id")
			private String subtopicId;
			@Column(name = "user_id")
			private String userId;
			
			
			public Questions() {
				// TODO Auto-generated constructor stub
			}

			public Questions(String questionId, String question, Date date, String userId, String subtopicId) {
				this.questionId = questionId;
				this.question = question;
				this.subtopicId = subtopicId;
				this.userId = userId;
				this.date = date;
			}

			public String getQuestionId() {
				return questionId;
			}

			public void setQuestionId(String qustionId) {
				this.questionId = qustionId;
			}

			public String getQuestion() {
				return question;
			}

			public void setQuestion(String question) {
				this.question = question;
			}

			public Date getDate() {
				return date;
			}

			public void setDate(Date date) {
				this.date = date;
			}
			
			public String getSubtopicId() {
				return subtopicId;
			}

			public void setSubtopicId(String subtopicId) {
				this.subtopicId = subtopicId;
			}


			public String getUserId() {
				return userId;
			}

			public void setUserId(String userId) {
				this.userId = userId;
			}
			

			@Override
			public String toString() {
				return "Questions [questionId=" + questionId + ", question=" + question + ", date=" + date
						 + ", subtopicId=" + subtopicId + ", userId="
						+ userId + "]";
			}
			
			
			
}
