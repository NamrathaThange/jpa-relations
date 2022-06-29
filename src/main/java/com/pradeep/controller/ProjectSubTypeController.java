package com.pradeep.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pradeep.dtos.OperationResponseDto;
import com.pradeep.entities.ProjectSubType;
import com.pradeep.entities.ProjectType;
import com.pradeep.exception.ResourceExistsException;
import com.pradeep.exception.ResourceNotFoundException;
import com.pradeep.service.IProjectSubTypeService;
import com.pradeep.service.IProjectTypeService;

@RequestMapping("/api/v1/projectsubtype")
@RestController
public class ProjectSubTypeController {

	@Autowired
	private IProjectSubTypeService projectSubTypeService;
	
	
	
	@GetMapping("{projectSubTypeId}")
	public ResponseEntity<ProjectSubType> getProjectSubTypeById(@PathVariable("projectSubTypeId") Long projectSubTypeId) throws ResourceNotFoundException  {
		ProjectSubType projectSubType=projectSubTypeService.getProjectSubTypeById(projectSubTypeId);
		return new ResponseEntity<ProjectSubType>(projectSubType,HttpStatus.OK);
	}
	
	@PutMapping("{projectSubTypeId}")
	public ResponseEntity<ProjectSubType> updateProjectSubType(@RequestBody ProjectSubType updatedProjectSubType,@PathVariable("projectSubTypeId") Long projectSubTypeId) throws ResourceNotFoundException, ResourceExistsException {
		ProjectSubType savedProjectSubType=projectSubTypeService.updateProjectSubType(projectSubTypeId,updatedProjectSubType);
		return new ResponseEntity<ProjectSubType>(savedProjectSubType, HttpStatus.OK);
	}
	
	@DeleteMapping("{projectSubTypeId}")
	public ResponseEntity<OperationResponseDto> deleteProjectType(@PathVariable("projectSubTypeId") Long projectSubTypeId) throws ResourceNotFoundException  {
		OperationResponseDto operationResponseDto=projectSubTypeService.deleteProjectSubType(projectSubTypeId);
		return new ResponseEntity<OperationResponseDto>(operationResponseDto,HttpStatus.OK);
	}
}
