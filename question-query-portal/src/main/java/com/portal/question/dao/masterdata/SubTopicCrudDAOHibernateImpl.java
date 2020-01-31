package com.portal.question.dao.masterdata;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.portal.question.model.SubTopic;
import com.portal.question.model.Topics;

@Repository
public class SubTopicCrudDAOHibernateImpl implements SubTopicCrudDAO {

	// define field for entitymanager	
	private EntityManager entityManager;
		
	// set up constructor injection
	@Autowired
	public SubTopicCrudDAOHibernateImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}

	//Fetch Sub-topic List from database
	@Override
	public List<SubTopic> findAll() 
	{
		Session currentSession = entityManager.unwrap(Session.class);
		Query<SubTopic> theQuery =	currentSession.createQuery("from SubTopic", SubTopic.class);
		List<SubTopic> subTopic = theQuery.getResultList();
		return subTopic;
	}

	//Saves Sub-topic details
	@Override
	public SubTopic save(SubTopic subTopic) 
	{
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(new Topics(subTopic.getTopicName()));
		currentSession.saveOrUpdate(subTopic);
		return subTopic;
	}

	
	//Finding Sub-topic by ID
	@Override
	public SubTopic findById(String subTopicId) 
	{
		Session currentSession 	=	entityManager.unwrap(Session.class);	
		SubTopic theSubTopic 	=	currentSession.get(SubTopic.class, subTopicId);
		return theSubTopic;
	}
	


}







