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
import com.pradeep.entities.Project;
import com.pradeep.exception.ResourceExistsException;
import com.pradeep.exception.ResourceNotFoundException;
import com.pradeep.service.IProjectService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RequestMapping("/api/v1/projects")
@RestController
@Tag(name = "Project", description = "The Project Api's")
public class ProjectController {

	@Autowired
	private IProjectService projectService;
	
	@PostMapping
	@Operation(summary = "Add Project", description = "Adds Project", tags = { "project" })
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "successful operation") })
	public ResponseEntity<Project> createProject(@RequestBody Project project) throws ResourceExistsException  {
		Project savedProject=projectService.createProject(project);
		return new ResponseEntity<Project>(savedProject, HttpStatus.CREATED);
	}
	
	@GetMapping("/company/{companyId}")
	public ResponseEntity<List<Project>> getOtherCompanies(@PathVariable("companyId") Long companyId) {
		List<Project> listOfProjects=projectService.getProjectsByCompanyId(companyId);
		return new ResponseEntity<List<Project>>(listOfProjects,HttpStatus.OK);
	}
	
	@GetMapping("{projectId}")
	public ResponseEntity<Project> getProjectById(@PathVariable("projectId") Long projectId) throws ResourceNotFoundException  {
		Project project=projectService.getProjectById(projectId);
		return new ResponseEntity<Project>(project,HttpStatus.OK);
	}
	
	@PutMapping("{projectId}")
	public ResponseEntity<Project> updateProject(@PathVariable("projectId") Long projectId,@RequestBody Project updatedProjectInfo) throws ResourceNotFoundException, ResourceExistsException {
		Project updatedProject=projectService.updateProject(projectId,updatedProjectInfo);
		return new ResponseEntity<Project>(updatedProject, HttpStatus.OK);
	}
	
	@DeleteMapping("{projectId}")
	public ResponseEntity<OperationResponseDto> deleteProject(@PathVariable("projectId") Long projectId) throws ResourceNotFoundException  {
		OperationResponseDto operationResponseDto=projectService.deleteProject(projectId);
		return new ResponseEntity<OperationResponseDto>(operationResponseDto,HttpStatus.OK);
	}
}
