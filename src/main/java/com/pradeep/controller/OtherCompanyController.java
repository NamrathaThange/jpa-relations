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
import com.pradeep.dtos.ResponseDto;
import com.pradeep.entities.OtherCompanyInfo;
import com.pradeep.exception.ResourceExistsException;
import com.pradeep.exception.ResourceNotFoundException;
import com.pradeep.service.IOtherCompanyService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RequestMapping("/api/v1/othercompanies")
@RestController
public class OtherCompanyController {
	
	@Autowired
	private IOtherCompanyService otherCompanyService;
	
	@PostMapping
	@Operation(summary = "Add Topic", description = "Adds topic to the FirstApp", tags = { "topic" })
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "successful operation") })
	public ResponseEntity<OtherCompanyInfo> addTopic(@RequestBody OtherCompanyInfo otherCompanyInfo) throws ResourceExistsException  {
		OtherCompanyInfo savedOtherCompanyInfo=otherCompanyService.createOtherCompanyInfo(otherCompanyInfo);
		return new ResponseEntity<OtherCompanyInfo>(savedOtherCompanyInfo, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<OtherCompanyInfo>> getOtherCompanies() {
		List<OtherCompanyInfo> listOfOtherCompanies=otherCompanyService.getOtherCompanies();
		return new ResponseEntity<List<OtherCompanyInfo>>(listOfOtherCompanies,HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<OtherCompanyInfo> getOtherCompanyById(@PathVariable("id") Long id) throws ResourceNotFoundException  {
		OtherCompanyInfo otherCompanyInfo=otherCompanyService.getOtherCompanyById(id);
		return new ResponseEntity<OtherCompanyInfo>(otherCompanyInfo,HttpStatus.OK);
	}
	
	@PutMapping("{otherCompanyId}")
	public ResponseEntity<OtherCompanyInfo> updateOtherCompany(@RequestBody OtherCompanyInfo otherCompanyInfo,@PathVariable("otherCompanyId") Long otherCompanyId) throws ResourceNotFoundException, ResourceExistsException {
		OtherCompanyInfo updatedOtherCompanyInfo=otherCompanyService.updateOtherCompany(otherCompanyId,otherCompanyInfo);
		return new ResponseEntity<OtherCompanyInfo>(updatedOtherCompanyInfo, HttpStatus.OK);
	}
	
	@DeleteMapping("{otherCompanyId}")
	public ResponseEntity<OperationResponseDto> deleteOtherCompany(@PathVariable("otherCompanyId") Long otherCompanyId) throws ResourceNotFoundException  {
		OperationResponseDto operationResponseDto=otherCompanyService.deleteOtherCompany(otherCompanyId);
		return new ResponseEntity<OperationResponseDto>(operationResponseDto,HttpStatus.OK);
	}
}
