package com.pradeep.service;

import java.util.List;

import com.pradeep.dtos.OperationResponseDto;
import com.pradeep.entities.Project;
import com.pradeep.exception.ResourceExistsException;
import com.pradeep.exception.ResourceNotFoundException;

public interface IProjectService {

	Project createProject(Project project) throws ResourceExistsException;

	List<Project> getProjectsByCompanyId(Long companyId);

	Project getProjectById(Long id) throws ResourceNotFoundException;

	Project updateProject(Long projectId, Project updatedProjectInfo) throws ResourceExistsException, ResourceNotFoundException;

	OperationResponseDto deleteProject(Long projectId) throws ResourceNotFoundException;

}
