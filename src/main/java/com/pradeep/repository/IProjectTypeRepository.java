package com.pradeep.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pradeep.entities.ProjectType;

@Repository
public interface IProjectTypeRepository extends JpaRepository<ProjectType, Long>{

	Optional<ProjectType> findByProjectTypeName(String projectTypeName);
}
