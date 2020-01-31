package com.portal.question.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.portal.question.model.AnswerComments;
import com.portal.question.model.AnswerLike;
import com.portal.question.model.Answers;
import com.portal.question.rest.CommentBuffer;

@Repository
public class AnswerDAOHibernateImpl implements AnswerDAO {

	// define field for entitymanager	
	private EntityManager entityManager;
		
	// set up constructor injection
	@Autowired
	public AnswerDAOHibernateImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}
	
	//Finding answer for given ID
	@Override
	public Answers findAnswerById(String answerId) 
	{
		Session currentSession 	=	entityManager.unwrap(Session.class);			
		Answers theAnswer 	=	currentSession.get(Answers.class, answerId);
		return theAnswer;
	}

	//Insert answer like data for existing Answer by an existing User
	@Override
	public void likeAnswer(AnswerLike answerLike) 
	{
		Session currentSession 	=	entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(answerLike);
	}
	
	//Saves answer detail for the given Question ID to the database
	@Override
	public String save(Answers answer) 
	{
		Session currentSession 	=	entityManager.unwrap(Session.class);
		
		//(new SimpleDateFormat("ddmm")).format(new Date())--> Formatting as date before submission
		String AID = "AN" + (new SimpleDateFormat("ddmm")).format(new Date()) + answer.getUserId();  
		currentSession.saveOrUpdate(new Answers(AID, answer.getAnswer(), answer.getUserId(), answer.getQuestionId()));
		return "Answer ID for submitted answer is: " + AID;
	}
	
	//Return Comment ID for an existing answer made by an existing user
	@Override
	public String saveComment(AnswerComments comment) 
	{
		Session currentSession 	=	entityManager.unwrap(Session.class);
		java.sql.Date date=new java.sql.Date(System.currentTimeMillis());
		String CMID = "CM" +(new SimpleDateFormat("ddmm")).format(new Date()) + comment.getUserId();
		
		currentSession.saveOrUpdate(new AnswerComments(CMID, comment.getComment(), comment.getUserId(), comment.getAnswerId(), date));
		return "Comment ID for submitted comment is: " + CMID;
	}
	
	// 
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Map<String, HashMap<String, List<Object>>> searchResult(String questionId) 
	{
		Map question_map                = new LinkedHashMap();
		Map<String, List> answer_res	= new HashMap<String, List>();
		Map<String, String> answer_map	= new HashMap<String, String>();
		Map<String, Object> answer_user = new HashMap<String, Object>();
		Map<String, Long> answer_like 	= new HashMap<>();
		
		Set<Object> company_list = new HashSet<Object>();
		Set<Object> tag_list 	 = new HashSet<Object>();
		Set<Object> topic_list 	 = new HashSet<Object>();
		List<Object> ans_list 	 = new LinkedList<Object>();
		
		List<CommentBuffer> comments = new ArrayList<>();
		Long ans_like;
		
		String answer = null;
		
		Session currentSession 	=	entityManager.unwrap(Session.class);
		
		// Query made to fetch Question, company name, tag and topic for the given question ID
		Query<Object[]> query = currentSession.createQuery("SELECT DISTINCT q.question, c.companyName, qt.tag, st.topicName FROM Questions q, QuestionTag qt, " + 
				 										   " QuestionCompanyMapping qc, Company c, SubTopic st WHERE q.questionId = :qid AND qc.questionId = :qid "
				 										   + "AND qt.questionId = :qid AND qc.companyId = c.companyId AND q.subtopicId = st.subtopicId")
				  							  .setParameter("qid", questionId);

		List<Object[]> qlist = query.getResultList();
		//Returns NULL if query contains no result
		if(qlist.isEmpty())
			return null;
		//Fetch Question ID from the first index of the first array from the list
		String question = (String) (Arrays.asList(qlist.get(0))).get(0);
		
		//Creating structure for Question Detials
		question_map.put("Question", question);
		for(Object[] arr : qlist)
		{
			company_list.add(arr[1]);
			topic_list.add(arr[2]);
			tag_list.add(arr[3]);
		}
		
		// Query to fetch Answer, User ID, Comment, user  name and date for the given Question ID
		query   =   currentSession.createQuery("SELECT DISTINCT a.answer,a.userId, a.answerId, ac.comment, u.userName, ac.date FROM "+
	            " Answers a, AnswerComments ac, Users u WHERE a.questionId= :qid AND a.answerId=ac.answerId AND ac.userId=u.userId ORDER BY a.answerId" )
				.setParameter("qid", questionId);
		
		List<Object[]> list = query.getResultList();
		
		// Returns NULL for no results found for currect query
		if(list.isEmpty())
			return null;
		
		//Fetch Answer ID at from third index of the first array from the list
		String tempans = (String) (Arrays.asList(list.get(0))).get(2);
		String tempUser = null;
		for(Object[] arr : list)
		{
			tempUser = (String)arr[1];
			
			//preventing from executing same operation redundently inside the loop
			if(!tempans.equals(arr[2]))
			{
				//Query to get no. of likes on Answer
				ans_like =	(long) currentSession.createQuery("SELECT COUNT(al.answerId) FROM AnswerLike al WHERE al.answerId = :aid")
												 .setParameter("aid", tempans)
												 .uniqueResult();
				//Structuring the Answer details
				answer_like.put("Like", ans_like);
				answer_user.put("User ID", tempUser);
				answer_map.put("Answer", answer);
				ans_list.add(answer_map);
				ans_list.add(answer_like);
				ans_list.add(answer_user);
				ans_list.add(comments);
				answer_res.put(tempans, ans_list);
				tempans = (String)arr[2];
				
				//Clearing for new data
				comments 	= new ArrayList<CommentBuffer>();
				ans_list 	= new LinkedList<Object>();
				answer_user.clear();
				answer_like.clear();
				
			}
			answer = (String)arr[0];
			//Saving comment details to CommentBuffer class List
			comments.add(new CommentBuffer((String)arr[3],(String)arr[4], new SimpleDateFormat("dd-MM-yyyy").format(arr[5])));
			
		}
		
		
		ans_like = (Long) currentSession.createQuery("SELECT COUNT(al.answerId) FROM AnswerLike al WHERE al.answerId = :aid")
											  .setParameter("aid", tempans)	
											  .uniqueResult();
			
		answer_like.put("Like", ans_like);
		answer_user.put("User ID", tempUser);
		answer_map.put("Answer", answer);
		ans_list.add(answer_map);
		ans_list.add(answer_like);
		ans_list.add(answer_user);
		ans_list.add(comments);
		answer_res.put(tempans, ans_list);
		
		question_map.put("Answer Info",answer_res);
		
		//Query to get likes for the given question ID
		Long ques_like = (long)currentSession.createQuery("SELECT COUNT(questionId) FROM QuestionLike WHERE questionId = :qid")
											 .setParameter("qid", questionId)
											 .uniqueResult();
		//Preparing final structure for response
		question_map.put("Company", company_list);
		question_map.put("Topic", topic_list);
		question_map.put("Tag", tag_list);
		question_map.put("Like", ques_like);
				
		return question_map;
	}
	
}







