package com.portal.question.rest.masterdata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portal.question.model.Users;
import com.portal.question.service.MasterDataService;

@RestController
@RequestMapping("/masterdata")
public class UserCRUDRestController 
{

	private MasterDataService masterDataService;
	
	//Injecting dependency
	@Autowired
	public UserCRUDRestController(MasterDataService theMasterDataService) 
	{
		masterDataService = theMasterDataService;
	}
	
	//Submitting User details
	@PostMapping("/user")
	public Users addUserDetails(@RequestBody Users userdetails) 
	{
		//Checking for empty inputs before submission
		if(userdetails.getUserId()==""||userdetails.getUserName()=="")
			throw new RuntimeException("All paramenters not entered");	
		else
			return masterDataService.saveUser(userdetails);
				
	}
	
	//Updating user
	@PutMapping("/user")
	public Users updateUser(@RequestBody Users userdetails) 
	{
		masterDataService.saveUser(userdetails);	
		return userdetails;
	}
	
	
}










