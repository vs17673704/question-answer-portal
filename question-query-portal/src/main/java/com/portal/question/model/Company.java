package com.portal.question.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "company")
public class Company 
{
	@Id
	@Column(name = "company_id", unique = true, nullable=false)
	private String companyId;
	@Column(name = "company_name")
	private String companyName;
	
	public Company() 
	{
		
	}

	public Company(String companyId, String companyName) {
		this.companyId = companyId;
		this.companyName = companyName;
	}

	@Override
	public String toString() {
		return "Company [companyId=" + companyId + ", companyName=" + companyName + "]";
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
}
