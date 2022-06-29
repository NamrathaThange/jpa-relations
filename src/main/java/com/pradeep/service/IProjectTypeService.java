package com.pradeep.service;

import java.util.List;

import com.pradeep.dtos.OperationResponseDto;
import com.pradeep.entities.ProjectSubType;
import com.pradeep.entities.ProjectType;
import com.pradeep.exception.ResourceExistsException;
import com.pradeep.exception.ResourceNotFoundException;

public interface IProjectTypeService {

	ProjectType createProjectType(ProjectType projectType) throws ResourceExistsException;

	List<ProjectType> listProjectTypes();

	ProjectType getProjectTypeById(Long id) throws ResourceNotFoundException;

	ProjectType updateProjectType(Long projectTypeId, ProjectType updatedProjectType) throws ResourceExistsException, ResourceNotFoundException;

	OperationResponseDto deleteProjectType(Long projectTypeId) throws ResourceNotFoundException;

	ProjectSubType createProjectSubType(Long projectTypeId, ProjectSubType projectSubType) throws ResourceNotFoundException, ResourceExistsException;

}
