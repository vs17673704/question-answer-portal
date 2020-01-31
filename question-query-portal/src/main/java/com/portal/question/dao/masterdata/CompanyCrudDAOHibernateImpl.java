package com.portal.question.dao.masterdata;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.portal.question.model.Company;

@Repository
public class CompanyCrudDAOHibernateImpl implements CompanyCrudDAO
{

	// define field for entitymanager	
	private EntityManager entityManager;
		
	// set up constructor injection
	@Autowired
	public CompanyCrudDAOHibernateImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}
	
	//Feching Company list from database
	@Override
	public List<Company> findAll() 
	{
		Session currentSession = entityManager.unwrap(Session.class);
		Query<Company> theQuery =	currentSession.createQuery("from Company", Company.class);
		List<Company> company = theQuery.getResultList();
		return company;
	}

	//Finding Company Details by given ID
	@Override
	public Company findById(String companyId) 
	{
		Session currentSession 	=	entityManager.unwrap(Session.class);	
		Company theCompany 	=	currentSession.get(Company.class, companyId);
		return theCompany;
	}

	//Saving Company details to database
	@Override
	public Company save(Company company) 
	{
		Session currentSession = entityManager.unwrap(Session.class);	
		currentSession.saveOrUpdate(company);
		return company;
	}

	//Deleting Company Details for the given Company ID
	@Override
	public String deleteById(String companyId) {
		
		Session currentSession = entityManager.unwrap(Session.class);		
		@SuppressWarnings("rawtypes")
		Query theQuery = 	currentSession.createQuery("DELETE FROM Company WHERE companyId=:companyId");
		theQuery.setParameter("companyId", companyId);
		theQuery.executeUpdate();
		
		//Deleting Company mapping after deleting the company details
		theQuery = 	currentSession.createQuery("DELETE FROM QuestionCompanyMapping WHERE companyId=:companyId");
		theQuery.setParameter("companyId", companyId);
		theQuery.executeUpdate();
		
		//Returns Company ID after successful execution of code
		return "Deleted company id - " + companyId;
		
	}


}







