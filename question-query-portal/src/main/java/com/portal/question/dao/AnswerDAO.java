package com.portal.question.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.portal.question.model.AnswerComments;
import com.portal.question.model.AnswerLike;
import com.portal.question.model.Answers;

public interface AnswerDAO {

	public Answers findAnswerById(String answerId);

	public void likeAnswer(AnswerLike answerLike);

	public String save(Answers answer);

	public Map<String, HashMap<String, List<Object>>> searchResult(String questionId);

	public String saveComment(AnswerComments comment);
	
}
