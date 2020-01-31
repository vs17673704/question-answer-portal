package com.portal.question.dao.masterdata;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.portal.question.model.Users;

@Repository
public class UserCrudDAOHibernateImpl implements UserCrudDAO
{
	//Defining field for Entity manager	
	private EntityManager entityManager;
	
	//Setting up dependency injection
	@Autowired
	public UserCrudDAOHibernateImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}
	
	//Finds User by given User ID
	@Override
	public Users findById(String userId) 
	{
		Session currentSession 	=	entityManager.unwrap(Session.class);	
		Users theUser 	=	currentSession.get(Users.class, userId);
		return theUser;
	}

	//Returns User details after save to the database
	@Override
	public Users save(Users userdetails) 
	{
		Session currentSession = entityManager.unwrap(Session.class);	
		currentSession.saveOrUpdate(userdetails);
		return userdetails;
	}


}







