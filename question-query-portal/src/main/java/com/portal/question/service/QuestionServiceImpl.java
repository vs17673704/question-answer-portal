package com.portal.question.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portal.question.dao.QuestionDAO;
import com.portal.question.model.QuestionLike;
import com.portal.question.model.Questions;
import com.portal.question.rest.QuestionBuffer;

@Service
public class QuestionServiceImpl implements QuestionService 
{

	private QuestionDAO questionDAO;
	;
	
	@Autowired
	public QuestionServiceImpl(QuestionDAO theQuestionDAO) {
		questionDAO = theQuestionDAO;
		
	}

	@Override 
	@Transactional	
	public Questions findQuestionById(String questionId)	
	{	
		return questionDAO.findQuestionById(questionId);	
	}

	@Override 
	@Transactional	
	public void saveQuestionLiked(QuestionLike questionLike) 		
	{	
		questionDAO.likeQuestion(questionLike);	
	}

	@Override
	@Transactional
	public String saveQuestion(QuestionBuffer questionBuffer) 
	{
		return questionDAO.save(questionBuffer);
	}

	@SuppressWarnings("rawtypes")
	@Override
	@Transactional
	public Map getSearchResults(List<String> company, List<String> subtopic, List<String> tag, Integer like, String date) 
	{
		return questionDAO.getRestults(company, subtopic, tag, like, date);
	}

}






