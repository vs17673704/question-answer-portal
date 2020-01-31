package com.portal.question.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.portal.question.model.QuestionLike;
import com.portal.question.model.Questions;
import com.portal.question.model.Users;
import com.portal.question.service.MasterDataService;
import com.portal.question.service.QuestionService;

@RestController
@RequestMapping("/question")
public class QuestionRestController {

	private QuestionService questionService;
	private MasterDataService masterDataService;
	
	@Autowired
	public QuestionRestController(QuestionService theEmployeeService,MasterDataService theMasterDataService ) {
		questionService = theEmployeeService;
		masterDataService = theMasterDataService;
	}
	

	@PostMapping("/like")
	public void likeQuestion(@RequestBody QuestionLike questionLike) 
	{
		String exception = "";
		Users theUser = masterDataService.findUserById(questionLike.getUserId());
		Questions theQuestion = questionService.findQuestionById(questionLike.getQuestionId());
		
		if(questionLike.getQuestionId()==""||questionLike.getUserId()=="")
			exception="Both IDs are required.";
		
		if((theUser==null||theQuestion==null)&&(questionLike.getUserId()!=""||questionLike.getQuestionId()!=""))
			exception+="Please check provided IDs for their availability in database.";
		
		if(exception!="")
			throw new RuntimeException(exception);
		
		questionService.saveQuestionLiked(questionLike);
	}
		
	
	@PostMapping("/submit")
	public String getQuestionDetails( @RequestBody QuestionBuffer questionBuffer)
	{
		String missing = "";
			if((questionBuffer.getSubTopicId()==null||questionBuffer.getSubTopicId()=="")
				||(questionBuffer.getUserId()==null||questionBuffer.getUserId()=="")
				||(questionBuffer.getQuestion()==null||questionBuffer.getQuestion()==""))
			{
				throw new RuntimeException("Sub-topic ID, use ID or Question required!");
			}
			
			if(masterDataService.findCompanyById(questionBuffer.getCompanyId())==null)
				missing+="Company";
			
			if(masterDataService.findSubTopicById(questionBuffer.getSubTopicId())==null)
			{
				if(missing!="")	missing+=", ";
				missing+="Sub-topic";
			}
			
			if(masterDataService.findUserById(questionBuffer.getUserId())==null)
			{
				if(missing!="")	missing+=", ";
				missing+=" User ";
			}
			
			if(missing!="")
				throw new RuntimeException(missing+" does not exist!");
		
			if(questionBuffer.getQuestion().length()<50 && questionBuffer.getQuestion().length()>500)
				throw new RuntimeException("Characters in question must be under the range of 50 to 500 characters!");

		return questionService.saveQuestion(questionBuffer);
	}
	
	
	@SuppressWarnings("rawtypes")
	@GetMapping("/search")
	Map searchResult(@RequestParam(required = false) List<String> company,
					 @RequestParam(required = false) List<String> subtopic,
					 @RequestParam(required = false) List<String> tag,
					 @RequestParam(required = false) Integer like,
					 @RequestParam(required = false) String date)
	{
		Map result = questionService.getSearchResults(company,subtopic,tag,like,date);
		
		if(result == null)
			throw new RuntimeException("No results found for the current query!");
		else
			return result;
		
	}
	
	
}










