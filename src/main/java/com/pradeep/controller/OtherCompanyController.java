package com.pradeep.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pradeep.dtos.OperationResponseDto;
import com.pradeep.entities.OtherCompanyInfo;
import com.pradeep.exception.ResourceExistsException;
import com.pradeep.exception.ResourceNotFoundException;
import com.pradeep.service.IOtherCompanyService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RequestMapping("/api/v1/othercompanies")
@RestController
@Tag(name = "Other Company", description = "The Other Company Api's")
public class OtherCompanyController {
	
	@Autowired
	private IOtherCompanyService otherCompanyService;
	
	@PostMapping
	@Operation(summary = "Adds Other/External/Contact Company", description = "Adds Other/External/Contact Company", tags = { "othercompany" })
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "successful operation") })
	public ResponseEntity<OtherCompanyInfo> addOtherCompany(@RequestBody OtherCompanyInfo otherCompanyInfo) throws ResourceExistsException  {
		OtherCompanyInfo savedOtherCompanyInfo=otherCompanyService.createOtherCompanyInfo(otherCompanyInfo);
		return new ResponseEntity<OtherCompanyInfo>(savedOtherCompanyInfo, HttpStatus.CREATED);
	}
	
	@PostMapping("list")
	public ResponseEntity<List<OtherCompanyInfo>> addOtherCompanyList(@RequestBody List<OtherCompanyInfo> list) throws ResourceExistsException  {
		List<OtherCompanyInfo> savedOtherCompanyInfo=otherCompanyService.addOtherCompanyList(list);
		return new ResponseEntity<List<OtherCompanyInfo>>(savedOtherCompanyInfo, HttpStatus.CREATED);
	}
	
	@GetMapping
	@Operation(summary = "Listing all Other/External/Contact Companies", description = "Listing all Other/External/Contact Companies", tags = { "othercompany" })
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "successful operation"),
			@ApiResponse(responseCode = "404", description = "Other/External/Contact Companies not found"),
			@ApiResponse(responseCode = "500", description = "server is down,please try few minutes later") 
	})
	public ResponseEntity<List<OtherCompanyInfo>> getOtherCompanies() {
		List<OtherCompanyInfo> listOfOtherCompanies=otherCompanyService.getOtherCompanies();
		return new ResponseEntity<List<OtherCompanyInfo>>(listOfOtherCompanies,HttpStatus.OK);
	}
	
	@GetMapping("{otherCompanyId}")
	@Operation(summary = "Get Other/External/Contact Company By OtherCompanyId", description = "Get Other/External/Contact Company By OtherCompanyId", tags = { "othercompany" })
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "successful operation"),
			@ApiResponse(responseCode = "404", description = "Other/External/Contact Company not found"),
			@ApiResponse(responseCode = "500", description = "server is down,please try few minutes later") 
	})
	public ResponseEntity<OtherCompanyInfo> getOtherCompanyById(@PathVariable("otherCompanyId") Long otherCompanyId) throws ResourceNotFoundException  {
		OtherCompanyInfo otherCompanyInfo=otherCompanyService.getOtherCompanyById(otherCompanyId);
		return new ResponseEntity<OtherCompanyInfo>(otherCompanyInfo,HttpStatus.OK);
	}
	
	@PutMapping("{otherCompanyId}")	
	@Operation(summary = "Updates the existing Other/External/Contact Company By OtherCompanyId", description = "Updates Other/External/Contact Company By OtherCompanyId", tags = { "othercompany" })
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "successful operation"),
			@ApiResponse(responseCode = "404", description = "Other/External/Contact Company not found"),
			@ApiResponse(responseCode = "500", description = "server is down,please try few minutes later") 
	})
	public ResponseEntity<OtherCompanyInfo> updateOtherCompany(@RequestBody OtherCompanyInfo otherCompanyInfo,@PathVariable("otherCompanyId") Long otherCompanyId) throws ResourceNotFoundException, ResourceExistsException {
		OtherCompanyInfo updatedOtherCompanyInfo=otherCompanyService.updateOtherCompany(otherCompanyId,otherCompanyInfo);
		return new ResponseEntity<OtherCompanyInfo>(updatedOtherCompanyInfo, HttpStatus.OK);
	}
	
	@DeleteMapping("{otherCompanyId}")
	@Operation(summary = "Deletes Other/External/Contact Company By OtherCompanyId", description = "Deletes Other/External/Contact Company By OtherCompanyId", tags = { "othercompany" })
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "successful operation"),
			@ApiResponse(responseCode = "404", description = "Other/External/Contact Company not found"),
			@ApiResponse(responseCode = "500", description = "server is down,please try few minutes later") 
	})
	public ResponseEntity<OperationResponseDto> deleteOtherCompany(@PathVariable("otherCompanyId") Long otherCompanyId) throws ResourceNotFoundException  {
		OperationResponseDto operationResponseDto=otherCompanyService.deleteOtherCompany(otherCompanyId);
		return new ResponseEntity<OperationResponseDto>(operationResponseDto,HttpStatus.OK);
	}
}
