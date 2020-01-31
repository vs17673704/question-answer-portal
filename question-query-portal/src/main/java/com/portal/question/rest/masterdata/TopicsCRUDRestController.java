package com.portal.question.rest.masterdata;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portal.question.model.Topics;
import com.portal.question.service.MasterDataService;

@RestController
@RequestMapping("/masterdata")
public class TopicsCRUDRestController 
{

	private MasterDataService masterDataService;
	
	
	//Injecting dependency
	@Autowired
	public TopicsCRUDRestController(MasterDataService theMasterDataService) 
	{
		masterDataService = theMasterDataService;
	}
	
	//Fetching topic list from database
	@GetMapping("/topics")
	public List<Topics> findAll() 
	{
		return masterDataService.findAllTopics();
	}
	
	//Inserting Topic to database
	@PostMapping("/topics")
	public List<String> saveTopics(@RequestBody Topics topics) 
	{
		return masterDataService.saveTopics(topics);	
	}
	
	
}










