package com.pradeep.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pradeep.dtos.OperationResponseDto;
import com.pradeep.entities.ProjectSubType;
import com.pradeep.exception.ResourceExistsException;
import com.pradeep.exception.ResourceNotFoundException;
import com.pradeep.repository.IProjectSubTypeRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProjectSubTypeServiceImpl implements IProjectSubTypeService {

	@Autowired
	private IProjectSubTypeRepository projectSubTypeRepository;
	
	@Override
	public List<ProjectSubType> listProjectSubTypes(Long projectTypeId) {
		List<ProjectSubType> listOfProjectSubTypes=projectSubTypeRepository.findProjectSubTypesByProjectTypeId(projectTypeId);
		return listOfProjectSubTypes;
	}

	@Override
	public ProjectSubType getProjectSubTypeById(Long projectSubTypeId) throws ResourceNotFoundException {
		ProjectSubType projectSubType = projectSubTypeRepository.findById(projectSubTypeId).orElseThrow(() -> new ResourceNotFoundException("Project Sub Type not found :: " + projectSubTypeId));
		return projectSubType;
	}

	@Override
	public ProjectSubType updateProjectSubType(Long projectSubTypeId, ProjectSubType updatedProjectSubType) throws ResourceExistsException, ResourceNotFoundException {
		verifyByProjectSubTypeAndProjectSubTypeId(projectSubTypeId,updatedProjectSubType.getProjectSubTypeName());
		ProjectSubType existingProjectSubType = projectSubTypeRepository.findById(projectSubTypeId).orElseThrow(() -> new ResourceNotFoundException("Project Sub Type not found :: " + projectSubTypeId));
		String[] ignoreFields = { "projectSubTypeId"};
		BeanUtils.copyProperties(updatedProjectSubType, existingProjectSubType, ignoreFields);
		ProjectSubType projectSubType=projectSubTypeRepository.save(existingProjectSubType);
		return projectSubType;
	}

	@Override
	public OperationResponseDto deleteProjectSubType(Long projectSubTypeId) throws ResourceNotFoundException {
		ProjectSubType projectSubType = projectSubTypeRepository.findById(projectSubTypeId).orElseThrow(() -> new ResourceNotFoundException("Project Sub Type not found :: " + projectSubTypeId));
		projectSubTypeRepository.delete(projectSubType);
		return new OperationResponseDto(true,"Deleted successfully");
	}
	
	private void verifyByProjectSubTypeAndProjectSubTypeId(Long projectSubTypeId, String projectSubTypeName) throws ResourceExistsException, ResourceNotFoundException {
		Optional<ProjectSubType> optionalProjectSubType = projectSubTypeRepository.findByProjectSubTypeName(projectSubTypeName);
		if(optionalProjectSubType.isPresent()) {
			ProjectSubType projectSubType=optionalProjectSubType.get();
			if(projectSubType.getProjectSubTypeId() .longValue()!=projectSubTypeId.longValue()) {
				throw new ResourceExistsException("Project Sub Type exists");
			}
		}
	}

}
