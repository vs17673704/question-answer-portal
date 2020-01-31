package com.portal.question.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portal.question.dao.masterdata.CompanyCrudDAO;
import com.portal.question.dao.masterdata.SubTopicCrudDAO;
import com.portal.question.dao.masterdata.TagsCrudDAO;
import com.portal.question.dao.masterdata.TopicsCrudDAO;
import com.portal.question.dao.masterdata.UserCrudDAO;
import com.portal.question.model.Company;
import com.portal.question.model.SubTopic;
import com.portal.question.model.Tags;
import com.portal.question.model.Topics;
import com.portal.question.model.Users;

@Service
public class MasterDataServiceImpl implements MasterDataService 
{

	
	private TagsCrudDAO tagsCrudDAO;
	private CompanyCrudDAO companyCrudDAO;
	private SubTopicCrudDAO subTopicCrudDAO;
	private TopicsCrudDAO topicCrudDAO;
	private UserCrudDAO userCrudDAO;
	
	
	@Autowired
	public MasterDataServiceImpl(TagsCrudDAO temptagsCrudDAO, CompanyCrudDAO tempcompanyCrudDAO, SubTopicCrudDAO tempsubTopicCrudDao, 
								 TopicsCrudDAO temptopicCrudDAO, UserCrudDAO tempuserCrudDAO) 
	{
		tagsCrudDAO = temptagsCrudDAO;
		companyCrudDAO = tempcompanyCrudDAO;
		topicCrudDAO = temptopicCrudDAO;
		subTopicCrudDAO = tempsubTopicCrudDao;
		userCrudDAO = tempuserCrudDAO;
	}
	
	
	/*----------------------------------------------------------------------------TAGS----------------------------------------------------------------------------------*/
	@Override	@Transactional	public List<Tags> findAllTag() 					 	{		return tagsCrudDAO.findAll();			}

	@Override	@Transactional	public String deleteTag(String tag) 			 	{		return tagsCrudDAO.delete(tag);			}

	@Override	@Transactional	public List<Tags> saveTag(List<String> tags) 				{		return tagsCrudDAO.save(tags);			}

	
	/*--------------------------------------------------------------------------TOPICS----------------------------------------------------------------------------------*/
	@Override	@Transactional	public List<Topics> findAllTopics() 				{		return topicCrudDAO.findAll();			}

	@Override	@Transactional	public List<String> saveTopics(Topics topics) 		{		return topicCrudDAO.save(topics);		}
	
	@Override	@Transactional	public Topics findTopic(String topic) 				{		return topicCrudDAO.findTopic(topic);	}
	
	/*---------------------------------------------------------------------------COPAMAY------------------------------------------------------------------------------*/
	@Override	@Transactional	public List<Company> findAllCompanies() 			{	return companyCrudDAO.findAll();			}

	@Override	@Transactional	public Company findCompanyById(String companyId)	{	return companyCrudDAO.findById(companyId);	}

	@Override	@Transactional	public Company saveCompany(Company company) 		{	return companyCrudDAO.save(company);		}

	@Override	@Transactional	public Company updateCompany(Company company) 		{	return companyCrudDAO.save(company);		}

	@Override	@Transactional	public String deleteCompanyById(String companyId) 	{	return companyCrudDAO.deleteById(companyId);}
	
	/*-------------------------------------------------------------------------SUBTOPICS-------------------------------------------------------------------------------*/
	@Override	@Transactional	public List<SubTopic> findAllSubTopics()			{	return subTopicCrudDAO.findAll();			}

	@Override	@Transactional	public SubTopic saveSubTopic(SubTopic subTopic)		{	return subTopicCrudDAO.save(subTopic);		}

	@Override	@Transactional	public SubTopic findSubTopicById(String subTopicId)	{	return subTopicCrudDAO.findById(subTopicId);}

	/*---------------------------------------------------------------------------USER--------------------------------------------------------------------------------*/
	@Override	@Transactional	public Users saveUser(Users userdetails)			{	return userCrudDAO.save(userdetails);		}

	@Override	@Transactional	public Users findUserById(String userId)			{	return userCrudDAO.findById(userId);		}
	
	
}






