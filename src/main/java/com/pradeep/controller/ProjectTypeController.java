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
import com.pradeep.entities.ProjectSubType;
import com.pradeep.entities.ProjectType;
import com.pradeep.exception.ResourceExistsException;
import com.pradeep.exception.ResourceNotFoundException;
import com.pradeep.service.IProjectTypeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RequestMapping("/api/v1/projecttype")
@RestController
public class ProjectTypeController {
	
	@Autowired
	private IProjectTypeService projectTypeService;
	
	@PostMapping
	@Operation(summary = "Add Project Type", description = "Add Project Type", tags = { "topic" })
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "successful operation") })
	public ResponseEntity<ProjectType> createProjectType(@RequestBody ProjectType projectType) throws ResourceExistsException  {
		ProjectType savedProjectType=projectTypeService.createProjectType(projectType);
		return new ResponseEntity<ProjectType>(savedProjectType, HttpStatus.CREATED);
	}
	
	@PostMapping("{projectTypeId}")
	@Operation(summary = "Add Project Type", description = "Add Project Type", tags = { "topic" })
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "successful operation") })
	public ResponseEntity<ProjectSubType> createProjectSubType(@PathVariable("projectTypeId") Long projectTypeId,@RequestBody ProjectSubType projectSubType) throws ResourceExistsException, ResourceNotFoundException  {
		ProjectSubType savedProjectSubType=projectTypeService.createProjectSubType(projectTypeId,projectSubType);
		return new ResponseEntity<ProjectSubType>(savedProjectSubType, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<ProjectType>> listProjectTypes() {
		List<ProjectType> listOfOtherCompanies=projectTypeService.listProjectTypes();
		return new ResponseEntity<List<ProjectType>>(listOfOtherCompanies,HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<ProjectType> getProjectTypeById(@PathVariable("id") Long id) throws ResourceNotFoundException  {
		ProjectType projectType=projectTypeService.getProjectTypeById(id);
		return new ResponseEntity<ProjectType>(projectType,HttpStatus.OK);
	}
	
	@PutMapping("{projectTypeId}")
	public ResponseEntity<ProjectType> updateProjectType(@RequestBody ProjectType updatedProjectType,@PathVariable("projectTypeId") Long projectTypeId) throws ResourceNotFoundException, ResourceExistsException {
		ProjectType savedProjectType=projectTypeService.updateProjectType(projectTypeId,updatedProjectType);
		return new ResponseEntity<ProjectType>(savedProjectType, HttpStatus.OK);
	}
	
	@DeleteMapping("{projectTypeId}")
	public ResponseEntity<OperationResponseDto> deleteProjectType(@PathVariable("projectTypeId") Long projectTypeId) throws ResourceNotFoundException  {
		OperationResponseDto operationResponseDto=projectTypeService.deleteProjectType(projectTypeId);
		return new ResponseEntity<OperationResponseDto>(operationResponseDto,HttpStatus.OK);
	}
}
