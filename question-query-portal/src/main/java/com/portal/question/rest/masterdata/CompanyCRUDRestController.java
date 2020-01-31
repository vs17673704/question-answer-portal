package com.portal.question.rest.masterdata;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portal.question.model.Company;
import com.portal.question.service.MasterDataService;

@RestController
@RequestMapping("/masterdata")
public class CompanyCRUDRestController 
{

	private MasterDataService masterDataService;
	
	//For dependency injection
	@Autowired
	public CompanyCRUDRestController(MasterDataService theMasterDataService) 
	{
		masterDataService = theMasterDataService;
	}
	
	// Returns list of all existing companies
	@GetMapping("/companies")
	public List<Company> findAll() 
	{
		return masterDataService.findAllCompanies();
	}
	
	// Inserts company details in database
	@PostMapping("/companies")
	public Company addCompany(@RequestBody Company company) 
	{
		//Check for empty inputs before submission
		if(company.getCompanyId()==""||company.getCompanyName()=="")
			throw new RuntimeException("All paramenters not entered");	
		else
			return masterDataService.saveCompany(company);
				
	}
	
	// Update Company details for existing Company
	@PutMapping("/companies")
	public Company updateCompany(@RequestBody Company company) 
	{
		//returns details of company saved
		return masterDataService.saveCompany(company);	
	}
	
	//Delete Company from database
	@DeleteMapping("/companies/{companyId}")
	public String deleteCompany(@PathVariable String companyId) 
	{
		//Check if company exists
		Company company = masterDataService.findCompanyById(companyId);
		if (company == null) 
		{
			throw new RuntimeException("Company id not found - " + companyId);
		}	
		
		//Returns Company ID for deleted company
		return masterDataService.deleteCompanyById(companyId);
	}
	
}










