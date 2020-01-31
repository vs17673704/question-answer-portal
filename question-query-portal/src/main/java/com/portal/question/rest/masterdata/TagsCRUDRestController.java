package com.portal.question.rest.masterdata;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portal.question.model.Tags;
import com.portal.question.service.MasterDataService;

@RestController
@RequestMapping("/masterdata")
public class TagsCRUDRestController 
{

	private MasterDataService masterDataService;
	
	//Injecting dependency
	@Autowired
	public TagsCRUDRestController(MasterDataService theMasterDataService) 
	{
		masterDataService = theMasterDataService;
	}
	
	//Fetching tag list from database
	@GetMapping("/tags")
	public List<Tags> findAll() 
	{
		return masterDataService.findAllTag();
	}
	 	
	//Inserting tag list to database
	@PostMapping("/tags")
	public List<Tags> saveTags(@RequestBody List<String> tags) 
	{
		System.out.println(tags);
		return masterDataService.saveTag(tags);	
	}
	
	//Finding Tag by name
	@DeleteMapping("/tags/{tag}")
	public String deleteTag(@PathVariable("tag") String tag) 
	{
		if(masterDataService.deleteTag(tag) == null)
			throw new RuntimeException(tag + " does not exist!");
		else
			return masterDataService.deleteTag(tag);
	}
	
}










