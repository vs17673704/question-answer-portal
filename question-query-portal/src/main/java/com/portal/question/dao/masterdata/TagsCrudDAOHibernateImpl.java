package com.portal.question.dao.masterdata;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.portal.question.model.Tags;

@Repository
public class TagsCrudDAOHibernateImpl implements TagsCrudDAO {

	// define field for entitymanager	
	private EntityManager entityManager;
		
	// set up constructor injection
	@Autowired
	public TagsCrudDAOHibernateImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}

	//Fetch list of Tags from database
	@Override
	public List<Tags> findAll() 
	{
		Session currentSession = entityManager.unwrap(Session.class);
		Query<Tags> theQuery =	currentSession.createQuery("from Tags", Tags.class);
		List<Tags> tags= theQuery.getResultList();
		return tags;
	}

	//Deletes tag
	@SuppressWarnings("rawtypes")
	@Override
	public String delete(String tag) 
	{
		Session currentSession = entityManager.unwrap(Session.class);		
		Tags temptag = currentSession.get(Tags.class, tag);
		
		if(temptag == null)
		{
			return null;
		}
		else
		{
			Query theQuery = 	currentSession.createQuery("DELETE FROM Tags WHERE tag=:tag");
			theQuery.setParameter("tag", tag);
			theQuery.executeUpdate();
			
			//Deletes tag from Question-Tag mapping
			theQuery = 	currentSession.createQuery("DELETE FROM QuestionTag WHERE tag=:tag");
			theQuery.setParameter("tag", tag);
			theQuery.executeUpdate();
			
			//returns deleted tag after successful execution of code
			return tag + " deleted!";
		}
	}

	//Save and returns list of saved tage
	@Override
	public List<Tags> save(List<String> tags) 
	{
		Session currentSession = entityManager.unwrap(Session.class);

		for(String ls : tags)
		{
			currentSession.saveOrUpdate(new Tags(ls));
		}
		
		return findAll();
		
	}

	

}







