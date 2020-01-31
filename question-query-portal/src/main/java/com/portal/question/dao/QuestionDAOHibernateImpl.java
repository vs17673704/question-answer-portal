package com.portal.question.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.portal.question.model.QuestionCompanyMapping;
import com.portal.question.model.QuestionLike;
import com.portal.question.model.Questions;
import com.portal.question.model.Tags;
import com.portal.question.rest.QuestionBuffer;

@Repository
public class QuestionDAOHibernateImpl implements QuestionDAO {
	
	private EntityManager entityManager;
		
	//Injecting dependency
	@Autowired
	public QuestionDAOHibernateImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}

	//Fetching Question Details for the given ID
	@Override
	public Questions findQuestionById(String questionId) 
	{
		Session currentSession 	=	entityManager.unwrap(Session.class);			
		Questions theQuestion 	=	currentSession.get(Questions.class, questionId);
		return theQuestion;
	}

	// Saving like details for an existing question submitted by an existing user
	@Override
	public void likeQuestion(QuestionLike questionLike) 
	{
		Session currentSession 	=	entityManager.unwrap(Session.class);
		currentSession.save(questionLike);
	}

	// Question details in the database
	@Override
	public String save(QuestionBuffer questionBuffer) 
	{
		Session currentSession 	=	entityManager.unwrap(Session.class);
		java.sql.Date date=new java.sql.Date(System.currentTimeMillis());
		String QID = "Q" +(new SimpleDateFormat("ddmm")).format(new Date()) + questionBuffer.getUserId();
		
			  currentSession.saveOrUpdate(new Questions(QID,questionBuffer.getQuestion(),date,questionBuffer.getUserId(),
						questionBuffer.getSubTopicId() ));
		  //Creating entry for submitted question in Question-Tag mapping
		if(questionBuffer.getTaglist() != null)
			for(String tag:questionBuffer.getTaglist())
			{	
				@SuppressWarnings("rawtypes")
				Query query = currentSession.createNativeQuery("INSERT INTO question_tag(qid, tag) VALUES(?,?)")
							 .setParameter(1,QID)
							 .setParameter(2, tag);
				query.executeUpdate();
				currentSession.saveOrUpdate(new Tags(tag));
			}
		
		//Creating entry for submitted question in Question-Company mapping
		currentSession.saveOrUpdate(new QuestionCompanyMapping(QID, questionBuffer.getCompanyId()));
		
		//Returns ID for the submitted question
		return "Question ID for submitted question is: " + QID;
	}

	
	//Query for question search
	@SuppressWarnings({ "rawtypes", "unchecked", "deprecation" })
	@Override
	public Map getRestults(List<String> company, List<String> subtopic, List<String> tag, Integer like, String date) 
	{
		
		SortedMap result_map = new TreeMap();
		Map question_map  = new HashMap();
		Map company_map   = new HashMap();
		Map qlike_map 	  = new HashMap(); 
		Map answer_map    = new HashMap();
		Map tag_map       = new HashMap();
		
		List qid_list    = new ArrayList();
		Set tag_list = new HashSet();
		Object answer    =null;
		String question =null, companyName = null; 
		Long qlike    =null;
		
		Session currentSession 	=	entityManager.unwrap(Session.class);
		String quesLike = "", companyList ="", tagList ="", subTopicList ="", Date = "";
		Date SQLdate = null;
		
		// Gets Question IDs with likes greater than input value
		if(like!=null)
		{
			Query query1 = currentSession.createQuery("SELECT e.questionId FROM QuestionLike e GROUP BY e.questionId having COUNT(e.questionId)>:l");
			query1.setInteger("l", like);
			List<String> q1l = query1.list();
			
			//Preparexs search string for likes
			if(!q1l.isEmpty())
			{
				quesLike="and q.qid IN (";
				
				for(String s:q1l )
					if(s.equals(q1l.get(q1l.size()-1)))
						quesLike=quesLike+"\""+s+"\""+")";
					else
						quesLike=quesLike+"\""+s+"\",";
			}
			
		}
		
		//Prepares string for search by date
		if(date!=null)
		{
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); // your template here
			java.util.Date dateStr = null;
			try {
				dateStr = formatter.parse(date);
			} catch (ParseException e) {
					e.printStackTrace();
			}
			SQLdate = new java.sql.Date(dateStr.getTime());
			Date = " and q.date = \""+ SQLdate +"\" ";
		}
		
		//Prepares string for search by provided company list
		if(company != null)
		{	
			companyList = "and cp.company_name in (";
		
			for(String s : company )
				if(s.equals(company.get(company.size()-1)))
					companyList += "\"" + s + "\"" + ")";
				else
					companyList += "\"" + s + "\",";
		}
		
		//Prepares string for search by provided sub-topic list
		
		if(subtopic != null)
		{	
			subTopicList = "and st.subtopic in (";
		
			for(String s : subtopic )
			{
				if(s.equals(subtopic.get(subtopic.size()-1)))
					subTopicList += "\"" + s + "\"" + ")";
				else
					subTopicList += "\"" + s + "\",";
			}
		}
		
		//Prepares string for search by provided tag list
		if(tag != null)
		{	
			tagList = "and qt.tag in (";
		
			for(String s : tag )
			{
				if(s.equals(tag.get(tag.size()-1)))
					tagList += "\"" + s + "\"" + ")";
				else
					tagList += "\"" + s + "\",";
			}
		}
		
		
		//Performs search based on given search inputs
		Query query = currentSession.createNativeQuery("SELECT DISTINCT q.qid, q.question, cp.company_name, qt.tag  FROM  questions q, question_tag qt, "+
			      									   " question_company_mapping qc, question_like ql, company cp, subtopic st WHERE q.qid = qt.qid and "
			      									   + " qt.qid = qc.qid and qc.company_id=cp.company_id AND q.subtopic_id=st.subtopic_id " + companyList 
			      									   + tagList + quesLike + subTopicList + Date + " ORDER BY q.qid");
		
		List<Object[]> list = query.list();
		
		//Returns null for no query results found
		if(list.isEmpty())
			return null;
		
		//fetched Question ID from the query list
		String tempqid = (String) (Arrays.asList(list.get(0))).get(0);
		for(Object[] arr : list)
		{
			
			if(!tempqid.equals(arr[0]))
			{
				//Get Answer with most likes.
				answer = currentSession.createNativeQuery("SELECT a.answer FROM answers a " + 
						"INNER JOIN (SELECT aid, COUNT(aid) AS alc FROM answer_like " + 
						"WHERE aid IN (SELECT aid FROM answers WHERE qid=\""+ tempqid +"\") GROUP BY aid) AS AL " + 
						" ON a.aid=AL.aid HAVING MAX(AL.alc)").uniqueResult();

				question_map.put("Question",question);
				qid_list.add(question_map);
				if(answer != null)
				{
					answer_map.put("Answer",answer );
					qid_list.add(answer_map);
				}	
				company_map.put("Companies", companyName);
				qid_list.add(company_map);
				//No. of likes for the question
				qlike=new Long((long) currentSession.createQuery("SELECT COUNT(questionId) FROM QuestionLike WHERE questionId = :tempqid")
						  .setParameter("tempqid", tempqid)
						  .uniqueResult());
				qlike_map.put("Like", qlike);
				qid_list.add(qlike_map);
				tag_map.put("Tags", tag_list);
				qid_list.add(tag_map);
				
				result_map.put(tempqid,qid_list);
				tempqid = (String) arr[0];
				//Clearing elements for new input
				qid_list	 = new ArrayList();
				question     = null;
				tag_list.clear();
				question_map = new HashMap();
				company_map  = new HashMap();
				answer_map   = new HashMap();
				qlike_map    = new HashMap();
				tag_map      = new HashMap();
			}
			question = (String)arr[1];
			companyName = (String)arr[2];
			tag_list.add(arr[3]);
			
		}
		
		answer = currentSession.createNativeQuery("SELECT a.answer FROM answers a " + 
				"INNER JOIN (SELECT aid, COUNT(aid) AS alc FROM answer_like " + 
				"WHERE aid IN (SELECT aid FROM answers WHERE qid=\""+ tempqid +"\") GROUP BY aid) AS AL " + 
				" ON a.aid=AL.aid HAVING MAX(AL.alc)").uniqueResult();
		
		qlike=new Long((long) currentSession.createQuery("SELECT COUNT(questionId) FROM QuestionLike WHERE questionId = :tempqid")
				  .setParameter("tempqid", tempqid)
				  .uniqueResult());
	
		question_map.put("Question",question);
		qid_list.add(question_map);
		if(answer != null)
		{
			answer_map.put("Answer",answer );
			qid_list.add(answer_map);
		}
		company_map.put("Companies", companyName);
		qid_list.add(company_map);
		qlike_map.put("Like", qlike);
		qid_list.add(qlike_map);
		tag_map.put("Tags", tag_list);
		qid_list.add(tag_map);
		
		result_map.put(tempqid,qid_list);
	
		return result_map;
	}
	
}







