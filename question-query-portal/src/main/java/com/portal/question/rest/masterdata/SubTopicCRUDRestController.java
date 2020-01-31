package com.portal.question.rest.masterdata;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portal.question.model.SubTopic;
import com.portal.question.service.MasterDataService;

@RestController
@RequestMapping("/masterdata")
public class SubTopicCRUDRestController 
{

	private MasterDataService masterDataService;
	
	
	//Injecting dependency
	@Autowired
	public SubTopicCRUDRestController(MasterDataService theMasterDataService) 
	{
		masterDataService = theMasterDataService;
	}
	
	//Returns list of all existing Sub-topics
	@GetMapping("/subtopic")
	public List<SubTopic> findAll() 
	{
		return masterDataService.findAllSubTopics();
	}
	
	// Inserts subtopic details for existing Topis
	@PostMapping("/subtopic")
	public SubTopic addSubtopic(@RequestBody SubTopic subTopic) 
	{	
		//Check for empty inputs and existence of topic before submission otherwise save the details
		if(subTopic.getSubtopicId()==""||subTopic.getSubtopic()==""||subTopic.getTopicName()=="")
			throw new RuntimeException("All paramenters not entered");
		else if(subTopic.getSubtopicId()==null||subTopic.getSubtopic()==null||subTopic.getTopicName()==null)
			throw new RuntimeException("Use proper paameter name to save data!");
		else
			return masterDataService.saveSubTopic(subTopic);
	}
	
	//Updating Sub-topic details
	@PutMapping("/subtopic")
	public SubTopic updateSubTopic(@RequestBody SubTopic subTopic) 
	{
		return masterDataService.saveSubTopic(subTopic);	
		
	}
	
	
}










