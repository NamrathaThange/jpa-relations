package com.pradeep.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pradeep.entities.ProjectSubType;

@Repository
public interface IProjectSubTypeRepository extends JpaRepository<ProjectSubType, Long>{

	Optional<ProjectSubType> findByProjectSubTypeName(String projectSubTypeName);
	
	@Query("SELECT pst from ProjectSubType pst WHERE pst.projectType.projectTypeId=:projectTypeId")
	List<ProjectSubType> findProjectSubTypesByProjectTypeId(@Param("projectTypeId")Long projectTypeId);

}
