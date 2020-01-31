package com.portal.question.service;

import java.util.List;
import java.util.Map;

import com.portal.question.model.QuestionLike;
import com.portal.question.model.Questions;
import com.portal.question.rest.QuestionBuffer;

@SuppressWarnings("rawtypes")
public interface QuestionService {

	public Questions findQuestionById(String questionId);

	public void saveQuestionLiked(QuestionLike questionLike);

	public String saveQuestion(QuestionBuffer questionBuffer);

	public Map getSearchResults(List<String> company, List<String> subtopic, List<String> tag, Integer like, String date);
	
	
}
