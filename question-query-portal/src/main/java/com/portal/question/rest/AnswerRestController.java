package com.portal.question.rest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portal.question.model.AnswerComments;
import com.portal.question.model.AnswerLike;
import com.portal.question.model.Answers;
import com.portal.question.model.Users;
import com.portal.question.service.AnswerService;
import com.portal.question.service.MasterDataService;
import com.portal.question.service.QuestionService;

@RestController
@RequestMapping("/answer")
public class AnswerRestController {

	private QuestionService questionService;
	private AnswerService answerService;
	private MasterDataService masterDataService;
	
	// To inject dependency
	@Autowired
	public AnswerRestController(AnswerService theAnswerService, QuestionService theQuestionService, MasterDataService theMasterDataServic) {
		answerService = theAnswerService;
		questionService = theQuestionService;
		masterDataService = theMasterDataServic;
	}
	
	
	//Sending like info to database for an existing answer submitted by an existing user
	@PostMapping("/like")
	public void likeQuestion(@RequestBody AnswerLike answerLike) 
	{
		String exception = "";
		Users theUser = masterDataService.findUserById(answerLike.getUserId());
		
		//Check for existence of answer in database 
		Answers theAnswer = answerService.findAnswerById(answerLike.getAnswerId());
		
		//Preparing error messge to display while throwing an exception
		if(answerLike.getAnswerId()==""||answerLike.getUserId()=="")
			exception="Both IDs are required.";
		
		if((theUser==null||theAnswer==null)&&(answerLike.getUserId()!=""||answerLike.getAnswerId()!=""))
			exception+="Please check provided IDs for their availability in database.";
		
		//Throws exception for answer missing in database
		if(exception!="")
			throw new RuntimeException(exception);
		
		answerService.saveAnswerLiked(answerLike);
	}
	
	//Insert Answer details to database for an existing question and submitted by an eisting user
	@PostMapping("/submit")
	public String getQuestionDetails( @RequestBody Answers answer)
	{
		String missing = "";
		
		//Checking for empty or non-existing IDs in database
		if((answer.getQuestionId()==null||answer.getQuestionId()=="")
			||(answer.getUserId()==null||answer.getUserId()=="")
			||(answer.getAnswer()==null||answer.getAnswer()==""))
		{
			throw new RuntimeException("Sub-topic ID, user ID or Question required!");
		}
		
		//Preparing message to display while throwing exception
			if(questionService.findQuestionById(answer.getQuestionId())==null)
			{
				missing+="Question";
			}
			
			if(masterDataService.findUserById(answer.getUserId())==null)
			{
				if(missing!="")	missing+=" & ";
				missing+="User ";
			}
			if(missing!="")
				throw new RuntimeException(missing+" does not exist!");
			// Check whether no. of character in submitted answer is between the count 50 and 500.
			if(answer.getAnswer().length()<50 && answer.getAnswer().length()>500)
				throw new RuntimeException("Characters in the answer must be under the range of 50 to 500 characters!");
		//Returns answer ID for submitted answer.
		return answerService.saveAnswer(answer);
	}
	
	@PostMapping("/comment")
	public String getComments( @RequestBody AnswerComments comment)
	{
		String missing = "";
		
		//Checking for empty or non-existing IDs in database
			if((comment.getAnswerId()== null || comment.getAnswerId()=="")
			 ||(comment.getUserId()  == null || comment.getUserId()  =="")
			 ||(comment.getComment() == null || comment.getComment() ==""))
			{
				throw new RuntimeException("Answer ID, user ID or comment required!");
			}
			
		//Preparing message to display while throwing exception
			if(answerService.findAnswerById(comment.getAnswerId())==null)
			{
				missing+="Answer";
			}
			
			if(masterDataService.findUserById(comment.getUserId())==null)
			{
				if(missing!="")	missing+="& ";
				missing+=" User ";
			}
			if(missing!="")
			{
				throw new RuntimeException(missing+" does not exist!");
			}
			
		// Check whether no. of character in submitted comment is between the count 50 and 500.
			if(comment.getComment().length()<50 && comment.getComment().length()>500)
			{
				throw new RuntimeException("Characters in the comment must be under the range of 50 to 500 characters!");
			}
		
		//Returns comment ID for submitted comment.
		return answerService.saveComment(comment);
	}
	
	// '{qid}' is Question ID for which theanswer details will be returned.
	@SuppressWarnings("rawtypes")
	@GetMapping("{qid}")
	public Map getMap(@PathVariable(value = "qid") String questionId)
	{
		Map result = answerService.getSearchResult(questionId);
		
		//Returns results for query if found otherwise throws exception
		if(result == null)
			throw new RuntimeException("No results found for the current query!");
		else
			return result;
		
	}
	
	
}










