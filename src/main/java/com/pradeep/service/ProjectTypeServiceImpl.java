package com.pradeep.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pradeep.dtos.OperationResponseDto;
import com.pradeep.entities.ProjectSubType;
import com.pradeep.entities.ProjectType;
import com.pradeep.exception.ResourceExistsException;
import com.pradeep.exception.ResourceNotFoundException;
import com.pradeep.repository.IProjectSubTypeRepository;
import com.pradeep.repository.IProjectTypeRepository;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class ProjectTypeServiceImpl implements IProjectTypeService {

	@Autowired
	private IProjectTypeRepository projectTypeRepository;
	
	@Autowired
	private IProjectSubTypeRepository projectSubTypeRepository;
	
	@Override
	public ProjectType createProjectType(ProjectType projectType) throws ResourceExistsException {
		findByProjectTypeName(projectType.getProjectTypeName());
		ProjectType savedProjectType=projectTypeRepository.save(projectType);
		return savedProjectType;
	}

	@Override
	public List<ProjectType> listProjectTypes() {
		List<ProjectType> listOfProjectTypes=projectTypeRepository.findAll();
		return listOfProjectTypes;
	}

	@Override
	public ProjectType getProjectTypeById(Long id) throws ResourceNotFoundException {
		ProjectType projectType = projectTypeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Project  Type not found :: " + id));
		return projectType;
	}

	@Override
	public ProjectType updateProjectType(Long projectTypeId, ProjectType updatedProjectType) throws ResourceExistsException, ResourceNotFoundException {
		verifyByProjectTypeAndProjectTypeId(projectTypeId,updatedProjectType.getProjectTypeName());
		ProjectType existingProjectType = projectTypeRepository.findById(projectTypeId).orElseThrow(() -> new ResourceNotFoundException("Project  Type not found :: " + projectTypeId));
		String[] ignoreFields = { "projectTypeId"};
		BeanUtils.copyProperties(updatedProjectType, existingProjectType, ignoreFields);
		ProjectType projectType=projectTypeRepository.save(existingProjectType);
		return projectType;
	}

	@Override
	public OperationResponseDto deleteProjectType(Long projectTypeId) throws ResourceNotFoundException {
		ProjectType projectType = projectTypeRepository.findById(projectTypeId).orElseThrow(() -> new ResourceNotFoundException("Project  Type not found :: " + projectTypeId));
		projectTypeRepository.delete(projectType);
		return new OperationResponseDto(true,"Deleted successfully");
	}

	@Override
	public ProjectSubType createProjectSubType(Long projectTypeId, ProjectSubType projectSubType) throws ResourceNotFoundException, ResourceExistsException {
		findByProjectSubTypeName(projectSubType.getProjectSubTypeName());
		ProjectType projectType = projectTypeRepository.findById(projectTypeId).orElseThrow(() -> new ResourceNotFoundException("Project  Type not found :: " + projectTypeId));
		projectSubType.setProjectType(projectType);
		ProjectSubType savedprojectSubType=projectSubTypeRepository.save(projectSubType);
		return savedprojectSubType;
	}
	
	private void findByProjectTypeName(String projectTypeName) throws ResourceExistsException {
		Optional<ProjectType> optionalProjectType=projectTypeRepository.findByProjectTypeName(projectTypeName);
		if(optionalProjectType.isPresent()) {
			throw new ResourceExistsException("Project Type Exists");
		}
	}
	
	private void findByProjectSubTypeName(String projectSubTypeName) throws ResourceExistsException {
		Optional<ProjectSubType> optionalProjectSubType = projectSubTypeRepository.findByProjectSubTypeName(projectSubTypeName);
		if(optionalProjectSubType.isPresent()) {
			throw new ResourceExistsException("Project Sub Type Exists");
		}
	}
	
	private void verifyByProjectTypeAndProjectTypeId(Long projectTypeId, String projectSubTypeName) throws ResourceExistsException, ResourceNotFoundException {
		Optional<ProjectType> optionalProjectType = projectTypeRepository.findByProjectTypeName(projectSubTypeName);
		if(optionalProjectType.isPresent()) {
			ProjectType projectType=optionalProjectType.get();
			if(projectType.getProjectTypeId() .longValue()!=projectTypeId.longValue()) {
				throw new ResourceExistsException("Project Type exists");
			}
		}
	}

}
