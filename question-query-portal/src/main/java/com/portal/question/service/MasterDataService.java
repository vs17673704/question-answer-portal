package com.portal.question.service;

import java.util.List;

import com.portal.question.model.Company;
import com.portal.question.model.SubTopic;
import com.portal.question.model.Tags;
import com.portal.question.model.Topics;
import com.portal.question.model.Users;

public interface MasterDataService {
	
	/////////////////////////////////////////////////////////////////////         TAGS         \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	public List<Tags> findAllTag();
	
	public String deleteTag(String tag);

	public List<Tags> saveTag(List<String> listOfTags);
	
	/////////////////////////////////////////////////////////////////////        TOPICS        \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	public List<Topics> findAllTopics();
	
	public List<String> saveTopics(Topics topic);
	
	public Topics findTopic(String topic);
	
	////////////////////////////////////////////////////////////////////        COMPANY        \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	public List<Company> findAllCompanies();
	
	public Company findCompanyById(String companyId);
	
	public Company saveCompany(Company company);
	
	public Company updateCompany(Company company);
	
	public String deleteCompanyById(String companyId);
	
	
	////////////////////////////////////////////////////////////////////       SUB-TOPICS      \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	public List<SubTopic> findAllSubTopics();
	
	public SubTopic saveSubTopic(SubTopic subTopic);
	
	public SubTopic findSubTopicById(String subTopicId);

	/////////////////////////////////////////////////////////////////////         USER         \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	public Users saveUser(Users userdetails);

	public Users findUserById(String userId);
	
}
