package com.pradeep.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pradeep.entities.ProjectUser;
import com.pradeep.exception.ResourceExistsException;
import com.pradeep.service.IProjectTeamService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RequestMapping("/api/v1/projectteams")
@RestController
public class ProjectTeamController {
	
	/*@Autowired
	private IProjectTeamService projectTeamService;
	
	@PostMapping
	@Operation(summary = "Add Project", description = "Adds Project", tags = { "project" })
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "successful operation") })
	public ResponseEntity<ProjectUser> createProjectUser(@RequestBody ProjectUser projectUser) throws ResourceExistsException  {
		ProjectUser savedProjectUser=projectTeamService.createProjectUser(projectUser);
		return new ResponseEntity<ProjectUser>(savedProjectUser, HttpStatus.CREATED);
	}
	
	@GetMapping("{projectId}")
	@Operation(summary = "Add Project", description = "Adds Project", tags = { "project" })
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "successful operation") })
	public ResponseEntity<List<ProjectUser>> getProjectTeamByProjectId(@PathVariable("projectId") Long projectId) throws ResourceExistsException  {
		List<ProjectUser> listOfProjectUsers=projectTeamService.getProjectTeamByProjectId(projectId);
		return new ResponseEntity<List<ProjectUser>>(listOfProjectUsers, HttpStatus.OK);
	}*/
	
}
