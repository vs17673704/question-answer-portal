package com.portal.question.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "question_company_mapping")
public class QuestionCompanyMapping 
{
	@Id
	@Column(name = "qid", nullable=false)
	private String questionId;
	@Id
	@Column(name = "company_id", nullable=false)
	private String companyId;
	
	public QuestionCompanyMapping() 
	{
		
	}

	public QuestionCompanyMapping(String questionId, String companyId) {
		this.questionId = questionId;
		this.companyId = companyId;
	}

	@Override
	public String toString() {
		return "QuestionCompanyMapping [questionId=" + questionId + ", companyId=" + companyId + "]";
	}

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	
	
}
