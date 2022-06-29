package com.pradeep.service;

import java.util.List;

import com.pradeep.dtos.OperationResponseDto;
import com.pradeep.entities.ProjectSubType;
import com.pradeep.exception.ResourceExistsException;
import com.pradeep.exception.ResourceNotFoundException;

public interface IProjectSubTypeService {

	List<ProjectSubType> listProjectSubTypes(Long projectTypeId);

	ProjectSubType getProjectSubTypeById(Long projectSubTypeId) throws ResourceNotFoundException;

	ProjectSubType updateProjectSubType(Long projectSubTypeId, ProjectSubType updatedProjectSubType) throws ResourceExistsException, ResourceNotFoundException;

	OperationResponseDto deleteProjectSubType(Long projectSubTypeId) throws ResourceNotFoundException;

}
