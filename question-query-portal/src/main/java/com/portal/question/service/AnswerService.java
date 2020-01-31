package com.portal.question.service;

import java.util.Map;

import com.portal.question.model.AnswerComments;
import com.portal.question.model.AnswerLike;
import com.portal.question.model.Answers;

public interface AnswerService {

	public Answers findAnswerById(String answerId);

	public void saveAnswerLiked(AnswerLike questionLike);

	public String saveAnswer(Answers answer);

	public Map<?, ?> getSearchResult(String questionId);

	public String saveComment(AnswerComments comment);	
	
}
