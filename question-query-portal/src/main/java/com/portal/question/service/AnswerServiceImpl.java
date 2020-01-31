package com.portal.question.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portal.question.dao.AnswerDAO;
import com.portal.question.model.AnswerComments;
import com.portal.question.model.AnswerLike;
import com.portal.question.model.Answers;

@Service
public class AnswerServiceImpl implements AnswerService {

	private AnswerDAO answerDAO;
	
	@Autowired
	public AnswerServiceImpl(AnswerDAO theAnswerDAO) {
		answerDAO = theAnswerDAO;
	}
	
	@Override 
	@Transactional	
	public Answers findAnswerById(String answerId)	
	{	
		return answerDAO.findAnswerById(answerId);	
	}

	@Override 
	@Transactional	
	public void saveAnswerLiked(AnswerLike questionLike) 		
	{	
		answerDAO.likeAnswer(questionLike);	
	}
	
	@Override
	@Transactional
	public String saveAnswer(Answers answer) 
	{
		return answerDAO.save(answer);
	}

	@Override
	@Transactional
	public Map<?,?> getSearchResult(String questionId) 
	{
		return answerDAO.searchResult(questionId);
	}

	@Override
	@Transactional
	public String saveComment(AnswerComments comment) 
	{
		return answerDAO.saveComment(comment);
	}
	
}






